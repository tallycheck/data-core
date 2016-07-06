package com.taoswork.tallycheck.datasolution.tallybiz;

import com.taoswork.tallycheck.datasolution.mongo.config.MongoDatasourceConfiguration;
import com.taoswork.tallycheck.datasolution.mongo.core.MongoDataSolutionBase;
import com.taoswork.tallycheck.datasolution.tallybiz.conf.TallyBizDatasourceConfiguration;
import com.taoswork.tallycheck.datasolution.tallybiz.conf.TallyBizPersistableConfiguration;

/**
 * Created by Gao Yuan on 2015/6/5.
 */
public class TallyBizDataSolution
        extends MongoDataSolutionBase<
        TallyBizPersistableConfiguration,
                        MongoDatasourceConfiguration> {
    public static final String COMPONENT_NAME = TallyBizDataSolutionDefinition.DATA_SOLUTION_NAME;

    public TallyBizDataSolution() {
        this(TallyBizDatasourceConfiguration.class);
    }

    public TallyBizDataSolution(Class<? extends MongoDatasourceConfiguration> dSrcConfClz) {
        super(new TallyBizDataSolutionDefinition(), TallyBizPersistableConfiguration.class, dSrcConfClz);
    }
}
