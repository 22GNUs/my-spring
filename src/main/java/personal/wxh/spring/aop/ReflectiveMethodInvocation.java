package personal.wxh.spring.aop;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author wangxinhua
 * @since 1.0
 */
public class ReflectiveMethodInvocation implements MethodInvocation {

  private final Object target;
  private final Method method;
  private final Object[] args;

  public ReflectiveMethodInvocation(Object target, Method method, Object[] args) {
    this.target = target;
    this.method = method;
    this.args = args;
  }

  @Override
  public Method getMethod() {
    return method;
  }

  @Override
  public Object[] getArguments() {
    return args;
  }

  @Override
  public Object proceed() throws Throwable {
    return method.invoke(target, args);
  }

  @Override
  public Object getThis() {
    return target;
  }

  @Override
  public AccessibleObject getStaticPart() {
    return method;
  }
}
