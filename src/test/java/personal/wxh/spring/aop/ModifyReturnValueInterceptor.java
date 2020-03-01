package personal.wxh.spring.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 测试用, 修改返回值
 *
 * @author yihua.huang@dianping.com
 */
public class ModifyReturnValueInterceptor implements MethodInterceptor {

  @Override
  public Object invoke(MethodInvocation invocation) throws Throwable {
    Object proceed = invocation.proceed();
    if (proceed instanceof String) {
      return proceed + "-modified";
    }
    return proceed;
  }
}
