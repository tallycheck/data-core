package com.taoswork.tallycheck.datasolution.tallyuser.service.impl;

import com.taoswork.tallycheck.datadomain.tallyuser.Person;
import com.taoswork.tallycheck.datadomain.tallyuser.PersonCertification;
import com.taoswork.tallycheck.datasolution.annotations.EntityServiceMark;
import com.taoswork.tallycheck.datasolution.core.entity.ServiceBase;
import com.taoswork.tallycheck.datasolution.tallyuser.dao.PersonCertificationDao;
import com.taoswork.tallycheck.datasolution.tallyuser.dao.PersonDao;
import com.taoswork.tallycheck.datasolution.tallyuser.dao.PersonKeyType;
import com.taoswork.tallycheck.datasolution.tallyuser.service.PersonService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Gao Yuan on 2015/5/8.
 */
@Service(PersonService.SERVICE_NAME)
@EntityServiceMark(PersonService.SERVICE_NAME)
public class PersonServiceImpl
        extends ServiceBase
        implements PersonService {

    @Resource(name = PersonDao.COMPONENT_NAME)
    protected PersonDao userDao;

    @Resource(name = PersonCertificationDao.COMPONENT_NAME)
    protected PersonCertificationDao personCertificationDao;

    @Override
    public Person readPersonByUUID(String userUuid) {
        return userDao.readPersonByKey(PersonKeyType.UUID, userUuid, false);
    }

    @Override
    public Person readPersonByID(String id) {
        return userDao.readPersonById(id);
    }

    @Override
    public Person savePerson(Person person) {
        return userDao.save(person);
    }

    @Override
    public Person readPersonByAnyIdentity(String s) {
        return userDao.readPersonByAnyIdentity(s);
    }

    @Override
    public PersonCertification readPersonCertificationByUUID(String personUUID) {
        return personCertificationDao.readPersonCertificationByPersonCode(personUUID);
    }

    @Override
    public boolean updatePassword(String personUUID, String password) {
        return personCertificationDao.updateCertification(personUUID, password);
    }
}
