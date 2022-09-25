package com.moxuanran.learning.enbale;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * @author 莫轩然(wutao07)
 * @date 2022/9/25 下午7:44
 */
@Configuration
@EnableServer(type = Server.Type.HTTP)
public class EnableServerBootstrap {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(EnableServerBootstrap.class);
        context.refresh();
        Server server = context.getBean(Server.class);
        server.start();
        server.stop();
    }
}
