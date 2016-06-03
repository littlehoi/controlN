/*** Eclipse Class Decompiler plugin, copyright (c) 2012 Chao Chen (cnfree2000@hotmail.com) ***/

package org.eclipse.jdt.internal.jarinjarloader;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;

public class RsrcURLConnection extends URLConnection {

  private ClassLoader classLoader;

  public RsrcURLConnection(URL url, ClassLoader classLoader) {
    super(url);
    this.classLoader = classLoader;
  }

  public void connect() throws IOException {}

  public InputStream getInputStream() throws IOException {
    String file = URLDecoder.decode(super.url.getFile(), "UTF-8");
    InputStream result = classLoader.getResourceAsStream(file);
    if (result == null)
      throw new MalformedURLException("Could not open InputStream for URL '" + super.url + "'");
    else
      return result;
  }
}
