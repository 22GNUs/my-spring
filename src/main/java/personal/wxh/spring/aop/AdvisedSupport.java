package personal.wxh.spring.aop;

import org.aopalliance.intercept.MethodInterceptor;

/**
 * 代理相关元数据
 *
 * @author wangxinhua
 * @since 1.0
 */
public class AdvisedSupport {

  /** 目标对象 */
  private final TargetSource targetSource;

  /** 方法拦截器 */
  private final MethodInterceptor methodInterceptor;

  /** 方法匹配器, 可选参数, 为空则表示拦截所有方法 */
  private final MethodMatcher methodMatcher;

  public AdvisedSupport(
      TargetSource targetSource, MethodInterceptor methodInterceptor, MethodMatcher methodMatcher) {
    this.targetSource = targetSource;
    this.methodInterceptor = methodInterceptor;
    this.methodMatcher = methodMatcher;
  }

  public TargetSource getTargetSource() {
    return targetSource;
  }

  public MethodInterceptor getMethodInterceptor() {
    return methodInterceptor;
  }

  public MethodMatcher getMethodMatcher() {
    return methodMatcher;
  }
}
