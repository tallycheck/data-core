package com.taoswork.tallycheck.tallyadmin.authority.security;

import com.taoswork.tallycheck.authority.provider.AuthorityProviderDelegate;
import com.taoswork.tallycheck.authority.provider.IAuthorityProvider;
import com.taoswork.tallycheck.datasolution.IDataSolution;
import com.taoswork.tallycheck.tallyadmin.authority.AdminAuthorityProvider;
import com.taoswork.tallycheck.tallyadmin.authority.conf.TAdminAuthoritySpecifiedConfiguration;

/**
 * Created by gaoyuan on 7/6/16.
 */
public class AdminAuthorityProviderImpl
        extends AuthorityProviderDelegate
        implements AdminAuthorityProvider {

   protected IDataSolution dataSolution;


    public AdminAuthorityProviderImpl() {
    }

    public void setDataSolution(IDataSolution dataSolution) {
        this.dataSolution = dataSolution;
        super.setAuthorityProvider ((IAuthorityProvider) dataSolution.getService(TAdminAuthoritySpecifiedConfiguration.ADMIN_PERMISSION_PROVIDER));
    }
}
