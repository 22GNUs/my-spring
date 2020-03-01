package personal.wxh.spring.aop;

import java.util.List;
import org.aopalliance.intercept.MethodInterceptor;
import personal.wxh.spring.BeanPostProcessor;
import personal.wxh.spring.beans.factory.AbstractBeanFactory;
import personal.wxh.spring.beans.factory.BeanFactory;

/**
 * 实现 {@link BeanPostProcessor}
 *
 * @author wangxinhua
 * @since 1.0
 */
public class AspectJAwareAdvisorAutoProxyCreator implements BeanPostProcessor, BeanFactoryAware {

  private AbstractBeanFactory beanFactory;

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) {
    return bean;
  }

  /**
   * 复写该方法, 修改返回值为代理对象
   *
   * @param bean bean对象
   * @param beanName bean名称
   * @return 代理对象
   */
  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) {
    if (bean instanceof AspectJExpressionPointcutAdvisor) {
      return bean;
    }
    if (bean instanceof MethodInterceptor) {
      return bean;
    }
    // 通过Advisor创建代理对象
    // 配置多个Advisor来拦截
    List<AspectJExpressionPointcutAdvisor> advisors =
        beanFactory.getBeanForType(AspectJExpressionPointcutAdvisor.class);
    for (AspectJExpressionPointcutAdvisor advisor : advisors) {
      // 判断切面类是否匹配
      if (advisor.getPointCut().getClassFilter().matches(bean.getClass())) {
        // 创建代理类
        TargetSource targetSource = new TargetSource(bean, bean.getClass().getInterfaces());
        // advice是任意接口, 强转为MethodInterceptor
        AdvisedSupport support =
            new AdvisedSupport(
                targetSource,
                (MethodInterceptor) advisor.getAdvice(),
                advisor.getPointCut().getMethodMatcher());
        return new JdkDynamicAopProxy(support).getProxy();
      }
    }
    return bean;
  }

  @Override
  public void setBeanFactory(BeanFactory beanFactory) {
    this.beanFactory = (AbstractBeanFactory) beanFactory;
  }
}
