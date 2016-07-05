package com.taoswork.tallycheck.datasolution.tallymanagement.conf;

import com.taoswork.tallycheck.datasolution.mongo.MongoDatasourceDefinition;
import com.taoswork.tallycheck.datasolution.mongo.MongoDatasourceDefinitionBase;
import com.taoswork.tallycheck.datasolution.mongo.config.MongoDatasourceConfiguration;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Gao Yuan on 2016/2/16.
 */
@Configuration
public class TallyManagementDatasourceConfiguration extends MongoDatasourceConfiguration {
    @Override
    protected MongoDatasourceDefinition createDatasourceDefinition() {
        return new DatasourceDefinition();
    }

    /**
     * Created by Gao Yuan on 2016/2/16.
     */
    public static class DatasourceDefinition
            extends MongoDatasourceDefinitionBase {

        @Override
        public String getDbName() {
            return "tallymanage";
        }

    }
}
