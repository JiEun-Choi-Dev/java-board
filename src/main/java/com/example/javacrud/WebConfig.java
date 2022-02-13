package com.example.javacrud;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableSpringDataWebSupport
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String staticPath = System.getProperty("user.dir");
        registry.addResourceHandler("/static/**")
                .addResourceLocations("file:///"+ staticPath + "/src/main/resources/static/")
                .setCachePeriod(0);
    }
}
