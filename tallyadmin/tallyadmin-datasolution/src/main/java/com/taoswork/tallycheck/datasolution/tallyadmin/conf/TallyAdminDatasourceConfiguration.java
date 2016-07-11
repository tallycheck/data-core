package com.taoswork.tallycheck.datasolution.tallyadmin.conf;

import com.mongodb.ServerAddress;
import com.taoswork.tallycheck.datadomain.tallyadmin.TallyAdminDataDomain;
import com.taoswork.tallycheck.datasolution.mongo.MongoDatasourceDefinition;
import com.taoswork.tallycheck.datasolution.mongo.MongoDatasourceDefinitionBase;
import com.taoswork.tallycheck.datasolution.mongo.config.MongoDatasourceConfiguration;
import com.taoswork.tallycheck.general.solution.conf.TallycheckConfiguration;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Gao Yuan on 2016/2/16.
 */
@Configuration
public class TallyAdminDatasourceConfiguration extends MongoDatasourceConfiguration {
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
        protected ServerAddress determineServerAddress() {
            org.apache.commons.configuration2.Configuration conf = TallycheckConfiguration.instance();
            String host = conf.getString(TallyAdminDataDomain.HOST_KEY, ServerAddress.defaultHost());
            int port = conf.getInt(TallyAdminDataDomain.PORT_KEY, ServerAddress.defaultPort());
            return new ServerAddress(host, port);
        }

        @Override
        public String getDbName() {
            return TallyAdminDataDomain.DB_NAME;
        }

    }
}
