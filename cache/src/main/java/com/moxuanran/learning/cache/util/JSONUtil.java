package com.moxuanran.learning.cache.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JSONUtil {

    public static final ParserConfig PARSER_CONFIG = new ParserConfig();

    static {
        PARSER_CONFIG.setAutoTypeSupport(true);
    }

    public static <T> T clone(T bean) {
        return JSON.parseObject(JSON.toJSONString(bean, SerializerFeature.WriteClassName), Object.class, PARSER_CONFIG);
    }

}
