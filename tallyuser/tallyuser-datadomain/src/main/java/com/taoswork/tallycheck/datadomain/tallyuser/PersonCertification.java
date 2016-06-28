package com.taoswork.tallycheck.datadomain.tallyuser;

import com.taoswork.tallycheck.datadomain.onmongo.PersistableDocument;

/**
 * Created by Gao Yuan on 2015/4/14.
 */
public interface PersonCertification extends PersistableDocument {
    String getPassword();

    PersonCertification setPassword(String password);

    String getUserCode();

    PersonCertification setUserCode(String userId);

    Long getLastUpdateDate();

    void setLastUpdateDate(Long lastUpdateDate);
}
