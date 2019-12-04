package com.belong.common.core.constant;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @Classname PermitAllUrl
 * @Description TODO
 * @Date 2019/12/4 15:55
 * @Created by FengYu
 */
public final class PermitAllUrl {

    /**
     * 监控中心和swagger需要访问的url
     */
    private static final String[] ENDPOINTS = {"/actuator/health", "/actuator/env", "/actuator/metrics/**", "/actuator/trace", "/actuator/dump",
            "/actuator/jolokia", "/actuator/info", "/actuator/logfile", "/actuator/refresh", "/actuator/flyway", "/actuator/liquibase",
            "/actuator/heapdump", "/actuator/loggers", "/actuator/auditevents", "/actuator/env/PID", "/actuator/jolokia/**", "/actuator", "/actuator/**",
            "/v2/api-docs/**", "/instances/**", "/swagger-ui.html", "/swagger-resources/**", "/webjars/**", "/v2/api-docs-ext", "/test/**", "/favicon.ico", "/doc.html"};

    /**
     * 需要放开权限的url
     *
     * @param urls 自定义的url
     * @return 自定义的url和监控中心需要访问的url集合
     */
    public static String[] permitAllUrl(String... urls) {
        if (urls == null || urls.length == 0) {
            return ENDPOINTS.clone();
        }

        Set<String> set = new HashSet<>();
        Collections.addAll(set, ENDPOINTS);
        Collections.addAll(set, urls);

        return set.toArray(new String[set.size()]);
    }
}
