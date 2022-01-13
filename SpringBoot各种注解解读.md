### @EnableAutoConfiguration
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
    借助@Import,引入AutoConfigurationImportSelector收集和注册特定场景相关的bean定义，即实现自动配置功能
    