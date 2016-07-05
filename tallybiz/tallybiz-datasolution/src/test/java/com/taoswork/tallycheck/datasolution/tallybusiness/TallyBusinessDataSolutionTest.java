package com.taoswork.tallycheck.datasolution.tallybusiness;

import com.taoswork.tallycheck.datasolution.mongo.config.TestDatasourceConfiguration;
import com.taoswork.tallycheck.datasolution.service.EntityMetaAccess;
import com.taoswork.tallycheck.datasolution.service.IEntityService;
import org.junit.Assert;
import org.junit.Test;

public class TallyBusinessDataSolutionTest {
    @Test
    public void testCreation() {
        try {
            TallyBusinessDataSolution tbDataService = new TallyBusinessDataSolution(TestDatasourceConfiguration.class);
            IEntityService entityService = tbDataService.getService(IEntityService.COMPONENT_NAME);
            EntityMetaAccess entityMetaAccess = tbDataService.getService(EntityMetaAccess.COMPONENT_NAME);
            Assert.assertNotNull(entityService);
            Assert.assertNotNull(entityMetaAccess);
        } catch (Exception e) {
            Assert.fail();
        }
    }
}
