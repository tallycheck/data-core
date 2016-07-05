package com.taoswork.tallycheck.datadomain.tallyuser.validation;

import com.taoswork.tallycheck.datadomain.base.entity.validation.BaseEntityValidator;
import com.taoswork.tallycheck.datadomain.base.entity.validation.error.EntityValidationErrors;
import com.taoswork.tallycheck.datadomain.tallyuser.Person;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Gao Yuan on 2015/10/4.
 */
public class PersonValidator extends BaseEntityValidator<Person> {
    @Override
    public boolean doValidate(Person entity, EntityValidationErrors validationErrors) {
        boolean noEmail = StringUtils.isEmpty(entity.getEmail());
        boolean noPhone = StringUtils.isEmpty(entity.getMobile());
        if (noEmail && noPhone) {
            String errorCode = "person.error.email.or.phone.required";
            validationErrors.appendError(errorCode);
            validationErrors.appendFieldError("email", errorCode);
            validationErrors.appendFieldError("mobile", errorCode);
            return false;
        }
        return true;
    }
}
