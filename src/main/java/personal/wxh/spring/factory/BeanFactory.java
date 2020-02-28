package personal.wxh.spring.factory;

import personal.wxh.spring.BeanDefinition;

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

  /**
   * 注册bean,实例化{@link BeanDefinition<T>} 中的bean
   *
   * @param name bean名称
   * @param beanDefinition bean包装对象
   * @param <T> 泛型类型
   * @throws Exception 抛出所有子类的异常
   */
  <T> void registerBeanDefinition(String name, BeanDefinition<T> beanDefinition) throws Exception;
}
