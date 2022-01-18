##  `@SpringBootApplication` 
    `@SpringBootApplication`是一个复合注解，用于SpringBoot应用启动类
```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(excludeFilters = { @Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
		@Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) })
public @interface SpringBootApplication {}
```
>忽略4个元注解，可以看出，`@SpringBootApplication`是`@SpringBootConfiguration`、`@EnableAutoConfiguration`和`@ComponentScan`的功能组合，这样写当然是为了方面和简洁
### `@SpringBootConfiguration` == `@Configuration`?
> 从`@SpringBootConfiguration`的实现可以看出，它是一个复合注解，

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Configuration
@Indexed
public @interface SpringBootConfiguration {
    
    @AliasFor(annotation = Configuration.class)
    boolean proxyBeanMethods() default true;
}
```


### `@EnableAutoConfiguration`实现自动配置
为了更好的了解`@EnableAutoConfiguration`的功能，先考虑下这几个问题？</br>
1.`@EnableAutoConfiguration`是如何找到自动配置的`内容`</br>
2.如果想要被`@EnableAutoConfiguration`自动配置需要做哪些`事情`
---
现在来看看@EnableAutoConfiguration的代码实现
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

+ 带有`@Configuration`注解的配置类,4.2版本前只能导入配置类，4.2后也可以导入普通类
+ ImportSelector的实现
+ ImportBeanDefinitionRegistrar的实现

借助`@Import(AutoConfigurationImportSelector.class)`, `AutoConfigurationImportSelector`可以帮助SpringBoot应用将所有符合条件的`@Configuration`配置都加载到当前SpringBoot创建并使用
    