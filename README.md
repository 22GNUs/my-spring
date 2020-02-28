My-Spring
==========

模仿Sring编写的Ioc容器

执行流程
-------

`BeanFactoryTest` 中的每个单元测试代表项目演进过程

1. 通过 `XmlBeanDefinitionReader` 读取XML中的Bean配置, 存储在内部的HashMap
2. 把 `XmlBeanDefinitionReader` 中的 `BeanDefinition` 读取到 `BeanFactory`, 并初始化

### 问题

**Q: 如何控制初始化顺序?**

A: 所有bean都是先装配再初始化, 所以在装配阶段(`BeanFactory` 从 `XmlBeanDefinitionReader` 读取)时,
所有bean已经全部读取到`XmlBeanDefinitionReader` 的HashMap中了, 所以不存在初始化顺序的问题

**Q: 如何处理循环依赖问题?**

A: 由于使用的是懒加载, 所以bean的初始化跟装配是两个阶段, 就不存在初始化时的竞争死锁问题

TODO
-----

- [x] 属性注入
- [x] XML解析
- [x] 处理bean依赖关系
- [ ] 完成AOP解析
