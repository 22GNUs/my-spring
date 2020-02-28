package personal.wxh.spring;

/**
 * bean的包装类, 包含bean的元信息
 *
 * @author wangxinhua
 * @since 1.0
 */
public class BeanDefinition<T> {

  /** bean对象, 在BeanFactory中初始化 */
  private T bean;

  /** bean对应的class对象 */
  private final Class<T> beanClass;

  /** bean的class名称 */
  private final String beanClassName;

  public BeanDefinition(String className) {
    this.beanClassName = className;
    try {
      @SuppressWarnings("unchecked")
      Class<T> aClass = (Class<T>) Class.forName(className);
      this.beanClass = aClass;
    } catch (ClassNotFoundException e) {
      // 简单处理
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  public T getBean() {
    return bean;
  }

  public void setBean(T bean) {
    this.bean = bean;
  }

  public Class<T> getBeanClass() {
    return beanClass;
  }

  public String getBeanClassName() {
    return beanClassName;
  }
}
