package com.centit.workorder.config;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Properties;

/**
 * Created by zou_wy on 2017/3/29.
 */
@Configuration
@EnableWebMvc
//@ComponentScan(basePackages = "com.centit.msgdlvry.controller")
public class WebConfig extends WebMvcConfigurerAdapter {
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(org.springframework.web.servlet.view.JstlView.class);
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Bean
    public PropertyPlaceholderConfigurer propertyConfigurer() {
        PropertyPlaceholderConfigurer propertyConfigurer = new PropertyPlaceholderConfigurer();
        propertyConfigurer.setLocation(new ClassPathResource("system.properties"));
        return propertyConfigurer;
    }

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messagesource/base/messages");
        Properties properties = new Properties();
        properties.put("fileEncodings", "utf-8");
        messageSource.setFileEncodings(properties);
        messageSource.setCacheSeconds(120);
        return messageSource;
    }

    @Bean
    public FormattingConversionServiceFactoryBean conversionService() {
        return new FormattingConversionServiceFactoryBean();
    }

    @Bean
    public ConfigurableWebBindingInitializer webBindingInitializer() {
        ConfigurableWebBindingInitializer webBindingInitializer = new ConfigurableWebBindingInitializer();
        webBindingInitializer.setConversionService(conversionService().getObject());
        return webBindingInitializer;
    }
}
