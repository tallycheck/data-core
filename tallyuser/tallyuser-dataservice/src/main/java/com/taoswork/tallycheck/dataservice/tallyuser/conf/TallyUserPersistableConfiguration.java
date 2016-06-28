package com.taoswork.tallycheck.dataservice.tallyuser.conf;

import com.taoswork.tallycheck.datadomain.tallyuser.TallyUserDataDomain;
import com.taoswork.tallycheck.dataservice.tallyuser.TallyUserDataService;
import com.taoswork.tallycheck.dataservice.annotations.Dao;
import com.taoswork.tallycheck.dataservice.annotations.EntityService;
import com.taoswork.tallycheck.dataservice.mongo.config.MongoPersistableConfiguration;
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
        basePackageClasses = TallyUserDataService.class,
        includeFilters = {@ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                value = {
                        Dao.class,
                        EntityService.class})},
        excludeFilters = {@ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                value = {Configuration.class}
        )}
)
public class TallyUserPersistableConfiguration
        extends MongoPersistableConfiguration {
    @Override
    protected Class<?>[] createPersistableEntities() {
        List<Class> classes = new ArrayList<Class>();
        CollectionUtils.addAll(classes, TallyUserDataDomain.persistableEntities());
        return classes.toArray(new Class[]{});
    }
}
