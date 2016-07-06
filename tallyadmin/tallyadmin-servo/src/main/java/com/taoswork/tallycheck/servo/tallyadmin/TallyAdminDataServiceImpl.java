package com.taoswork.tallycheck.servo.tallyadmin;

import com.taoswork.tallycheck.dataservice.DataServiceDelegate;
import com.taoswork.tallycheck.dataservice.IDataService;
import com.taoswork.tallycheck.datasolution.service.impl.BasicDataService;
import com.taoswork.tallycheck.datasolution.tallyadmin.TallyAdminDataSolution;
import com.taoswork.tallycheck.tallyadmin.TallyAdminDataService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by gaoyuan on 7/5/16.
 */
public class TallyAdminDataServiceImpl
        extends DataServiceDelegate
        implements TallyAdminDataService, ApplicationContextAware {
    public TallyAdminDataServiceImpl() {
        super();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        TallyAdminDataSolution dataSolution = (TallyAdminDataSolution) applicationContext.getBean("tallyAdminDataSolution");
        setDataService((IDataService) dataSolution.getService(BasicDataService.COMPONENT_NAME));
    }
}
