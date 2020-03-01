package personal.wxh.spring.beans.factory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import personal.wxh.spring.BeanDefinition;
import personal.wxh.spring.BeanReference;
import personal.wxh.spring.PropertyValue;
import personal.wxh.spring.PropertyValues;
import personal.wxh.spring.aop.BeanFactoryAware;
import personal.wxh.spring.error.BeanCreationException;

/**
 * 有自动注入功能的BeanFactory
 *
 * @author wangxinhua
 * @since 1.0
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory {

  @Override
  protected Object doCreateBean(BeanDefinition beanDefinition) {
    try {
      if (beanDefinition.getBean() != null) {
        return beanDefinition.getBean();
      }
      // 创建实例, 并初始化注入属性
      final Object bean = createInstance(beanDefinition);
      applyPropertyValues(bean, beanDefinition);
      return bean;
    } catch (Exception e) {
      e.printStackTrace();
      throw new BeanCreationException("bean" + beanDefinition.getBeanClassName() + "创建失败");
    }
  }

  private Object createInstance(BeanDefinition beanDefinition) throws Exception {
    return beanDefinition.getBeanClass().getDeclaredConstructor().newInstance();
  }

  private void applyPropertyValues(Object bean, BeanDefinition definition) throws Exception {
    if (bean instanceof BeanFactoryAware) {
      ((BeanFactoryAware) bean).setBeanFactory(this);
    }
    final PropertyValues pvs = definition.getPropertyValues();
    if (pvs == null) {
      return;
    }
    // 使用反射设置属性
    for (PropertyValue pv : pvs.getPropertyValues()) {
      Object value = pv.getValue();
      if (value instanceof BeanReference) {
        // 如果是引用则通过getBean再次初始化对象
        BeanReference ref = (BeanReference) value;
        value = getBean(ref.getName());
      }
      // 通过setter方法注入
      try {
        Method declaredMethod =
            bean.getClass()
                .getDeclaredMethod(
                    "set" + pv.getName().substring(0, 1).toUpperCase() + pv.getName().substring(1),
                    value.getClass());
        declaredMethod.setAccessible(true);

        declaredMethod.invoke(bean, value);
      } catch (NoSuchMethodException e) {
        Field declaredField = bean.getClass().getDeclaredField(pv.getName());
        declaredField.setAccessible(true);
        declaredField.set(bean, value);
      }
    }
  }
}
