package com.taoswork.tallycheck.datadomain.tallybiz.subject;

import com.taoswork.tallycheck.datadomain.base.entity.CollectionField;
import com.taoswork.tallycheck.datadomain.base.entity.CollectionMode;
import com.taoswork.tallycheck.datadomain.base.entity.PersistEntity;
import com.taoswork.tallycheck.datadomain.base.entity.PersistField;
import com.taoswork.tallycheck.datadomain.base.presentation.FieldType;
import com.taoswork.tallycheck.datadomain.base.presentation.PresentationField;
import com.taoswork.tallycheck.datadomain.base.presentation.Visibility;
import com.taoswork.tallycheck.datadomain.onmongo.AbstractDocument;
import com.taoswork.tallycheck.datadomain.tallybiz.Privacy;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;

import java.util.List;

/**
 * Created by Gao Yuan on 2015/4/14.
 * <p>
 * Asset: owned by business partner, may have work plan on that.
 */
@Entity("asset")
@PersistEntity("asset")
public class Asset extends AbstractDocument {

    @PersistField(fieldType = FieldType.NAME, required = true)
    @PresentationField(order = 2)
    protected String name;

    @PresentationField(order = 4, visibility = Visibility.GRID_HIDE)
    protected String description;

    @PersistField(fieldType = FieldType.ENUMERATION)
    protected Privacy privacy;
//
//    @Reference
//    @CollectionField(mode = CollectionMode.Entity)
//    protected List<WorkPlan> workPlans;

    @Reference
    //inverse controlled fields:
    @PersistField(fieldType = FieldType.ADDITIONAL_FOREIGN_KEY)
    protected Bu owner;

    @CollectionField(mode = CollectionMode.Basic)
    private List<AssetFacet> facets;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //    public List<WorkPlan> getWorkPlans() {
//        return workPlans;
//    }
//
//    public void setWorkPlans(List<WorkPlan> workPlans) {
//        this.workPlans = workPlans;
//    }
//
    public Bu getOwner() {
        return owner;
    }

    public void setOwner(Bu owner) {
        this.owner = owner;
    }

    public Privacy getPrivacy() {
        return privacy;
    }

    public void setPrivacy(Privacy privacy) {
        this.privacy = privacy;
    }

    public List<AssetFacet> getFacets() {
        return facets;
    }

    public void setFacets(List<AssetFacet> facets) {
        this.facets = facets;
    }
}
