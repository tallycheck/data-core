package com.taoswork.tallycheck.authentication;

/**
 * Created by gaoyuan on 7/11/16.
 */
public class UserAuthenticationServiceMock implements UserAuthenticationService {
    @Override
    public PasswordSetSpec getPasswordSetSpec() {
        return null;
    }

    @Override
    public boolean setPassword(String userId, String rawPasswordCheck, String encryptedPassword) {
        return false;
    }

    @Override
    public boolean checkPassword(String userId, String rawPassword) {
        return false;
    }

    @Override
    public boolean checkPasswordByAnyIdentity(String identity, String rawPassword) {
        return false;
    }
}
