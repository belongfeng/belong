package com.belong.common.util.json;

import com.alibaba.fastjson.JSON;
import com.belong.common.util.StringUtils;

import java.util.Map;

/**
 * @Description: JSON转换工具类
 * @Author: fengyu
 * @CreateDate: 2019/12/16 14:40
 */
public class JSONUtils {

    /**
     * Bean对象转JSON
     *
     * @param object
     * @return
     */
    public static String beanToJson(Object object) {
        if (object != null) {
            return JSON.toJSONString(object);
        } else {
            return null;
        }
    }


    /**
     * json字符串转map
     *
     * @param json
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> jsonToMap(String json) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        return JSON.parseObject(json, Map.class);
    }
}
