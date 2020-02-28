package personal.wxh.spring.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import personal.wxh.spring.AbstractBeanDefinitionReader;
import personal.wxh.spring.BeanDefinition;
import personal.wxh.spring.PropertyValue;
import personal.wxh.spring.PropertyValues;
import personal.wxh.spring.io.ResourceLoader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangxinhua
 * @since 1.0
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

  /** XML定义的名称属性名称 */
  private static final String FIELD_NAME = "name";
  /** XML定义的class属性名称 */
  private static final String FIELD_CLASS_NAME = "class";
  /** XML定义的property属性名称 */
  private static final String FIELD_PROPERTY_NAME = "property";
  /** XML定义的property中的key属性名称 */
  private static final String FIELD_PROPERTY_KEY_NAME = "name";
  /** XML定义的property中的value属性名称 */
  private static final String FIELD_PROPERTY_VALUE_NAME = "value";

  public XmlBeanDefinitionReader(ResourceLoader resourceLoader) {
    super(resourceLoader);
  }

  @Override
  public void loadBeanDefinition(String location) throws Exception {
    try (final InputStream is = getResourceLoader().getResource(location).getInputStream()) {
      // XML解析输入流
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document doc = builder.parse(is);
      // 通过XML注册bean
      registerBeanDefinitions(doc);
    }
  }

  private void registerBeanDefinitions(Document doc) {
    Element root = doc.getDocumentElement();
    NodeList nl = root.getChildNodes();
    if (nl == null) {
      return;
    }
    for (int i = 0; i < nl.getLength(); i++) {
      Node node = nl.item(i);
      if (node instanceof Element) {
        Element e = (Element) node;
        // 解析单个节点
        processBeanDefinition(e);
      }
    }
  }

  private void processBeanDefinition(Element e) {
    String name = e.getAttribute(FIELD_NAME);
    String className = e.getAttribute(FIELD_CLASS_NAME);
    PropertyValues propertyValues = processPropertyValues(e);
    BeanDefinition<?> beanDefinition = new BeanDefinition<>(className, propertyValues);
    getRegistry().put(name, beanDefinition);
  }

  private PropertyValues processPropertyValues(Element e) {
    NodeList nl = e.getElementsByTagName(FIELD_PROPERTY_NAME);
    if (nl == null) {
      return null;
    }
    List<PropertyValue> values = new ArrayList<>();
    for (int i = 0; i < nl.getLength(); i++) {
      Node node = nl.item(i);
      if (node instanceof Element) {
        Element propertyEle = (Element) node;
        String name = propertyEle.getAttribute(FIELD_PROPERTY_KEY_NAME);
        String value = propertyEle.getAttribute(FIELD_PROPERTY_VALUE_NAME);
        values.add(new PropertyValue(name, value));
      }
    }
    return new PropertyValues(values);
  }
}
