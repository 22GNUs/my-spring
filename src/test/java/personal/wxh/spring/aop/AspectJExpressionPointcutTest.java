package personal.wxh.spring.aop;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import personal.wxh.spring.service.HelloWorldService;
import personal.wxh.spring.service.HelloWorldServiceImpl;

/**
 * @author wangxinhua
 * @since 1.0
 */
public class AspectJExpressionPointcutTest {

  /** 测试class切面表达式 */
  @Test
  public void testClassFilter() {
    String expression = "execution(* personal.wxh.spring.service.*.*(..))";
    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut(expression);
    boolean matches = pointcut.getClassFilter().matches(HelloWorldService.class);
    assertTrue(matches);
  }

  @Test
  public void testMethodInterceptor() throws Exception {
    String expression = "execution(* personal.wxh.spring.service.*.*(..))";
    AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut(expression);
    boolean matches =
        aspectJExpressionPointcut
            .getMethodMatcher()
            .matches(
                HelloWorldServiceImpl.class.getDeclaredMethod("helloWorld"),
                HelloWorldServiceImpl.class);
    assertTrue(matches);
  }
}
