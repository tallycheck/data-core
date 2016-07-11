package com.taoswork.tallycheck.datasolution.tallybus.conf;
import com.taoswork.tallycheck.datadomain.tallybus.TallyBusDataDomain;
import com.taoswork.tallycheck.datasolution.annotations.DaoMark;
import com.taoswork.tallycheck.datasolution.annotations.EntityServiceMark;
import com.taoswork.tallycheck.datasolution.mongo.config.MongoPersistableConfiguration;
import com.taoswork.tallycheck.datasolution.tallybus.TallyBusDataSolution;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gao Yuan on 2015/7/6.
 */
@Configuration
@ComponentScan(
        basePackageClasses = TallyBusDataSolution.class,
        includeFilters = {@ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                value = {
                        DaoMark.class,
                        EntityServiceMark.class})},
        excludeFilters = {@ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                value = {Configuration.class}
        )}
)
public class TallyBusPersistableConfiguration
        extends MongoPersistableConfiguration {
    @Override
    protected Class<?>[] createPersistableEntities() {
        List<Class> classes = new ArrayList<Class>();
        CollectionUtils.addAll(classes, TallyBusDataDomain.persistableEntities());
        return classes.toArray(new Class[]{});
    }
}
