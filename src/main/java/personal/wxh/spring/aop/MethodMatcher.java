package personal.wxh.spring.aop;

import java.lang.reflect.Method;

/**
 * @author wangxinhua
 * @since 1.0
 */
@FunctionalInterface
public interface MethodMatcher {

  /**
   * 判断方式是否匹配
   *
   * @param method 方法
   * @param targetClass 方法所属类
   * @return 是否匹配
   */
  boolean matches(Method method, Class<?> targetClass);
}
