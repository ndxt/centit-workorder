package com.centit.workorder.config;

import com.centit.framework.security.*;
import com.centit.framework.security.model.CentitMd5PasswordEncoder;
import com.centit.framework.security.model.MemorySessionRegistryImpl;
import com.centit.framework.staticsystem.service.impl.StaticSystemConfigImpl;
import com.centit.framework.staticsystem.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.csrf.CsrfLogoutHandler;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zou_wy on 2017/3/29.
 */
@EnableWebSecurity
@Conditional(SecurityDaoCondition.class)
public class SpringSecurityDaoConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private HttpSessionCsrfTokenRepository csrfTokenRepository;

    @Autowired
    private CentitMd5PasswordEncoder passwordEncoder;

    @Autowired
    private StaticSystemConfigImpl platformEnvironment;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
                .antMatchers("/system/mainframe/login","/system/mainframe/csrf","/system/exception")
                    .access("IS_AUTHENTICATED_ANONYMOUSLY")
                .and()
                    .exceptionHandling().accessDeniedPage("/system/exception/accessDenied")
                .and()
                    .httpBasic()
                        .authenticationEntryPoint(authenticationEntryPoint());
        http.addFilterAt(pretreatmentAuthenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(centitPowerFilter(), FilterSecurityInterceptor.class)
            .addFilterAt(logoutFilter(), LogoutFilter.class);
    }

    @Bean
    public LoginUrlAuthenticationEntryPoint authenticationEntryPoint() {
        return new LoginUrlAuthenticationEntryPoint("/system/mainframe/login");
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
    public PretreatmentAuthenticationProcessingFilter pretreatmentAuthenticationProcessingFilter() {

        PretreatmentAuthenticationProcessingFilter pretreatmentAuthenticationProcessingFilter = new PretreatmentAuthenticationProcessingFilter();
        pretreatmentAuthenticationProcessingFilter.setAuthenticationManager(authenticationManager());
        pretreatmentAuthenticationProcessingFilter.setCheckCaptcha(false);
        pretreatmentAuthenticationProcessingFilter.setMaxTryTimes(0);
        pretreatmentAuthenticationProcessingFilter.setCheckTimeTnterval(3);
        pretreatmentAuthenticationProcessingFilter.setCheckType("loginName");
        pretreatmentAuthenticationProcessingFilter.setLockMinites(3);
        pretreatmentAuthenticationProcessingFilter.setContinueChainBeforeSuccessfulAuthentication(false);
        pretreatmentAuthenticationProcessingFilter.setAuthenticationFailureHandler(ajaxFailureHandler());
        pretreatmentAuthenticationProcessingFilter.setAuthenticationSuccessHandler(ajaxSuccessHandler());
        return pretreatmentAuthenticationProcessingFilter;
    }

    @Bean
    public LogoutFilter logoutFilter() {
        return new LogoutFilter("/system/mainframe/login",new CsrfLogoutHandler(csrfTokenRepository),new CookieClearingLogoutHandler("JSESSIONID","remember-me"),new SecurityContextLogoutHandler());
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setHideUserNotFoundExceptions(false);
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        authenticationProvider.setSaltSource(saltSource());
        return authenticationProvider;
    }

    @Bean
    public UserDetailsServiceImpl userDetailsService() {
        UserDetailsServiceImpl userDetailsService = new UserDetailsServiceImpl();
        userDetailsService.setPlatformEnvironment(platformEnvironment);
        return userDetailsService;
    }

    @Bean
    public ReflectionSaltSource saltSource() {
        ReflectionSaltSource saltSource = new ReflectionSaltSource();
        saltSource.setUserPropertyToUse("userCode");
        return saltSource;
    }

    @Bean
    public ProviderManager authenticationManager() {
        List<AuthenticationProvider> providerList = new ArrayList<AuthenticationProvider>();
        providerList.add(authenticationProvider());
        return new ProviderManager(providerList);
    }

}
