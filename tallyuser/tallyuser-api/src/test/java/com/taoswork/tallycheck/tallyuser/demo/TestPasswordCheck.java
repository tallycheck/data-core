package com.taoswork.tallycheck.tallyuser.demo;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;

import java.util.Map;

import com.taoswork.tallycheck.general.solution.cryptology.RSAUtility;
import com.taoswork.tallycheck.tallyuser.EncryptPasswordHelper;
import com.taoswork.tallycheck.tallyuser.PasswordSetSpec;
import org.junit.Assert;
import org.junit.Test;

import javax.crypto.NoSuchPaddingException;


/**
 * Created by gaoyuan on 16-6-13.
 */
public class TestPasswordCheck {
    @Test
    public void testPasswordSettingChecking()  {
        try {
            CredentialServer cp = new CredentialServer();
            Map<String, String> passwords = new HashMap<String, String>();
            passwords.put("aaa", "4576tg");
            passwords.put("bbb", "trdfvbj");
            passwords.put("ccc", "tvgh l");
            passwords.put("abc", "dch");
            passwords.put("bca", "oihnjh");
            passwords.put("cab", "rtc o");

            PasswordSetSpec sspec = cp.getPasswordSetSpec();
            RSAPublicKey publicKey = sspec.publicKey;

//        sspec.publicKey.
            for (Map.Entry<String, String> entry : passwords.entrySet()) {
                String passwd = EncryptPasswordHelper.encrypt(publicKey, entry.getValue());
                boolean setOk = cp.setPassword(entry.getKey(), "", passwd);
                Assert.assertTrue(setOk);
            }

            for (Map.Entry<String, String> entry : passwords.entrySet()) {
                String correctPwd = entry.getValue();
                boolean shouldTrue = cp.checkPassword(entry.getKey(), correctPwd);
                boolean shouldFalse = cp.checkPassword(entry.getKey(), entry.getKey());
                Assert.assertTrue(shouldTrue);
                Assert.assertFalse(shouldFalse);
            }
        } catch (NoSuchAlgorithmException e) {
            Assert.fail();
        }
    }
}
