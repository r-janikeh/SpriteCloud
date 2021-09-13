package main.java.api;

/**
 * @author lionel.mangoua
 * date: 07/08/21
 */

import com.google.gson.JsonObject;
import com.jayway.jsonpath.DocumentContext;
import io.restassured.response.ValidatableResponse;
import junit.framework.Assert;
import main.java.engine.DriverFactory;
import main.java.utils.JsonUtility;
import org.json.JSONException;

import java.io.File;
import java.util.Random;

import static main.java.api.CustomHeaders.buildCustomHeaders;
import static main.java.api.CustomHeaders.customHeadersMap;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class StoreMethods extends DriverFactory {

    APICommonMethods api = new APICommonMethods();
    JsonUtility json_File = new JsonUtility();
    DocumentContext jsonDoc = null;
    Object jsonO = null;
    Random random = new Random();

    public static String errMessage = "";
    public static String deleteErrMessage = "";
    public static String placeOrderForPetPayload = "";
    public static int ORDER_ID;
    public static Long Order_ID;

    String placeOrderForPetJsonBodyPath = property.returnPropVal_api(api_fileName, "placeOrderForPetJsonBodyPath");
    String store_id = property.returnPropVal_api(api_fileName, "store_id");
    String store_petId = property.returnPropVal_api(api_fileName, "store_petId");
    String store_quantity = property.returnPropVal_api(api_fileName, "store_quantity");
    String store_shipDate = property.returnPropVal_api(api_fileName, "store_shipDate");
    String store_status = property.returnPropVal_api(api_fileName, "store_status");
    String store_complete = property.returnPropVal_api(api_fileName, "store_complete");

    //region <placeAnOrderForPetPayload>
    public String placeAnOrderForPetPayload() {

        try {
            String filePath = placeOrderForPetJsonBodyPath;
            ORDER_ID = random.nextInt(90000000); //generate random order Id number
            int PetID = Integer.parseInt(currentTestData.get("PetId"));
            int Quantity = Integer.parseInt(currentTestData.get("Quantity"));

            jsonO = JsonUtility.loadJson(filePath);

            JsonUtility.updateJsonFileWithInt(jsonO, store_id, ORDER_ID);
            JsonUtility.updateJsonFileWithInt(jsonO, store_petId, PetID);
            JsonUtility.updateJsonFileWithInt(jsonO, store_quantity, Quantity);
            JsonUtility.updateJsonFileWithString(jsonO, store_shipDate, currentTestData.get("ShipDate"));
//            JsonUtility.updateJsonFileWithString(jsonO, store_complete, currentTestData.get("Complete"));
            jsonDoc = JsonUtility.updateJsonFileWithString(jsonO, store_status, currentTestData.get("Status")); //to assign json updated value to jsonDoc

            JsonObject jso = JsonUtility.convertDocumentContextToJsonObject(jsonDoc);

            return jso + "";
        }
        catch (JSONException ex) {
            log("Failed to update Add New Pet To Store body: " + ex,"ERROR",  "text");
            return "\nFailed to update Add New Pet To Store body: " + ex + "\n";
        }
    }
    //endregion

    public ValidatableResponse get_returnPetInventoryByStatus() {

        log("========================== Return Pet Inventory By Status ============================", "INFO", "text");

        //Build Headers
        buildCustomHeaders("Content-Type", contentTypeJson);

        String uri_string = property.returnPropVal_api(api_fileName, "returnPetInventoryByStatus_uri");

        response = api.getMethod(uri_string, customHeadersMap);
        return response;
    }

    public ValidatableResponse post_placeOrderForPet() {

        log("========================== Place Order For Pet ============================", "INFO", "text");

        //Build Headers
        buildCustomHeaders("accept", contentTypeJson);
        buildCustomHeaders("Content-Type", contentTypeJson);

        String uri_string = property.returnPropVal_api(api_fileName, "placeOrderForPet_uri");
        placeOrderForPetPayload = placeAnOrderForPetPayload(); // build payload

        response = api.postMethod(placeOrderForPetPayload, uri_string, customHeadersMap); // Post request
        return response;
    }

    public ValidatableResponse get_findPurchaseOrderById(String orderId) {

        log("========================== Find Purchase Order By Id ============================", "INFO", "text");

        //Build Headers
        buildCustomHeaders("Content-Type", contentTypeJson);

        String uri_string = property.returnPropVal_api(api_fileName, "findPurchaseOrderById_uri");
        uri_string = uri_string.replace("order_id", orderId);

        response = api.getMethod(uri_string, customHeadersMap);
        return response;
    }

    public ValidatableResponse delete_deletePurchaseOrder(String orderId) {

        log("========================== Delete Purchase Order ============================", "INFO", "text");

        //Build Headers
        buildCustomHeaders("Content-Type", contentTypeJson);

        String uri_string = property.returnPropVal_api(api_fileName, "deletePurchaseOrder_uri");
        uri_string = uri_string.replace("order_id", orderId);

        response = api.deleteMethod(uri_string, customHeadersMap);
        return response;
    }

    /**
     * validations
     */
    //region <validateReturnPetInventoryByStatus>
    public void validateReturnPetInventoryByStatus(ValidatableResponse response, int status) {

        response.assertThat().statusCode(status);
        log("ASSERT: StatusCode \nEXPECTED: 200 \nACTUAL: " + status, "INFO", "text");
    }
    //endregion

    //region <validatePlaceOrderForPet>
    public void validatePlaceOrderForPet(ValidatableResponse response, int status, int order_id) {

        response.assertThat().statusCode(status);
        log("ASSERT: StatusCode \nEXPECTED: 200 \nACTUAL: " + status, "INFO", "text");

        Long extractedID = Long.valueOf(APICommonMethods.getFirstValueFromJson(response,"id"));
        Assert.assertNotNull(extractedID);

        log("ASSERT: OrderID    \nEXPECTED: Not to be null  \nACTUAL: " + extractedID, "INFO", "text");

        response.body("id", equalTo(order_id));
        log("ASSERT: Message    \nEXPECTED: " + order_id + "\nACTUAL: " + extractedID, "INFO", "text");
    }
    //endregion

    //region <validateFindPurchaseOrderById>
    public void validateFindPurchaseOrderById(ValidatableResponse response, int status, String order_id) {

        response.assertThat().statusCode(status);
        log("ASSERT: StatusCode \nEXPECTED: 200 \nACTUAL: " + status, "INFO", "text");

        Long extractedID = Long.valueOf(APICommonMethods.getValueFromJson(response,"id"));
        Assert.assertNotNull(extractedID);

        log("ASSERT: OrderID    \nEXPECTED: Not to be null  \nACTUAL: " + extractedID, "INFO", "text");
    }
    //endregion

    //region <validateFindPurchaseOrderByIdNegativeScenario>
    public void validateFindPurchaseOrderByIdNegativeScenario(ValidatableResponse response, int status, String errorMessage) {

        response.assertThat().statusCode(status);
        log("ASSERT: StatusCode \nEXPECTED: 404 \nACTUAL: " + status, "INFO", "text");

        response.body("message", containsString(errorMessage));
        errMessage = APICommonMethods.getValueFromJsonResp("message");
        log("ASSERT: Message    \nEXPECTED: " + errorMessage + "\nACTUAL: " + errMessage, "INFO", "text");
    }
    //endregion

    //region <validateDeletePurchaseOrder>
    public void validateDeletePurchaseOrder(ValidatableResponse response, int status, String orderId) {

        response.assertThat().statusCode(status);
        log("ASSERT: StatusCode \nEXPECTED: 200 \nACTUAL: " + status, "INFO", "text");

        response.body("message", containsString(orderId));
        deleteErrMessage = APICommonMethods.getValueFromJsonResp("message");
        log("ASSERT: Message    \nEXPECTED: " + orderId + "\nACTUAL: " + deleteErrMessage, "INFO", "text");
    }
    //endregion

    //region <validateDeletePurchaseOrderNegativeScenario>
    public void validateDeletePurchaseOrderNegativeScenario(ValidatableResponse response, int status, String errMessage) {

        response.assertThat().statusCode(status);
        log("ASSERT: StatusCode \nEXPECTED: 404 \nACTUAL: " + status, "INFO", "text");

        response.body("message", containsString(errMessage));
        deleteErrMessage = APICommonMethods.getValueFromJsonResp("message");
        log("ASSERT: Message    \nEXPECTED: " + errMessage + "\nACTUAL: " + deleteErrMessage, "INFO", "text");
    }
    //endregion
}
