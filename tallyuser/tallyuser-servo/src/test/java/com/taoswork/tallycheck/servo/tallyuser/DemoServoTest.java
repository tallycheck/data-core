package com.taoswork.tallycheck.servo.tallyuser;

import com.taoswork.tallycheck.authority.provider.AllPassAuthorityProvider;
import com.taoswork.tallycheck.datadomain.base.entity.Persistable;
import com.taoswork.tallycheck.datadomain.tallyuser.Person;
import com.taoswork.tallycheck.dataservice.SecurityAccessor;
import com.taoswork.tallycheck.dataservice.exception.ServiceException;
import com.taoswork.tallycheck.dataservice.io.request.ReadRequest;
import com.taoswork.tallycheck.dataservice.io.response.ReadResponse;
import com.taoswork.tallycheck.datasolution.tallyuser.TallyUserDataSolution;
import com.taoswork.tallycheck.datasolution.tallyuser.TallyUserDataSolutionDefinition;
import com.taoswork.tallycheck.tallyuser.EncryptPasswordHelper;
import com.taoswork.tallycheck.tallyuser.TallyUserDataService;
import com.taoswork.tallycheck.tallyuser.UserCertificationService;
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
    private static UserCertificationService userCertificationService;
    private static TallyUserDataService tallyUserDataService;
    private static final String ADMIN_ID = "000000000000000000000000";
    private SecurityAccessor accessor = new SecurityAccessor();

    @BeforeClass
    public static void setup() {
        providerContext =
                new ClassPathXmlApplicationContext("dubbo-tallyuser-demo-servo.xml");
        providerContext.start();
        System.out.println("context started");
         tallyUserDataSolution = (TallyUserDataSolution)
                 providerContext.getBean("tallyUserDataSolution");
        tallyUserDataSolution.setAuthorityProvider(new AllPassAuthorityProvider());
        userCertificationService = (UserCertificationService)
                providerContext.getBean("tallyUserCertService");
        tallyUserDataService = (TallyUserDataService)
                providerContext.getBean("tallyUserDataService");
    }

    @Test
    public void testTallyUserCertService() {
        try {
            String adminId = ADMIN_ID;
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

    @Test
    public void testTallyUserDataService() throws ServiceException {
        Assert.assertEquals(TallyUserDataSolutionDefinition.DATA_SOLUTION_NAME, tallyUserDataService.getName());
        ReadRequest readRequest = new ReadRequest(Person.class);
        readRequest.setId(ADMIN_ID);
        ReadResponse response = tallyUserDataService.read(accessor, readRequest);
        Assert.assertTrue(response.isSuccess());

        Persistable persistable = response.result;
        Assert.assertNotNull(persistable);
        Assert.assertTrue(persistable instanceof Person);
        Person person = (Person) persistable;
        Assert.assertEquals(person.getName(), "admin");
        Assert.assertEquals(person.getId().toString(), ADMIN_ID);
    }

    @AfterClass
    public static void teardown() {
        providerContext.close();
        providerContext = null;
        System.out.println("context stop");
    }
}