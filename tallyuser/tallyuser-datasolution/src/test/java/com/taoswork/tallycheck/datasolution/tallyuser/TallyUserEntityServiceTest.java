package com.taoswork.tallycheck.datasolution.tallyuser;

import com.taoswork.tallycheck.authority.provider.AllPassAuthorityProvider;
import com.taoswork.tallycheck.datadomain.onmongo.PersistableDocument;
import com.taoswork.tallycheck.datadomain.tallyuser.Person;
import com.taoswork.tallycheck.datadomain.tallyuser.PersonCertification;
import com.taoswork.tallycheck.datadomain.tallyuser.impl.PersonImpl;
import com.taoswork.tallycheck.dataservice.SecurityAccessor;
import com.taoswork.tallycheck.dataservice.exception.EntityValueValidationException;
import com.taoswork.tallycheck.dataservice.exception.ServiceException;
import com.taoswork.tallycheck.dataservice.operator.Operator;
import com.taoswork.tallycheck.dataservice.query.CriteriaQueryResult;
import com.taoswork.tallycheck.dataservice.query.CriteriaTransferObject;
import com.taoswork.tallycheck.datasolution.config.IDatasourceConfiguration;
import com.taoswork.tallycheck.datasolution.mongo.config.TestDatasourceConfiguration;
import com.taoswork.tallycheck.datasolution.service.EasyEntityService;
import com.taoswork.tallycheck.datasolution.service.IEntityService;
import com.taoswork.tallycheck.datasolution.tallyuser.dao.PersonCertificationDao;
import com.taoswork.tallycheck.datasolution.tallyuser.dao.PersonDao;
import com.taoswork.tallycheck.datasolution.tallyuser.service.PersonService;
import com.taoswork.tallycheck.descriptor.service.MetaInfoService;
import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

/**
 * Created by Gao Yuan on 2015/5/10.
 */
public class TallyUserEntityServiceTest {
    TallyUserDataSolution dataSolution = null;
    private SecurityAccessor accessor = new SecurityAccessor();
    private Operator operator = new Operator();
    int counter = 0;

    @Before
    public void setupDataSource() {
        counter++;
        dataSolution = new TallyUserDataSolution(TestDatasourceConfiguration.class);
        dataSolution.setAuthorityProvider(new AllPassAuthorityProvider());
    }

    @After
    public void tearDown() {
        TestDatasourceConfiguration.DatasourceDefinition mdbDef = dataSolution.getService(IDatasourceConfiguration.DATA_SOURCE_PATH_DEFINITION);
        mdbDef.dropDatabase();
        dataSolution = null;
    }
//
//    @Test
//    public void testCreate() throws ServiceException {
//        IEntityService<PersistableDocument> es = dataSolution.getService(IEntityService.COMPONENT_NAME);
//        for(int i = 0 ; i < 67 ; i ++) {
//            Person p = new PersonImpl();
//            p.setName("admin" + i).setGender(Gender.male).setEmail("admin_" + i + "@aa.com").setMobile("12345678901" + i);
//            es.create(p);
//        }
//    }

    private final static String AdminId = "000000000000000000000000";

    @Test
    public void testDao() {
        PersonDao personDao = dataSolution.getService(PersonDao.COMPONENT_NAME);
        Assert.assertNotNull(personDao);
        Person addminPerson = personDao.readPersonById(AdminId);
        Assert.assertTrue(addminPerson.getName().equals("admin")); //Loaded from load_person.xml

        PersonCertificationDao personCertificationDao = dataSolution.getService(PersonCertificationDao.COMPONENT_NAME);
        Assert.assertNotNull(personCertificationDao);
        PersonCertification pc = personCertificationDao.readPersonCertificationById(AdminId);
        Assert.assertEquals(pc.getUserCode(), addminPerson.getUuid());
    }

    @Test
    public void testdataSolutions() {

        PersonService personService = dataSolution.getService(PersonService.SERVICE_NAME);
        Assert.assertTrue(personService != null);

        Person adminP = personService.readPersonByAnyIdentity("admin");
        PersonCertification adminPC = personService.readPersonCertificationByUUID(adminP.getUuid());
        Assert.assertEquals(adminP.getUuid(), adminPC.getUserCode());

        int createAttempt = 10;
        int created = 0;
        try {
            //idInitialValue, base on PersonImpl.id's annotation: @TableGenerator's initialValue + 1
            int idInitialValue = 1;
            for (int i = 0; i < createAttempt; ++i) {
                int expected = i + idInitialValue;
                Person admin = new PersonImpl();
                admin.setName("admin").setUuid(UUID.randomUUID().toString());
                personService.savePerson(admin);

                ObjectId id = admin.getId();
                Person adminFromDb = personService.readPersonByID(id.toHexString());

                Assert.assertTrue("Created and Read should be same: " + i, admin.getId().equals(adminFromDb.getId()));
//                Assert.assertTrue("Created Object [" + admin.getId() + "] should have Id: " + expected, admin.getId().equals(0L + expected));
                created++;
            }
        } finally {
            Assert.assertEquals(createAttempt, created);
        }
    }

    @Test
    public void testMetaDescriptionService() {
        MetaInfoService metaDescriptionService = dataSolution.getService(MetaInfoService.SERVICE_NAME);
        Assert.assertNotNull(metaDescriptionService);
    }

    @Test
    public void testDynamicEntityService() {
        EasyEntityService easyEntityService = new EasyEntityService(dataSolution);
        IEntityService<PersistableDocument> entityService = dataSolution.getService(IEntityService.COMPONENT_NAME);
        Assert.assertNotNull(entityService);
        try {
            Person admin = new PersonImpl();
            admin.setName("admin").setUuid(UUID.randomUUID().toString());
            entityService.create(operator, accessor, admin);
            Assert.fail();
        } catch (ServiceException e) {
            if (!(e instanceof EntityValueValidationException)) {
                Assert.fail();
            }
        }

        long existingCount = 0;
        try {
            Person personImp = easyEntityService.straightRead(operator, accessor, PersonImpl.class, AdminId);
            Person person = easyEntityService.straightRead(operator, accessor, Person.class, AdminId);
            Assert.assertEquals(person.getUuid(), personImp.getUuid());
            Assert.assertEquals(person.getId(), personImp.getId());

            Assert.assertNotNull(person);
            Assert.assertTrue(person.getName().equals("admin")); //Loaded from load_person.xml

            CriteriaQueryResult<Person> personsExisting = easyEntityService.query(accessor, Person.class, new CriteriaTransferObject());
            existingCount = personsExisting.getTotalCount();
            Assert.assertTrue(existingCount >= 1);
        } catch (ServiceException e) {
            Assert.fail(e.getMessage());
        } finally {
        }

        int createAttempt = 10;
        int created = 0;
        try {
            //idInitialValue, base on PersonImpl.id's annotation: @TableGenerator's initialValue + 1
            int idInitialValue = 1;
            for (int i = 0; i < createAttempt; ++i) {
                int expected = i + idInitialValue;
                Person admin = new PersonImpl();
                admin.setName("admin").setUuid(UUID.randomUUID().toString());
                admin.setMobile("1234567890" + (1000 + i));
                entityService.create(operator, accessor, admin);

                ObjectId id = admin.getId();
                Person adminFromDb = easyEntityService.straightRead(operator, accessor, Person.class, id);

                Assert.assertTrue("Created and Read should be same: " + i, admin.getId().equals(adminFromDb.getId()));
//                Assert.assertTrue("Created Object [" + admin.getId() + "] should have Id: " + expected, admin.getId().equals(0L + expected));
                created++;
            }
        } catch (ServiceException e) {
            Assert.fail(e.getMessage());
        } finally {
            Assert.assertEquals(createAttempt, created);
        }

        try {
            CriteriaQueryResult<Person> persons = easyEntityService.query(accessor, Person.class, new CriteriaTransferObject());
            Assert.assertEquals(persons.getTotalCount().longValue(), existingCount + createAttempt);
        } catch (ServiceException e) {
            Assert.fail(e.getMessage());
        }
    }
}