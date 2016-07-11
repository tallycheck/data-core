package com.taoswork.tallycheck.servo.tallybus;

import com.taoswork.tallycheck.dataservice.DataServiceDelegate;
import com.taoswork.tallycheck.dataservice.IDataService;
import com.taoswork.tallycheck.datasolution.service.impl.BasicDataService;
import com.taoswork.tallycheck.datasolution.tallybus.TallyManagementDataSolution;
import com.taoswork.tallycheck.tallybus.TallyBusDataService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by gaoyuan on 7/1/16.
 */
public class TallyBusDataServiceImpl
        extends DataServiceDelegate
        implements TallyBusDataService, ApplicationContextAware {
    public TallyBusDataServiceImpl() {
        super();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        TallyManagementDataSolution dataSolution = (TallyManagementDataSolution) applicationContext.getBean("tallyManagementDataSolution");
        setDataService((IDataService)dataSolution.getService(BasicDataService.COMPONENT_NAME));
    }
}
