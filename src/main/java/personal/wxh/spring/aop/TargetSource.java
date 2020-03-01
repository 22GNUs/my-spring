package personal.wxh.spring.aop;

/**
 * 被代理对象
 *
 * @author wangxinhua
 * @since 1.0
 */
public class TargetSource {

  private final Class<?>[] targetClass;
  private final Object target;

  public TargetSource(Object target, Class<?>... targetClass) {
    this.targetClass = targetClass;
    this.target = target;
  }

  public Class<?>[] getTargetClass() {
    return targetClass;
  }

  public Object getTarget() {
    return target;
  }
}
