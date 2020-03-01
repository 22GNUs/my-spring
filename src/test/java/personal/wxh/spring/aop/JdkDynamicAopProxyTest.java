package personal.wxh.spring.aop;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;
import personal.wxh.spring.context.ApplicationContext;
import personal.wxh.spring.context.ClassPathXmlApplicationContext;
import personal.wxh.spring.service.HelloWorldService;

/**
 * @author wangxinhua
 * @since 1.0
 */
public class JdkDynamicAopProxyTest {

  @Test
  public void testInterceptor() throws Exception {
    ApplicationContext context = new ClassPathXmlApplicationContext("test-application-context.xml");
    HelloWorldService service = (HelloWorldService) context.getBean("helloWorldService");

    AdvisedSupport advisedSupport =
        new AdvisedSupport(
            new TargetSource(service, HelloWorldService.class),
            new ModifyReturnValueInterceptor(),
            new AspectJExpressionPointcut(
                "execution(* personal.wxh.spring.service.HelloWorldService.*(..))"));

    AopProxy proxy = new JdkDynamicAopProxy(advisedSupport);
    HelloWorldService proxyService = (HelloWorldService) proxy.getProxy();
    assertNotEquals(service, proxyService);
    assertEquals(service.helloWorld() + "-modified", proxyService.helloWorld());
  }
}
