package com.taoswork.tallycheck.dataservice.tallyuser.dao.impl;

import com.taoswork.tallycheck.datadomain.tallyuser.PersonCertification;
import com.taoswork.tallycheck.datadomain.tallyuser.impl.PersonCertificationImpl;
import com.taoswork.tallycheck.dataservice.tallyuser.dao.PersonCertificationDao;
import com.taoswork.tallycheck.dataservice.annotations.Dao;
import com.taoswork.tallycheck.dataservice.core.entity.DaoBase;
import com.taoswork.tallycheck.dataservice.mongo.MongoDatasourceDefinition;
import com.taoswork.tallycheck.general.extension.collections.ListUtility;
import org.bson.types.ObjectId;
import org.mongodb.morphia.AdvancedDatastore;
import org.mongodb.morphia.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by Gao Yuan on 2015/5/3.
 */
@Dao(PersonCertificationDao.COMPONENT_NAME)
@Repository(PersonCertificationDao.COMPONENT_NAME)
public class PersonCertificationDaoImpl
        extends DaoBase
        implements PersonCertificationDao {

    @Resource(name = MongoDatasourceDefinition.DATASTORE_BEAN_NAME)
    private AdvancedDatastore datastore;

    @Override
    public PersonCertification readPersonCertificationById(String id) {
        return datastore.get(PersonCertificationImpl.class, new ObjectId(id));
    }

    @Override
    public PersonCertification readPersonCertificationByPersonCode(String userCode) {
        Query<PersonCertificationImpl> query = datastore.find(PersonCertificationImpl.class, "userCode", userCode);
        query.limit(2);
        List<PersonCertificationImpl> users = query.asList();
        return ListUtility.getTheSingleElement(users);
    }

    @Override
    public boolean updateCertification(String userCode, String password) {
        Query<PersonCertificationImpl> q = datastore.createQuery(PersonCertificationImpl.class);
        q.field(PersonCertificationImpl.FIELD_NAME_USER_CODE).equal(userCode).limit(2);
        List<PersonCertificationImpl> certs = q.asList();
        if(certs.size() != 1)
            return false;

        PersonCertificationImpl cert = certs.get(0);
        cert.setPassword(password);
        cert.setLastUpdateDate(new Date().getTime());
        datastore.save(cert);
        return true;
    }
}
