package com.taoswork.tallycheck.datasolution.tallyadmin.dao;

import com.taoswork.tallycheck.datadomain.tallyadmin.AdminEmployee;
import com.taoswork.tallycheck.datasolution.core.entity.IDao;

/**
 * Created by Gao Yuan on 2015/5/10.
 */
public interface AdminEmployeeDao extends IDao {
    public static final String COMPONENT_NAME = PREFIX + "AdminEmployeeDao";

    AdminEmployee readAdminEmployeeByPersonId(String id);

    AdminEmployee save(AdminEmployee employee);
}
