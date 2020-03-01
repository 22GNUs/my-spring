package personal.wxh.spring.beans.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import personal.wxh.spring.BeanDefinition;
import personal.wxh.spring.BeanPostProcessor;

/**
 * 抽象的BeanFactory
 *
 * @author wangxinhua
 * @since 1.0
 */
public abstract class AbstractBeanFactory implements BeanFactory {

  /** 使用一个ConcurrentHashMap保存全局的Bean对象实例 */
  private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

  /** 所有BeanPostProcessor集合 */
  private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

  @Override
  public Object getBean(String name) {
    // 利用ConcurrentHashMap特性在getBean时lazy-init
    BeanDefinition beanDefinition =
        beanDefinitionMap.computeIfPresent(
            name,
            (key, bd) -> {
              if (bd.getBean() == null) {
                try {
                  // 如果bean还未初始化则初始化
                  Object bean = doCreateBean(bd);
                  Object afterInitializeBean = initializeBean(bean, name);
                  bd.setBean(afterInitializeBean);
                } catch (Exception e) {
                  e.printStackTrace();
                  // 简单处理了, 实际环境要把异常跑出去
                  throw new RuntimeException("bean creation error");
                }
              }
              return bd;
            });
    if (beanDefinition == null) {
      throw new IllegalArgumentException("No bean named " + name + " is defined");
    }
    return beanDefinition.getBean();
  }

  /**
   * 通过 {@link BeanPostProcessor} 包装Bean创建过程
   *
   * @param bean 原始bean
   * @param name 原始bean名称
   * @return 包装后的bean, AOP代理只是BeanPostProcessor的一种实现
   * @throws Exception 抛出所有异常, 该方法子类可复写
   */
  protected Object initializeBean(Object bean, String name) throws Exception {
    for (BeanPostProcessor processor : beanPostProcessors) {
      // 前置处理
      bean = processor.postProcessBeforeInitialization(bean, name);
    }
    for (BeanPostProcessor processor : beanPostProcessors) {
      // 后置处理
      bean = processor.postProcessAfterInitialization(bean, name);
    }
    return bean;
  }

  /**
   * 根据类型找到对应的bean集合
   *
   * @param type 类型
   * @param <T> 类型泛型
   * @return bean集合
   */
  public <T> List<T> getBeanForType(Class<T> type) {
    List<T> ret = new ArrayList<>();
    for (Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
      BeanDefinition value = entry.getValue();
      if (type.isAssignableFrom(value.getBeanClass())) {
        @SuppressWarnings("unchecked")
        T bean = (T) getBean(entry.getKey());
        ret.add(bean);
      }
    }
    return ret;
  }

  /**
   * 注册bean
   *
   * @param name bean名称
   * @param beanDefinition bean定义对象
   */
  public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
    beanDefinitionMap.putIfAbsent(name, beanDefinition);
  }

  /**
   * 添加 {@link BeanPostProcessor}
   *
   * @param processor processor对象
   */
  public void addBeanPostProcessor(BeanPostProcessor processor) {
    beanPostProcessors.add(processor);
  }

  /**
   * 预初始化所有bean实例
   *
   * @throws Exception 抛出异常
   */
  public void preInstantiateSingletons() throws Exception {
    // getBean方法会初始化bean
    beanDefinitionMap.keySet().forEach(this::getBean);
  }

  /**
   * 模板方法, 子类实现初始化bean
   *
   * @param beanDefinition bean包装对象
   * @return bean对象
   */
  protected abstract Object doCreateBean(BeanDefinition beanDefinition);
}
