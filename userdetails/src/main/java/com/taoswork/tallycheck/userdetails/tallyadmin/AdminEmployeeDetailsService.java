package com.taoswork.tallycheck.userdetails.tallyadmin;

import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by Gao Yuan on 2015/5/14.
 */
public interface AdminEmployeeDetailsService extends UserDetailsService {
    public static final String COMPONENT_NAME = "adminEmployeeDetailsService";
}
