package com.taoswork.tallycheck.datadomain.tallyadmin;

import com.taoswork.tallycheck.authority.domain.ProtectionSpec;
import org.mongodb.morphia.annotations.Entity;

/**
 * Created by Gao Yuan on 2016/3/1.
 */
@Entity("adminprotectspace")
public class AdminProtectionSpec extends ProtectionSpec {
    public static final String COMMON_SPEC_NAME = "admin-protection-spec";

    public AdminProtectionSpec() {
        this.setSpecName(COMMON_SPEC_NAME);
    }
}
