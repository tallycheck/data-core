package com.taoswork.tallycheck.servo.tallyuser;

import com.taoswork.tallycheck.datadomain.base.entity.Persistable;
import com.taoswork.tallycheck.datadomain.tallyuser.Person;
import com.taoswork.tallycheck.dataservice.SecurityAccessor;
import com.taoswork.tallycheck.dataservice.exception.ServiceException;
import com.taoswork.tallycheck.dataservice.io.request.ReadRequest;
import com.taoswork.tallycheck.dataservice.io.response.ReadResponse;
import com.taoswork.tallycheck.dataservice.operator.Operator;
import com.taoswork.tallycheck.datasolution.tallyuser.TallyUserDataSolution;
import com.taoswork.tallycheck.datasolution.tallyuser.TallyUserDataSolutionDefinition;
import com.taoswork.tallycheck.tallyuser.TallyUserDataService;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Locale;

/**
 * Created by Gao Yuan on 2016/6/27.
 */
public class DemoServoTest {
    private static ClassPathXmlApplicationContext providerContext;
    private static TallyUserDataSolution tallyUserDataSolution;
    private static TallyUserDataService tallyUserDataService;
    private static final String ADMIN_ID = "000000000000000000000000";
    private SecurityAccessor accessor = new SecurityAccessor();
    private Operator operator = new Operator();

    @BeforeClass
    public static void setup() {
        providerContext =
                new ClassPathXmlApplicationContext("dubbo-tallyuser-demo-servo.xml");
        providerContext.start();
        System.out.println("context started");
         tallyUserDataSolution = (TallyUserDataSolution)
                 providerContext.getBean("tallyUserDataSolution");
        tallyUserDataService = (TallyUserDataService)
                providerContext.getBean("tallyUserDataService");
    }


    @Test
    public void testTallyUserDataService() throws ServiceException {
        Assert.assertEquals(TallyUserDataSolutionDefinition.DATA_SOLUTION_NAME, tallyUserDataService.getName());
        ReadRequest readRequest = new ReadRequest(Person.class, Locale.US);
        readRequest.setId(ADMIN_ID);
        ReadResponse response = tallyUserDataService.read(operator, accessor, readRequest);
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