package personal.wxh.spring.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 打印时间的拦截器
 *
 * @author wangxinhua
 * @since 1.0
 */
public class TimeInterceptor implements MethodInterceptor {
  @Override
  public Object invoke(MethodInvocation invocation) throws Throwable {
    long time = System.nanoTime();
    System.out.println("Invocation of Method " + invocation.getMethod().getName() + " start!");
    Object proceed = invocation.proceed();
    System.out.println(
        "Invocation of Method "
            + invocation.getMethod().getName()
            + " end! takes "
            + (System.nanoTime() - time)
            + " nanoseconds.");
    return proceed;
  }
}
