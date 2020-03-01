package personal.wxh.spring.context;

import java.util.List;
import personal.wxh.spring.BeanPostProcessor;
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

  /** 加载BeanDefinitions */
  protected abstract void loadBeanDefinitions() throws Exception;

  /** 注册BeanPostProcessor */
  protected void registerBeanPostProcessor() {
    List<BeanPostProcessor> processors = beanFactory.getBeanForType(BeanPostProcessor.class);
    for (BeanPostProcessor p : processors) {
      beanFactory.addBeanPostProcessor(p);
    }
  }

  /**
   * 刷新钩子
   *
   * @throws Exception 抛出所有异常
   */
  protected void onRefresh() throws Exception {
    beanFactory.preInstantiateSingletons();
  }

  /**
   * 刷新容器
   *
   * @throws Exception 抛出异常
   */
  public void refresh() throws Exception {
    // 分3步加载
    loadBeanDefinitions();
    registerBeanPostProcessor();
    onRefresh();
  }

  @Override
  public Object getBean(String name) {
    return beanFactory.getBean(name);
  }
}
