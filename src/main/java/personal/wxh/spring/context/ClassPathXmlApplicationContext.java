package personal.wxh.spring.context;

import java.util.Map;
import personal.wxh.spring.BeanDefinition;
import personal.wxh.spring.beans.factory.AbstractBeanFactory;
import personal.wxh.spring.beans.factory.AutowireCapableBeanFactory;
import personal.wxh.spring.beans.io.ResourceLoader;
import personal.wxh.spring.beans.xml.XmlBeanDefinitionReader;

/**
 * 基于XML的ApplicationContext实现
 *
 * <pre>
 * 组合了{@link personal.wxh.spring.BeanDefinitionReader} 和 {@link AbstractBeanFactory}
 * </pre>
 *
 * @author wangxinhua
 * @since 1.0
 */
public class ClassPathXmlApplicationContext extends AbstractApplicationContext {

  /** 配置文件路径 */
  private final String cfgLocation;

  public ClassPathXmlApplicationContext(String cfgLocation) throws Exception {
    this(cfgLocation, new AutowireCapableBeanFactory());
  }

  public ClassPathXmlApplicationContext(String cfgLocation, AbstractBeanFactory beanFactory)
      throws Exception {
    super(beanFactory);
    this.cfgLocation = cfgLocation;
    refresh();
  }

  @Override
  protected void loadBeanDefinitions() throws Exception {
    XmlBeanDefinitionReader xmlBeanDefinitionReader =
        new XmlBeanDefinitionReader(new ResourceLoader());
    xmlBeanDefinitionReader.loadBeanDefinitions(cfgLocation);
    for (Map.Entry<String, BeanDefinition> beanDefinitionEntry :
        xmlBeanDefinitionReader.getRegistry().entrySet()) {
      beanFactory.registerBeanDefinition(
          beanDefinitionEntry.getKey(), beanDefinitionEntry.getValue());
    }
  }
}
