package personal.wxh.spring.factory;

import personal.wxh.spring.BeanDefinition;
import personal.wxh.spring.error.BeanCreationException;

/**
 * 有自动注入功能的BeanFactory
 *
 * @author wangxinhua
 * @since 1.0
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory {
  @Override
  protected <T> T doCreateBean(BeanDefinition<T> beanDefinition) {
    try {
      return createInstance(beanDefinition);
    } catch (Exception e) {
      e.printStackTrace();
      throw new BeanCreationException("bean" + beanDefinition.getBeanClassName() + "创建失败");
    }
  }

  private <T> T createInstance(BeanDefinition<T> beanDefinition) throws Exception {
    return beanDefinition.getBeanClass().getDeclaredConstructor().newInstance();
  }
}
