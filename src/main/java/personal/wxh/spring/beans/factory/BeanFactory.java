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
   * @throws ClassCastException 泛型强制转换失败
   */
  Object getBean(String name);
}
