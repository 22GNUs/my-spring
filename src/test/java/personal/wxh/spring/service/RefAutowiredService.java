package personal.wxh.spring.service;

/**
 * 测试引用依赖的service
 *
 * @author wangxinhua
 * @since 1.0
 */
public class RefAutowiredService {

  private XmlAutowiredService xmlAutowiredService;

  public XmlAutowiredService getXmlAutowiredService() {
    return xmlAutowiredService;
  }

  public void setXmlAutowiredService(XmlAutowiredService xmlAutowiredService) {
    this.xmlAutowiredService = xmlAutowiredService;
  }
}
