package personal.wxh.spring.factory;

import org.junit.Test;
import personal.wxh.spring.*;
import personal.wxh.spring.io.ResourceLoader;
import personal.wxh.spring.service.FieldAutowiredService;
import personal.wxh.spring.service.HelloWorldService;
import personal.wxh.spring.service.XmlAutowiredService;
import personal.wxh.spring.xml.XmlBeanDefinitionReader;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author wangxinhua
 * @since 1.0
 */
public class BeanFactoryTest {

  /**
   * 测试基础的Factory创建方法
   *
   * @throws Exception 抛出所有异常
   */
  @Test
  public void testHelloWorld() throws Exception {
    final BeanFactory beanFactory = new AutowireCapableBeanFactory();
    final String testName = "helloWorldService";
    beanFactory.registerBeanDefinition(
        testName,
        new BeanDefinition<HelloWorldService>("personal.wxh.spring.service.HelloWorldService"));
    final HelloWorldService service = beanFactory.getBean(testName);
    assertEquals("hello world", service.getName());
  }

  /**
   * 测试属性自动注入
   *
   * @throws Exception 抛出所有异常
   */
  @Test
  public void testFiledAutowired() throws Exception {
    final BeanFactory beanFactory = new AutowireCapableBeanFactory();
    final String testName = "fieldAutowiredService";
    final String textField = "Im autowired";
    final PropertyValues pvs = new PropertyValues(new PropertyValue("text", textField));
    beanFactory.registerBeanDefinition(
        testName, new BeanDefinition<>("personal.wxh.spring.service.FieldAutowiredService", pvs));
    final FieldAutowiredService s = beanFactory.getBean(testName);
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
    // 1. 读取配置文件并解析BeanDefinition缓存到reader
    BeanDefinitionReader reader = new XmlBeanDefinitionReader(new ResourceLoader());
    reader.loadBeanDefinition("test-application-context.xml");

    // 2. 初始化BeanFactory, 把reader中的BeanDefinition读取并初始化到BeanFactory
    BeanFactory factory = new AutowireCapableBeanFactory();
    for (var beanDefinition : reader.getRegistry().entrySet()) {
      factory.registerBeanDefinition(beanDefinition.getKey(), beanDefinition.getValue());
    }

    // 3. 读取Bean, 名字跟XMl中配置的一致
    XmlAutowiredService service = factory.getBean("xmlService");
    assertNotNull(service);
    assertEquals(service.getText(), "Im autowired by xml");
  }
}
