My-Spring
==========

模仿Sring编写的Ioc容器

执行流程
-------

`BeanFactoryTest` 中的每个单元测试代表项目演进过程

1. 通过 `XmlBeanDefinitionReader` 读取XML中的Bean配置, 存储在内部的HashMap
2. 把 `XmlBeanDefinitionReader` 中的 `BeanDefinition` 读取到 `BeanFactory`, 并初始化

### 问题|笔记 (自问自答-_-)

**Q: 如何控制初始化顺序?**

A: 所有bean都是先装配再初始化, 所以在装配阶段(`BeanFactory` 从 `XmlBeanDefinitionReader` 读取)时,
所有bean已经全部读取到`XmlBeanDefinitionReader` 的HashMap中了, 所以不存在初始化顺序的问题

**Q: 如何处理循环依赖问题?**

A: 由于使用的是懒加载, 所以bean的初始化跟装配是两个阶段, 就不存在初始化时的竞争死锁问题

**Q: AOP为什么要定义切点(PointCut)**

A: 因为通过动态代理默认会代理所有方法, 可是多数情况我们只需要拦截部分方法

**Q: 什么是Advisor?**

A: PointCut相对来说比较好理解, advice表示拦截的位置(方法前还是方法后), advisor感觉不是很好理解,
找到一句英文的解释:

> Advisor – Group ‘Advice’ and ‘Pointcut’ into a single unit, and pass it to a proxy factory object.

Advisor就是把PointCut和Advice组合起来的一个单位

**Q: 如何实现自动代理?**

A:

1. 在 `AbstractBeanFactory#getBean()` 进行懒加载时(逻辑应该是只要加载bean时)调用 `PostProcessor` 进行拦截

2. 通过配置 `AspectJExpressionPointcutAdvisor` 并复写 `BeanPostProcessor` 的方法, 在方法返回时创建代理对象, 覆盖默认的返回对象

**Q: 什么时候自动代理?**

A: 在 `ApplicationContext` 中会注册 `BeanPostProcessor`, 直接调用 `BeanFactory` 时由于没有注册
`BeanPostProcessor` 所以不会被代理

TODO
-----

- [x] 属性注入
- [x] XML解析
- [x] 处理bean依赖关系
- [x] JDK动态代理
- [x] AOP解析
- [x] AOP自动代理
