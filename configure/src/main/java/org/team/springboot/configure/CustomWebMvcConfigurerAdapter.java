package org.team.springboot.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author zhangds
 * @date 2024/7/6
 * @Notes 标注此文件为一个配置项，spring boot才会扫描到该配置。该注解类似于之前使用xml进行配置
 **/

@Configuration
public class CustomWebMvcConfigurerAdapter implements WebMvcConfigurer {

    @Resource
    WebInterceptor webInterceptor;

//    @Autowired
//    AccessLimitInterceptor accessLimitInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(webInterceptor).excludePathPatterns("/","/error","/static/**","/swagger**/**","/webjars/springfox**/**").addPathPatterns("/**");
//        registry.addInterceptor(accessLimitInterceptor);

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

}
