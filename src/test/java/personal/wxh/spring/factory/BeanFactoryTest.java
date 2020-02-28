package personal.wxh.spring.factory;

import org.junit.Test;
import personal.wxh.spring.BeanDefinition;
import personal.wxh.spring.HelloWorldService;

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
  public void testFactory() throws Exception {
    final BeanFactory beanFactory = new AutowireCapableBeanFactory();
    final String testName = "helloWorldService";
    beanFactory.registerBeanDefinition(
        testName, new BeanDefinition<HelloWorldService>("personal.wxh.spring.HelloWorldService"));
    final HelloWorldService service = beanFactory.getBean(testName);
    assertEquals("hello world", service.getName());
  }
}
