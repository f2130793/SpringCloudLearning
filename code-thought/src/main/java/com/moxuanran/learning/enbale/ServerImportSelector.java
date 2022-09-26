package com.moxuanran.learning.enbale;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

/**
 * @author 莫轩然(wutao07)
 * @date 2022/9/25 下午7:38
 */
@Slf4j
public class ServerImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        Map<String, Object> annotationAttributes = annotationMetadata.getAnnotationAttributes(EnableServer.class.getName());
        Server.Type type = (Server.Type) annotationAttributes.get("type");
        String[] importClassNames = new String[0];
        switch (type) {
            case HTTP:
                importClassNames = new String[]{HttpServer.class.getName()};
                break;
            case FTP:
                importClassNames = new String[]{FtpServer.class.getName()};
                break;
            default:
                log.warn("没有可用的Server服务");
                break;
        }
        return importClassNames;
    }
}
