package com.taoswork.tallycheck.servo.tallybiz;

import com.taoswork.tallycheck.dataservice.DataServiceDelegate;
import com.taoswork.tallycheck.dataservice.IDataService;
import com.taoswork.tallycheck.datasolution.IDataSolution;
import com.taoswork.tallycheck.datasolution.service.impl.BasicDataService;
import com.taoswork.tallycheck.tallybiz.TallyBizDataService;

/**
 * Created by gaoyuan on 7/1/16.
 */
public class TallyBizDataServiceImpl
        extends DataServiceDelegate
        implements TallyBizDataService {
    public TallyBizDataServiceImpl() {
        super();
    }

    protected IDataSolution dataSolution;

    public void setDataSolution(IDataSolution dataSolution) {
        this.dataSolution = dataSolution;
        setDataService((IDataService)dataSolution.getService(BasicDataService.COMPONENT_NAME));
    }

}
