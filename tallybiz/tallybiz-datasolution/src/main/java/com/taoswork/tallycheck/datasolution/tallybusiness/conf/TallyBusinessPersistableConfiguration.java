package com.taoswork.tallycheck.datasolution.tallybusiness.conf;

import com.taoswork.tallycheck.authority.domain.AuthorityDomain;
import com.taoswork.tallycheck.datadomain.tallybiz.TallyBusinessDataDomain;
import com.taoswork.tallycheck.datasolution.annotations.DaoMark;
import com.taoswork.tallycheck.datasolution.annotations.EntityServiceMark;
import com.taoswork.tallycheck.datasolution.mongo.config.MongoPersistableConfiguration;
import com.taoswork.tallycheck.datasolution.tallybusiness.TallyBusinessDataSolution;
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
        basePackageClasses = TallyBusinessDataSolution.class,
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
public class TallyBusinessPersistableConfiguration
        extends MongoPersistableConfiguration {
    @Override
    protected Class<?>[] createPersistableEntities() {
        List<Class> classes = new ArrayList<Class>();
        CollectionUtils.addAll(classes, AuthorityDomain.domainEntities());
        CollectionUtils.addAll(classes, TallyBusinessDataDomain.persistableEntities());
        return classes.toArray(new Class[]{});
    }
}
