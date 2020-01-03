package com.centit.workorder.config;

import com.centit.fileserver.common.FileStore;
import com.centit.fileserver.utils.OsFileStore;
import com.centit.framework.components.impl.NotificationCenterImpl;
import com.centit.framework.components.impl.TextOperationLogWriterImpl;
import com.centit.framework.config.InitialWebRuntimeEnvironment;
import com.centit.framework.config.SpringSecurityDaoConfig;
import com.centit.framework.ip.app.config.IPOrStaticAppSystemBeanConfig;
import com.centit.framework.jdbc.config.JdbcConfig;
import com.centit.framework.model.adapter.NotificationCenter;
import com.centit.framework.model.adapter.OperationLogWriter;
import com.centit.framework.security.model.StandardPasswordEncoderImpl;
import com.centit.search.document.FileDocument;
import com.centit.search.service.ESServerConfig;
import com.centit.search.service.Impl.ESIndexer;
import com.centit.search.service.Impl.ESSearcher;
import com.centit.search.service.IndexerSearcherFactory;
import com.centit.support.algorithm.NumberBaseOpt;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

/**
 * Created by codefan on 17-7-18.
 */
@ComponentScan(basePackages = "com.centit",
        excludeFilters = @ComponentScan.Filter(value = org.springframework.stereotype.Controller.class))
@Import({IPOrStaticAppSystemBeanConfig.class,
        JdbcConfig.class,
        SpringSecurityDaoConfig.class})
@Configuration
public class ServiceConfig {

    @Autowired
    private Environment env;

    @Bean("passwordEncoder")
    public StandardPasswordEncoderImpl passwordEncoder() {
        return  new StandardPasswordEncoderImpl();
    }

    @Bean
    @Lazy(value = false)
    public InitialWebRuntimeEnvironment initialEnvironment() {
        InitialWebRuntimeEnvironment initialEnvironment = new InitialWebRuntimeEnvironment();
        initialEnvironment.initialEnvironment();
        return initialEnvironment;
    }

    @Bean
    public ESServerConfig esServerConfig(){
        ESServerConfig config = new ESServerConfig();
        config.setServerHostIp(this.env.getProperty("workorder.elasticsearch.server.ip"));
        config.setServerHostPort(this.env.getProperty("workorder.elasticsearch.server.port"));
        config.setClusterName(this.env.getProperty("workorder.elasticsearch.server.cluster"));
        config.setOsId(this.env.getProperty("workorder.elasticsearch.osId"));
        config.setMinScore(NumberBaseOpt.parseFloat(this.env.getProperty("workorder.elasticSearch.minScore"), 0.5F));
        return config;
    }

    @Bean(name = "esIndexer")
    public ESIndexer esIndexer(@Autowired ESServerConfig esServerConfig){
        return IndexerSearcherFactory.obtainIndexer(
            esServerConfig, FileDocument.class);
    }

    @Bean(name = "esSearcher")
    public ESSearcher esSearcher(@Autowired ESServerConfig esServerConfig){
        return IndexerSearcherFactory.obtainSearcher(
            esServerConfig, FileDocument.class);
    }

    @Bean
    public FileStore fileStore(){
        //String fileStoreType= env.getProperty("filestore.type","os");
        String baseHome = env.getProperty("os.file.base.dir");
        if(StringUtils.isBlank(baseHome)) {
            baseHome = env.getProperty("app.home") + "/upload";
        }
        return new OsFileStore(baseHome);
    }

    @Bean
    public NotificationCenter notificationCenter() {
        NotificationCenterImpl notificationCenter = new NotificationCenterImpl();
        //notificationCenter.initMsgSenders();
        notificationCenter.initDummyMsgSenders();
        //notificationCenter.registerMessageSender("innerMsg",innerMessageManager);
        return notificationCenter;
    }

    @Bean
    @Lazy(value = false)
    public OperationLogWriter operationLogWriter() {
        TextOperationLogWriterImpl operationLog = new TextOperationLogWriterImpl();
        operationLog.init();
        return operationLog;
    }

   @Bean
   public InstantiationServiceBeanPostProcessor instantiationServiceBeanPostProcessor() {
       return new InstantiationServiceBeanPostProcessor();
   }
}

