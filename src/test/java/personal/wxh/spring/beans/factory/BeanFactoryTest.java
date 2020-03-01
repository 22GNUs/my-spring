package personal.wxh.spring.beans.factory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import personal.wxh.spring.BeanDefinition;
import personal.wxh.spring.BeanDefinitionReader;
import personal.wxh.spring.PropertyValue;
import personal.wxh.spring.PropertyValues;
import personal.wxh.spring.beans.io.ResourceLoader;
import personal.wxh.spring.beans.xml.XmlBeanDefinitionReader;
import personal.wxh.spring.service.FieldAutowiredService;
import personal.wxh.spring.service.HelloWorldService;
import personal.wxh.spring.service.RefAutowiredService;
import personal.wxh.spring.service.XmlAutowiredService;

/**
 * @author wangxinhua
 * @since 1.0
 */
public class BeanFactoryTest {

  /** 测试基础的Factory创建方法 */
  @Test
  public void testHelloWorld() {
    final AbstractBeanFactory beanFactory = new AutowireCapableBeanFactory();
    final String testName = "helloWorldService";
    beanFactory.registerBeanDefinition(
        testName, new BeanDefinition("personal.wxh.spring.service.HelloWorldServiceImpl"));
    final HelloWorldService service = (HelloWorldService) beanFactory.getBean(testName);
    assertEquals("hello world", service.helloWorld());
  }

  /** 测试属性自动注入 */
  @Test
  public void testFiledAutowired() {
    final AbstractBeanFactory beanFactory = new AutowireCapableBeanFactory();
    final String testName = "fieldAutowiredService";
    final String textField = "Im autowired";
    final PropertyValues pvs = new PropertyValues(new PropertyValue("text", textField));
    beanFactory.registerBeanDefinition(
        testName, new BeanDefinition("personal.wxh.spring.service.FieldAutowiredService", pvs));
    final FieldAutowiredService s = (FieldAutowiredService) beanFactory.getBean(testName);
    assertNotNull(s);
    assertEquals(textField, s.getText());
  }

  /**
   * 测试XML读取装载bean
   *
   * @throws Exception 抛出所有异常
   */
  @Test
  public void testXmlReaderAutowired() throws Exception {
    BeanFactory factory = loadFactoryFromXML();
    // 读取Bean, 名字跟XMl中配置的一致
    XmlAutowiredService service = (XmlAutowiredService) factory.getBean("xmlService");
    assertNotNull(service);
    assertEquals(service.getText(), "Im autowired by xml");
  }

  /**
   * 测试引用注入
   *
   * @throws Exception 抛出所有异常
   */
  @Test
  public void testBeanRefAutowired() throws Exception {
    BeanFactory factory = loadFactoryFromXML();
    RefAutowiredService refService = (RefAutowiredService) factory.getBean("refService");
    assertNotNull(refService);

    XmlAutowiredService xmlServiceFromRef = refService.getXmlAutowiredService();
    assertNotNull(xmlServiceFromRef);
    XmlAutowiredService xmlFromFactory = (XmlAutowiredService) factory.getBean("xmlService");
    // 引用的bean应该等于注入的bean
    assertEquals(xmlServiceFromRef, xmlFromFactory);
  }

  private BeanFactory loadFactoryFromXML() throws Exception {
    // 1. 读取配置文件并解析BeanDefinition缓存到reader
    BeanDefinitionReader reader = new XmlBeanDefinitionReader(new ResourceLoader());
    reader.loadBeanDefinitions("test-application-context.xml");

    // 2. 初始化BeanFactory, 把reader中的BeanDefinition读取并初始化到BeanFactory
    AbstractBeanFactory factory = new AutowireCapableBeanFactory();
    for (var beanDefinition : reader.getRegistry().entrySet()) {
      factory.registerBeanDefinition(beanDefinition.getKey(), beanDefinition.getValue());
    }
    return factory;
  }
}
