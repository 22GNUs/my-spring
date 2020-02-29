package personal.wxh.spring.aop;

import static org.junit.Assert.*;

import org.junit.Test;
import personal.wxh.spring.context.ApplicationContext;
import personal.wxh.spring.context.ClassPathXmlApplicationContext;
import personal.wxh.spring.service.HelloWorldService;
import personal.wxh.spring.service.HelloWorldServiceImpl;

/**
 * @author wangxinhua
 * @since 1.0
 */
public class JdkDynamicAopProxyTest {

  @Test
  public void testInterceptor() throws Exception {
    ApplicationContext context = new ClassPathXmlApplicationContext("test-application-context.xml");
    HelloWorldServiceImpl service = context.getBean("helloWorldService");

    AdvisedSupport<HelloWorldService> advisedSupport =
        new AdvisedSupport<>(
            new TargetSource<>(HelloWorldService.class, service),
            new ModifyReturnValueInterceptor());

    AopProxy<HelloWorldService> proxy = new JdkDynamicAopProxy<>(advisedSupport);
    HelloWorldService proxyService = proxy.getProxy();
    assertNotEquals(service, proxyService);
    assertEquals(service.helloWorld() + "modified", proxyService.helloWorld());
  }
}
