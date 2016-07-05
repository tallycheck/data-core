package com.taoswork.tallycheck.datasolution.tallyadmin.service.tallyadmin;

import com.taoswork.tallycheck.datadomain.tallyadmin.AdminEmployee;
import com.taoswork.tallycheck.datasolution.core.entity.IService;

/**
 * Created by Gao Yuan on 2015/5/12.
 */
public interface AdminEmployeeService extends IService {
    public static final String SERVICE_NAME = PREFIX + "AdminEmployeeService";

    AdminEmployee readAdminEmployeeByPersonId(String personId);

    AdminEmployee saveAdminEmployee(AdminEmployee employee);
}
