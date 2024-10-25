package apiTestingFramework.APIs;

import apiTestingFramework.setup.BaseTest;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class UpdateCustomerApi extends BaseTest {

    public static String createdCustomerId;

    public static Response UpdateCustomer(String username, String email, String description, int id){
        Response createCustomerResponse = CreateCustomerApi.createCustomerFromPojo("Adi", "Adi@gmail.com", "New Description for a new user");
        JSONObject jsonObject= new JSONObject(createCustomerResponse.asString());
        createdCustomerId = (jsonObject.get("id")).toString();

        Response response = given()
                .auth()
                .basic(config.getProperty("secretKey"),"")
                .formParam("name", username)
                .formParam("email", email)
                .formParam("description", description)
                .post(config.getProperty("updateCustomerApiEndpoint")+"/"+createdCustomerId);

        return response;
    }
}
