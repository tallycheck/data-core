package com.taoswork.tallycheck.datadomain.tallybiz.subject;

import com.taoswork.tallycheck.authority.domain.user.GroupAuthority;
import com.taoswork.tallycheck.datadomain.base.entity.PersistEntity;
import com.taoswork.tallycheck.datadomain.base.entity.PersistField;
import com.taoswork.tallycheck.datadomain.base.presentation.FieldType;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;

/**
 * Created by Gao Yuan on 2016/2/29.
 */
@Entity("role")
@PersistEntity(value = "role",
        asDefaultPermissionGuardian = true)
public class Role extends GroupAuthority {

        @Reference(lazy = true)
        @PersistField(required = true, fieldType = FieldType.FOREIGN_KEY)
        protected Bu bu;

        public Bu getBu() {
                return bu;
        }

        public void setBu(Bu bu) {
                this.bu = bu;
        }
}
