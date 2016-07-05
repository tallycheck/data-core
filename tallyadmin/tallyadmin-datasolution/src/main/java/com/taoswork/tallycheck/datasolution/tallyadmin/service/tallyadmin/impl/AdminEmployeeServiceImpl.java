package com.taoswork.tallycheck.datasolution.tallyadmin.service.tallyadmin.impl;

import com.taoswork.tallycheck.datadomain.tallyadmin.AdminEmployee;
import com.taoswork.tallycheck.datasolution.annotations.EntityServiceMark;
import com.taoswork.tallycheck.datasolution.core.entity.ServiceBase;
import com.taoswork.tallycheck.datasolution.tallyadmin.dao.AdminEmployeeDao;
import com.taoswork.tallycheck.datasolution.tallyadmin.service.tallyadmin.AdminEmployeeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Gao Yuan on 2015/5/12.
 */
@Service(AdminEmployeeService.SERVICE_NAME)
@EntityServiceMark(AdminEmployeeService.SERVICE_NAME)
public class AdminEmployeeServiceImpl
        extends ServiceBase
        implements AdminEmployeeService {

    @Resource(name = AdminEmployeeDao.COMPONENT_NAME)
    protected AdminEmployeeDao adminEmployeeDao;
//
//    @Resource(name = TallyUserDataService.COMPONENT_NAME)
//    protected TallyUserDataService tallyUserDataService;

    @Override
    public AdminEmployee readAdminEmployeeByPersonId(String personId) {
        return adminEmployeeDao.readAdminEmployeeByPersonId(personId);
    }

    @Override
    public AdminEmployee saveAdminEmployee(AdminEmployee employee) {
        return adminEmployeeDao.save(employee);
    }
}
