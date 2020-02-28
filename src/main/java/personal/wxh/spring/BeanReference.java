package personal.wxh.spring;

/**
 * 表示bean引用对象
 *
 * @author wangxinhua
 * @since 1.0
 */
public class BeanReference {
  /** 引用名称 */
  private final String name;

  public BeanReference(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
