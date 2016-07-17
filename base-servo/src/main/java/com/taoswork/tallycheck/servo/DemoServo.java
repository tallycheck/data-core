package com.taoswork.tallycheck.servo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by gaoyuan on 7/15/16.
 */
public class DemoServo {

    public static void runServo(String configXml)throws IOException {
        String tallyDebug = System.getProperty("tally.debug");
        if (!"true".equals(tallyDebug)) {
            return;
        }

        ClassPathXmlApplicationContext providerContext =
                new ClassPathXmlApplicationContext(configXml);
        providerContext.start();
        System.out.println("context started");

        System.in.read();

        providerContext.close();
        System.out.println("context stop");

    }
}
