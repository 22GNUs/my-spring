package personal.wxh.spring.factory;

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
    @SuppressWarnings("unchecked")
    final T ret =
        (T)
            Optional.ofNullable(beanDefinitionMap.get(name))
                .map(BeanDefinition::getBean)
                .orElse(null);
    return ret;
  }

  @Override
  public <T> void registerBeanDefinition(String name, BeanDefinition<T> beanDefinition) {
    final BeanDefinition<?> old = beanDefinitionMap.putIfAbsent(name, beanDefinition);
    if (old == null) {
      // old == null 说明是第一次放入, 执行初始化
      beanDefinition.setBean(this.doCreateBean(beanDefinition));
    }
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
