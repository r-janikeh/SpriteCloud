package apiTest;


import main.java.api.PetMethods;
import main.java.api.StoreMethods;
import main.java.api.UserMethods;
import main.java.engine.DriverFactory;
import main.java.utils.SetEnvironmentDataUtility;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;

import static main.java.api.StoreMethods.ORDER_ID;

public class PetTest extends DriverFactory {

    SetEnvironmentDataUtility setEnv = new SetEnvironmentDataUtility();
    PetMethods petMeth = new PetMethods();
    StoreMethods storeMeth = new StoreMethods();
    UserMethods userMeth = new UserMethods();

    @Features("SpriteCloud Test")
    @Description("Perform an API request to find pets by status")
    @Parameters({"testCase", "environmentName"})
    @Test
    public void FindPetsByStatus(String testCase, String environmentName) {

        setTestDataForTest(testCase); //set Data to extract from sheet
        setEnv.setTestEnvironment(environmentName); //set values

        response = petMeth.get_findPetsByStatus(currentTestData.get("PetStatus"));
        petMeth.validateFindPetsByStatus(response, 200, currentTestData.get("PetStatus"));

        System.out.println("\n=============== FindPetsByStatus test executed successfully ===============\n");
    }

    @Features("SpriteCloud Test")
    @Description("Perform an API request to upload an image")
    @Parameters({"testCase", "environmentName"})
    @Test
    public void UploadAnImage(String testCase, String environmentName) {

        setTestDataForTest(testCase); //set Data to extract from sheet
        setEnv.setTestEnvironment(environmentName); //set values

        response = petMeth.post_uploadImage(petID);
        petMeth.validateUploadImage(response, 200);

        System.out.println("\n=============== UploadAnImage test executed successfully ===============\n");
    }

    @Features("SpriteCloud Test")
    @Description("Perform an API request to add new pet to store")
    @Parameters({"testCase", "environmentName"})
    @Test
    public void AddNewPetToStore(String testCase, String environmentName) {

        setTestDataForTest(testCase); //set Data to extract from sheet
        setEnv.setTestEnvironment(environmentName); //set values

        response = petMeth.post_addNewPetToStore();
        petMeth.validateAddNewPetToStore(response, 200);

        System.out.println("\n=============== AddNewPetToStore test executed successfully ===============\n");
    }

    @Features("SpriteCloud Test")
    @Description("Perform an API request to update an existing pet")
    @Parameters({"testCase", "environmentName"})
    @Test
    public void UpdateExistingPet(String testCase, String environmentName) {

        setTestDataForTest(testCase); //set Data to extract from sheet
        setEnv.setTestEnvironment(environmentName); //set values

        response = petMeth.put_updateExistingPet();
        petMeth.validateUpdateExistingPet(response, 200);

        System.out.println("\n=============== UpdateExistingPet test executed successfully ===============\n");
    }

    @Features("SpriteCloud Test")
    @Description("Perform an API request to update pet in store with form data")
    @Parameters({"testCase", "environmentName"})
    @Test
    public void UpdatePetWithFormData(String testCase, String environmentName) {

        setTestDataForTest(testCase); //set Data to extract from sheet
        setEnv.setTestEnvironment(environmentName); //set values

        response = petMeth.post_updatePetWithFormData(currentTestData.get("PetId"), currentTestData.get("Name"), currentTestData.get("Status"));
        petMeth.validateUpdatePetWithFormData(response, 200, currentTestData.get("PetId"));

        System.out.println("\n=============== UpdatePetWithFormData test executed successfully ===============\n");
    }

    @Features("SpriteCloud Test")
    @Description("Perform an API request to find pets by id")
    @Parameters({"testCase", "environmentName"})
    @Test
    public void FindPetsById(String testCase, String environmentName) {

        setTestDataForTest(testCase); //set Data to extract from sheet
        setEnv.setTestEnvironment(environmentName); //set values

//        response = petMeth.get_findPetsById(petID.toString()); //To use petId extracted from 'FindPetsByStatus' test case
        response = petMeth.get_findPetsById(currentTestData.get("PetId")); //To use petId extracted from Excel sheet

        if(currentTestData.get("ScenarioType").equalsIgnoreCase("Positive")) {
//            petMeth.validateFindPetsById(response, 200, petID.toString()); //To use petId extracted from 'FindPetsByStatus' test case
        petMeth.validateFindPetsById(response, 200, currentTestData.get("PetId")); //To use petId extracted from Excel sheet
        }
        else if(currentTestData.get("ScenarioType").equalsIgnoreCase("Negative")) {
            petMeth.validateFindPetsByIdNegativeScenario(response, 404, "Pet not found"); //To use petId extracted from 'FindPetsByStatus' test case
        }
        else {
//            petMeth.validateFindPetsById(response, 200, petID.toString()); //To use petId extracted from 'FindPetsByStatus' test case
        petMeth.validateFindPetsById(response, 200, currentTestData.get("PetId")); //To use petId extracted from Excel sheet
        }

        System.out.println("\n=============== FindPetsById test executed successfully ===============\n");
    }

    @Features("SpriteCloud Test")
    @Description("Perform an API request to update pet in store")
    @Parameters({"testCase", "environmentName"})
    @Test
    public void UpdatePetInStore(String testCase, String environmentName) {

        setTestDataForTest(testCase); //set Data to extract from sheet
        setEnv.setTestEnvironment(environmentName); //set values

        response = petMeth.put_updatePetInStore(petID);
        petMeth.validateUpdatePetInStore(response, 200);

        System.out.println("\n=============== UpdatePetInStore test executed successfully ===============\n");
    }

    @Features("SpriteCloud Test")
    @Description("Perform an API request to delete pet")
    @Parameters({"testCase", "environmentName"})
    @Test
    public void DeletePet(String testCase, String environmentName) throws InterruptedException {

        setTestDataForTest(testCase); //set Data to extract from sheet
        setEnv.setTestEnvironment(environmentName); //set values

        Thread.sleep(7000);

        if(currentTestData.get("ScenarioType").equalsIgnoreCase("Positive")) {
            response = petMeth.delete_deletePet(petID.toString());
            petMeth.validateDeletePet(response, 200, petID.toString()); //To use petId extracted from 'FindPetsByStatus' test case
//            petMeth.validateDeletePet(response, 200, currentTestData.get("PetId")); //To use petId extracted from Excel sheet
        }
        else if(currentTestData.get("ScenarioType").equalsIgnoreCase("Negative")) {
            response = petMeth.get_findPetsById(currentTestData.get("PetId")); //To use petId extracted from Excel sheet
            petMeth.validateDeletePetNegativeScenario(response, 404);
//            petMeth.validateFindPetsByIdNegativeScenario(response, 404); //To use petId extracted from 'FindPetsByStatus' test case
        }
        else {
            response = petMeth.delete_deletePet(petID.toString());
            petMeth.validateDeletePet(response, 200, petID.toString()); //To use petId extracted from 'FindPetsByStatus' test case
//            petMeth.validateDeletePet(response, 200, currentTestData.get("PetId")); //To use petId extracted from Excel sheet
        }

        System.out.println("\n=============== DeletePet test executed successfully ===============\n");
    }

    @Features("SpriteCloud Test")
    @Description("Perform an API request to return pet inventories by status")
    @Parameters({"testCase", "environmentName"})
    @Test
    public void ReturnPetInventoryByStatus(String testCase, String environmentName) {

        setTestDataForTest(testCase); //set Data to extract from sheet
        setEnv.setTestEnvironment(environmentName); //set values

        response = storeMeth.get_returnPetInventoryByStatus();
        storeMeth.validateReturnPetInventoryByStatus(response, 200);

        System.out.println("\n=============== ReturnPetInventoryByStatus test executed successfully ===============\n");
    }

    @Features("SpriteCloud Test")
    @Description("Perform an API request to place order for pet")
    @Parameters({"testCase", "environmentName"})
    @Test
    public void PlaceOrderForPet(String testCase, String environmentName) {

        setTestDataForTest(testCase); //set Data to extract from sheet
        setEnv.setTestEnvironment(environmentName); //set values

        response = storeMeth.post_placeOrderForPet();
        storeMeth.validatePlaceOrderForPet(response, 200, ORDER_ID);

        System.out.println("\n=============== PlaceOrderForPet test executed successfully ===============\n");
    }

    @Features("SpriteCloud Test")
    @Description("Perform an API request to find purchase order by id")
    @Parameters({"testCase", "environmentName"})
    @Test
    public void FindPurchaseOrderById(String testCase, String environmentName) {

        setTestDataForTest(testCase); //set Data to extract from sheet
        setEnv.setTestEnvironment(environmentName); //set values

        response = storeMeth.get_findPurchaseOrderById(currentTestData.get("OrderId")); //To use petId extracted from Excel sheet

        if(currentTestData.get("ScenarioType").equalsIgnoreCase("Positive")) {
            storeMeth.validateFindPurchaseOrderById(response, 200, currentTestData.get("OrderId")); //To use petId extracted from Excel sheet
        }
        else if(currentTestData.get("ScenarioType").equalsIgnoreCase("Negative")) {
            storeMeth.validateFindPurchaseOrderByIdNegativeScenario(response, 404, "Order not found");
        }
        else {
            storeMeth.validateFindPurchaseOrderById(response, 200, currentTestData.get("OrderId")); //To use petId extracted from Excel sheet
        }

        System.out.println("\n=============== FindPurchaseOrderById test executed successfully ===============\n");
    }

    @Features("SpriteCloud Test")
    @Description("Perform an API request to delete purchase order by id")
    @Parameters({"testCase", "environmentName"})
    @Test
    public void DeletePurchaseOrderById(String testCase, String environmentName) throws InterruptedException {

        setTestDataForTest(testCase); //set Data to extract from sheet
        setEnv.setTestEnvironment(environmentName); //set values

        Thread.sleep(12000);

        if(currentTestData.get("ScenarioType").equalsIgnoreCase("Positive")) {
            response = storeMeth.delete_deletePurchaseOrder(String.valueOf(ORDER_ID));
            storeMeth.validateDeletePurchaseOrder(response, 200, String.valueOf(ORDER_ID));
//            storeMeth.validateDeletePurchaseOrder(response, 200, currentTestData.get("OrderId"));
        }
        else if(currentTestData.get("ScenarioType").equalsIgnoreCase("Negative")) {
            response = storeMeth.delete_deletePurchaseOrder(currentTestData.get("OrderId"));
            storeMeth.validateDeletePurchaseOrderNegativeScenario(response, 404, "Order Not Found");
        }
        else {
            response = storeMeth.delete_deletePurchaseOrder(String.valueOf(ORDER_ID));
            storeMeth.validateDeletePurchaseOrder(response, 200, String.valueOf(ORDER_ID));
//            storeMeth.validateDeletePurchaseOrder(response, 200, currentTestData.get("OrderId"));
        }

        System.out.println("\n=============== DeletePurchaseOrderById test executed successfully ===============\n");
    }

    @Features("SpriteCloud Test")
    @Description("Perform an API request to create list of users with input list")
    @Parameters({"testCase", "environmentName"})
    @Test
    public void CreateListOfUsersWithInputList(String testCase, String environmentName) {

        setTestDataForTest(testCase); //set Data to extract from sheet
        setEnv.setTestEnvironment(environmentName); //set values

        response = userMeth.post_createListOfUsersWithInputList();
        userMeth.validateCreateListOfUsersWithInputList(response, 200, "ok");

        System.out.println("\n=============== CreateListOfUsersWithInputList test executed successfully ===============\n");
    }

    @Features("SpriteCloud Test")
    @Description("Perform an API request to create list of users with input array")
    @Parameters({"testCase", "environmentName"})
    @Test
    public void CreateListOfUsersWithInputArray(String testCase, String environmentName) {

        setTestDataForTest(testCase); //set Data to extract from sheet
        setEnv.setTestEnvironment(environmentName); //set values

        response = userMeth.post_createListOfUsersWithInputArray();
        userMeth.validateCreateListOfUsersWithInputArray(response, 200, "ok");

        System.out.println("\n=============== CreateListOfUsersWithInputArray test executed successfully ===============\n");
    }

    @Features("SpriteCloud Test")
    @Description("Perform an API request to get user by username")
    @Parameters({"testCase", "environmentName"})
    @Test
    public void GetUserByUsername(String testCase, String environmentName) {

        setTestDataForTest(testCase); //set Data to extract from sheet
        setEnv.setTestEnvironment(environmentName); //set values

        response = userMeth.get_findUserByUsername(currentTestData.get("Username"));

        if(currentTestData.get("ScenarioType").equalsIgnoreCase("Positive")) {
            userMeth.validateGetUserByUsername(response, 200, currentTestData.get("Username"));
        }
        else if(currentTestData.get("ScenarioType").equalsIgnoreCase("Negative")) {
            userMeth.validateGetUserByUsernameNegativeScenario(response, 404, "User not found");
        }
        else {
            userMeth.validateGetUserByUsername(response, 200, currentTestData.get("Username"));
        }

        System.out.println("\n=============== FindPurchaseOrderById test executed successfully ===============\n");
    }

    @Features("SpriteCloud Test")
    @Description("Perform an API request to create user")
    @Parameters({"testCase", "environmentName"})
    @Test
    public void CreateUser(String testCase, String environmentName) {

        setTestDataForTest(testCase); //set Data to extract from sheet
        setEnv.setTestEnvironment(environmentName); //set values

        response = userMeth.post_createUser();
        userMeth.validateCreateUser(response, 200, Integer.parseInt(currentTestData.get("Id")));

        System.out.println("\n=============== CreateUser test executed successfully ===============\n");
    }

    @Features("SpriteCloud Test")
    @Description("Perform an API request to update user")
    @Parameters({"testCase", "environmentName"})
    @Test
    public void UpdateUser(String testCase, String environmentName) {

        setTestDataForTest(testCase); //set Data to extract from sheet
        setEnv.setTestEnvironment(environmentName); //set values

        response = userMeth.put_updateUser(currentTestData.get("Username"));
        userMeth.validateUpdateUser(response, 200);

        System.out.println("\n=============== UpdateUser test executed successfully ===============\n");
    }

    @Features("SpriteCloud Test")
    @Description("Perform an API request to delete user")
    @Parameters({"testCase", "environmentName"})
    @Test
    public void DeleteUser(String testCase, String environmentName) throws InterruptedException {

        setTestDataForTest(testCase); //set Data to extract from sheet
        setEnv.setTestEnvironment(environmentName); //set values

        Thread.sleep(7000);
        response = userMeth.delete_deleteUser(currentTestData.get("Username"));

        if(currentTestData.get("ScenarioType").equalsIgnoreCase("Positive")) {
            userMeth.validateDeleteUser(response, 200, currentTestData.get("Username"));
        }
        else if(currentTestData.get("ScenarioType").equalsIgnoreCase("Negative")) {
            userMeth.validateDeleteUserNegativeScenario(response, 404);
        }
        else {
            userMeth.validateDeleteUser(response, 200, currentTestData.get("Username"));
        }

        System.out.println("\n=============== DeleteUser test executed successfully ===============\n");
    }

    @Features("SpriteCloud Test")
    @Description("Perform an API request to log in a user")
    @Parameters({"testCase", "environmentName"})
    @Test
    public void LoginUser(String testCase, String environmentName) {

        setTestDataForTest(testCase); //set Data to extract from sheet
        setEnv.setTestEnvironment(environmentName); //set values

        response = userMeth.get_loginUser(currentTestData.get("Username"), currentTestData.get("Password"));
        userMeth.validateLoginUser(response, 200, "logged in user session:");

        System.out.println("\n=============== LoginUser test executed successfully ===============\n");
    }

    @Features("SpriteCloud Test")
    @Description("Perform an API request to log out a user")
    @Parameters({"testCase", "environmentName"})
    @Test
    public void LogoutUser(String testCase, String environmentName) {

        setTestDataForTest(testCase); //set Data to extract from sheet
        setEnv.setTestEnvironment(environmentName); //set values

        response = userMeth.get_logoutUser();
        userMeth.validateLogoutUser(response, 200, "ok");

        System.out.println("\n=============== LogoutUser test executed successfully ===============\n");
    }
}
