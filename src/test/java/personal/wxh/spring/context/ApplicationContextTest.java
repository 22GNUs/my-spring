package personal.wxh.spring.context;

import static org.junit.Assert.*;

import java.lang.reflect.Proxy;
import org.junit.Test;
import personal.wxh.spring.service.RefAutowiredService;
import personal.wxh.spring.service.ReturnStringService;
import personal.wxh.spring.service.XmlAutowiredService;

/**
 * @author wangxinhua
 * @since 1.0
 */
public class ApplicationContextTest {

  /** 测试applicationContext注入 */
  @Test
  public void testContextAutowired() throws Exception {
    ApplicationContext ctx = new ClassPathXmlApplicationContext("test-application-context.xml");
    RefAutowiredService refService = (RefAutowiredService) ctx.getBean("refService");
    assertNotNull(refService);
    XmlAutowiredService xmlService = refService.getXmlAutowiredService();
    assertNotNull(xmlService);
    assertEquals("Im autowired by xml", xmlService.getText());
  }

  /**
   * 测试自动代理, 需要把测试拦截对象先配置到XML
   *
   * @throws Exception 抛出所有异常
   */
  @Test
  public void testApplicationContextAutoProxy() throws Exception {
    ApplicationContext ctx = new ClassPathXmlApplicationContext("test-auto-proxy-context.xml");
    ReturnStringService returnService = (ReturnStringService) ctx.getBean("returnService");
    assertNotNull(returnService);
    assertTrue(returnService instanceof Proxy);
    assertEquals("Im Return-modified", returnService.ret());
  }
}
