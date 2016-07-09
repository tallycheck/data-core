package com.taoswork.tallycheck.datadomain.tallybiz.work;


import com.taoswork.tallycheck.datadomain.base.entity.CollectionField;
import com.taoswork.tallycheck.datadomain.base.entity.CollectionMode;
import com.taoswork.tallycheck.datadomain.base.entity.PersistEntity;
import com.taoswork.tallycheck.datadomain.base.entity.PersistField;
import com.taoswork.tallycheck.datadomain.base.presentation.FieldType;
import com.taoswork.tallycheck.datadomain.onmongo.AbstractDocument;
import com.taoswork.tallycheck.datadomain.tallybiz.subject.Asset;
import com.taoswork.tallycheck.datadomain.tallybiz.subject.Bu;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;

import java.util.List;

/**
 * Created by Gao Yuan on 2015/4/16.
 */
@Entity("workplan")
@PersistEntity("workplan")
public class WorkPlan extends AbstractDocument {
    @PersistField(fieldType = FieldType.NAME)
    protected String name;
    protected String description;

    @Reference
    @PersistField(fieldType = FieldType.FOREIGN_KEY)
    protected Bu host;

    @Reference
    @PersistField(fieldType = FieldType.FOREIGN_KEY)
    protected Asset asset;

    @CollectionField(mode = CollectionMode.Basic)
    protected List<WorkPlanUnit> planUnits;

    @Reference(lazy = true)
    @CollectionField(mode = CollectionMode.Entity)
    protected List<WorkTicket> tickets;

//    //an cached index?
//    @Reference(lazy = true)
//    @PersistField(fieldType = FieldType.FOREIGN_KEY)
//    protected WorkTicket nextTicket;
//\


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

    public Bu getHost() {
        return host;
    }

    public void setHost(Bu host) {
        this.host = host;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public List<WorkPlanUnit> getPlanUnits() {
        return planUnits;
    }

    public void setPlanUnits(List<WorkPlanUnit> planUnits) {
        this.planUnits = planUnits;
    }

    public List<WorkTicket> getTickets() {
        return tickets;
    }

    public void setTickets(List<WorkTicket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public String getInstanceName() {
        return name;
    }
}
