package personal.wxh.spring.beans.factory;

import personal.wxh.spring.BeanDefinition;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 抽象的BeanFactory
 *
 * @author wangxinhua
 * @since 1.0
 */
public abstract class AbstractBeanFactory implements BeanFactory {

  /** 使用一个ConcurrentHashMap保存全局的Bean对象实例 */
  private final Map<String, BeanDefinition<?>> beanDefinitionMap = new ConcurrentHashMap<>();

  @Override
  public <T> T getBean(String name) {
    // 利用ConcurrentHashMap特性在getBean时lazy-init
    @SuppressWarnings("unchecked")
    BeanDefinition<T> beanDefinition =
        (BeanDefinition<T>)
            beanDefinitionMap.computeIfPresent(
                name,
                (key, bd) -> {
                  if (bd.getBean() == null) {
                    doCreateBean(bd);
                  }
                  return bd;
                });
    if (beanDefinition == null) {
      throw new IllegalArgumentException("No bean named " + name + " is defined");
    }
    return beanDefinition.getBean();
  }

  public <T> void registerBeanDefinition(String name, BeanDefinition<T> beanDefinition) {
    beanDefinitionMap.putIfAbsent(name, beanDefinition);
  }

  /**
   * 模板方法, 子类实现初始化bean
   *
   * @param beanDefinition bean包装对象
   * @param <T> bean泛型类型
   * @return bean对象
   */
  protected abstract <T> T doCreateBean(BeanDefinition<T> beanDefinition);
}
