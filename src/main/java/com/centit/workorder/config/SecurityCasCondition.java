package com.centit.workorder.config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Created by zou_wy on 2017/4/18.
 */
public class SecurityCasCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment evn = context.getEnvironment();
        return Boolean.parseBoolean(evn.getProperty("cas.sso"));
    }
}
