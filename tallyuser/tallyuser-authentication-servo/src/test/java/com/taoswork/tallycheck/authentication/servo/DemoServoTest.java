package com.taoswork.tallycheck.authentication.servo;

import com.taoswork.tallycheck.authentication.EncryptPasswordHelper;
import com.taoswork.tallycheck.authentication.UserAuthenticationService;
import com.taoswork.tallycheck.authority.provider.AllPassAuthorityProvider;
import com.taoswork.tallycheck.datasolution.tallyuser.TallyUserDataSolution;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Gao Yuan on 2016/6/27.
 */
public class DemoServoTest {
    private static ClassPathXmlApplicationContext providerContext;
    private static TallyUserDataSolution tallyUserDataSolution;
    private static UserAuthenticationService userAuthenticationService;
    private static final String ADMIN_ID = "000000000000000000000000";

    @BeforeClass
    public static void setup() {
        providerContext =
                new ClassPathXmlApplicationContext("dubbo-tallyuser-authentication-demo-servo.xml");
        providerContext.start();
        System.out.println("context started");
         tallyUserDataSolution = (TallyUserDataSolution)
                 providerContext.getBean("userAuthenticationDataSolution");
        tallyUserDataSolution.setAuthorityProvider(new AllPassAuthorityProvider());
        userAuthenticationService = (UserAuthenticationService)
                providerContext.getBean("userAuthenticationService");
    }

    @Test
    public void testTallyUserCertService() {
        try {
            String adminId = ADMIN_ID;
            String encryptedAbcd = EncryptPasswordHelper.encrypt(userAuthenticationService.getPasswordSetSpec(), "abcd");
            String encryptedAdmin = EncryptPasswordHelper.encrypt(userAuthenticationService.getPasswordSetSpec(), "admin");
            boolean setok = false;
            boolean checkok = false;

            checkok = userAuthenticationService.checkPassword(adminId, "admin");
            Assert.assertTrue(checkok);
            checkok = userAuthenticationService.checkPassword(adminId, "admin1");
            Assert.assertFalse(checkok);
            checkok = userAuthenticationService.checkPassword("100000000000000000000001", "admin");
            Assert.assertFalse(checkok);
            checkok = userAuthenticationService.checkPassword("100000000000000000000001", "admin1");
            Assert.assertFalse(checkok);

            setok = userAuthenticationService.setPassword(adminId, "", encryptedAbcd);
            Assert.assertFalse(setok);
            setok = userAuthenticationService.setPassword(adminId, "admin", encryptedAbcd);
            Assert.assertTrue(setok);
            checkok = userAuthenticationService.checkPassword(adminId, "abcd");
            Assert.assertTrue(checkok);
            checkok = userAuthenticationService.checkPassword(adminId, "admin");
            Assert.assertFalse(checkok);
            setok = userAuthenticationService.setPassword(adminId, "abcd", encryptedAdmin);
            Assert.assertTrue(setok);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }


    @AfterClass
    public static void teardown() {
        providerContext.close();
        providerContext = null;
        System.out.println("context stop");
    }
}