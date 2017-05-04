package com.centit.workorder.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zou_wy on 2017/3/29.
 */
@Configuration
//@Import(WebConfig.class)
@ComponentScan(basePackages = {"com.centit.msgdlvry.controller"})
public class SystemSpringMvcConfig extends WebConfig {
}
