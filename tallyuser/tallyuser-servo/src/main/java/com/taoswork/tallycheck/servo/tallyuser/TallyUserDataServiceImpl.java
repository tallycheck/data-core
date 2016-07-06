package com.taoswork.tallycheck.servo.tallyuser;

import com.taoswork.tallycheck.dataservice.DataServiceDelegate;
import com.taoswork.tallycheck.dataservice.IDataService;
import com.taoswork.tallycheck.datasolution.service.impl.BasicDataService;
import com.taoswork.tallycheck.datasolution.tallyuser.TallyUserDataSolution;
import com.taoswork.tallycheck.tallyuser.TallyUserDataService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by gaoyuan on 7/1/16.
 */
public class TallyUserDataServiceImpl
        extends DataServiceDelegate
        implements TallyUserDataService, ApplicationContextAware {
    public TallyUserDataServiceImpl() {
        super();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        TallyUserDataSolution dataSolution = (TallyUserDataSolution) applicationContext.getBean("tallyUserDataSolution");
        setDataService((IDataService)dataSolution.getService(BasicDataService.COMPONENT_NAME));
    }
}
