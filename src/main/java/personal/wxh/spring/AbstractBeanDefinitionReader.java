package personal.wxh.spring;

import personal.wxh.spring.beans.io.ResourceLoader;

import java.util.HashMap;
import java.util.Map;

/**
 * 出项Reader
 *
 * @author wangxinhua
 * @since 1.0
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

  private final Map<String, BeanDefinition<?>> registry;
  private final ResourceLoader resourceLoader;

  public AbstractBeanDefinitionReader(ResourceLoader resourceLoader) {
    this.registry = new HashMap<>();
    this.resourceLoader = resourceLoader;
  }

  public Map<String, BeanDefinition<?>> getRegistry() {
    return registry;
  }

  protected ResourceLoader getResourceLoader() {
    return resourceLoader;
  }
}
