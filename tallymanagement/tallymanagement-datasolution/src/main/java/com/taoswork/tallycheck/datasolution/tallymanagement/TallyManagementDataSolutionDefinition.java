package com.taoswork.tallycheck.datasolution.tallymanagement;


import com.taoswork.tallycheck.datasolution.IDataSolutionDefinition;

/**
 * Created by Gao Yuan on 2015/6/5.
 */
public class TallyManagementDataSolutionDefinition implements IDataSolutionDefinition {
    //DataServiceName
    public final static String DATA_SOLUTION_NAME = "TallyManagementDataSolution";

    //TMANAGEMENT_DSD : Data Source Definition
    public final static String TMANAGEMENT_DSD = "tallymanagement";

    public static final String TMANAGEMENT_ENTITY_MESSAGES = ENTITY_MESSAGES_FILE_PREFIX +
            "tallymanagement/";

    public static final String TMANAGEMENT_ERROR_MESSAGES = ERROR_MESSAGES_FILE_PREFIX +
            "tallymanagement/";

    public static final String TMANAGEMENT_RUNTIME_PROPERTIES = RUNTIME_PROPERTIES_FILE_PREFIX +
            "tallymanagement/";

    @Override
    public String getDataSolutionName() {
        return DATA_SOLUTION_NAME;
    }

    @Override
    public String getEntityMessageDirectory() {
        return TMANAGEMENT_ENTITY_MESSAGES;
    }

    @Override
    public String getErrorMessageDirectory() {
        return TMANAGEMENT_ERROR_MESSAGES;
    }

    @Override
    public String getPropertiesResourceDirectory() {
        return TMANAGEMENT_RUNTIME_PROPERTIES;
    }

    @Override
    public Class[] getExtraConfig() {
        return new Class[0];
    }
}
