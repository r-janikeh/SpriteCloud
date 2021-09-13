package main.java.api;

/**
 * @author lionel.mangoua
 * date: 05/08/21
 */

import com.google.gson.JsonObject;
import com.jayway.jsonpath.DocumentContext;
import io.restassured.response.ValidatableResponse;
import junit.framework.Assert;
import main.java.engine.DriverFactory;
import main.java.utils.JsonUtility;
import org.json.JSONException;

import java.io.File;

import static main.java.api.CustomHeaders.buildCustomHeaders;
import static main.java.api.CustomHeaders.customHeadersMap;
import static org.hamcrest.CoreMatchers.containsString;

public class PetMethods extends DriverFactory {

    APICommonMethods api = new APICommonMethods();
    JsonUtility json_File = new JsonUtility();
    DocumentContext jsonDoc = null;
    Object jsonO = null;

    public static String addNewPetPayload = "";
    public static String updateExistingPetPayload = "";
    public static String updatePetInStorePayload = "";
    public static String updatePetWithFormDataPayload = "";
    public static String category_name = "";
    public static String petId_message = "";
    public static String errMessage = "";
    public static String deleteErrMessage = "";

    String addNewPetJsonBodyPath = property.returnPropVal_api(api_fileName, "addNewPetJsonBodyPath");
    String updatePetJsonBodyPath = property.returnPropVal_api(api_fileName, "updatePetJsonBodyPath");
    String updatePetInStoreJsonBodyPath = property.returnPropVal_api(api_fileName, "updatePetInStoreJsonBodyPath");
    String id = property.returnPropVal_api(api_fileName, "id");
    String categoryId = property.returnPropVal_api(api_fileName, "categoryId");
    String categoryName = property.returnPropVal_api(api_fileName, "categoryName");
    String name = property.returnPropVal_api(api_fileName, "name");
    String photoUrls = property.returnPropVal_api(api_fileName, "photoUrls");
    String tagsId = property.returnPropVal_api(api_fileName, "tagsId");
    String tagsName = property.returnPropVal_api(api_fileName, "tagsName");
    String status = property.returnPropVal_api(api_fileName, "status");

    //region <newPetPayload>
    public String newPetPayload() {

        try {
            String filePath = addNewPetJsonBodyPath;
            int ID = Integer.parseInt(currentTestData.get("Id"));
            int categoryID = Integer.parseInt(currentTestData.get("CategoryId"));
            int tagsID = Integer.parseInt(currentTestData.get("TagsId"));

            jsonO = JsonUtility.loadJson(filePath);

            JsonUtility.updateJsonFileWithInt(jsonO, id, ID);
            JsonUtility.updateJsonFileWithInt(jsonO, categoryId, categoryID);
            JsonUtility.updateJsonFileWithString(jsonO, categoryName, currentTestData.get("CategoryName"));
            JsonUtility.updateJsonFileWithString(jsonO, name, currentTestData.get("Name"));
            JsonUtility.updateJsonFileWithString(jsonO, photoUrls, currentTestData.get("PhotoUrl"));
            JsonUtility.updateJsonFileWithInt(jsonO, tagsId, tagsID);
            JsonUtility.updateJsonFileWithString(jsonO, tagsName, currentTestData.get("TagsName"));
            jsonDoc = JsonUtility.updateJsonFileWithString(jsonO, status, currentTestData.get("Status")); //to assign json updated value to jsonDoc

            JsonObject jso = JsonUtility.convertDocumentContextToJsonObject(jsonDoc);

            return jso + "";
        }
        catch (JSONException ex) {
            log("Failed to update Add New Pet To Store body: " + ex,"ERROR",  "text");
            return "\nFailed to update Add New Pet To Store body: " + ex + "\n";
        }
    }
    //endregion

    //region <updatePetPayload>
    public String updatePetPayload() {

        try {
            String filePath = updatePetJsonBodyPath;
            int ID = Integer.parseInt(currentTestData.get("Id"));
            int categoryID = Integer.parseInt(currentTestData.get("CategoryId"));
            int tagsID = Integer.parseInt(currentTestData.get("TagsId"));
            String PHOTO_URL = currentTestData.get("PhotoUrl");

            jsonO = JsonUtility.loadJson(filePath);

            JsonUtility.updateJsonFileWithInt(jsonO, id, ID);
            JsonUtility.updateJsonFileWithInt(jsonO, categoryId, categoryID);
            JsonUtility.updateJsonFileWithString(jsonO, categoryName, currentTestData.get("CategoryName"));
            JsonUtility.updateJsonFileWithString(jsonO, name, currentTestData.get("Name"));
            JsonUtility.updateJsonFileWithString(jsonO, photoUrls, PHOTO_URL);
            JsonUtility.updateJsonFileWithInt(jsonO, tagsId, tagsID);
            JsonUtility.updateJsonFileWithString(jsonO, tagsName, currentTestData.get("TagsName"));
            jsonDoc = JsonUtility.updateJsonFileWithString(jsonO, status, currentTestData.get("Status")); //to assign json updated value to jsonDoc

            JsonObject jso = JsonUtility.convertDocumentContextToJsonObject(jsonDoc);

            return jso + "";
        }
        catch (JSONException ex) {
            log("Failed to update Update Existing Pet body: " + ex,"ERROR",  "text");
            return "\nFailed to update Update Existing Pet body: " + ex + "\n";
        }
    }
    //endregion

    //region <updatePetInStorePayload>
    public String updatePetInStorePayload() {

        try {
            String filePath = updatePetJsonBodyPath;
            int ID = Integer.parseInt(currentTestData.get("Id"));
            int categoryID = Integer.parseInt(currentTestData.get("CategoryId"));
            int tagsID = Integer.parseInt(currentTestData.get("TagsId"));
            String PHOTO_URL = currentTestData.get("PhotoUrl");

            jsonO = JsonUtility.loadJson(filePath);

            JsonUtility.updateJsonFileWithInt(jsonO, id, ID);
            JsonUtility.updateJsonFileWithInt(jsonO, categoryId, categoryID);
            JsonUtility.updateJsonFileWithString(jsonO, categoryName, currentTestData.get("CategoryName"));
            JsonUtility.updateJsonFileWithString(jsonO, name, currentTestData.get("Name"));
            JsonUtility.updateJsonFileWithString(jsonO, photoUrls, PHOTO_URL);
            JsonUtility.updateJsonFileWithInt(jsonO, tagsId, tagsID);
            JsonUtility.updateJsonFileWithString(jsonO, tagsName, currentTestData.get("TagsName"));
            jsonDoc = JsonUtility.updateJsonFileWithString(jsonO, status, currentTestData.get("Status")); //to assign json updated value to jsonDoc

            JsonObject jso = JsonUtility.convertDocumentContextToJsonObject(jsonDoc);

            return jso + "";
        }
        catch (JSONException ex) {
            log("Failed to update Update Pet In Store body: " + ex,"ERROR",  "text");
            return "\nFailed to update Update Pet In Store body: " + ex + "\n";
        }
    }
    //endregion

    //region <Update Pet In Store Payload>
    public String updatePetStorePayload() {

        try {
            String filePath = updatePetJsonBodyPath;
            int ID = Integer.parseInt(currentTestData.get("Id"));
            int categoryID = Integer.parseInt(currentTestData.get("CategoryId"));
            int tagsID = Integer.parseInt(currentTestData.get("TagsId"));

            jsonO = JsonUtility.loadJson(filePath);

            JsonUtility.updateJsonFileWithInt(jsonO, id, ID);
            JsonUtility.updateJsonFileWithInt(jsonO, categoryId, categoryID);
            JsonUtility.updateJsonFileWithString(jsonO, categoryName, currentTestData.get("CategoryName"));
            JsonUtility.updateJsonFileWithString(jsonO, name, currentTestData.get("Name"));
//            JsonUtility.updateJsonFileWithString(jsonO, photoUrls, currentTestData.get("PhotoUrls"));
            JsonUtility.updateJsonFileWithInt(jsonO, tagsId, tagsID);
            JsonUtility.updateJsonFileWithString(jsonO, tagsName, currentTestData.get("TagsName"));
            jsonDoc = JsonUtility.updateJsonFileWithString(jsonO, status, currentTestData.get("Status")); //to assign json updated value to jsonDoc

            JsonObject jso = JsonUtility.convertDocumentContextToJsonObject(jsonDoc);

            return jso + "";
        }
        catch (JSONException ex) {
            log("Failed to update Update Pet In Store body: " + ex,"ERROR",  "text");
            return "\nFailed to update Update Pet In Store body: " + ex + "\n";
        }
    }
    //endregion

    public ValidatableResponse get_findPetsByStatus(String petStatus) {

        log("========================== Find Pets By Status ============================", "INFO", "text");

        //Build Headers
        buildCustomHeaders("Content-Type", contentTypeJson);

        String uri_string = property.returnPropVal_api(api_fileName, "findPetByStatus_uri");
        uri_string = uri_string.replace("pet_status", petStatus);

        response = api.getMethod(uri_string, customHeadersMap);
        return response;
    }

    public ValidatableResponse get_findPetsById(String petId) {

        log("========================== Find Pets By Id ============================", "INFO", "text");

        //Build Headers
        buildCustomHeaders("Content-Type", contentTypeJson);

        String uri_string = property.returnPropVal_api(api_fileName, "findPetById_uri");
        uri_string = uri_string.replace("pet_id", petId);

        response = api.getMethod(uri_string, customHeadersMap);
        return response;
    }

    public ValidatableResponse post_uploadImage(Long petId) {

        log("========================== Upload Image ============================", "INFO", "text");

        //Build Headers
        buildCustomHeaders("Content-Type", contentTypeFormDataJson);
        buildCustomHeaders("accept", contentTypeJson);

        String uri_string = property.returnPropVal_api(api_fileName, "uploadImage_uri");
        uri_string = uri_string.replace("pet_id", petId.toString());
        File uploadFile = new File("src/main/resources/images/image.png"); //Specify your own location and file

        response = api.postImageMethod(uploadFile, uri_string, customHeadersMap);
        return response;
    }

    public ValidatableResponse post_addNewPetToStore() {

        log("========================== Add New Pet To Store ============================", "INFO", "text");

        //Build Headers
        buildCustomHeaders("Content-Type", contentTypeJson);

        String uri_string = property.returnPropVal_api(api_fileName, "addNewPetToStore_uri");
        addNewPetPayload = newPetPayload(); // build payload

        response = api.postMethod(addNewPetPayload, uri_string, customHeadersMap); // Post request
        return response;
    }

    public ValidatableResponse put_updateExistingPet() {

        log("========================== Update Existing Pet ============================", "INFO", "text");

        //Build Headers
        buildCustomHeaders("Content-Type", contentTypeJson);

        String uri_string = property.returnPropVal_api(api_fileName, "updateExistingPet_uri");
        updateExistingPetPayload = updatePetPayload(); // build payload

        response = api.putMethod(updateExistingPetPayload, uri_string, customHeadersMap); // Put request
        return response;
    }

    public ValidatableResponse post_updatePetWithFormData(String petId, String name, String status) {

        log("========================== Update Pet With Form Data ============================", "INFO", "text");

        //Build Headers
        buildCustomHeaders("accept", contentTypeJson);
        buildCustomHeaders("Content-Type", contentTypeFormDataJson);

        String uri_string = property.returnPropVal_api(api_fileName, "updatePetInStore_uri");
        uri_string = uri_string.replace("pet_id", petId);

        response = api.postFormParamMethod(uri_string, customHeadersMap, name, status); // Put request
        return response;
    }

    public ValidatableResponse put_updatePetInStore(Long petId) {

        log("========================== Update Pet In Store ============================", "INFO", "text");

        //Build Headers
        buildCustomHeaders("accept", contentTypeJson);

        String uri_string = property.returnPropVal_api(api_fileName, "updatePetInStore_uri");
        uri_string = uri_string.replace("pet_id", petId.toString());
        updatePetInStorePayload = updatePetStorePayload(); // build payload

        response = api.putMethod(updatePetInStorePayload, uri_string, customHeadersMap); // Put request
        return response;
    }

    public ValidatableResponse delete_deletePet(String petId) {

        log("========================== Delete Pet ============================", "INFO", "text");

        //Build Headers
        buildCustomHeaders("Content-Type", contentTypeJson);

        String uri_string = property.returnPropVal_api(api_fileName, "deletePet_uri");
        uri_string = uri_string.replace("pet_id", petId);

        response = api.deleteMethod(uri_string, customHeadersMap);
        return response;
    }

    /**
     * validations
     */
    //region <validateFindPetsByStatus>
    public void validateFindPetsByStatus(ValidatableResponse response, int status, String pet_status) {

        response.assertThat().statusCode(status);
        log("ASSERT: StatusCode \nEXPECTED: 200 \nACTUAL: " + status, "INFO", "text");

        if (pet_status.equalsIgnoreCase("available") || pet_status.equalsIgnoreCase("pending")
                || pet_status.equalsIgnoreCase("sold")) {
            petID = Long.valueOf(APICommonMethods.getFirstValueFromJson(response,"id"));
            Assert.assertNotNull(petID);

            log("ASSERT: PetID    \nEXPECTED: Not to be null  \nACTUAL: " + petID, "INFO", "text");
        }
        else {
            try {
                petID = Long.valueOf(APICommonMethods.getFirstValueFromJson(response,"id"));
            }
            catch (NumberFormatException e) {
                log("ASSERT: We are not getting any value    \nEXPECTED: Empty list []  \nACTUAL: []", "INFO", "text");
            }
        }
    }
    //endregion

    //region <validateFindPetsById>
    public void validateFindPetsById(ValidatableResponse response, int status, String pet_id) {

        response.assertThat().statusCode(status);
        log("ASSERT: StatusCode \nEXPECTED: 200 \nACTUAL: " + status, "INFO", "text");

        petID = Long.valueOf(APICommonMethods.getFirstValueFromJson(response,"id"));
        Assert.assertNotNull(petID);

        log("ASSERT: PetID    \nEXPECTED: Not to be null  \nACTUAL: " + petID, "INFO", "text");
    }
    //endregion

    //region <validateFindPetsByIdNegativeScenario>
    public void validateFindPetsByIdNegativeScenario(ValidatableResponse response, int status, String errorMessage) {

        response.assertThat().statusCode(status);
        log("ASSERT: StatusCode \nEXPECTED: 404 \nACTUAL: " + status, "INFO", "text");

        response.body("message", containsString(errorMessage));
        errMessage = APICommonMethods.getValueFromJsonResp("message");
        log("ASSERT: Message    \nEXPECTED: " + errorMessage + "\nACTUAL: " + errMessage, "INFO", "text");
    }
    //endregion

    //region <validateUploadImage>
    public void validateUploadImage(ValidatableResponse response, int status) {

        response.assertThat().statusCode(status);
        log("ASSERT: StatusCode \nEXPECTED: 200 \nACTUAL: " + status, "INFO", "text");
    }
    //endregion

    //region <validateAddNewPetToStore>
    public void validateAddNewPetToStore(ValidatableResponse response, int status) {

        response.assertThat().statusCode(status);
        log("ASSERT: StatusCode \nEXPECTED: 200 \nACTUAL: " + status, "INFO", "text");

        response.body("category.name", containsString(currentTestData.get("CategoryName")));
        category_name = APICommonMethods.getValueFromJsonResp("category.name");
        log("ASSERT: CategoryName    \nEXPECTED: " + currentTestData.get("CategoryName") + "\nACTUAL: " + category_name, "INFO", "text");
    }
    //endregion

    //region <validateUpdateExistingPet>
    public void validateUpdateExistingPet(ValidatableResponse response, int status) {

        response.assertThat().statusCode(status);
        log("ASSERT: StatusCode \nEXPECTED: 200 \nACTUAL: " + status, "INFO", "text");

        response.body("category.name", containsString(currentTestData.get("CategoryName")));
        category_name = APICommonMethods.getValueFromJsonResp("category.name");
        log("ASSERT: CategoryName    \nEXPECTED: " + currentTestData.get("CategoryName") + "\nACTUAL: " + category_name, "INFO", "text");
    }
    //endregion

    //region <validateUpdatePetWithFormData>
    public void validateUpdatePetWithFormData(ValidatableResponse response, int status, String pet_Id) {

        response.assertThat().statusCode(status);
        log("ASSERT: StatusCode \nEXPECTED: 200 \nACTUAL: " + status, "INFO", "text");

        response.body("message", containsString(pet_Id));
        petId_message = APICommonMethods.getValueFromJsonResp("message");
        log("ASSERT: Message    \nEXPECTED: " + pet_Id + "\nACTUAL: " + petId_message, "INFO", "text");
    }
    //endregion

    //region <validateUpdatePetInStore>
    public void validateUpdatePetInStore(ValidatableResponse response, int status) {

        response.assertThat().statusCode(status);
        log("ASSERT: StatusCode \nEXPECTED: 200 \nACTUAL: " + status, "INFO", "text");

        response.body("category.name", containsString(currentTestData.get("CategoryName")));
        category_name = APICommonMethods.getValueFromJsonResp("category.name");
        log("ASSERT: CategoryName    \nEXPECTED: " + currentTestData.get("CategoryName") + "\nACTUAL: " + category_name, "INFO", "text");
    }
    //endregion

    //region <validateDeletePet>
    public void validateDeletePet(ValidatableResponse response, int status, String petId) {

        response.assertThat().statusCode(status);
        log("ASSERT: StatusCode \nEXPECTED: 200 \nACTUAL: " + status, "INFO", "text");

        response.body("message", containsString(petId));
        deleteErrMessage = APICommonMethods.getValueFromJsonResp("message");
        log("ASSERT: Message    \nEXPECTED: " + petId + "\nACTUAL: " + deleteErrMessage, "INFO", "text");
    }
    //endregion

    //region <validateDeletePetNegativeScenario>
    public void validateDeletePetNegativeScenario(ValidatableResponse response, int status) {

        response.assertThat().statusCode(status);
        log("ASSERT: StatusCode \nEXPECTED: 404 \nACTUAL: " + status, "INFO", "text");
    }
    //endregion
}
