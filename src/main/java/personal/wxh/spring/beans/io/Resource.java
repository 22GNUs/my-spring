package personal.wxh.spring.beans.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * 模仿Spring的Resource接口
 *
 * @author wangxinhua
 * @since 1.0
 */
@FunctionalInterface
public interface Resource {

  /**
   * 读取输入流
   *
   * @return 输入流
   * @throws IOException IO读写异常
   */
  InputStream getInputStream() throws IOException;
}
