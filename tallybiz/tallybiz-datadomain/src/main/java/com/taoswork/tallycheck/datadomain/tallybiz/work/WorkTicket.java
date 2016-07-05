package com.taoswork.tallycheck.datadomain.tallybiz.work;

import com.taoswork.tallycheck.datadomain.base.entity.CollectionField;
import com.taoswork.tallycheck.datadomain.base.entity.CollectionMode;
import com.taoswork.tallycheck.datadomain.base.entity.PersistEntity;
import com.taoswork.tallycheck.datadomain.base.entity.PersistField;
import com.taoswork.tallycheck.datadomain.base.presentation.FieldType;
import com.taoswork.tallycheck.datadomain.base.presentation.PresentationField;
import com.taoswork.tallycheck.datadomain.base.presentation.Visibility;
import com.taoswork.tallycheck.datadomain.base.presentation.typed.DateCellMode;
import com.taoswork.tallycheck.datadomain.base.presentation.typed.PresentationDate;
import com.taoswork.tallycheck.datadomain.onmongo.AbstractDocument;
import com.taoswork.tallycheck.datadomain.tallybiz.subject.Employee;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;

import java.util.Date;
import java.util.List;

/**
 * Created by Gao Yuan on 2015/4/16.
 */
@Entity("workticket")
@PersistEntity("workticket")
public class WorkTicket extends AbstractDocument {
    public String module;

    public String sheet;

    public String sheetFillId;

    @Reference(lazy = true)
    @PersistField(fieldType = FieldType.FOREIGN_KEY)
    protected Employee worker;

    @Reference(lazy = true)
    @PersistField(fieldType = FieldType.FOREIGN_KEY)
    protected Employee supervisior;

    @Reference(lazy = true)
    @PersistField(fieldType = FieldType.FOREIGN_KEY)
    protected WorkPlan workPlan;

    @PersistField(fieldType = FieldType.DATE)
    @PresentationField(visibility = Visibility.GRID_HIDE)
    @PresentationDate(cellMode = DateCellMode.DateAndTime)
    protected Date startWorkingTime;
    @PersistField(fieldType = FieldType.DATE)
    @PresentationField(visibility = Visibility.GRID_HIDE)
    @PresentationDate(cellMode = DateCellMode.DateAndTime)
    protected Date endWorkingTime;

    @Reference
    @CollectionField(mode = CollectionMode.Entity)
    protected List<WorkFeedback> feedbacks;

    protected WorkTicketStatus status;

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getSheet() {
        return sheet;
    }

    public void setSheet(String sheet) {
        this.sheet = sheet;
    }

    public String getSheetFillId() {
        return sheetFillId;
    }

    public void setSheetFillId(String sheetFillId) {
        this.sheetFillId = sheetFillId;
    }

    public Employee getWorker() {
        return worker;
    }

    public void setWorker(Employee worker) {
        this.worker = worker;
    }

    public Employee getSupervisior() {
        return supervisior;
    }

    public void setSupervisior(Employee supervisior) {
        this.supervisior = supervisior;
    }

    public WorkPlan getWorkPlan() {
        return workPlan;
    }

    public void setWorkPlan(WorkPlan workPlan) {
        this.workPlan = workPlan;
    }

    public Date getStartWorkingTime() {
        return startWorkingTime;
    }

    public void setStartWorkingTime(Date startWorkingTime) {
        this.startWorkingTime = startWorkingTime;
    }

    public Date getEndWorkingTime() {
        return endWorkingTime;
    }

    public void setEndWorkingTime(Date endWorkingTime) {
        this.endWorkingTime = endWorkingTime;
    }

    public List<WorkFeedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<WorkFeedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public WorkTicketStatus getStatus() {
        return status;
    }

    public void setStatus(WorkTicketStatus status) {
        this.status = status;
    }
}
