package personal.wxh.spring.aop;

/**
 * 判断类型是否匹配
 *
 * @author wangxinhua
 * @since 1.0
 */
@FunctionalInterface
public interface ClassFilter {
  boolean matches(Class<?> targetClass);
}
