package main.java.utils;

/**
 * @author lionel.mangoua
 * date: 07/08/21
 */

import main.java.config.GlobalEnums;
import main.java.engine.DriverFactory;

public class SetEnvironmentDataUtility extends DriverFactory {

    //region <setTestEnvironment>
    public void setTestEnvironment(String environmentName) {

        switch(environmentName) {
            case "pet":
                env = GlobalEnums.Environment.PET;
                setUpBaseUrl();
                break;
            case "store":
                env = GlobalEnums.Environment.STORE;
                setUpBaseUrl();
                break;
            case "user":
                env = GlobalEnums.Environment.USER;
                setUpBaseUrl();
                break;
        }

        log("Set Environment Name: '" + environmentName + "' successfully", "INFO", "text");
    }
    //endregion
}
