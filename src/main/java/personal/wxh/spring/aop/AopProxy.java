package personal.wxh.spring.aop;

/**
 * AOP代理
 *
 * @author wangxinhua
 * @since 1.0
 */
public interface AopProxy<T> {

  /**
   * 获取代理对象
   *
   * @return 代理对象
   */
  T getProxy();
}
