package personal.wxh.spring.beans.io;

import java.net.URL;

/**
 * 资源加载类
 *
 * @author wangxinhua
 * @since 1.0
 */
public class ResourceLoader {

  /**
   * 读取classPath下的URL资源
   *
   * @param location 资源文件路径
   * @return 资源对象
   */
  public Resource getResource(String location) {
    final URL resource = this.getClass().getClassLoader().getResource(location);
    return new UrlResource(resource);
  }
}
