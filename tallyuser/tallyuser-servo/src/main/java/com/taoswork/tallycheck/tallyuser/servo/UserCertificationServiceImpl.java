package com.taoswork.tallycheck.tallyuser.servo;

import com.taoswork.tallycheck.datadomain.tallyuser.Person;
import com.taoswork.tallycheck.datadomain.tallyuser.PersonCertification;
import com.taoswork.tallycheck.dataservice.mongo.core.entityservice.MongoEntityService;
import com.taoswork.tallycheck.dataservice.service.IEntityService;
import com.taoswork.tallycheck.dataservice.tallyuser.TallyUserDataService;
import com.taoswork.tallycheck.dataservice.tallyuser.service.tallyuser.PersonService;
import com.taoswork.tallycheck.general.solution.conf.TallycheckConfiguration;
import com.taoswork.tallycheck.general.solution.cryptology.RSAUtility;
import com.taoswork.tallycheck.general.solution.exception.UnexpectedException;
import com.taoswork.tallycheck.general.solution.quickinterface.DataHolder;
import com.taoswork.tallycheck.tallyuser.PasswordSetSpec;
import com.taoswork.tallycheck.tallyuser.UserCertificationService;
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
public class UserCertificationServiceImpl implements UserCertificationService {

    private final StandardPasswordEncoder passwordEncoder;

    private RSAPublicKey setPublicKey;
    private RSAPrivateKey setPrivateKey;
    private int setVersion = 0;

    private final TallyUserDataService dataService;
    private final MongoEntityService entityService;
    private final PersonService personService;

    public UserCertificationServiceImpl() {
        dataService = new TallyUserDataService();
        entityService = dataService.getService(IEntityService.COMPONENT_NAME);
        personService = dataService.getService(PersonService.SERVICE_NAME);
        String secret = TallycheckConfiguration.instance().getString("tallyuser.standard.password.encoder.secret", "tallyuser");
        passwordEncoder = new StandardPasswordEncoder(secret);
        updateVersion();
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
