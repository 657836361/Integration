package com.camelot.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.camelot.robot.interceptor.SignCheckInterceptor;

/**
 * <p>
 * Description:[web全局配置]
 * </p>
 *
 * @version 1.0
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    @Autowired
    private SignCheckInterceptor signCheckInterceptor;

    /**
     * <p>
     * Description:[全局资源配置]
     * </p>
     *
     * @param registry
     * @return void
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns 用于添加拦截规则
        registry.addInterceptor(signCheckInterceptor).addPathPatterns("/recieve/**");
    }

    /**
     * <p>
     * Description:[全局跨域配置]
     * </p>
     * 
     * @param registry
     * @return void
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*")
            .allowedMethods("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH").allowCredentials(true);
    }

}
