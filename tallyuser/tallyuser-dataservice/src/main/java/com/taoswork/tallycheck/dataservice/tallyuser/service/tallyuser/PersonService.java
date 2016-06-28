package com.taoswork.tallycheck.dataservice.tallyuser.service.tallyuser;

import com.taoswork.tallycheck.datadomain.tallyuser.Person;
import com.taoswork.tallycheck.datadomain.tallyuser.PersonCertification;
import com.taoswork.tallycheck.dataservice.core.entity.IService;

/**
 * Created by Gao Yuan on 2015/5/8.
 */
public interface PersonService extends IService {
    public static final String SERVICE_NAME = PREFIX + "PersonService";

    Person readPersonByUUID(String userUuid);

    Person savePerson(Person person);

    Person readPersonByID(String id);

    Person readPersonByAnyIdentity(String s);

    PersonCertification readPersonCertificationByUUID(String personUUID);

    boolean updatePassword(String personUUID, String password);
}
