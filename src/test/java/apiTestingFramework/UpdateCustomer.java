package apiTestingFramework;

import apiTestingFramework.APIs.CreateCustomerApi;
import apiTestingFramework.APIs.UpdateCustomerApi;
import apiTestingFramework.pojo.Customer;
import apiTestingFramework.setup.BaseTest;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class UpdateCustomer extends BaseTest {
    Customer createCustomerData = new Customer("Adi", "Adi@gmail.com", "New Description for a new user");
    Customer updateCustomerData = new Customer("Updated Name", "updatedName@gmail.com", "Updated Description");
    static String createdCustomerId;

    @BeforeTest
    void createCustomer() {
        Response createCustomerResponse = CreateCustomerApi.createCustomerFromPojo(createCustomerData.getName(), createCustomerData.getEmail(), createCustomerData.getDescription());
        JSONObject jsonObject = new JSONObject(createCustomerResponse.asString());
        createdCustomerId = (jsonObject.get("id")).toString();
    }

    @Test
    public void updateCustomer() {

        UpdateCustomerApi.UpdateCustomer(updateCustomerData.getName(), updateCustomerData.getEmail(), updateCustomerData.getDescription(), createdCustomerId)
                .then()
                .statusCode(200)
                .body("id", is(notNullValue()))
                .body("name", equalTo(updateCustomerData.getName()))
                .body("description", equalTo(updateCustomerData.getDescription()))
                .body("email", equalTo(updateCustomerData.getEmail()));
    }
}
