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
public class JdkDynamicAopProxy implements AopProxy, InvocationHandler {

  private final AdvisedSupport advised;

  public JdkDynamicAopProxy(AdvisedSupport advised) {
    this.advised = advised;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    MethodInterceptor interceptor = advised.getMethodInterceptor();
    MethodMatcher methodMatcher = advised.getMethodMatcher();
    if (methodMatcher != null
        && methodMatcher.matches(method, advised.getTargetSource().getClass())) {
      // proxy是代理对象自己, 此处执行应该调用被代理对象自己
      return interceptor.invoke(
          new ReflectiveMethodInvocation(advised.getTargetSource().getTarget(), method, args));
    }
    // 没有匹配表达式则不代理
    return method.invoke(advised.getTargetSource().getTarget(), args);
  }

  @Override
  public Object getProxy() {
    return Proxy.newProxyInstance(
        getClass().getClassLoader(), advised.getTargetSource().getTargetClass(), this);
  }
}
