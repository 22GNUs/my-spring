package personal.wxh.spring;

import java.util.Map;

/**
 * 统一bean加载接口
 *
 * @author wangxinhua
 * @since 1.0
 */
public interface BeanDefinitionReader {

  /**
   * 从指定位置加载BeanDefinition
   * @param location 位置地址
   * @throws Exception 抛出子类异常
   */
  void loadBeanDefinition(String location) throws Exception;

  /**
   * 获取读取到的BeanDefinition数据
   * @return Map形式的BeanDefinition
   */
  Map<String, BeanDefinition<?>> getRegistry();
}
