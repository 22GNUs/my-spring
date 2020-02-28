package personal.wxh.spring;

/**
 * 表示一组key-value属性
 *
 * @author wangxinhua
 * @since 1.0
 */
public class PropertyValue {
  private final String name;
  private final Object value;

  public PropertyValue(String name, Object value) {
    this.name = name;
    this.value = value;
  }

  public String getName() {
    return name;
  }

  public Object getValue() {
    return value;
  }
}
