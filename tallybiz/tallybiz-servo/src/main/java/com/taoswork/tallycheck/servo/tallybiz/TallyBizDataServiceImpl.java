package com.taoswork.tallycheck.servo.tallybiz;


import com.taoswork.tallycheck.dataservice.DataServiceDelegate;
import com.taoswork.tallycheck.dataservice.IDataService;
import com.taoswork.tallycheck.datasolution.service.impl.BasicDataService;
import com.taoswork.tallycheck.datasolution.tallybiz.TallyBizDataSolution;
import com.taoswork.tallycheck.tallybiz.TallyBizDataService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by gaoyuan on 7/1/16.
 */
public class TallyBizDataServiceImpl
        extends DataServiceDelegate
        implements TallyBizDataService, ApplicationContextAware {
    public TallyBizDataServiceImpl() {
        super();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        TallyBizDataSolution dataSolution = (TallyBizDataSolution) applicationContext.getBean("tallyBizDataSolution");
        setDataService((IDataService)dataSolution.getService(BasicDataService.COMPONENT_NAME));
    }
}
