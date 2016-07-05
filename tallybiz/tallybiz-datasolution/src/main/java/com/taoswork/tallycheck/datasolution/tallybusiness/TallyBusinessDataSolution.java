package com.taoswork.tallycheck.datasolution.tallybusiness;

import com.taoswork.tallycheck.datasolution.mongo.config.MongoDatasourceConfiguration;
import com.taoswork.tallycheck.datasolution.mongo.core.MongoDataSolutionBase;
import com.taoswork.tallycheck.datasolution.tallybusiness.conf.TallyBusinessDatasourceConfiguration;
import com.taoswork.tallycheck.datasolution.tallybusiness.conf.TallyBusinessPersistableConfiguration;

/**
 * Created by Gao Yuan on 2015/6/5.
 */
public class TallyBusinessDataSolution
        extends MongoDataSolutionBase<
                        TallyBusinessPersistableConfiguration,
                        MongoDatasourceConfiguration> {
    public static final String COMPONENT_NAME = TallyBusinessDataSolutionDefinition.DATA_SOLUTION_NAME;

    public TallyBusinessDataSolution() {
        this(TallyBusinessDatasourceConfiguration.class);
    }

    public TallyBusinessDataSolution(Class<? extends MongoDatasourceConfiguration> dSrcConfClz) {
        super(new TallyBusinessDataSolutionDefinition(), TallyBusinessPersistableConfiguration.class, dSrcConfClz);
    }
}
