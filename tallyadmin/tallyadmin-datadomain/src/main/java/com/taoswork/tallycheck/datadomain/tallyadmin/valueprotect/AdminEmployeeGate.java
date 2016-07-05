package com.taoswork.tallycheck.datadomain.tallyadmin.valueprotect;

import com.taoswork.tallycheck.datadomain.base.entity.valuegate.BaseEntityGate;
import com.taoswork.tallycheck.datadomain.tallyadmin.AdminEmployee;
import com.taoswork.tallycheck.datadomain.tallyuser.AccountStatus;
import com.taoswork.tallycheck.datadomain.tallyuser.Person;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

public class AdminEmployeeGate extends BaseEntityGate<AdminEmployee> {
    @Override
    protected void doStore(AdminEmployee entity, AdminEmployee oldEntity) {
        Person person = entity.getPerson();
        if (person != null) {
            if (StringUtils.isEmpty(entity.getName())) {
                entity.setName(person.getName());
            }
            entity.setPersonId(person.getId().toHexString());
        }
        AccountStatus as = entity.getStatus();
        if (as == null) {
            as = new AccountStatus();
            entity.setStatus(as);
        }
        if (as.getLastLoginDate() == null) {
            as.setLastLoginDate(new Date());
        }
        if (as.getCreateDate() == null) {
            as.setCreateDate(new Date());
        }
    }

    @Override
    protected void doFetch(AdminEmployee entity) {
        AccountStatus as = entity.getStatus();
        if (as == null) {
            as = new AccountStatus();
            entity.setStatus(as);
        }
        if (as.getLastLoginDate() == null) {
            as.setLastLoginDate(new Date());
        }
        if (as.getCreateDate() == null) {
            as.setCreateDate(new Date());
        }
    }
}
