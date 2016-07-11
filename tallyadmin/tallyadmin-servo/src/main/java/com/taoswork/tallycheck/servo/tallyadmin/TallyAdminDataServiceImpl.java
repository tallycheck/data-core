package com.taoswork.tallycheck.servo.tallyadmin;

import com.taoswork.tallycheck.datadomain.tallyadmin.AdminEmployee;
import com.taoswork.tallycheck.dataservice.DataServiceDelegate;
import com.taoswork.tallycheck.dataservice.IDataService;
import com.taoswork.tallycheck.datasolution.service.impl.BasicDataService;
import com.taoswork.tallycheck.datasolution.tallyadmin.TallyAdminDataSolution;
import com.taoswork.tallycheck.datasolution.tallyadmin.service.AdminEmployeeService;
import com.taoswork.tallycheck.tallyadmin.TallyAdminDataService;

/**
 * Created by gaoyuan on 7/5/16.
 */
public class TallyAdminDataServiceImpl
        extends DataServiceDelegate
        implements TallyAdminDataService
        //, ApplicationContextAware
{
    protected TallyAdminDataSolution dataSolution;
    protected AdminEmployeeService adminEmployeeService;

    public TallyAdminDataServiceImpl() {
        super();
    }

    public void setDataSolution(TallyAdminDataSolution dataSolution) {
        this.dataSolution = dataSolution;
        this.adminEmployeeService = dataSolution.getService(AdminEmployeeService.SERVICE_NAME);

        IDataService insideDataService = dataSolution.getService(BasicDataService.COMPONENT_NAME);
        setDataService(insideDataService);
    }

    //    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        dataSolution = (TallyAdminDataSolution) applicationContext.getBean("tallyAdminDataSolution");
//
//        adminEmployeeService = dataSolution.getService(AdminEmployeeService.SERVICE_NAME);
//
//        IDataService insideDataService = dataSolution.getService(BasicDataService.COMPONENT_NAME);
//        setDataService(insideDataService);
//
//    }

    @Override
    public AdminEmployee getAdminEmployeeByPersonId(String personId) {
        return adminEmployeeService.readAdminEmployeeByPersonId(personId);
    }
}
