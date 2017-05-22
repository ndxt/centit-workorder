package com.centit.workorder.config;

import com.centit.framework.filter.RequestThreadLocalFilter;
import com.centit.framework.filter.ResponseCorsFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.util.Log4jConfigListener;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

/**
 * Created by zou_wy on 2017/3/29.
 */


public class WebInitializer implements WebApplicationInitializer {


    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        initParameter(servletContext);

        initializeSpringMvcConfig(servletContext);

        registListener(servletContext);

        registFilter(servletContext);
    }

    private void initParameter(ServletContext servletContext) {
        servletContext.setInitParameter("webAppRootKey", "workOrder");
    }

    private void initializeSpringConfig(ServletContext servletContext){
        AnnotationConfigWebApplicationContext springContext = new AnnotationConfigWebApplicationContext();
        springContext.register(SpringConfig.class);
        servletContext.addListener(new ContextLoaderListener(springContext));
    }

    private void initializeSpringMvcConfig(ServletContext servletContext) {
//        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
//        context.register(SystemSpringMvcConfig.class);
//        context.setServletContext(servletContext);
//        Dynamic system  = servletContext.addServlet("system", new DispatcherServlet(context));
//        system.addMapping("/system/*");
//        system.setLoadOnStartup(1);
//        system.setAsyncSupported(true);

        AnnotationConfigWebApplicationContext contextSer = new AnnotationConfigWebApplicationContext();
        contextSer.register(NormalSpringMvcConfig.class);
        contextSer.setServletContext(servletContext);
        Dynamic service  = servletContext.addServlet("service", new DispatcherServlet(contextSer));
        service.addMapping("/service/*");
        service.setLoadOnStartup(1);
        service.setAsyncSupported(true);
    }

    private void registListener(ServletContext servletContext) {
        servletContext.setInitParameter("log4jConfigLocation", "classpath:log4j.properties");
        servletContext.setInitParameter("log4jRefreshInterval", "60000");
        servletContext.addListener(Log4jConfigListener.class);

        servletContext.addListener(RequestContextListener.class);
    }

    private void registFilter(ServletContext servletContext) {
        javax.servlet.FilterRegistration.Dynamic corsFilter = servletContext.addFilter("corsFilter", ResponseCorsFilter.class);
        corsFilter.addMappingForUrlPatterns(null, false, "/service/*");

        javax.servlet.FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("encodingFilter", CharacterEncodingFilter.class);
        encodingFilter.addMappingForUrlPatterns(null, false, "*.jsp", "*.html", "/service/*", "/system/*");
        encodingFilter.setAsyncSupported(true);
        encodingFilter.setInitParameter("encoding", "UTF-8");
        encodingFilter.setInitParameter("forceEncoding", "true");

        javax.servlet.FilterRegistration.Dynamic hiddenHttpMethodFilter = servletContext.addFilter("hiddenHttpMethodFilter", HiddenHttpMethodFilter.class);
        hiddenHttpMethodFilter.addMappingForUrlPatterns(null, false, "/service", "/system");
        hiddenHttpMethodFilter.setAsyncSupported(true);

        javax.servlet.FilterRegistration.Dynamic requestThreadLocalFilter = servletContext.addFilter("requestThreadLocalFilter", RequestThreadLocalFilter.class);
        requestThreadLocalFilter.addMappingForUrlPatterns(null, false, "/*");
        requestThreadLocalFilter.setAsyncSupported(true);

        javax.servlet.FilterRegistration.Dynamic springSecurityFilterChain = servletContext.addFilter("springSecurityFilterChain", DelegatingFilterProxy.class);
        springSecurityFilterChain.addMappingForUrlPatterns(null, false, "/login" ,"/logout", "/service", "/system");
        springSecurityFilterChain.setAsyncSupported(true);
    }
}
