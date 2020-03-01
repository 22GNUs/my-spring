package personal.wxh.spring;

/** Bean初始化回调接口 */
public interface BeanPostProcessor {

  /**
   * 在bean初始化前回调
   *
   * @param bean bean对象
   * @param beanName bean名称
   * @return 自定义返回值
   * @throws Exception 抛出所有异常
   */
  Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception;

  /**
   * 在bean初始后前回调
   *
   * @param bean bean对象
   * @param beanName bean名称
   * @return 自定义返回值
   * @throws Exception 抛出所有异常
   */
  Object postProcessAfterInitialization(Object bean, String beanName) throws Exception;
}
