/*** Eclipse Class Decompiler plugin, copyright (c) 2012 Chao Chen (cnfree2000@hotmail.com) ***/

package org.eclipse.jdt.internal.jarinjarloader;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;


public class JarRsrcLoader {
  private static class ManifestInfo {

    String rsrcMainClass;
    String rsrcClassPath[];

    private ManifestInfo() {}

    ManifestInfo(ManifestInfo manifestinfo) {
      this();
    }
  }



  public static void main(String args[]) throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, SecurityException, NoSuchMethodException,
      IOException {
    ManifestInfo mi = getManifestInfo();
    ClassLoader cl = Thread.currentThread().getContextClassLoader();
    URL.setURLStreamHandlerFactory(new RsrcURLStreamHandlerFactory(cl));
    URL rsrcUrls[] = new URL[mi.rsrcClassPath.length];
    for (int i = 0; i < mi.rsrcClassPath.length; i++) {
      String rsrcPath = mi.rsrcClassPath[i];
      if (rsrcPath.endsWith("/"))
        rsrcUrls[i] = new URL("rsrc:" + rsrcPath);
      else
        rsrcUrls[i] = new URL("jar:rsrc:" + rsrcPath + "!/");
    }

    ClassLoader jceClassLoader = new URLClassLoader(rsrcUrls, null);
    Thread.currentThread().setContextClassLoader(jceClassLoader);
    Class c = Class.forName(mi.rsrcMainClass, true, jceClassLoader);
    Method main = c.getMethod("main", new Class[] {args.getClass()});
    main.invoke(null, new Object[] {args});
  }
  private static ManifestInfo getManifestInfo() throws IOException {
		Enumeration resEnum;
		resEnum = Thread.currentThread().getContextClassLoader().getResources(JarFile.MANIFEST_NAME); 
		while (resEnum.hasMoreElements()) {
			try {
				URL url = (URL)resEnum.nextElement();
				InputStream is = url.openStream();
				if (is != null) {
					ManifestInfo result = new ManifestInfo();
					Manifest manifest = new Manifest(is);
					Attributes mainAttribs = manifest.getMainAttributes();
					result.rsrcMainClass = mainAttribs.getValue(JIJConstants.REDIRECTED_MAIN_CLASS_MANIFEST_NAME); 
					String rsrcCP = mainAttribs.getValue(JIJConstants.REDIRECTED_CLASS_PATH_MANIFEST_NAME); 
					if (rsrcCP == null)
						rsrcCP = JIJConstants.DEFAULT_REDIRECTED_CLASSPATH; 
					result.rsrcClassPath = splitSpaces(rsrcCP);
					if ((result.rsrcMainClass != null) && !result.rsrcMainClass.trim().equals(""))    //$NON-NLS-1$
							return result;
				}
			}
			catch (Exception e) {
				// Silently ignore wrong manifests on classpath?
			}
		}
		System.err.println("Missing attributes for JarRsrcLoader in Manifest ("+JIJConstants.REDIRECTED_MAIN_CLASS_MANIFEST_NAME+", "+JIJConstants.REDIRECTED_CLASS_PATH_MANIFEST_NAME+")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return null;
	}

  private static String[] splitSpaces(String line) {
    if (line == null)
      return null;
    List result = new ArrayList();
    int lastPos;
    for (int firstPos = 0; firstPos < line.length(); firstPos = lastPos + 1) {
      lastPos = line.indexOf(' ', firstPos);
      if (lastPos == -1)
        lastPos = line.length();
      if (lastPos > firstPos)
        result.add(line.substring(firstPos, lastPos));
    }

    return (String[]) result.toArray(new String[result.size()]);
  }
}
