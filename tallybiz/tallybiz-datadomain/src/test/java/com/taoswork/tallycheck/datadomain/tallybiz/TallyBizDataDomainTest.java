package com.taoswork.tallycheck.datadomain.tallybiz;

import org.junit.Assert;
import org.junit.Test;
import org.mongodb.morphia.Morphia;

/**
 * Created by Gao Yuan on 2015/10/19.
 */
public class TallyBizDataDomainTest {
    @Test
    public void testCreateDb() {
        try {
            Morphia morphia = new Morphia();
            morphia.map(TallyBizDataDomain.persistableEntities());
        } catch (Exception exp) {
            Assert.fail();
        }

    }
}
