package com.taoswork.tallycheck.userdetails.tallyadmin.impl;

import com.taoswork.tallycheck.datadomain.tallyadmin.AdminEmployee;
import com.taoswork.tallycheck.datadomain.tallyuser.Person;
import com.taoswork.tallycheck.datasolution.tallyadmin.TallyAdminDataService;
import com.taoswork.tallycheck.datasolution.tallyadmin.service.tallyadmin.AdminEmployeeService;
import com.taoswork.tallycheck.datasolution.tallyuser.TallyUserDataService;
import com.taoswork.tallycheck.userdetails.tallyadmin.AdminEmployeeDetails;
import com.taoswork.tallycheck.userdetails.tallyadmin.AdminEmployeeDetailsService;
import com.taoswork.tallycheck.userdetails.tallyuser.FacetDetails;
import com.taoswork.tallycheck.userdetails.tallyuser.PersonDetails;
import com.taoswork.tallycheck.userdetails.tallyuser.PersonDetailsService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * Created by Gao Yuan on 2015/5/10.
 */
//@Service(AdminEmployeeDetailsService.COMPONENT_NAME)
//unable to declare @Service component here, because we don't have bean TallyUserDataService declared in this maven module
public class AdminEmployeeDetailsServiceImpl implements AdminEmployeeDetailsService {

    @Resource(name = TallyUserDataService.COMPONENT_NAME)
    private TallyUserDataService tallyUserDataService;

    @Resource(name = TallyAdminDataService.COMPONENT_NAME)
    private TallyAdminDataService tallyAdminDataService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        PersonDetailsService personDetailsService = tallyUserDataService.getService(PersonDetailsService.COMPONENT_NAME);
        PersonDetails personDetails = personDetailsService.getPersistentPersonDetails();
        if (null == personDetails) {
            personDetails = personDetailsService.loadPersonByUsername(s);
        }
        if (personDetails == null) {
            return null;
        }

        Person person = personDetails.getPerson();
        AdminEmployeeService adminEmployeeService = tallyAdminDataService.getService(AdminEmployeeService.SERVICE_NAME);
        AdminEmployee adminEmployee = adminEmployeeService.readAdminEmployeeByPersonId(person.getId().toHexString());
        if (adminEmployee == null || adminEmployee.getId() == null) {
            return null;
        }

        FacetDetails adminFacetDetails = new AdminEmployeeDetails(personDetails, adminEmployee, "", new ArrayList<GrantedAuthority>());

        personDetails.addFacetDetails(adminFacetDetails);
        return personDetails;
    }

}
