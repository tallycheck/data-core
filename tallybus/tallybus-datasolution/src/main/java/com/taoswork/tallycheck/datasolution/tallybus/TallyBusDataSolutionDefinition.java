package com.taoswork.tallycheck.datasolution.tallybus;


import com.taoswork.tallycheck.datasolution.IDataSolutionDefinition;

/**
 * Created by Gao Yuan on 2015/6/5.
 */
public class TallyBusDataSolutionDefinition implements IDataSolutionDefinition {
    //DataServiceName
    public final static String DATA_SOLUTION_NAME = "TallyBusDataSolution";

    //TBUS_DSD : Data Source Definition
    public final static String TBUS_DSD = "tallybus";

    public static final String TBUS_ENTITY_MESSAGES = ENTITY_MESSAGES_FILE_PREFIX +
            "tallybus/";

    public static final String TBUS_ERROR_MESSAGES = ERROR_MESSAGES_FILE_PREFIX +
            "tallybus/";

    public static final String TBUS_RUNTIME_PROPERTIES = RUNTIME_PROPERTIES_FILE_PREFIX +
            "tallybus/";

    @Override
    public String getDataSolutionName() {
        return DATA_SOLUTION_NAME;
    }

    @Override
    public String getEntityMessageDirectory() {
        return TBUS_ENTITY_MESSAGES;
    }

    @Override
    public String getErrorMessageDirectory() {
        return TBUS_ERROR_MESSAGES;
    }

    @Override
    public String getPropertiesResourceDirectory() {
        return TBUS_RUNTIME_PROPERTIES;
    }

    @Override
    public Class[] getExtraConfig() {
        return new Class[0];
    }
}
