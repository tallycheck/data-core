package com.taoswork.tallycheck.datadomain.tallybus;

import org.junit.Assert;
import org.junit.Test;
import org.mongodb.morphia.Morphia;

/**
 * Created by Gao Yuan on 2015/10/19.
 */
public class TallyBusDataDomainTest {
    @Test
    public void testCreateDb() {
        try {
            Morphia morphia = new Morphia();
            morphia.map(TallyBusDataDomain.persistableEntities());
        } catch (Exception exp) {
            Assert.fail();
        }
    }
}
