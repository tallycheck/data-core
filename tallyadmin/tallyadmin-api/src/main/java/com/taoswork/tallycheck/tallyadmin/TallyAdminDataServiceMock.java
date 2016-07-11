package com.taoswork.tallycheck.tallyadmin;

import com.taoswork.tallycheck.datadomain.tallyadmin.AdminEmployee;
import com.taoswork.tallycheck.dataservice.BasicDataServiceMock;

/**
 * Created by gaoyuan on 7/11/16.
 */
public class TallyAdminDataServiceMock extends BasicDataServiceMock implements TallyAdminDataService {
    @Override
    public AdminEmployee getAdminEmployeeByPersonId(String personId) {
        return new AdminEmployee();
    }
}
