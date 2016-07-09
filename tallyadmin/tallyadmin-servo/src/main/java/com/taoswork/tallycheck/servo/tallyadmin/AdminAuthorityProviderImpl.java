package com.taoswork.tallycheck.servo.tallyadmin;

import com.taoswork.tallycheck.authority.provider.AuthorityProviderDelegate;
import com.taoswork.tallycheck.authority.provider.IAuthorityProvider;
import com.taoswork.tallycheck.datasolution.tallyadmin.TallyAdminDataSolution;
import com.taoswork.tallycheck.datasolution.tallyadmin.conf.AdminSpecifiedConfiguration;
import com.taoswork.tallycheck.tallyadmin.AdminAuthorityProvider;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by gaoyuan on 7/6/16.
 */
public class AdminAuthorityProviderImpl
        extends AuthorityProviderDelegate
        implements AdminAuthorityProvider, ApplicationContextAware {

    public AdminAuthorityProviderImpl() {
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        TallyAdminDataSolution dataSolution = (TallyAdminDataSolution) applicationContext.getBean("tallyAdminDataSolution");
        super.setAuthorityProvider ((IAuthorityProvider) dataSolution.getService(AdminSpecifiedConfiguration.ADMIN_PERMISSION_PROVIDER));
    }
}
