### @EnableAutoConfiguration是如何实现自动配置
为了更好的记住@EnableAutoConfiguration的功能，我们先考虑下这几个问题？</br>
<font color=red>1、什么样的东西会被@EnableAutoConfiguration自动配置</font></br>
<font color=red>2、@EnableAutoConfiguration是如何找到自动配置的内容</font></br>
<font color=red>3、如果想要被@EnableAutoConfiguration自动配置需要做哪些事情</font>
---
现在我们来看看@EnableAutoConfiguration的代码实现
```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@AutoConfigurationPackage   // 自动配置打包
@Import(AutoConfigurationImportSelector.class) // 自动配置导入选择器
public @interface EnableAutoConfiguration {

	String ENABLED_OVERRIDE_PROPERTY = "spring.boot.enableautoconfiguration";
    
	Class<?>[] exclude() default {};
    
	String[] excludeName() default {};

}
```
    这里主要有两个注解@Import和@AutoConfigurationPackage,需要了解下他们的作用
    @Import导入类 功能类似Spring XML的<import/> 目前支持三种导入

+ 带有@Configuration注解的配置类,4.2版本前只能导入配置类，4.2后也可以导入普通类
+ ImportSelector的实现
+ ImportBeanDefinitionRegistrar的实现

借助@Import,引入AutoConfigurationImportSelector收集和注册特定场景相关的bean定义，即实现自动配置功能
    