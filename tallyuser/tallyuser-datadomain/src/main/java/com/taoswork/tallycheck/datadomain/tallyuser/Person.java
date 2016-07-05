package com.taoswork.tallycheck.datadomain.tallyuser;

import com.taoswork.tallycheck.datadomain.base.entity.PersistEntity;
import com.taoswork.tallycheck.datadomain.onmongo.PersistableDocument;
import com.taoswork.tallycheck.datadomain.tallyuser.gate.PersonGate;
import com.taoswork.tallycheck.datadomain.tallyuser.validation.PersonValidator;

import java.util.Date;

@PersistEntity(
        validators = {PersonValidator.class},
        valueGates = {PersonGate.class}
)
public interface Person extends PersistableDocument {

    String getName();

    Person setName(String name);

    Boolean isActive();

    Person setActive(Boolean active);

    String getUuid();

    Person setUuid(String uuid);

    Gender getGender();

    Person setGender(Gender gender);

    String getEmail();

    Person setEmail(String email);

    String getMobile();

    Person setMobile(String mobile);

    Date getCreateDate();

    void setCreateDate(Date createDate);
}
