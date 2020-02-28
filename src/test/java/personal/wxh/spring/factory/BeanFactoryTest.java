package personal.wxh.spring.factory;

import org.junit.Test;
import personal.wxh.spring.*;

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
        testName, new BeanDefinition<HelloWorldService>("personal.wxh.spring.HelloWorldService"));
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
        testName, new BeanDefinition<>("personal.wxh.spring.FieldAutowiredService", pvs));
    final FieldAutowiredService s = beanFactory.getBean(testName);
    assertNotNull(s);
    assertEquals(s.getText(), textField);
  }
}
