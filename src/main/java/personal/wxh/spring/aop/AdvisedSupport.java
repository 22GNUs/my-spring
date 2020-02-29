package personal.wxh.spring.aop;

import org.aopalliance.intercept.MethodInterceptor;

/**
 * 代理相关元数据
 *
 * @author wangxinhua
 * @since 1.0
 */
public class AdvisedSupport<T> {

  private final TargetSource<T> targetSource;
  private final MethodInterceptor methodInterceptor;

  public AdvisedSupport(TargetSource<T> targetSource, MethodInterceptor methodInterceptor) {
    this.targetSource = targetSource;
    this.methodInterceptor = methodInterceptor;
  }

  public TargetSource<T> getTargetSource() {
    return targetSource;
  }

  public MethodInterceptor getMethodInterceptor() {
    return methodInterceptor;
  }
}
