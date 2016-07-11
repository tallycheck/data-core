package com.taoswork.tallycheck.tallyadmin.authority.conf;

import com.taoswork.tallycheck.authority.provider.AuthorityProviderImpl;
import com.taoswork.tallycheck.authority.provider.IAuthorityProvider;
import com.taoswork.tallycheck.datadomain.tallyadmin.AdminEmployee;
import com.taoswork.tallycheck.datadomain.tallyadmin.AdminGroup;
import com.taoswork.tallycheck.datasolution.mongo.core.entityservice.MongoEntityService;
import com.taoswork.tallycheck.datasolution.service.IEntityService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * Created by Gao Yuan on 2016/2/26.
 */
@Configuration
public class TAdminAuthoritySpecifiedConfiguration {
    public static final String ADMIN_PERMISSION_PROVIDER = "AdminPermissionProvider";

    @Resource(name = IEntityService.COMPONENT_NAME)
    protected MongoEntityService entityService;

    @Bean(name = ADMIN_PERMISSION_PROVIDER)
    public IAuthorityProvider getAuthorityProvider() {
        return new AuthorityProviderImpl(entityService,
                AdminEmployee.class, AdminGroup.class);
    }
}