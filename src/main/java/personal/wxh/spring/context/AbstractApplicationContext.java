package personal.wxh.spring.context;

import personal.wxh.spring.beans.factory.AbstractBeanFactory;

/**
 * @author wangxinhua
 * @since 1.0
 */
public abstract class AbstractApplicationContext implements ApplicationContext {

  protected final AbstractBeanFactory beanFactory;

  public AbstractApplicationContext(AbstractBeanFactory beanFactory) {
    this.beanFactory = beanFactory;
  }

  /**
   * 刷新容器
   * @throws Exception 抛出异常
   */
  public abstract void refresh() throws Exception;

  @Override
  public <T> T getBean(String name) {
      return beanFactory.getBean(name);
  }
}
