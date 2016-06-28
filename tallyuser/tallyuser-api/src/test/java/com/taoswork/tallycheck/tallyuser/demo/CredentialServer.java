package com.taoswork.tallycheck.tallyuser.demo;

import com.taoswork.tallycheck.general.solution.cryptology.RSAUtility;
import com.taoswork.tallycheck.general.solution.quickinterface.DataHolder;
import com.taoswork.tallycheck.tallyuser.PasswordSetSpec;
import com.taoswork.tallycheck.tallyuser.UserCertificationService;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;

import java.util.Map;

import java.security.NoSuchAlgorithmException;

/**
 * Created by gaoyuan on 16-6-13.
 */
public class CredentialServer implements UserCertificationService {
    private final Map<String, String> encodedPws = new HashMap<String, String>();

    public CredentialServer() throws NoSuchAlgorithmException {
        passwordEncoder = new StandardPasswordEncoder("tally-VR(*GF05K*");

        DataHolder<RSAPublicKey> publicKeyDataHolder = new DataHolder<RSAPublicKey>();
        DataHolder<RSAPrivateKey> privateKeyDataHolder = new DataHolder<RSAPrivateKey>();
        RSAUtility.generateKeyPair(publicKeyDataHolder, privateKeyDataHolder);
        setPublicKey = publicKeyDataHolder.data;
        setPrivateKey = privateKeyDataHolder.data;
    }

    private final StandardPasswordEncoder passwordEncoder;

    private final RSAPublicKey setPublicKey;
    private final RSAPrivateKey setPrivateKey;
    private int setVersion = 0;

    @Override
    public PasswordSetSpec getPasswordSetSpec() {
        return new PasswordSetSpec(setPublicKey.getModulus(), setPublicKey.getPublicExponent(), setVersion);
    }

    @Override
    public boolean setPassword(String userId, String rawPasswordCheck, String encryptedPassword) {
        String currentPwd = encodedPws.getOrDefault(userId, "");
        boolean checkPass = false;
        if("".equals(currentPwd)){
            checkPass = true;
        }else {
            checkPass = passwordEncoder.matches(rawPasswordCheck, currentPwd);
        }
        try {
            if(checkPass){
                String rawPwd = RSAUtility.decrypt(encryptedPassword, setPrivateKey);
                String encoded = passwordEncoder.encode(rawPwd);
                encodedPws.put(userId, encoded);
                return true;
            }
        } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IOException e) {
            return false;
        }
        return false;
    }

    @Override
    public boolean checkPassword(String userId, String rawPassword) {
        String encodedPw = encodedPws.getOrDefault(userId, "");
        return passwordEncoder.matches(rawPassword, encodedPw);
    }

    @Override
    public boolean checkPasswordByAnyIdentity(String identity, String rawPassword) {
        return checkPassword(identity, rawPassword);
    }
}
