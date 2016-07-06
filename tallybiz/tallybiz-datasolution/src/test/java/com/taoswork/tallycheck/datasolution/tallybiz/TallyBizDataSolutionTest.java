package com.taoswork.tallycheck.datasolution.tallybiz;

import com.taoswork.tallycheck.datasolution.mongo.config.TestDatasourceConfiguration;
import com.taoswork.tallycheck.datasolution.service.EntityMetaAccess;
import com.taoswork.tallycheck.datasolution.service.IEntityService;
import org.junit.Assert;
import org.junit.Test;

public class TallyBizDataSolutionTest {
    @Test
    public void testCreation() {
        try {
            TallyBizDataSolution tbDataService = new TallyBizDataSolution(TestDatasourceConfiguration.class);
            IEntityService entityService = tbDataService.getService(IEntityService.COMPONENT_NAME);
            EntityMetaAccess entityMetaAccess = tbDataService.getService(EntityMetaAccess.COMPONENT_NAME);
            Assert.assertNotNull(entityService);
            Assert.assertNotNull(entityMetaAccess);
        } catch (Exception e) {
            Assert.fail();
        }
    }
}
