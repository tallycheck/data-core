package com.taoswork.tallycheck.tallyadmin.authority;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Gao Yuan on 2016/6/27.
 */
public class AuthorityServoTest {
    private static ClassPathXmlApplicationContext providerContext;

    @BeforeClass
    public static void setup() {
        providerContext =
                new ClassPathXmlApplicationContext("META-INF/spring/dubbo-tallyadmin-authority-servo.xml");
        providerContext.start();
        System.out.println("context started");
    }

    @Test
    public void testTallyUserCertService() {
        AdminAuthorityProvider provider = (AdminAuthorityProvider) providerContext.getBean("adminAuthorityProvider");
        Assert.assertNotNull(provider);
    }

    @AfterClass
    public static void teardown() {
        providerContext.close();
        providerContext = null;
        System.out.println("context stop");
    }
}