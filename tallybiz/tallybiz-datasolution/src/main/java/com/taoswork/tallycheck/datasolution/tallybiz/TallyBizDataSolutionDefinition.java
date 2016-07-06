package com.taoswork.tallycheck.datasolution.tallybiz;

import com.taoswork.tallycheck.datasolution.IDataSolutionDefinition;

/**
 * Created by Gao Yuan on 2015/6/5.
 */
public class TallyBizDataSolutionDefinition implements IDataSolutionDefinition {
    //DataServiceName
    public final static String DATA_SOLUTION_NAME = "TallyBizDataSolution";

    //TBUSINESS_DSD : Data Source Definition
    public final static String TBUSINESS_DSD = "tallybiz";


    public static final String TBUSINESS_ENTITY_MESSAGES = ENTITY_MESSAGES_FILE_PREFIX +
            "tallybiz/";

    public static final String TBUSINESS_ERROR_MESSAGES = ERROR_MESSAGES_FILE_PREFIX +
            "tallybiz/";

    public static final String TBUSINESS_RUNTIME_PROPERTIES = RUNTIME_PROPERTIES_FILE_PREFIX +
            "tallybiz/";

    @Override
    public String getDataSolutionName() {
        return DATA_SOLUTION_NAME;
    }

    @Override
    public String getEntityMessageDirectory() {
        return TBUSINESS_ENTITY_MESSAGES;
    }

    @Override
    public String getErrorMessageDirectory() {
        return TBUSINESS_ERROR_MESSAGES;
    }

    @Override
    public String getPropertiesResourceDirectory() {
        return TBUSINESS_RUNTIME_PROPERTIES;
    }

    @Override
    public Class[] getExtraConfig() {
        return new Class[0];
    }
}
