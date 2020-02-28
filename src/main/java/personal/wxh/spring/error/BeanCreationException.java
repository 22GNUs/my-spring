package personal.wxh.spring.error;

/**
 * 包装bean初始化异常为RuntimeException
 * @author wangxinhua
 * @since 1.0
 */
public class BeanCreationException extends RuntimeException {
  public BeanCreationException(String message) {
    super(message);
  }
}
