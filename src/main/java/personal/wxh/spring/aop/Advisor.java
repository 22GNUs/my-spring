package personal.wxh.spring.aop;

import org.aopalliance.aop.Advice;

/**
 * 通知类
 *
 * @author wangxinhua
 * @since 1.0
 */
public interface Advisor {

  Advice getAdvice();
}
