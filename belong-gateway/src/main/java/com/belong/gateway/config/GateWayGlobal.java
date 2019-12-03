package com.belong.gateway.config;

import com.belong.common.util.StringUtils;
import com.belong.common.util.YamlUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 全局读取配置
 * @Author: fengyu
 * @CreateDate: 2019/11/27 10:21
 * @UpdateUser: fengyu
 * @UpdateDate: 2019/11/27 10:21
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Configuration
@Slf4j
public class GateWayGlobal {

    private static String NAME = "application.yml";

    /**
     * 当前对象实例
     */
    private static GateWayGlobal gateWayGlobal;
    private static String env;

    public static String getEnv() {
        return env;
    }

    @Value("${spring.profiles.active}")
    public void setEnv(String env) {
        GateWayGlobal.env = env;
    }

    /**
     * 保存全局属性值
     */
    private static Map<String, String> map = new HashMap<String, String>();

    public GateWayGlobal() {
    }

    /**
     * 静态工厂方法
     */
    public static synchronized GateWayGlobal getInstance() {
        if (gateWayGlobal == null) {
            gateWayGlobal = new GateWayGlobal();
        }
        return gateWayGlobal;
    }

    /**
     * 获取配置
     */
    public static String getConfig(String key) {
        String value = map.get(key);
        if (value == null) {
            Map<?, ?> yamlMap = null;
            try {
                if (StringUtils.isNotNull(env) && StringUtils.isNotEmpty(env)) {
                    NAME = "application-" + env + ".yml";
                    log.debug("配置环境为：" + NAME);
                }
                yamlMap = YamlUtil.loadYaml(NAME);
                value = String.valueOf(YamlUtil.getProperty(yamlMap, key));
                map.put(key, value != null ? value : StringUtils.EMPTY);
            } catch (FileNotFoundException e) {
                log.error("获取全局配置异常 {}", key);
            }
        }
        return value;
    }

    /**
     * 方法实现说明: 获取全局参数加密key
     *
     * @param
     * @return java.lang.String
     * @throws
     * @author fengyu
     * @date 2019/9/16 11:32
     */
    public static String getParameterKey() {
        return StringUtils.nvl(getConfig("panda.aes.parameter.key"), "xlmParameter6HaF");
    }

    /**
     * 方法实现说明: 获取全局参数配偏移量
     *
     * @param
     * @return java.lang.String
     * @throws
     * @author fengyu
     * @date 2019/9/16 11:32
     */
    public static String getParameterIv() {
        return StringUtils.nvl(getConfig("panda.aes.parameter.iv"), "FY9BQGaFk0QBv6aF");
    }

    /**
     * 方法实现说明: 获取访问接口的时间限制
     *
     * @param
     * @return java.lang.String
     * @throws
     * @author fengyu
     * @date 2019/9/17 9:43
     */
    public static String getLimitTime() {
        return StringUtils.nvl(getConfig("panda.limit.time"), "8000");
    }

    /**
     * 方法实现说明:获取单位时间内接口访问次数
     *
     * @param
     * @return java.lang.String
     * @throws
     * @author fengyu
     * @date 2019/9/17 9:43
     */
    public static String getLimitNumber() {

        return StringUtils.nvl(getConfig("panda.limit.number"), "8");
    }
}
