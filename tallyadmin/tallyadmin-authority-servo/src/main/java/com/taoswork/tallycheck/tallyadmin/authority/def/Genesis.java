package com.taoswork.tallycheck.tallyadmin.authority.def;

import com.taoswork.tallycheck.authority.domain.ProtectionSpec;
import com.taoswork.tallycheck.authority.domain.resource.Protection;
import com.taoswork.tallycheck.authority.domain.user.GroupAuthority;
import com.taoswork.tallycheck.authority.domain.user.UserAuthority;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gao Yuan on 2016/2/24.
 */
public class Genesis {
    public static final String GENESIS_PROTECTION_SPEC = "Genesis";

    public static final String GENESIS_ALIAS1 = "genesis1";
    public static final String GENESIS_ALIAS2 = "genesis1.2";

    public static final String GENESIS_REGION = "root.region";
    public static final String GENESIS_USER_ID = "god";

    public static final Class[] GENESIS_RESOURCES = new Class[]{
            ProtectionSpec.class,
            Protection.class,
            UserAuthority.class,
            GroupAuthority.class
    };

    private ProtectionSpec makeProtectionSpec(){
        ProtectionSpec protectionSpec = new ProtectionSpec();
        protectionSpec.setSpecName(GENESIS_PROTECTION_SPEC);

        protectionSpec.addAliases(Genesis.class.getName(),
                new String[]{GENESIS_ALIAS1, GENESIS_ALIAS2});

        return protectionSpec;
    }

    private Protection[] makeSecuredResource(){
        List<Protection> srs = new ArrayList<Protection>();
        for(Class resClz : GENESIS_RESOURCES){
            srs.add(makeSecuredResource(resClz));
        }
        return srs.toArray(new Protection[]{});
    }

//    private UserAuthority makeRootPerson(){
//        UserAuthority pp = new UserAuthority();
//        pp.setProtectionSpec(GENESIS_PROTECTION_SPEC);
//        pp.setProtectionRegion(GENESIS_REGION);
//        pp.setOwnerId(GENESIS_USER_ID);
//
//        //ProtectionSpec.class
//        {
//            Permission rperm = new Permission();
//            rperm.setResource(ProtectionSpec.class.getName());
//            rperm.setAccess(ResourceAccess.createByAccess(Access.Read));
//        }
//        //Protection.class
//        {
//            Permission rperm = new Permission();
//            rperm.setResource(Protection.class.getName());
//            rperm.setAccess(ResourceAccess.createByAccess(Access.Read));
//        }
//        //SKAuthority.class
//        {
//            Permission rperm = new Permission();
//            rperm.setResource(UserAuthority.class.getName());
//            rperm.setAccess(ResourceAccess.createByAccess(Access.Read));
//        }
//        //GroupAuthority.class
//        {
//            Permission rperm = new Permission();
//            rperm.setResource(GroupAuthority.class.getName());
//            rperm.setAccess(ResourceAccess.createByAccess(Access.Read));
//        }
//        return pp;
//    }

    private Protection makeSecuredResource(Class resourceClz){
        Protection sr = new Protection();
        sr.setProtectionSpec(GENESIS_PROTECTION_SPEC);
        sr.setProtectionRegion(GENESIS_REGION);
        sr.setResource(resourceClz.getName());

        return sr;
    }
//
//    public void makeGenesisSetting(Datastore datastore){
//        datastore.save(makeProtectionSpec());
//        datastore.save(makeSecuredResource());
//        datastore.save(makeRootPerson());
//    }
}
