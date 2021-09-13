package main.java.config;

/**
 * @author lionel.mangoua
 * date: 04/08/21
 */

import main.java.engine.DriverFactory;

public class GlobalEnums extends DriverFactory {

    public static String httpsString = "https://";
    public static String petStoreBaseUrl = httpsString + property.returnPropVal_api(api_fileName, "petBaseUrlPath");
    public static String petPath = property.returnPropVal_api(api_fileName, "petUrlPath");
    public static String storePath = property.returnPropVal_api(api_fileName, "storeUrlPath");
    public static String userPath = property.returnPropVal_api(api_fileName, "userUrlPath");

    //region <API>
    public enum Environment {

        PET(petStoreBaseUrl, petPath, "pet"),
        STORE(petStoreBaseUrl, storePath, "store"),
        USER(petStoreBaseUrl, userPath, "user");

        public final String baseUrl;
        public final String path;
        public final String environmentName;

        //Setters
        Environment(String baseUrl, String path, String environmentName) {

            this.baseUrl = baseUrl;
            this.path = path;
            this.environmentName = environmentName;
        }
    }
    //endregion
}
