package personal.wxh.spring.aop;

/**
 * 被代理对象
 *
 * @author wangxinhua
 * @since 1.0
 */
public class TargetSource<T> {

  private final Class<T> targetClass;
  private final T target;

  public TargetSource(Class<T> targetClass, T target) {
    this.targetClass = targetClass;
    this.target = target;
  }

  public Class<T> getTargetClass() {
    return targetClass;
  }

  public T getTarget() {
    return target;
  }
}
