package com.airparking.apms;

import com.airparking.apms.server.container.Container;
import com.airparking.apms.server.spring.SpringContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class ApplicationMain
{
    private static final Logger logger = LoggerFactory.getLogger(ApplicationMain.class);
    static {
        Runtime.getRuntime().addShutdownHook(
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                    }
                }
        );
    }

    public static void main( String[] args ) throws IOException {
        try {
            SpringContext.getContext().getBean("nettyContainer", Container.class).start();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }

//        System.out.println("Press enter to end this application.");
//        System.in.read();
//        System.exit(0);
    }
}
