package com.taoswork.tallycheck.tallyadmin.authority;

import com.taoswork.tallycheck.datasolution.annotations.DataSolutionMark;
import com.taoswork.tallycheck.datasolution.mongo.config.MongoDatasourceConfiguration;
import com.taoswork.tallycheck.datasolution.mongo.core.MongoDataSolutionBase;
import com.taoswork.tallycheck.tallyadmin.authority.conf.TAdminAuthorityDatasourceConfiguration;
import com.taoswork.tallycheck.tallyadmin.authority.conf.TAdminAuthorityPersistableConfiguration;
import com.taoswork.tallycheck.tallyadmin.authority.conf.TAdminAuthoritySpecifiedConfiguration;

/**
 * Created by Gao Yuan on 2015/5/12.
 */
@DataSolutionMark
//@Component(TallyAdminDataSolution.COMPONENT_NAME)
public class TAdminAuthorityDataSolution
        extends MongoDataSolutionBase<
        TAdminAuthorityPersistableConfiguration,
                                MongoDatasourceConfiguration> {

    public static final String COMPONENT_NAME = TAdminAuthorityDataSolutionDefinition.DATA_SOLUTION_NAME;

    public TAdminAuthorityDataSolution() {
        this(TAdminAuthorityDatasourceConfiguration.class);
    }

    public TAdminAuthorityDataSolution(Class<? extends MongoDatasourceConfiguration> dSrcConfClz) {
        super(new TAdminAuthorityDataSolutionDefinition(),
                TAdminAuthorityPersistableConfiguration.class,
                dSrcConfClz, TAdminAuthoritySpecifiedConfiguration.class);
    }

    @Override
    protected void postConstruct() {
        super.postConstruct();
    }
}
