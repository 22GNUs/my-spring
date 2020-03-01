package personal.wxh.spring.aop;

import personal.wxh.spring.beans.factory.BeanFactory;

/**
 * 实现了该接口的类可以自动注入BeanFactory
 * <pre>
 * @see personal.wxh.spring.beans.factory.AutowireCapableBeanFactory
 * </pre>
 * @author wangxinhua
 * @since 1.0
 */
public interface BeanFactoryAware {
  void setBeanFactory(BeanFactory beanFactory) throws Exception;
}
