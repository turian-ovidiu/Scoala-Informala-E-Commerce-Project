package com.Ecommerce.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Turian Ovidiu.
 * This class extends WebMvcConfigurerAdapter and add resource handler configuration.
 */
@Configuration
public class MvcConfiguration extends WebMvcConfigurerAdapter {

    @Value("${local.files.dir}")
    private String localFilesDir;



    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/ext-img/**")
                .addResourceLocations("file:///"+localFilesDir)
                .setCachePeriod(0);
    }


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }
}
