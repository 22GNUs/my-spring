package personal.wxh.spring.aop;

import org.aopalliance.aop.Advice;

/**
 * Advisor: 组合PointCut和Advice
 *
 * @author wangxinhua
 * @since 1.0
 */
public class AspectJExpressionPointcutAdvisor implements PointCutAdvisor {

  private AspectJExpressionPointcut pointCut = new AspectJExpressionPointcut();
  private Advice advice;

  public void setAdvice(Advice advice) {
    this.advice = advice;
  }

  public void setExpression(String expression) {
    this.pointCut.setExpression(expression);
  }

  @Override
  public PointCut getPointCut() {
    return pointCut;
  }

  public Advice getAdvice() {
    return advice;
  }
}
