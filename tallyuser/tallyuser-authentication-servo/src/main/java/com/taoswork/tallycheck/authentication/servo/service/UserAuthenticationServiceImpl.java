package com.taoswork.tallycheck.authentication.servo.service;

import com.taoswork.tallycheck.authentication.PasswordSetSpec;
import com.taoswork.tallycheck.authentication.UserAuthenticationService;
import com.taoswork.tallycheck.authentication.servo.UserAuthenticationDataSolution;
import com.taoswork.tallycheck.datadomain.tallyuser.Person;
import com.taoswork.tallycheck.datadomain.tallyuser.PersonCertification;
import com.taoswork.tallycheck.datasolution.tallyuser.service.PersonService;
import com.taoswork.tallycheck.general.solution.conf.TallycheckConfiguration;
import com.taoswork.tallycheck.general.solution.cryptology.RSAUtility;
import com.taoswork.tallycheck.general.solution.exception.UnexpectedException;
import com.taoswork.tallycheck.general.solution.quickinterface.DataHolder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;


/**
 * Created by Gao Yuan on 2016/6/27.
 */
public class UserAuthenticationServiceImpl
        implements UserAuthenticationService {

    private UserAuthenticationDataSolution dataSolution;
    private final StandardPasswordEncoder passwordEncoder;

    private RSAPublicKey setPublicKey;
    private RSAPrivateKey setPrivateKey;
    private int setVersion = 0;

    private PersonService personService;

    public UserAuthenticationServiceImpl() {
        String secret = TallycheckConfiguration.instance().getString("tallyuser.standard.password.encoder.secret", "tallyuser");
        passwordEncoder = new StandardPasswordEncoder(secret);
        updateVersion();
    }

    public void setDataSolution(UserAuthenticationDataSolution dataSolution) {
        this.dataSolution = dataSolution;
        personService = dataSolution.getService(PersonService.SERVICE_NAME);
    }

    public int updateVersion() {
        try {
            DataHolder<RSAPublicKey> publicKeyDataHolder = new DataHolder<RSAPublicKey>();
            DataHolder<RSAPrivateKey> privateKeyDataHolder = new DataHolder<RSAPrivateKey>();
            RSAUtility.generateKeyPair(publicKeyDataHolder, privateKeyDataHolder);
            setPublicKey = publicKeyDataHolder.data;
            setPrivateKey = privateKeyDataHolder.data;
            setVersion++;
            return setVersion;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public PasswordSetSpec getPasswordSetSpec() {
        return new PasswordSetSpec(setPublicKey.getModulus(), setPublicKey.getPublicExponent(), setVersion);
    }

    @Override
    public boolean setPassword(String userId, String rawPasswordCheck, String encryptedPassword) {
        Person person = personService.readPersonByID(userId);
        if(!checkPassword(person, rawPasswordCheck)){
            return false;
        }
        try {
            String rawPwd = RSAUtility.decrypt(encryptedPassword, setPrivateKey);
            String encoded = passwordEncoder.encode(rawPwd);
            personService.updatePassword(person.getUuid(), encoded);
            return true;
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | IOException e) {
            throw new UnexpectedException(e);
        }
    }

    @Override
    public boolean checkPassword(String userId, String rawPassword) {
        Person person = personService.readPersonByID(userId);
        return checkPassword(person, rawPassword);
    }

    @Override
    public boolean checkPasswordByAnyIdentity(String identity, String rawPassword){
        Person person = personService.readPersonByAnyIdentity(identity);
        return checkPassword(person, rawPassword);
    }

    protected boolean checkPassword(Person person, String rawPassword){
        if(person == null)
            return false;
        PersonCertification personCertification = personService.readPersonCertificationByUUID(person.getUuid());
        if(personCertification == null)
            return false;
        String certPwd = personCertification.getPassword();
        if(certPwd.startsWith("P:")){
            String pwd = certPwd.substring(2);
            return pwd.equals(rawPassword);
        }
        return passwordEncoder.matches(rawPassword, certPwd);
    }

}
