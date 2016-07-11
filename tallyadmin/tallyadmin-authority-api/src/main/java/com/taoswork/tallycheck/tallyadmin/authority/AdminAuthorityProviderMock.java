package com.taoswork.tallycheck.tallyadmin.authority;

import com.taoswork.tallycheck.authority.atom.Access;
import com.taoswork.tallycheck.authority.core.ProtectionScope;
import com.taoswork.tallycheck.authority.core.permission.IKPermission;
import com.taoswork.tallycheck.authority.provider.ResProtection;
import com.taoswork.tallycheck.authority.provider.ResProtectionWithPermission;

/**
 * Created by gaoyuan on 7/11/16.
 */
public class AdminAuthorityProviderMock implements AdminAuthorityProvider {
    @Override
    public ResProtection getProtection(ProtectionScope scope, String resourceTypeName) {
        return null;
    }

    @Override
    public IKPermission getPermission(ProtectionScope scope, String resourceTypeName, String userId) {
        return null;
    }

    @Override
    public ResProtectionWithPermission getProtectionWithPermission(ProtectionScope scope, String resourceTypeName, String userId) {
        return null;
    }

    @Override
    public boolean canAccessMappedResource(ProtectionScope scope, String virtualResource, Access requiredAccess, String userId) {
        return false;
    }
}
