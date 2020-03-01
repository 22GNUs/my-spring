package personal.wxh.spring;

/**
 * bean的包装类, 包含bean的元信息
 *
 * @author wangxinhua
 * @since 1.0
 */
public class BeanDefinition {

  /** bean对象, 在BeanFactory中初始化 */
  private Object bean;

  /** 依赖属性 */
  private PropertyValues propertyValues;

  /** bean对应的class对象 */
  private final Class<?> beanClass;

  /** bean的class名称 */
  private final String beanClassName;

  public BeanDefinition(String className) {
    this.beanClassName = className;
    try {
      this.beanClass = Class.forName(className);
    } catch (ClassNotFoundException e) {
      // 简单处理
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  public BeanDefinition(String className, PropertyValues propertyValues) {
    this(className);
    this.propertyValues = propertyValues;
  }

  public Object getBean() {
    return bean;
  }

  public void setBean(Object bean) {
    this.bean = bean;
  }

  public Class<?> getBeanClass() {
    return beanClass;
  }

  public String getBeanClassName() {
    return beanClassName;
  }

  public PropertyValues getPropertyValues() {
    return propertyValues;
  }

  public void setPropertyValues(PropertyValues propertyValues) {
    this.propertyValues = propertyValues;
  }
}
