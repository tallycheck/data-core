package com.taoswork.tallycheck.tallyuser;

import com.taoswork.tallycheck.datadomain.tallyuser.Person;
import com.taoswork.tallycheck.datadomain.tallyuser.impl.PersonImpl;
import com.taoswork.tallycheck.dataservice.BasicDataServiceMock;

/**
 * Created by gaoyuan on 7/11/16.
 */
public class TallyUserDataServiceMock extends BasicDataServiceMock implements TallyUserDataService {
    @Override
    public Person getPersonByAnyIdentity(String identity) {
        return new PersonImpl();
    }
}
