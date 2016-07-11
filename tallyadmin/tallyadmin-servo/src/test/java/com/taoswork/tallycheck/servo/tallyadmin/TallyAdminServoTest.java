package com.taoswork.tallycheck.servo.tallyadmin;

import com.taoswork.tallycheck.tallyuser.TallyUserDataService;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Gao Yuan on 2016/7/11.
 */
public class TallyAdminServoTest {
    private static ClassPathXmlApplicationContext providerContext;

    @BeforeClass
    public static void setup() {
        providerContext =
                new ClassPathXmlApplicationContext("dubbo-tallyadmin-demo-servo.xml");
        providerContext.start();
        System.out.println("context started");
    }

    @AfterClass
    public static void teardown() {
        providerContext.close();
        providerContext = null;
        System.out.println("context stop");
    }
}
