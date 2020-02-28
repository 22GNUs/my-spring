My-Spring
==========

模仿Sring编写的Ioc容器

执行流程
-------

1. 通过XmlBeanDefinitionReader读取XML中的Bean配置, 存储在内部的HashMap
2. 把XmlBeanDefinitionReader中的BeanDefinition读取到BeanFactory, 并初始化

TODO
-----

- [x] 属性注入
- [x] XML解析
- [ ] 完成AOP解析
