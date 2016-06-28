package com.taoswork.tallycheck.dataservice.tallyuser;

import com.taoswork.tallycheck.dataservice.tallyuser.conf.TallyUserDatasourceConfiguration;
import com.taoswork.tallycheck.dataservice.tallyuser.conf.TallyUserPersistableConfiguration;
import com.taoswork.tallycheck.dataservice.annotations.DataService;
import com.taoswork.tallycheck.dataservice.mongo.config.MongoDatasourceConfiguration;
import com.taoswork.tallycheck.dataservice.mongo.core.MongoDataServiceBase;

/**
 * Created by Gao Yuan on 2015/5/10.
 */
@DataService
public class TallyUserDataService
        extends MongoDataServiceBase<
        TallyUserPersistableConfiguration,
        MongoDatasourceConfiguration> {
    public static final String COMPONENT_NAME = TallyUserDataServiceDefinition.DATA_SERVICE_NAME;

    public TallyUserDataService() {
        this(TallyUserDatasourceConfiguration.class);
    }

    public TallyUserDataService(Class<? extends MongoDatasourceConfiguration> dSrcConfClz) {
        super(new TallyUserDataServiceDefinition(), TallyUserPersistableConfiguration.class, dSrcConfClz);
    }
}
