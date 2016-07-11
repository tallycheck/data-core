package com.taoswork.tallycheck.datasolution.tallyadmin;

import com.taoswork.tallycheck.datasolution.annotations.DataSolutionMark;
import com.taoswork.tallycheck.datasolution.mongo.config.MongoDatasourceConfiguration;
import com.taoswork.tallycheck.datasolution.mongo.core.MongoDataSolutionBase;
import com.taoswork.tallycheck.datasolution.tallyadmin.conf.TallyAdminDatasourceConfiguration;
import com.taoswork.tallycheck.datasolution.tallyadmin.conf.TallyAdminPersistableConfiguration;

/**
 * Created by Gao Yuan on 2015/5/12.
 */
@DataSolutionMark
//@Component(TallyAdminDataSolution.COMPONENT_NAME)
public class TallyAdminDataSolution
        extends MongoDataSolutionBase<
                        TallyAdminPersistableConfiguration,
                        MongoDatasourceConfiguration> {

    public static final String COMPONENT_NAME = TallyAdminDataSolutionDefinition.DATA_SOLUTION_NAME;

    public TallyAdminDataSolution() {
        this(TallyAdminDatasourceConfiguration.class);
    }

    public TallyAdminDataSolution(Class<? extends MongoDatasourceConfiguration> dSrcConfClz) {
        super(new TallyAdminDataSolutionDefinition(),
                TallyAdminPersistableConfiguration.class,
                dSrcConfClz);
    }

    @Override
    protected void postConstruct() {
        super.postConstruct();
//        final String masterPersonId = AdminEmployee.ROOT_PERSON_ID;
//        IEntityService<Persistable> entityService = this.getService(IEntityService.COMPONENT_NAME);
//        try {
//            CriteriaTransferObject cto = new CriteriaTransferObject(AdminEmployee.FN_PERSON_ID, "" + masterPersonId);
//            AdminEmployee result = entityService.queryOne(AdminEmployee.class, cto);
//            if(result == null){
//                AdminEmployee newMaster = new AdminEmployee();
//                newMaster.setPersonId(masterPersonId);
//                newMaster.setName("Admin");
//                newMaster.setTitle("Master");
//                newMaster.setProtectionSpace(AdminSecurityDefinition.PROTECTION_SPACE);
//                newMaster.setNamespace(AdminSecurityDefinition.ADMIN_TENANT);
//                entityService.create(newMaster);
//            }
//        } catch (ServiceException e) {
//            e.printStackTrace();
//        }
//        IPermissionEngine permissionEngine = this.getService(AdminSpecifiedConfiguration.ADMIN_PERMISSION_PROVIDER);
//        ISecurityVerifier securityVerifier = new SecurityVerifierOnAuthority(permissionEngine, ADMIN_DATA_PROTECTION_SCOPE, ADMIN_DATA_TENANT);
//        this.setSecurityVerifier(securityVerifier);
    }
}
