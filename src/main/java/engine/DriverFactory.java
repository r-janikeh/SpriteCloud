package main.java.engine;

/**
 * @author lionel.mangoua
 * date: 04/08/21
 */

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import main.java.config.GlobalEnums;
import main.java.utils.ExcelUtility;
import main.java.utils.PropertyFileReader;
import org.apache.log4j.PropertyConfigurator;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.lessThan;

public class DriverFactory {

    public static PropertyFileReader property = new PropertyFileReader();
    public static String api_fileName = "API";
    public static String testName;
    public static Long petID;
    public static String[][] excelData = null;
    public static String[][] petData = null;
    public static String[][] storeData = null;
    public static String[][] userData = null;
    public static Map<String, String> currentTestData = null; //to get test data from excel sheet
    public static String USER_DATA_XLSX_FILE_PATH = property.returnPropVal_api(api_fileName, "userDataSheetPath"); //TODO delete
    public static GlobalEnums.Environment env; //setup Environment
    public static final Logger LOGGER = LoggerFactory.getLogger(DriverFactory.class);
    //Headers
    public static ValidatableResponse response = null;
    public static RequestSpecBuilder builder;
    public static RequestSpecification requestSpec; //used with ValidatableResponse
    public static ResponseSpecification responseSpec; //used with ValidatableResponse
    public static ResponseSpecBuilder respec;
    public static String contentTypeJson = property.returnPropVal_api(api_fileName, "content_type_json");
    public static String contentTypeFormDataJson = property.returnPropVal_api(api_fileName, "content_type_form_data_json");
    public static String cacheControl = property.returnPropVal_api(api_fileName, "cache_control");
    public static String baseUrl = property.returnPropVal_api(api_fileName, "base_url");
    public static String path = property.returnPropVal_api(api_fileName, "path");

    @BeforeClass
    //region <startEngine>
    public void startEngine() {

        //Read excel sheets
        setUpExcelDataSheets();

        //region <To remove the warning: "log4j:WARN No appenders could be found for logger">
        Properties prop = new Properties();
        prop.setProperty("log4j.rootLogger", "WARN");
        PropertyConfigurator.configure(prop);
        //endregion

        //Set Up default environment for API tests
        env = GlobalEnums.Environment.PET;

        //Set Up Base Url
        setUpBaseUrl();
    }
    //endregion

    //API
    //region <Set Up Base Url>
    public static void setUpBaseUrl() {

        RestAssured.baseURI = env.baseUrl;
        RestAssured.basePath = env.path;

        builder = new RequestSpecBuilder();
        respec = new ResponseSpecBuilder();

        builder.addFilter(new AllureRestAssured());//To setup Filter that gonna attach Request/Response logs to report
        respec.expectResponseTime(lessThan(10L), TimeUnit.SECONDS);

        requestSpec = builder.build();
        responseSpec = respec.build();

        log("Setup Base URL successfully \n", "INFO", "text");
    }
    //endregion

    //Test Data Setup
    //region <xlsxFilePath>
    /**
     * Set up file path for "UserData" excel sheet
     */
    public static String xlsxFilePath() {

        String path = "";

        path = USER_DATA_XLSX_FILE_PATH;

        log("Setup .xlsx File Path: '" + path + "' successfully \n", "INFO", "text");
        return path;
    }
    //endregion

    //region <setUpExcelDataSheets>
    /**
     * This method is used to read the excel file and store
     * all data from each sheet into a set up variable.
     * Every time we create a new excel sheet, we need to set it
     * up here.
     */
    public static void setUpExcelDataSheets() {

        try {
            //Read excel sheets based on the Sheet Name
            petData = ExcelUtility.readExcelFile("Pet"); //Pet = sheet name
            if(petData == null || petData.equals("")) {
                LOGGER.info("[ERROR] userInfoData value is: " + petData);
            }

            storeData = ExcelUtility.readExcelFile("Store"); //Store = sheet name
            if(storeData == null || storeData.equals("")) {
                LOGGER.info("[ERROR] optionsData value is: " + storeData);
            }

            userData = ExcelUtility.readExcelFile("User"); //User = sheet name
            if(userData == null || userData.equals("")) {
                LOGGER.info("[ERROR] locationsData value is: " + userData);
            }
        }
        catch(Exception e) {
            log("Something went wrong reading the excel sheet --- " + e, "INFO", "text");
        }
    }
    //endregion

    //region <setTestDataForTest>
    /**
     * This method is used to set a test method
     * so that its data can be used within a different test method.
     * It stores every data based on the excel sheet name into currentTestData..
     * Every time we create a new Sheet in the .xlsx file, this function should be updated
     */
    public static void setTestDataForTest(String test_name) {

        testName = test_name;

        if(checkTestOnArray(testName, petData)) {
            currentTestData = getSpecificTestData(testName, petData);
        } //Pet sheet in 'SpriteCloudData.xlsx' file

        if(checkTestOnArray(testName, storeData)) {
            currentTestData = getSpecificTestData(testName, storeData);
        } //Store sheet in 'SpriteCloudData.xlsx' file

        if(checkTestOnArray(testName, userData)) {
            currentTestData = getSpecificTestData(testName, userData);
        } //User sheet in 'SpriteCloudData.xlsx' file

        log("Setup test data for test '" + testName + "' successfully \n", "INFO", "text");
    }
    //endregion

    //region <getSpecific TestData>
    /**
     * This method gets a specific test case data from the excelData 2D array
     */
    public static Map<String, String> getSpecificTestData(String testName, String[][] excelData) {

        currentTestData = new LinkedHashMap<>();

        int numRows = excelData.length; //Get number of rows
        int numCols = excelData[0].length; //Get number of columns

        for(int i = 0; i < numRows; i++) {
            if(excelData[i][0].equalsIgnoreCase(testName)) {
                for(int j = 0; j < numCols; j++) {
                    currentTestData.put(excelData[0][j], excelData[i][j]);
                }
            }
        }

        log("Get specific data for test case: '" + testName + "' successfully \n", "INFO", "text");
        return currentTestData;
    }
    //endregion

    //region <checkTestOnArray>
    /**
     * This method checks that the testName extracted from the excel sheet
     * is stored in the excelData array
     */
    public static boolean checkTestOnArray(String testName, String[][] excelData) {

        int numRows = excelData.length;

        for(int i = 0; i < numRows; i++) {
            if(excelData[i][0].equalsIgnoreCase(testName)) {
                return true;
            }
        }

        return false;
    }
    //endregion

    //region <logger>
    public static void logger(final String message, final String level, String format) {

        if(format.equalsIgnoreCase(("json"))) {
            String json = (new JSONObject(message)).toString(4); //To convert into pretty Json format
            LOGGER.info("\n" + json); //To print on the console
        }
        else {
            LOGGER.info(message); //To print on the console
        }
    }

    public static void log(final String message, final String level, String format) {

        try {
            logger(message, level, format);
        }
        catch (JSONException err) {
            logger(message, level, "text");
        }
    }
    //endregion
}
