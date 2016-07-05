package com.taoswork.tallycheck.datasolution.tallyuser.dao;

import com.taoswork.tallycheck.datadomain.tallyuser.PersonCertification;
import com.taoswork.tallycheck.datasolution.core.entity.IDao;

/**
 * Created by Gao Yuan on 2015/5/3.
 */
public interface PersonCertificationDao extends IDao {
    public static final String COMPONENT_NAME = PREFIX + "PersonCertificationDao";

    PersonCertification readPersonCertificationById(String id);

    PersonCertification readPersonCertificationByPersonCode(String userCode);

    boolean updateCertification(String userCode, String password);
}
