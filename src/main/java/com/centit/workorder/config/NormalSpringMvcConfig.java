package com.centit.workorder.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by zou_wy on 2017/3/29.
 */
@Configuration
@Import(WebConfig.class)
@ComponentScan(basePackages = {"com.centit.fileserver.controller","com.centit.filetransfer.controller"},
        includeFilters = {@ComponentScan.Filter(type= FilterType.ANNOTATION,value= org.springframework.stereotype.Controller.class)},
        useDefaultFilters = false)
public class NormalSpringMvcConfig extends WebMvcConfigurerAdapter {

}
