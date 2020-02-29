package personal.wxh.spring.aop;

/**
 * 切面接口
 *
 * @author wangxinhua
 * @since 1.0
 */
public interface PointCut {

  ClassFilter getClassFilter();

  MethodMatcher getMethodMatcher();
}
