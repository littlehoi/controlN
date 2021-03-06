/*** Eclipse Class Decompiler plugin, copyright (c) 2012 Chao Chen (cnfree2000@hotmail.com) ***/

package org.eclipse.jdt.internal.jarinjarloader;

import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;


public class RsrcURLStreamHandlerFactory implements URLStreamHandlerFactory {

  private ClassLoader classLoader;
  private URLStreamHandlerFactory chainFac;

  public RsrcURLStreamHandlerFactory(ClassLoader cl) {
    classLoader = cl;
  }

  public URLStreamHandler createURLStreamHandler(String protocol) {
    if ("rsrc".equals(protocol))
      return new RsrcURLStreamHandler(classLoader);
    if (chainFac != null)
      return chainFac.createURLStreamHandler(protocol);
    else
      return null;
  }

  public void setURLStreamHandlerFactory(URLStreamHandlerFactory fac) {
    chainFac = fac;
  }
}
