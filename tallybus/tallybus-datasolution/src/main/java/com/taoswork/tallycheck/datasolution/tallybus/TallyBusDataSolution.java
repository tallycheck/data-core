package com.taoswork.tallycheck.datasolution.tallybus;

import com.taoswork.tallycheck.datasolution.mongo.config.MongoDatasourceConfiguration;
import com.taoswork.tallycheck.datasolution.mongo.core.MongoDataSolutionBase;
import com.taoswork.tallycheck.datasolution.tallybus.conf.TallyBusDatasourceConfiguration;
import com.taoswork.tallycheck.datasolution.tallybus.conf.TallyBusPersistableConfiguration;


/**
 * Created by Gao Yuan on 2015/6/5.
 */
public class TallyBusDataSolution
        extends MongoDataSolutionBase<TallyBusPersistableConfiguration,
                MongoDatasourceConfiguration> {
    public static final String COMPONENT_NAME = TallyBusDataSolutionDefinition.DATA_SOLUTION_NAME;

    public TallyBusDataSolution() {
        this(TallyBusDatasourceConfiguration.class);
    }

    public TallyBusDataSolution(Class<? extends MongoDatasourceConfiguration> dSrcConfClz) {
        super(new TallyBusDataSolutionDefinition(), TallyBusPersistableConfiguration.class, dSrcConfClz);
    }
}
