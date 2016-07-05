package com.taoswork.tallycheck.datadomain.tallyadmin;

import com.taoswork.tallycheck.authority.domain.user.GroupAuthority;
import com.taoswork.tallycheck.datadomain.base.entity.PersistEntity;
import org.mongodb.morphia.annotations.Entity;

/**
 * Created by Gao Yuan on 2016/2/29.
 */
@Entity("admingroup")
@PersistEntity(value = "admingroup",
        asDefaultPermissionGuardian = true)
public class AdminGroup extends GroupAuthority {
}
