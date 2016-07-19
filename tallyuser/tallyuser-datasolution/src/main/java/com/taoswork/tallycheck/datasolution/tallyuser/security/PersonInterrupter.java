package com.taoswork.tallycheck.datasolution.tallyuser.security;

import com.taoswork.tallycheck.datadomain.tallyuser.Person;
import com.taoswork.tallycheck.datadomain.tallyuser.impl.PersonImpl;
import com.taoswork.tallycheck.dataservice.operator.Operator;
import com.taoswork.tallycheck.datasolution.security.AccessInterrupterTyped;
import org.bson.types.ObjectId;

/**
 * Created by gaoyuan on 7/18/16.
 */
public class PersonInterrupter extends AccessInterrupterTyped<Person> {
    public PersonInterrupter() {
        super(PersonImpl.class);
    }

    @Override
    public void probeDeleteTyped(Operator operator, Person entity) throws SecurityException {
        super.probeDeleteTyped(operator, entity);
        if(entity.getId().equals(new ObjectId(operator.personId))){
            throw new SecurityException("cannot delete self");
        }
    }
}
