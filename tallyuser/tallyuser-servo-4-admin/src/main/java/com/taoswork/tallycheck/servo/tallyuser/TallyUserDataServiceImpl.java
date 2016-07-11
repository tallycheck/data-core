package com.taoswork.tallycheck.servo.tallyuser;

import com.taoswork.tallycheck.datadomain.tallyuser.Person;
import com.taoswork.tallycheck.dataservice.DataServiceDelegate;
import com.taoswork.tallycheck.dataservice.IDataService;
import com.taoswork.tallycheck.datasolution.IDataSolution;
import com.taoswork.tallycheck.datasolution.service.impl.BasicDataService;
import com.taoswork.tallycheck.datasolution.tallyuser.service.PersonService;
import com.taoswork.tallycheck.tallyuser.TallyUserDataService;

/**
 * Created by gaoyuan on 7/1/16.
 */
public class TallyUserDataServiceImpl
        extends DataServiceDelegate
        implements TallyUserDataService {
    public TallyUserDataServiceImpl() {
        super();
    }

    private IDataSolution dataSolution;
    private PersonService personService;

    public void setDataSolution(IDataSolution dataSolution) {
        this.dataSolution = dataSolution;

        IDataService insideDataService = dataSolution.getService(BasicDataService.COMPONENT_NAME);
        this.setDataService(insideDataService);

        personService = dataSolution.getService(PersonService.SERVICE_NAME);
    }

    @Override
    public Person getPersonByAnyIdentity(String identity) {
        return personService.readPersonByAnyIdentity(identity);
    }
}
