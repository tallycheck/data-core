package com.taoswork.tallycheck.datasolution.tallyuser;

import com.taoswork.tallycheck.datasolution.IDataSolution;
import com.taoswork.tallycheck.datasolution.annotations.DataSolutionMark;
import com.taoswork.tallycheck.datasolution.mongo.config.MongoDatasourceConfiguration;
import com.taoswork.tallycheck.datasolution.mongo.core.MongoDataSolutionBase;
import com.taoswork.tallycheck.datasolution.security.AccessInterrupters;
import com.taoswork.tallycheck.datasolution.tallyuser.conf.TallyUserDatasourceConfiguration;
import com.taoswork.tallycheck.datasolution.tallyuser.conf.TallyUserPersistableConfiguration;
import com.taoswork.tallycheck.datasolution.tallyuser.security.PersonInterrupter;

/**
 * Created by Gao Yuan on 2015/5/10.
 */
@DataSolutionMark
public class TallyUserDataSolution
        extends MongoDataSolutionBase<
                TallyUserPersistableConfiguration,
                MongoDatasourceConfiguration> {
    public static final String COMPONENT_NAME = TallyUserDataSolutionDefinition.DATA_SOLUTION_NAME;

    public TallyUserDataSolution() {
        this(TallyUserDatasourceConfiguration.class);
    }

    public TallyUserDataSolution(Class<? extends MongoDatasourceConfiguration> dSrcConfClz) {
        super(new TallyUserDataSolutionDefinition(), TallyUserPersistableConfiguration.class, dSrcConfClz);
    }

    @Override
    protected void postConstruct() {
        super.postConstruct();

        AccessInterrupters accessInterrupters = this.getService(IDataSolution.ACCESS_INTERRUPTERS);
        accessInterrupters.register(new PersonInterrupter());
    }
}
