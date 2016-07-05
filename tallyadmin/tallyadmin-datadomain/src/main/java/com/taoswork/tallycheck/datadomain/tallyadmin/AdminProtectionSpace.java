package com.taoswork.tallycheck.datadomain.tallyadmin;

import com.taoswork.tallycheck.authority.domain.ProtectionSpace;
import org.mongodb.morphia.annotations.Entity;

/**
 * Created by Gao Yuan on 2016/3/1.
 */
@Entity("adminprotectspace")
public class AdminProtectionSpace extends ProtectionSpace {
    public static final String COMMON_SPACE_NAME = "admin-protection-space";

    public AdminProtectionSpace() {
        this.setSpaceName(COMMON_SPACE_NAME);
    }
}
