package com.taoswork.tallybook.business.dataservice.tallyuser.service.userdetails;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by Gao Yuan on 2015/5/14.
 */
public interface PersonDetailsService extends UserDetailsService {
    public static final String ANONYMOUS_USER_NAME = "anonymousUser";

    public static final String COMPONENT_NAME = "PersonDetailsService";

    //
    //    private Boolean userDbHasData = null;
    PersonDetails loadPersonByUsername(String username) throws UsernameNotFoundException;

    public PersonDetails getPersistentPersonDetails();
}
