package com.taoswork.tallycheck.tallyuser;

import com.taoswork.tallycheck.datadomain.tallyuser.Person;

/**
 * Created by gaoyuan on 16-6-11.
 */
public interface UserCertificationService {

    PasswordSetSpec getPasswordSetSpec();

    boolean setPassword(String userId, String rawPasswordCheck, String encryptedPassword);

    boolean checkPassword(String userId, String rawPassword);

    boolean checkPasswordByAnyIdentity(String identity, String rawPassword);

    Person getPersonByAnyIdentity(String identity);

}
