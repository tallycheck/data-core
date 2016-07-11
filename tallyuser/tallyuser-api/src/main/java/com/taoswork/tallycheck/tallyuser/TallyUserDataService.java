package com.taoswork.tallycheck.tallyuser;


import com.taoswork.tallycheck.datadomain.tallyuser.Person;
import com.taoswork.tallycheck.dataservice.IDataService;

/**
 * Created by gaoyuan on 6/29/16.
 */
public interface TallyUserDataService extends IDataService {

    Person getPersonByAnyIdentity(String identity);
}
