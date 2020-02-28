package personal.wxh.spring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 对属性列表做封装
 *
 * @author wangxinhua
 * @since 1.0
 */
public class PropertyValues {

  /** 保存所有注入的属性 */
  private final List<PropertyValue> propertyValues;

  public PropertyValues() {
    this.propertyValues = new ArrayList<>();
  }

  public PropertyValues(PropertyValue... pvs) {
    this.propertyValues = Arrays.asList(pvs);
  }

  public void addPropertyValue(String name, Object value) {
    addPropertyValue(new PropertyValue(Objects.requireNonNull(name), value));
  }

  public void addPropertyValue(PropertyValue value) {
    this.propertyValues.add(Objects.requireNonNull(value));
  }

  public List<PropertyValue> getPropertyValues() {
    // TODO 考虑复制一份拷贝方式发布溢出
    return propertyValues;
  }
}
