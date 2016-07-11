package com.taoswork.tallycheck.authentication;

import com.taoswork.tallycheck.general.solution.cryptology.RSAUtility;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by gaoyuan on 16-6-13.
 */
final public class PasswordSetSpec {
    public PasswordSetSpec(BigInteger modulus, BigInteger exponent, int version) {
        try {
            publicKey = RSAUtility.getPublicKey(modulus, exponent);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
        this.version = version;
    }

    final public RSAPublicKey publicKey;

    final public int version;


}
