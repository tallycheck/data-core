package com.taoswork.tallycheck.servo.tallyuser;

import com.taoswork.tallycheck.dataservice.SecurityAccessor;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by gaoyuan on 7/10/16.
 */
public class DemoDebugServoTest {

    @Test
    public void runAsMain() throws IOException {
        String mavenDebug = System.getProperty("maven.surefire.debug");
        if (!"true".equals(mavenDebug)) {
            return;
        }

        String tallyDebug = System.getProperty("tally.debug");
        if (!"true".equals(tallyDebug)) {
            return;
        }
        final String ADMIN_ID = "000000000000000000000000";
        SecurityAccessor accessor = new SecurityAccessor();

        ClassPathXmlApplicationContext providerContext =
                new ClassPathXmlApplicationContext("dubbo-tallyuser-demo-servo.xml");
        providerContext.start();
        System.out.println("context started");

        System.in.read();

        providerContext.close();
        System.out.println("context stop");
    }
}
