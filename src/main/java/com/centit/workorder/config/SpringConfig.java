package com.centit.workorder.config;

import com.centit.framework.components.impl.NotificationCenterImpl;
import com.centit.framework.listener.InitialWebRuntimeEnvironment;
import com.centit.framework.security.model.CentitMd5PasswordEncoder;
import com.centit.framework.staticsystem.service.impl.StaticSystemConfigImpl;
import com.centit.framework.staticsystem.service.impl.TextOperationLogWriterImpl;
import org.apache.commons.dbcp2.BasicDataSource;
import org.flywaydb.core.Flyway;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)//启用注解事物管理
@ComponentScan(basePackages = {"com.centit.*"})
@PropertySource("classpath:/system.properties")
@Import({SpringSecurityDaoConfig.class, SpringSecurityCasConfig.class})
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Lazy
public class SpringConfig implements EnvironmentAware {

    @Autowired
    Environment env;

    @Override
    public void setEnvironment(final Environment environment) {
        this.env = environment;
    }

    @Bean(destroyMethod = "close")
    public BasicDataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(env.getProperty("jdbc.driver"));
        dataSource.setUrl(env.getProperty("jdbc.url"));
        dataSource.setUsername(env.getProperty("jdbc.user"));
        dataSource.setPassword(env.getProperty("jdbc.password"));
        dataSource.setMaxTotal(env.getProperty("jdbc.maxActive", Integer.class));
        dataSource.setMaxIdle(env.getProperty("jdbc.maxIdle", Integer.class));
        dataSource.setMaxWaitMillis(env.getProperty("jdbc.maxWait", Integer.class));
        dataSource.setDefaultAutoCommit(env.getProperty("jdbc.defaultAutoCommit", Boolean.class));
        dataSource.setRemoveAbandonedTimeout(env.getProperty("jdbc.removeAbandonedTimeout", Integer.class));
        return dataSource;
    }

    @Bean(initMethod = "migrate")
    @Lazy(value = false)
    public Flyway flyway() {
        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource());
        flyway.setBaselineOnMigrate(true);
        flyway.setLocations(env.getProperty("flyway.sql.dir"),"com.centit.framework.system.update");
        return flyway;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", env.getProperty("jdbc.dialect"));
        hibernateProperties.put("hibernate.show_sql", true);
        hibernateProperties.put("hibernate.id.new_generator_mappings", true);
        sessionFactory.setHibernateProperties(hibernateProperties);
        String[] packagesToScan = new String[]{"com.centit.*"};
        sessionFactory.setPackagesToScan(packagesToScan);
        return  sessionFactory;
    }

    @Bean
    @DependsOn("flyway")
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor() {
        return new AutowiredAnnotationBeanPostProcessor();
    }

    @Bean(initMethod = "initialEnvironment")
    @Lazy(value = false)
    public InitialWebRuntimeEnvironment initialEnvironment() {
        return new InitialWebRuntimeEnvironment();
    }

    @Bean(initMethod = "init")
    @Lazy(value = false)
    public TextOperationLogWriterImpl operationLogWriter() {
        return new TextOperationLogWriterImpl();
    }

    @Bean(initMethod = "initMsgSenders")
    public NotificationCenterImpl notificationCenter() {
        return new NotificationCenterImpl();
    }

    @Bean(initMethod = "init")
    @Lazy(value = false)
    public StaticSystemConfigImpl platformEnvironment() {
        StaticSystemConfigImpl platformEnvironment = new StaticSystemConfigImpl();
        platformEnvironment.setPasswordEncoder(passwordEncoder());
        return platformEnvironment;
    }

    @Bean
    public CentitMd5PasswordEncoder passwordEncoder() {
        return  new CentitMd5PasswordEncoder();
    }

    @Bean
    public HttpSessionCsrfTokenRepository csrfTokenRepository() {
        return new HttpSessionCsrfTokenRepository();
    }

}
