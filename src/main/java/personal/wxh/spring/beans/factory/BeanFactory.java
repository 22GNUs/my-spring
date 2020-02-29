package personal.wxh.spring.beans.factory;

/**
 * @author wangxinhua
 * @since 1.0
 */
public interface BeanFactory {

  /**
   * 通过名称获取bean对象
   *
   * @param name bean名称
   * @return bean对象
   * @param <T> 泛型类型
   * @throws ClassCastException 泛型强制转换失败
   */
  <T> T getBean(String name);
}
