package com.taoswork.tallycheck.tallyuser.servo;

import com.taoswork.tallycheck.tallyuser.EncryptPasswordHelper;
import com.taoswork.tallycheck.tallyuser.UserCertificationService;
import org.junit.Assert;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Gao Yuan on 2016/6/27.
 */
public class DemoServoTest {
    private static ClassPathXmlApplicationContext providerContext;

    @BeforeClass
    public static void setup() {
        providerContext =
                new ClassPathXmlApplicationContext("dubbo-tallyuser-demo-servo.xml");
        providerContext.start();
        System.out.println("context started");
    }

    @Test
    public void xxx() {
        try {
            String adminId = "000000000000000000000000";
            UserCertificationService userCertificationService = (UserCertificationService) providerContext.getBean("tallyuserService");
            String encryptedAbcd = EncryptPasswordHelper.encrypt(userCertificationService.getPasswordSetSpec(), "abcd");
            String encryptedAdmin = EncryptPasswordHelper.encrypt(userCertificationService.getPasswordSetSpec(), "admin");
            boolean setok = false;
            boolean checkok = false;

            checkok = userCertificationService.checkPassword(adminId, "admin");
            Assert.assertTrue(checkok);
            checkok = userCertificationService.checkPassword(adminId, "admin1");
            Assert.assertFalse(checkok);
            checkok = userCertificationService.checkPassword("100000000000000000000001", "admin");
            Assert.assertFalse(checkok);
            checkok = userCertificationService.checkPassword("100000000000000000000001", "admin1");
            Assert.assertFalse(checkok);

            setok = userCertificationService.setPassword(adminId, "", encryptedAbcd);
            Assert.assertFalse(setok);
            setok = userCertificationService.setPassword(adminId, "admin", encryptedAbcd);
            Assert.assertTrue(setok);
            checkok = userCertificationService.checkPassword(adminId, "abcd");
            Assert.assertTrue(checkok);
            checkok = userCertificationService.checkPassword(adminId, "admin");
            Assert.assertFalse(checkok);
            setok = userCertificationService.setPassword(adminId, "abcd", encryptedAdmin);
            Assert.assertTrue(setok);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @AfterClass
    public static void teardown() {
        providerContext = null;
        System.out.println("context stop");
    }
}
