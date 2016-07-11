package com.taoswork.tallycheck.authentication.servo;

import com.taoswork.tallycheck.authority.provider.AllPassAuthorityProvider;
import com.taoswork.tallycheck.datasolution.annotations.DataSolutionMark;
import com.taoswork.tallycheck.datasolution.tallyuser.TallyUserDataSolution;

/**
 * Created by Gao Yuan on 2015/5/10.
 */
@DataSolutionMark
public class UserAuthenticationDataSolution
        extends TallyUserDataSolution {
    public UserAuthenticationDataSolution() {
        super();
        this.setAuthorityProvider(new AllPassAuthorityProvider());
    }
}
