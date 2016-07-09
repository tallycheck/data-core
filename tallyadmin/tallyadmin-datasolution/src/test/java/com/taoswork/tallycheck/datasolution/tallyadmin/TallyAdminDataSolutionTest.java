package com.taoswork.tallycheck.datasolution.tallyadmin;

import com.taoswork.tallycheck.authority.provider.AllPassAuthorityProvider;
import com.taoswork.tallycheck.datadomain.tallyadmin.AdminEmployee;
import com.taoswork.tallycheck.dataservice.SecurityAccessor;
import com.taoswork.tallycheck.dataservice.exception.ServiceException;
import com.taoswork.tallycheck.dataservice.query.CriteriaQueryResult;
import com.taoswork.tallycheck.datasolution.config.IDatasourceConfiguration;
import com.taoswork.tallycheck.datasolution.mongo.config.TestDatasourceConfiguration;
import com.taoswork.tallycheck.datasolution.service.EasyEntityService;
import com.taoswork.tallycheck.datasolution.service.IEntityService;
import com.taoswork.tallycheck.datasolution.tallyadmin.dao.AdminEmployeeDao;
import com.taoswork.tallycheck.datasolution.tallyadmin.service.AdminEmployeeService;
import org.bson.types.ObjectId;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by Gao Yuan on 2015/5/13.
 */
public class TallyAdminDataSolutionTest {
    static TallyAdminDataSolution dataSolution = null;
    private SecurityAccessor accessor = new SecurityAccessor();

    @BeforeClass
    public static void setDataService() {
        dataSolution = new TallyAdminDataSolution(TestDatasourceConfiguration.class);
        dataSolution.setAuthorityProvider(new AllPassAuthorityProvider());
    }

    @AfterClass
    public static void tearDown() {
        TestDatasourceConfiguration.DatasourceDefinition mdbDef = dataSolution.getService(IDatasourceConfiguration.DATA_SOURCE_PATH_DEFINITION);
        mdbDef.dropDatabase();
        dataSolution = null;
    }

    @Test
    public void testDataService() throws ServiceException {
        EasyEntityService easyEntityService = new EasyEntityService(dataSolution);
        IEntityService entityService = dataSolution.getService(IEntityService.COMPONENT_NAME);
        Assert.assertNotNull(entityService);
        CriteriaQueryResult<AdminEmployee> admins = easyEntityService.query(accessor,
                AdminEmployee.class, null);

        AdminEmployeeDao employeeDao = dataSolution.getService(AdminEmployeeDao.COMPONENT_NAME);
        Assert.assertNotNull(employeeDao);

        AdminEmployeeDao employeeDao1 = dataSolution.getService(AdminEmployeeDao.class);
        Assert.assertEquals(employeeDao, employeeDao1);

        AdminEmployeeService employeeService = dataSolution.getService(AdminEmployeeService.SERVICE_NAME);
        Assert.assertNotNull(employeeService);

        String rootPersonId =  AdminEmployee.ROOT_PERSON_ID;
        AdminEmployee employeeInDb = employeeService.readAdminEmployeeByPersonId(rootPersonId);
        Assert.assertTrue(employeeInDb.getPersonId().equals(rootPersonId));
        Assert.assertTrue(employeeInDb.getTitle().equals("Master"));

        int createAttempt = 10;
        int created = 0;
        try {
            for (int i = 0; i < createAttempt; ++i) {
                int expected = i + 1;
                ObjectId personId = new ObjectId();
                AdminEmployee employee = new AdminEmployee();
                employee.setPersonId(personId.toHexString());
                employee.setTitle("Title" + expected);
                employee.setName("Name" + expected);

                employeeService.saveAdminEmployee(employee);
                AdminEmployee employeeLoaded = employeeService.readAdminEmployeeByPersonId(personId.toHexString());

                Assert.assertTrue(personId.toHexString().equals(employeeLoaded.getPersonId()));
                Assert.assertTrue(employee.getTitle().equals(employeeLoaded.getTitle()));
                Assert.assertTrue(employee.getTitle().equals("Title" + expected));

                created++;
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        } finally {
            Assert.assertEquals(createAttempt, created);
        }
    }
}
