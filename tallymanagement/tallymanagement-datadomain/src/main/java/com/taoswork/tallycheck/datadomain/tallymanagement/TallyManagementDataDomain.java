package com.taoswork.tallycheck.datadomain.tallymanagement;

/**
 * Created by Gao Yuan on 2016/2/29.
 */
public class TallyManagementDataDomain {
    public static Class[] persistableEntities(){
        return new Class[]{
                ModuleEntry.class,
        };
    }
}
