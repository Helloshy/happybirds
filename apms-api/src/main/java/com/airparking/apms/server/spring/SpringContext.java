package com.airparking.apms.server.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by ryan on 12/31/15.
 */
public class SpringContext {
    private static final Logger logger = LoggerFactory.getLogger(SpringContext.class);

    private static AbstractApplicationContext context;

    public synchronized static AbstractApplicationContext getContext() {
        if (context == null) {
            context = new ClassPathXmlApplicationContext("classpath:/spring/**/spring*.xml");
            context.registerShutdownHook();
        }
        return context;
    }
}
