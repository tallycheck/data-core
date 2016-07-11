package com.taoswork.tallycheck.datadomain.tallybus;

/**
 * Created by Gao Yuan on 2016/2/29.
 */
public class TallyBusDataDomain {
    public static Class[] persistableEntities(){
        return new Class[]{
                ModuleEntry.class,
        };
    }
}
