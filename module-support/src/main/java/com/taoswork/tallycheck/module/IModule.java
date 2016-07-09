package com.taoswork.tallycheck.module;

import com.taoswork.tallycheck.module.io.AssetCategory;
import com.taoswork.tallycheck.module.io.AssetFacet;
import com.taoswork.tallycheck.module.io.Sheet;

import java.util.Collection;
import java.util.Date;

/**
 * Created by Gao Yuan on 2016/3/9.
 */
public interface IModule {

    String getName();

    String getFullName();

    String getDescription();

    String getProducer();

    String getVersion();

    Date getUpdateDate();

    Collection<String> getCategories();

    AssetCategory getCategory(String category);

    Collection<String> getSheets();

    Sheet getSheet(String sheet);

    AssetFacet getFacet(String facet);
}
