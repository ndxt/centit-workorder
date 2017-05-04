package com.centit.workorder.config;

import com.centit.framework.security.*;
import com.centit.framework.security.model.MemorySessionRegistryImpl;
import com.centit.framework.staticsystem.service.impl.StaticSystemConfigImpl;
import com.centit.framework.staticsystem.service.impl.UserDetailsServiceImpl;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.validation.Cas20ServiceTicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zou_wy on 2017/3/29.
 */
@EnableWebSecurity
@Conditional(SecurityCasCondition.class)
public class SpringSecurityCasConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private StaticSystemConfigImpl platformEnvironment;

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 设置不拦截规则
        web.ignoring().antMatchers("/system/login", "/service/exception/**", "/system/login/**");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .logout()
                .logoutSuccessUrl("/index.jsp")
                .and()
            .exceptionHandling().accessDeniedPage("/service/exception/accessDenied")
                .and()
            .httpBasic()
                .authenticationEntryPoint(casEntryPoint());
        http.addFilterAt(casFilter(), UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(centitPowerFilter(), FilterSecurityInterceptor.class)
            .addFilterBefore(requestSingleLogoutFilter(), LogoutFilter.class)
            .addFilterBefore(singleLogoutFilter(), CasAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.authenticationProvider(casAuthenticationProvider());

    }

    @Bean
    public CasAuthenticationEntryPoint casEntryPoint() {
        CasAuthenticationEntryPoint casEntryPoint = new CasAuthenticationEntryPoint();
        casEntryPoint.setLoginUrl("https://product.centit.com:8443/cas/login");
        casEntryPoint.setServiceProperties(casServiceProperties());
        return casEntryPoint;
    }

    @Bean
    public ServiceProperties casServiceProperties() {
        ServiceProperties casServiceProperties = new ServiceProperties();
        casServiceProperties.setService("http://codefanpc:8180/msg-delivery/login/cas");
        casServiceProperties.setSendRenew(false);
        return casServiceProperties;
    }

    @Bean
    public UserDetailsServiceImpl userDetailsService() {
        UserDetailsServiceImpl userDetailsService = new UserDetailsServiceImpl();
        userDetailsService.setPlatformEnvironment(platformEnvironment);
        return userDetailsService;
    }

    @Bean
    public CasAuthenticationProvider casAuthenticationProvider() {
        CasAuthenticationProvider casAuthenticationProvider = new CasAuthenticationProvider();
        casAuthenticationProvider.setUserDetailsService(userDetailsService());
        casAuthenticationProvider.setServiceProperties(casServiceProperties());
        casAuthenticationProvider.setTicketValidator(new Cas20ServiceTicketValidator("https://product.centit.com:8443/cas"));
        casAuthenticationProvider.setKey("centit");
        return casAuthenticationProvider;
    }


    @Bean
    public DaoAccessDecisionManager centitAccessDecisionManagerBean() {
        return new DaoAccessDecisionManager();
    }

    @Bean
    public DaoInvocationSecurityMetadataSource centitSecurityMetadataSource() {
        return new DaoInvocationSecurityMetadataSource();
    }

    @Bean
    public MemorySessionRegistryImpl centitSessionRegistry() {
        return new MemorySessionRegistryImpl();
    }

    @Bean
    public DaoFilterSecurityInterceptor centitPowerFilter() {
        DaoFilterSecurityInterceptor centitPowerFilter = new DaoFilterSecurityInterceptor();
        centitPowerFilter.setAuthenticationManager(authenticationManager());
        centitPowerFilter.setAccessDecisionManager(centitAccessDecisionManagerBean());
        centitPowerFilter.setSecurityMetadataSource(centitSecurityMetadataSource());
        centitPowerFilter.setSessionRegistry(centitSessionRegistry());
        return centitPowerFilter;
    }

    @Bean
    public AjaxAuthenticationFailureHandler ajaxFailureHandler() {
        AjaxAuthenticationFailureHandler ajaxFailureHandler = new AjaxAuthenticationFailureHandler();
        ajaxFailureHandler.setDefaultFailureUrl("/system/mainframe/login/error");
        ajaxFailureHandler.setWriteLog(false);
        return ajaxFailureHandler;
    }

    @Bean
    public AjaxAuthenticationSuccessHandler ajaxSuccessHandler() {
        AjaxAuthenticationSuccessHandler ajaxSuccessHandler = new AjaxAuthenticationSuccessHandler();
        ajaxSuccessHandler.setWriteLog(true);
        return ajaxSuccessHandler;
    }

    @Bean
    public CasAuthenticationFilter casFilter() {
        CasAuthenticationFilter casFilter = new CasAuthenticationFilter();
        casFilter.setAuthenticationManager(authenticationManager());
        casFilter.setAuthenticationFailureHandler(ajaxFailureHandler());
        casFilter.setAuthenticationSuccessHandler(ajaxSuccessHandler());
        return casFilter;
    }

    @Bean
    public SingleSignOutFilter singleLogoutFilter() {
        SingleSignOutFilter singleLogoutFilter = new SingleSignOutFilter();
        singleLogoutFilter.setCasServerUrlPrefix("https://product.centit.com:8443/cas");
        return singleLogoutFilter;
    }

    @Bean
    public LogoutFilter requestSingleLogoutFilter() {
        return new LogoutFilter("https://product.centit.com:8443/cas/logout", new SecurityContextLogoutHandler());
    }

    @Bean
    public ProviderManager authenticationManager() {
        List<AuthenticationProvider> providerList = new ArrayList<AuthenticationProvider>();
        providerList.add(casAuthenticationProvider());
        return new ProviderManager(providerList);
    }

}
