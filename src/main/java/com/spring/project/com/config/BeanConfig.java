package com.spring.project.com.config;

import com.spring.project.com.bean.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description 自定义一个config,了解它是如何被加载到Spring里的
 * @Date 2022/1/18 14:19
 * @Created by lianhai.deng
 */
@Configuration
public class BeanConfig {

    @Bean(initMethod = "init")
    private User getUser(String name, Integer age){
        User user = new User();
        user.setAge(age);
        user.setName(name);
        return user;
    }
}
