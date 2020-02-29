package personal.wxh.spring.aop;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;
import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.PointcutPrimitive;
import org.aspectj.weaver.tools.ShadowMatch;

/**
 * 基于表达式的切面实现
 *
 * @author wangxinhua
 * @since 1.0
 */
public class AspectJExpressionPointcut implements PointCut, ClassFilter, MethodMatcher {

  private final String expression;

  private final PointcutExpression pointcutExpression;

  private PointcutParser pointcutParser;

  private static final Set<PointcutPrimitive> DEFAULT_SUPPORTED_PRIMITIVES = new HashSet<>();

  static {
    DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.EXECUTION);
    DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.ARGS);
    DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.REFERENCE);
    DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.THIS);
    DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.TARGET);
    DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.WITHIN);
    DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_ANNOTATION);
    DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_WITHIN);
    DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_ARGS);
    DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_TARGET);
  }

  public AspectJExpressionPointcut(String expression) {
    this(expression, DEFAULT_SUPPORTED_PRIMITIVES);
  }

  public AspectJExpressionPointcut(String expression, Set<PointcutPrimitive> supportedPrimitives) {
    this.expression = expression;
    pointcutParser =
        PointcutParser
            .getPointcutParserSupportingSpecifiedPrimitivesAndUsingContextClassloaderForResolution(
                supportedPrimitives);
    // 根据表达式否建匹配对象
    this.pointcutExpression = buildPointcutExpression();
  }

  private PointcutExpression buildPointcutExpression() {
    return pointcutParser.parsePointcutExpression(expression);
  }

  @Override
  public ClassFilter getClassFilter() {
    return this;
  }

  @Override
  public MethodMatcher getMethodMatcher() {
    return this;
  }

  @Override
  public boolean matches(Class<?> targetClass) {
    // 调用是否匹配
    return pointcutExpression.couldMatchJoinPointsInType(targetClass);
  }

  @Override
  public boolean matches(Method method, Class<?> targetClass) {
    ShadowMatch shadowMatch = pointcutExpression.matchesMethodExecution(method);
    if (shadowMatch.alwaysMatches()) {
      return true;
    } else if (shadowMatch.neverMatches()) {
      return false;
    }
    return false;
  }
}
