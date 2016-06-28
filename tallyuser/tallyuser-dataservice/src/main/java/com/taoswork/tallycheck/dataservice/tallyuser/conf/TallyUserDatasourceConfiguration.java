package com.taoswork.tallycheck.dataservice.tallyuser.conf;

import com.mongodb.ServerAddress;
import com.taoswork.tallycheck.dataservice.mongo.MongoDatasourceDefinition;
import com.taoswork.tallycheck.dataservice.mongo.MongoDatasourceDefinitionBase;
import com.taoswork.tallycheck.dataservice.mongo.config.MongoDatasourceConfiguration;
import com.taoswork.tallycheck.general.solution.conf.TallycheckConfiguration;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Gao Yuan on 2016/2/16.
 */
@Configuration
public class TallyUserDatasourceConfiguration extends MongoDatasourceConfiguration {
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
            String host = conf.getString("tallyuser.db.mongo.host", ServerAddress.defaultHost());
            int port = conf.getInt("tallyuser.db.mongo.port", ServerAddress.defaultPort());
            return new ServerAddress(host, port);
        }

        @Override
        public String getDbName() {
            return "tallyuser";
        }

    }
}
