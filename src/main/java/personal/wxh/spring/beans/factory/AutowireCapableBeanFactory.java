package personal.wxh.spring.beans.factory;

import java.lang.reflect.Field;
import personal.wxh.spring.BeanDefinition;
import personal.wxh.spring.BeanReference;
import personal.wxh.spring.PropertyValue;
import personal.wxh.spring.PropertyValues;
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
      if (beanDefinition.getBean() != null) {
        return beanDefinition.getBean();
      }
      // 创建实例, 并初始化注入属性
      final T bean = createInstance(beanDefinition);
      applyPropertyValues(bean, beanDefinition);
      beanDefinition.setBean(bean);
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
      Object value = pv.getValue();
      if (value instanceof BeanReference) {
        // 如果是引用则通过getBean再次初始化对象
        BeanReference ref = (BeanReference) value;
        value = getBean(ref.getName());
      }
      f.setAccessible(true);
      f.set(bean, value);
    }
  }
}
