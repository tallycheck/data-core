package com.taoswork.tallycheck.tallyadmin;


import com.taoswork.tallycheck.datadomain.tallyadmin.AdminEmployee;
import com.taoswork.tallycheck.dataservice.IDataService;

/**
 * Created by gaoyuan on 6/29/16.
 */
public interface TallyAdminDataService extends IDataService {

    AdminEmployee getAdminEmployeeByPersonId (String personId);
}
