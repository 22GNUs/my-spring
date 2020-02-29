package personal.wxh.spring.aop;

import org.aopalliance.aop.Advice;

/**
 * Advisor: 组合PointCut和Advice
 *
 * @author wangxinhua
 * @since 1.0
 */
public class AspectJExpressionPointcutAdvisor implements PointCutAdvisor {

  private final PointCut pointCut;
  private final Advice advice;

  public AspectJExpressionPointcutAdvisor(String expression, Advice advice) {
    this.pointCut = new AspectJExpressionPointcut(expression);
    this.advice = advice;
  }

  @Override
  public PointCut getPointCut() {
    return pointCut;
  }

  public Advice getAdvice() {
    return advice;
  }
}
