package com.taoswork.tallycheck.datadomain.tallyuser;

import com.taoswork.tallycheck.datadomain.tallyuser.impl.PersonCertificationImpl;
import com.taoswork.tallycheck.datadomain.tallyuser.impl.PersonFacetCertificationImpl;
import com.taoswork.tallycheck.datadomain.tallyuser.impl.PersonImpl;

/**
 * Created by Gao Yuan on 2015/5/12.
 */
public final class TallyUserDataDomain {
    public static Class[] persistableEntities() {
        return new Class[]{
                PersonImpl.class,
                PersonCertificationImpl.class,
                PersonFacetCertificationImpl.class
        };
    }

}
