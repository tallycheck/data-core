package com.taoswork.tallycheck.tallyuser;

import com.taoswork.tallycheck.general.solution.cryptology.RSAUtility;
import com.taoswork.tallycheck.general.solution.exception.UnexpectedException;

import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;

/**
 * Created by Gao Yuan on 2016/6/28.
 */
public class EncryptPasswordHelper {
    public static String encrypt(RSAPublicKey publicKey, String rawPassword) {
        try {
            return RSAUtility.encrypt(rawPassword, publicKey);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | IOException e) {
            throw new UnexpectedException(e);
        }
    }

    public static String encrypt(PasswordSetSpec spec, String rawPassword) {
        return encrypt(spec.publicKey, rawPassword);
    }
}
