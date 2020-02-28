package personal.wxh.spring.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * 通过URl读取资源
 *
 * @author wangxinhua
 * @since 1.0
 */
public class UrlResource implements Resource {

  private final URL url;

  public UrlResource(URL url) {
    this.url = url;
  }

  @Override
  public InputStream getInputStream() throws IOException {
    final URLConnection con = url.openConnection();
    con.connect();
    return con.getInputStream();
  }
}
