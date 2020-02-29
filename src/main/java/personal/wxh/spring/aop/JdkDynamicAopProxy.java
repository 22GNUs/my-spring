package personal.wxh.spring.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import org.aopalliance.intercept.MethodInterceptor;

/**
 * JDK动态代理类
 *
 * @author wangxinhua
 * @since 1.0
 */
public class JdkDynamicAopProxy<T> implements AopProxy<T>, InvocationHandler {

  /** 此处的泛型必须是一个接口, 才可以转换成功 */
  private final AdvisedSupport<T> advised;

  public JdkDynamicAopProxy(AdvisedSupport<T> advised) {
    this.advised = advised;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    MethodInterceptor interceptor = advised.getMethodInterceptor();
    return interceptor.invoke(
        new ReflectiveMethodInvocation(advised.getTargetSource().getTarget(), method, args));
  }

  @Override
  @SuppressWarnings("unchecked")
  public T getProxy() {
    return (T)
        Proxy.newProxyInstance(
            getClass().getClassLoader(),
            new Class[] {advised.getTargetSource().getTargetClass()},
            this);
  }
}
