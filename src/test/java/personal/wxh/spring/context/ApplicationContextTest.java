package personal.wxh.spring.context;

import static org.junit.Assert.*;

import org.junit.Test;
import personal.wxh.spring.service.RefAutowiredService;
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
    RefAutowiredService refService = ctx.getBean("refService");
    assertNotNull(refService);
    XmlAutowiredService xmlService = refService.getXmlAutowiredService();
    assertNotNull(xmlService);
    assertEquals("Im autowired by xml", xmlService.getText());
  }
}
