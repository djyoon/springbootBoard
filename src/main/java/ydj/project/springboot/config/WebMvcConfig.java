package ydj.project.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ydj.project.springboot.interceptor.HttpSessionChecker;

/**
 * Created by djyoon on 2018-11-28.
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private HttpSessionChecker httpSessionChecker;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(httpSessionChecker)
                .addPathPatterns("/board/**")
                .excludePathPatterns("/board/list");
    }
}
