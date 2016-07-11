package com.taoswork.tallycheck.datasolution.tallybus;

import com.taoswork.tallycheck.datasolution.mongo.config.MongoDatasourceConfiguration;
import com.taoswork.tallycheck.datasolution.mongo.core.MongoDataSolutionBase;
import com.taoswork.tallycheck.datasolution.tallybus.conf.TallyManagementDatasourceConfiguration;
import com.taoswork.tallycheck.datasolution.tallybus.conf.TallyManagementPersistableConfiguration;


/**
 * Created by Gao Yuan on 2015/6/5.
 */
public class TallyManagementDataSolution
        extends MongoDataSolutionBase<TallyManagementPersistableConfiguration,
                MongoDatasourceConfiguration> {
    public static final String COMPONENT_NAME = TallyBusDataSolutionDefinition.DATA_SOLUTION_NAME;

    public TallyManagementDataSolution() {
        this(TallyManagementDatasourceConfiguration.class);
    }

    public TallyManagementDataSolution(Class<? extends MongoDatasourceConfiguration> dSrcConfClz) {
        super(new TallyBusDataSolutionDefinition(), TallyManagementPersistableConfiguration.class, dSrcConfClz);
    }
}
