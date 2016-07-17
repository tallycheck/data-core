package com.taoswork.tallycheck.datadomain.tallyadmin;

/**
 * Created by Gao Yuan on 2015/5/12.
 */
public class TallyAdminDataDomain {

    public static Class<?>[] persistableEntities() {
        return new Class<?>[]{
                AdminProtectionSpec.class,
                AdminProtection.class,
                AdminEmployee.class,
                AdminGroup.class,
        };
    }

    public static final String HOST_KEY = "tallyadmin.db.mongo.host";
    public static final String PORT_KEY = "tallyadmin.db.mongo.port";
    public static final String DB_NAME = "tally-admin";

}
