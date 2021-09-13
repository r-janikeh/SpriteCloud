package main.java.api;

/**
 * @author lionel.mangoua
 * date: 07/08/21
 */

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jayway.jsonpath.DocumentContext;
import io.restassured.response.ValidatableResponse;
import main.java.engine.DriverFactory;
import main.java.utils.JsonUtility;
import org.json.JSONException;

import static main.java.api.CustomHeaders.buildCustomHeaders;
import static main.java.api.CustomHeaders.customHeadersMap;
import static org.hamcrest.CoreMatchers.containsString;

public class UserMethods extends DriverFactory {

    APICommonMethods api = new APICommonMethods();
    JsonUtility json_File = new JsonUtility();
    DocumentContext jsonDoc = null;
    Object jsonO = null;

    public static String createListOfUsersWithInputListPayload = "";
    public static String createListOfUsersWithInputArrayPayload = "";
    public static String updateUsernamePayload = "";
    public static String createUserPayload = "";
    public static String usrname = "";
    public static String respMessage = "";
    public static String errMessage = "";
    public static String usrMessage = "";

    String createListUsersWithInputListJsonBodyPath = property.returnPropVal_api(api_fileName, "createListUsersWithInputListJsonBodyPath");
    String createListUsersWithInputArrayJsonBodyPath = property.returnPropVal_api(api_fileName, "createListUsersWithInputArrayJsonBodyPath");
    String createUsernmJsonBodyPath = property.returnPropVal_api(api_fileName, "createUsernmJsonBodyPath");
    String updateUsernmJsonBodyPath = property.returnPropVal_api(api_fileName, "updateUsernmJsonBodyPath");

    String user_id = property.returnPropVal_api(api_fileName, "user_id");
    String user_username = property.returnPropVal_api(api_fileName, "user_username");
    String user_firstName = property.returnPropVal_api(api_fileName, "user_firstName");
    String user_lastName = property.returnPropVal_api(api_fileName, "user_lastName");
    String user_email = property.returnPropVal_api(api_fileName, "user_email");
    String user_password = property.returnPropVal_api(api_fileName, "user_password");
    String user_phone = property.returnPropVal_api(api_fileName, "user_phone");
    String user_userStatus = property.returnPropVal_api(api_fileName, "user_userStatus");
    String user_id1 = property.returnPropVal_api(api_fileName, "user_id1");
    String user_username1 = property.returnPropVal_api(api_fileName, "user_username1");
    String user_firstName1 = property.returnPropVal_api(api_fileName, "user_firstName1");
    String user_lastName1 = property.returnPropVal_api(api_fileName, "user_lastName1");
    String user_email1 = property.returnPropVal_api(api_fileName, "user_email1");
    String user_password1 = property.returnPropVal_api(api_fileName, "user_password1");
    String user_phone1 = property.returnPropVal_api(api_fileName, "user_phone1");
    String user_userStatus1 = property.returnPropVal_api(api_fileName, "user_userStatus1");
    String user_id2 = property.returnPropVal_api(api_fileName, "user_id2");
    String user_username2 = property.returnPropVal_api(api_fileName, "user_username2");
    String user_firstName2 = property.returnPropVal_api(api_fileName, "user_firstName2");
    String user_lastName2 = property.returnPropVal_api(api_fileName, "user_lastName2");
    String user_email2 = property.returnPropVal_api(api_fileName, "user_email2");
    String user_password2 = property.returnPropVal_api(api_fileName, "user_password2");
    String user_phone2 = property.returnPropVal_api(api_fileName, "user_phone2");
    String user_userStatus2 = property.returnPropVal_api(api_fileName, "user_userStatus2");

    //region <createListUsersWithInputListPayload>
    public String createListUsersWithInputListPayload() {

        try {
            String filePath = createListUsersWithInputListJsonBodyPath;
            int ID1 = Integer.parseInt(currentTestData.get("Id1"));
            String USERNAME1 = currentTestData.get("Username1");
            String FIRSTNAME1 = currentTestData.get("FirstName1");
            String LASTNAME1 = currentTestData.get("LastName1");
            String EMAIL1 = currentTestData.get("Email1");
            String PASSWORD1 = currentTestData.get("Password1");
            String PHONE1 = currentTestData.get("Phone1");
            int USERSTATUS1 = Integer.parseInt(currentTestData.get("UserStatus1"));

            int ID2 = Integer.parseInt(currentTestData.get("Id2"));
            String USERNAME2 = currentTestData.get("Username2");
            String FIRSTNAME2 = currentTestData.get("FirstName2");
            String LASTNAME2 = currentTestData.get("LastName2");
            String EMAIL2 = currentTestData.get("Email2");
            String PASSWORD2 = currentTestData.get("Password2");
            String PHONE2 = currentTestData.get("Phone2");
            int USERSTATUS2 = Integer.parseInt(currentTestData.get("UserStatus2"));

            jsonO = JsonUtility.loadJson(filePath);

            JsonUtility.updateJsonFileWithInt(jsonO, user_id1, ID1);
            JsonUtility.updateJsonFileWithString(jsonO, user_username1, USERNAME1);
            JsonUtility.updateJsonFileWithString(jsonO, user_firstName1, FIRSTNAME1);
            JsonUtility.updateJsonFileWithString(jsonO, user_lastName1, LASTNAME1);
            JsonUtility.updateJsonFileWithString(jsonO, user_email1, EMAIL1);
            JsonUtility.updateJsonFileWithString(jsonO, user_password1, PASSWORD1);
            JsonUtility.updateJsonFileWithString(jsonO, user_phone1, PHONE1);
            JsonUtility.updateJsonFileWithInt(jsonO, user_userStatus1, USERSTATUS1);

            JsonUtility.updateJsonFileWithInt(jsonO, user_id2, ID2);
            JsonUtility.updateJsonFileWithString(jsonO, user_username2, USERNAME2);
            JsonUtility.updateJsonFileWithString(jsonO, user_firstName2, FIRSTNAME2);
            JsonUtility.updateJsonFileWithString(jsonO, user_lastName2, LASTNAME2);
            JsonUtility.updateJsonFileWithString(jsonO, user_email2, EMAIL2);
            JsonUtility.updateJsonFileWithString(jsonO, user_password2, PASSWORD2);
            JsonUtility.updateJsonFileWithString(jsonO, user_phone2, PHONE2);
            jsonDoc = JsonUtility.updateJsonFileWithInt(jsonO, user_userStatus2, USERSTATUS2); //to assign json updated value to jsonDoc

            JsonArray jso = JsonUtility.convertDocumentContextToJsonArray(jsonDoc); //when the Json payload is an Array

            return jso + "";
        }
        catch (JSONException ex) {
            log("Failed to update Create List Users With Input List body: " + ex,"ERROR",  "text");
            return "\nFailed to update Create List Users With Input List body: " + ex + "\n";
        }
    }
    //endregion

    //region <createListUsersWithInputArrayPayload>
    public String createListUsersWithInputArrayPayload() {

        try {
            String filePath = createListUsersWithInputArrayJsonBodyPath;
            int ID = Integer.parseInt(currentTestData.get("Id"));
            String USERNAME = currentTestData.get("Username");
            String FIRSTNAME = currentTestData.get("FirstName");
            String LASTNAME = currentTestData.get("LastName");
            String EMAIL = currentTestData.get("Email");
            String PASSWORD = currentTestData.get("Password");
            String PHONE = currentTestData.get("Phone");
            int USERSTATUS = Integer.parseInt(currentTestData.get("UserStatus"));

            jsonO = JsonUtility.loadJson(filePath);

            JsonUtility.updateJsonFileWithInt(jsonO, user_id1, ID);
            JsonUtility.updateJsonFileWithString(jsonO, user_username1, USERNAME);
            JsonUtility.updateJsonFileWithString(jsonO, user_firstName1, FIRSTNAME);
            JsonUtility.updateJsonFileWithString(jsonO, user_lastName1, LASTNAME);
            JsonUtility.updateJsonFileWithString(jsonO, user_email1, EMAIL);
            JsonUtility.updateJsonFileWithString(jsonO, user_password1, PASSWORD);
            JsonUtility.updateJsonFileWithString(jsonO, user_phone1, PHONE);
            jsonDoc = JsonUtility.updateJsonFileWithInt(jsonO, user_userStatus1, USERSTATUS); //to assign json updated value to jsonDoc

            JsonArray jso = JsonUtility.convertDocumentContextToJsonArray(jsonDoc); //when the Json payload is an Array

            return jso + "";
        }
        catch (JSONException ex) {
            log("Failed to update Create List Users With Input Array body: " + ex,"ERROR",  "text");
            return "\nFailed to update Create List Users With Input Array body: " + ex + "\n";
        }
    }
    //endregion

    //region <createUsernmPayload>
    public String createUsernmPayload() {

        try {
            String filePath =  createUsernmJsonBodyPath;
            int ID = Integer.parseInt(currentTestData.get("Id"));
            String USERNAME = currentTestData.get("Username");
            String FIRSTNAME = currentTestData.get("FirstName");
            String LASTNAME = currentTestData.get("LastName");
            String EMAIL = currentTestData.get("Email");
            String PASSWORD = currentTestData.get("Password");
            String PHONE = currentTestData.get("Phone");
            int USERSTATUS = Integer.parseInt(currentTestData.get("UserStatus"));

            jsonO = JsonUtility.loadJson(filePath);

            JsonUtility.updateJsonFileWithInt(jsonO, user_id, ID);
            JsonUtility.updateJsonFileWithString(jsonO, user_username, USERNAME);
            JsonUtility.updateJsonFileWithString(jsonO, user_firstName, FIRSTNAME);
            JsonUtility.updateJsonFileWithString(jsonO, user_lastName, LASTNAME);
            JsonUtility.updateJsonFileWithString(jsonO, user_email, EMAIL);
            JsonUtility.updateJsonFileWithString(jsonO, user_password, PASSWORD);
            JsonUtility.updateJsonFileWithString(jsonO, user_phone, PHONE);
            jsonDoc = JsonUtility.updateJsonFileWithInt(jsonO, user_userStatus, USERSTATUS); //to assign json updated value to jsonDoc

            JsonObject jso = JsonUtility.convertDocumentContextToJsonObject(jsonDoc);

            return jso + "";
        }
        catch (JSONException ex) {
            log("Failed to update Update Username body: " + ex,"ERROR",  "text");
            return "\nFailed to update Update Username body: " + ex + "\n";
        }
    }
    //endregion

    //region <updateUsernmPayload>
    public String updateUsernmPayload() {

        try {
            String filePath = updateUsernmJsonBodyPath;
            int ID = Integer.parseInt(currentTestData.get("Id"));
            String USERNAME = currentTestData.get("Username");
            String FIRSTNAME = currentTestData.get("FirstName");
            String LASTNAME = currentTestData.get("LastName");
            String EMAIL = currentTestData.get("Email");
            String PASSWORD = currentTestData.get("Password");
            String PHONE = currentTestData.get("Phone");
            int USERSTATUS = Integer.parseInt(currentTestData.get("UserStatus"));

            jsonO = JsonUtility.loadJson(filePath);

            JsonUtility.updateJsonFileWithInt(jsonO, user_id, ID);
            JsonUtility.updateJsonFileWithString(jsonO, user_username, USERNAME);
            JsonUtility.updateJsonFileWithString(jsonO, user_firstName, FIRSTNAME);
            JsonUtility.updateJsonFileWithString(jsonO, user_lastName, LASTNAME);
            JsonUtility.updateJsonFileWithString(jsonO, user_email, EMAIL);
            JsonUtility.updateJsonFileWithString(jsonO, user_password, PASSWORD);
            JsonUtility.updateJsonFileWithString(jsonO, user_phone, PHONE);
            jsonDoc = JsonUtility.updateJsonFileWithInt(jsonO, user_userStatus, USERSTATUS); //to assign json updated value to jsonDoc

            JsonObject jso = JsonUtility.convertDocumentContextToJsonObject(jsonDoc);

            return jso + "";
        }
        catch (JSONException ex) {
            log("Failed to update Update Username body: " + ex,"ERROR",  "text");
            return "\nFailed to update Update Username body: " + ex + "\n";
        }
    }
    //endregion

    public ValidatableResponse post_createListOfUsersWithInputList() {

        log("========================== Create List Of Users With Input List ============================", "INFO", "text");

        //Build Headers
        buildCustomHeaders("Content-Type", contentTypeJson);

        String uri_string = property.returnPropVal_api(api_fileName, "createListOfUsersWithInputList_uri");
        createListOfUsersWithInputListPayload = createListUsersWithInputListPayload(); // build payload

        response = api.postMethod(createListOfUsersWithInputListPayload, uri_string, customHeadersMap); // Post request
        return response;
    }

    public ValidatableResponse post_createListOfUsersWithInputArray() {

        log("========================== Create List Of Users With Input Array ============================", "INFO", "text");

        //Build Headers
        buildCustomHeaders("accept", contentTypeJson);

        String uri_string = property.returnPropVal_api(api_fileName, "createListOfUsersWithInputArray_uri");
        createListOfUsersWithInputArrayPayload = createListUsersWithInputArrayPayload(); // build payload

        response = api.postMethod(createListOfUsersWithInputListPayload, uri_string, customHeadersMap); // Post request
        return response;
    }

    public ValidatableResponse get_findUserByUsername(String username) {

        log("========================== Find User By Username ============================", "INFO", "text");

        //Build Headers
        buildCustomHeaders("Content-Type", contentTypeJson);

        String uri_string = property.returnPropVal_api(api_fileName, "findUserByUsername_uri");
        uri_string = uri_string.replace("username", username);

        response = api.getMethod(uri_string, customHeadersMap);
        return response;
    }

    public ValidatableResponse post_createUser() {

        log("========================== Create User ============================", "INFO", "text");

        //Build Headers
        buildCustomHeaders("accept", contentTypeJson);
        buildCustomHeaders("Content-Type", contentTypeJson);

        String uri_string = property.returnPropVal_api(api_fileName, "createUser_uri");
        createUserPayload = createUsernmPayload(); // build payload

        response = api.postMethod(createUserPayload, uri_string, customHeadersMap); // Put request
        return response;
    }

    public ValidatableResponse put_updateUser(String usrn) {

        log("========================== Update User ============================", "INFO", "text");

        //Build Headers
        buildCustomHeaders("accept", contentTypeJson);

        String uri_string = property.returnPropVal_api(api_fileName, "updateUser_uri");
        uri_string = uri_string.replace("username", usrn);
        updateUsernamePayload = updateUsernmPayload(); // build payload

        response = api.putMethod(updateUsernamePayload, uri_string, customHeadersMap); // Put request
        return response;
    }

    public ValidatableResponse delete_deleteUser(String usrnm) {

        log("========================== Delete User ============================", "INFO", "text");

        //Build Headers
        buildCustomHeaders("Content-Type", contentTypeJson);

        String uri_string = property.returnPropVal_api(api_fileName, "deleteUser_uri");
        uri_string = uri_string.replace("username", usrnm);

        response = api.deleteMethod(uri_string, customHeadersMap);
        return response;
    }

    public ValidatableResponse get_loginUser(String username, String password) {

        log("========================== Login User ============================", "INFO", "text");

        //Build Headers
        buildCustomHeaders("Content-Type", contentTypeJson);

        String uri_string = property.returnPropVal_api(api_fileName, "loginUser_uri");
        uri_string = uri_string.replace("usrn", username);
        uri_string = uri_string.replace("pwd", password);

        response = api.getMethod(uri_string, customHeadersMap);
        return response;
    }

    public ValidatableResponse get_logoutUser() {

        log("========================== Logout User ============================", "INFO", "text");

        //Build Headers
        buildCustomHeaders("Content-Type", contentTypeJson);

        String uri_string = property.returnPropVal_api(api_fileName, "logoutUser_uri");

        response = api.getMethod(uri_string, customHeadersMap);
        return response;
    }

    /**
     * validations
     */
    //region <validateCreateListOfUsersWithInputList>
    public void validateCreateListOfUsersWithInputList(ValidatableResponse response, int status, String msg) {

        response.assertThat().statusCode(status);
        log("ASSERT: StatusCode \nEXPECTED: 200 \nACTUAL: " + status, "INFO", "text");

        response.body("message", containsString(msg));
        respMessage = APICommonMethods.getValueFromJsonResp("message");
        log("ASSERT: Message    \nEXPECTED: " + msg + "\nACTUAL: " + respMessage, "INFO", "text");
    }
    //endregion

    //region <validateCreateListOfUsersWithInputArray>
    public void validateCreateListOfUsersWithInputArray(ValidatableResponse response, int status, String msg) {

        response.assertThat().statusCode(status);
        log("ASSERT: StatusCode \nEXPECTED: 200 \nACTUAL: " + status, "INFO", "text");

        response.body("message", containsString(msg));
        respMessage = APICommonMethods.getValueFromJsonResp("message");
        log("ASSERT: Message    \nEXPECTED: " + msg + "\nACTUAL: " + respMessage, "INFO", "text");
    }
    //endregion

    //region <validateGetUserByUsername>
    public void validateGetUserByUsername(ValidatableResponse response, int status, String username) {

        response.assertThat().statusCode(status);
        log("ASSERT: StatusCode \nEXPECTED: 200 \nACTUAL: " + status, "INFO", "text");

        response.body("username", containsString(username));
        usrname = APICommonMethods.getValueFromJsonResp("username");
        log("ASSERT: Username    \nEXPECTED: " + username + "\nACTUAL: " + usrname, "INFO", "text");
    }
    //endregion

    //region <validateGetUserByUsernameNegativeScenario>
    public void validateGetUserByUsernameNegativeScenario(ValidatableResponse response, int status, String errorMessage) {

        response.assertThat().statusCode(status);
        log("ASSERT: StatusCode \nEXPECTED: 404 \nACTUAL: " + status, "INFO", "text");

        response.body("message", containsString(errMessage));
        errMessage = APICommonMethods.getValueFromJsonResp("message");
        log("ASSERT: Message    \nEXPECTED: " + errorMessage + "\nACTUAL: " + errMessage, "INFO", "text");
    }
    //endregion

    //region <validateCreateUser>
    public void validateCreateUser(ValidatableResponse response, int status, int id) {

        response.assertThat().statusCode(status);
        log("ASSERT: StatusCode \nEXPECTED: 200 \nACTUAL: " + status, "INFO", "text");

        response.body("message", containsString(String.valueOf(id)));
        usrMessage = APICommonMethods.getValueFromJsonResp("message");
        log("ASSERT: Message    \nEXPECTED: " + id + "\nACTUAL: " + usrMessage, "INFO", "text");
    }
    //endregion

    //region <validateUpdateUser>
    public void validateUpdateUser(ValidatableResponse response, int status) {

        response.assertThat().statusCode(status);
        log("ASSERT: StatusCode \nEXPECTED: 200 \nACTUAL: " + status, "INFO", "text");
    }
    //endregion

    //region <validateDeleteUser>
    public void validateDeleteUser(ValidatableResponse response, int status, String usrnm) {

        response.assertThat().statusCode(status);
        log("ASSERT: StatusCode \nEXPECTED: 200 \nACTUAL: " + status, "INFO", "text");

        response.body("message", containsString(usrnm));
        usrMessage = APICommonMethods.getValueFromJsonResp("message");
        log("ASSERT: Message    \nEXPECTED: " + usrnm + "\nACTUAL: " + usrMessage, "INFO", "text");
    }
    //endregion

    //region <validateDeleteUserNegativeScenario>
    public void validateDeleteUserNegativeScenario(ValidatableResponse response, int status) {

        response.assertThat().statusCode(status);
        log("ASSERT: StatusCode \nEXPECTED: 404 \nACTUAL: " + status, "INFO", "text");

    }
    //endregion

    //region <validateLoginUser>
    public void validateLoginUser(ValidatableResponse response, int status, String msg) {

        response.assertThat().statusCode(status);
        log("ASSERT: StatusCode \nEXPECTED: 200 \nACTUAL: " + status, "INFO", "text");

        response.body("message", containsString(msg));
        respMessage = APICommonMethods.getValueFromJsonResp("message");
        log("ASSERT: Message    \nEXPECTED: " + msg + "\nACTUAL: " + respMessage, "INFO", "text");
    }
    //endregion

    //region <validateLogoutUser>
    public void validateLogoutUser(ValidatableResponse response, int status, String msg) {

        response.assertThat().statusCode(status);
        log("ASSERT: StatusCode \nEXPECTED: 200 \nACTUAL: " + status, "INFO", "text");

        response.body("message", containsString(msg));
        respMessage = APICommonMethods.getValueFromJsonResp("message");
        log("ASSERT: Message    \nEXPECTED: " + msg + "\nACTUAL: " + respMessage, "INFO", "text");
    }
    //endregion
}
