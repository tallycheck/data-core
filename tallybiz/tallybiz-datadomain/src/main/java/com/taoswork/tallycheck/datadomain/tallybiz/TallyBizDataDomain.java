package com.taoswork.tallycheck.datadomain.tallybiz;

import com.taoswork.tallycheck.datadomain.tallybiz.module.ModuleUsage;
import com.taoswork.tallycheck.datadomain.tallybiz.subject.*;
import com.taoswork.tallycheck.datadomain.tallybiz.work.WorkFeedback;
import com.taoswork.tallycheck.datadomain.tallybiz.work.WorkPlan;
import com.taoswork.tallycheck.datadomain.tallybiz.work.WorkTicket;

/**
 * Created by Gao Yuan on 2015/10/5.
 */
public class TallyBizDataDomain {
    public static Class[] persistableEntities(){
        return new Class[]{
                //subject
                Asset.class,
                Bu.class,
                Bp.class,
                Employee.class,
                Role.class,

                //module
                ModuleUsage.class,

                //work
                WorkPlan.class,
                WorkTicket.class,
                WorkFeedback.class
        };
    }
}
