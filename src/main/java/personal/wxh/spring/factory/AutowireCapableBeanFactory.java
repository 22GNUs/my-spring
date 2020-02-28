package personal.wxh.spring.factory;

import personal.wxh.spring.BeanDefinition;
import personal.wxh.spring.PropertyValue;
import personal.wxh.spring.PropertyValues;
import personal.wxh.spring.error.BeanCreationException;

import java.lang.reflect.Field;

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
      // 创建实例, 并初始化注入属性
      final T bean = createInstance(beanDefinition);
      applyPropertyValues(bean, beanDefinition);
      return bean;
    } catch (Exception e) {
      e.printStackTrace();
      throw new BeanCreationException("bean" + beanDefinition.getBeanClassName() + "创建失败");
    }
  }

  private <T> T createInstance(BeanDefinition<T> beanDefinition) throws Exception {
    return beanDefinition.getBeanClass().getDeclaredConstructor().newInstance();
  }

  private <T> void applyPropertyValues(T bean, BeanDefinition<T> definition)
      throws NoSuchFieldException, IllegalAccessException {
    final PropertyValues pvs = definition.getPropertyValues();
    if (pvs == null) {
      return;
    }
    // 使用反射设置属性
    for (PropertyValue pv : pvs.getPropertyValues()) {
      final Field f = bean.getClass().getDeclaredField(pv.getName());
      f.setAccessible(true);
      f.set(bean, pv.getValue());
    }
  }
}
