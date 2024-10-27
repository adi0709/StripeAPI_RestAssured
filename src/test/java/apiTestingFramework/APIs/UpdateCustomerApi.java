package apiTestingFramework.APIs;

import apiTestingFramework.setup.BaseTest;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UpdateCustomerApi extends BaseTest {

    public static Response UpdateCustomer(String username, String email, String description, String customerId) {
        Response response = given()
                .auth()
                .basic(config.getProperty("secretKey"), "")
                .formParam("name", username)
                .formParam("email", email)
                .formParam("description", description)
                .log().all()
                .when()
                .post(config.getProperty("updateCustomerApiEndpoint") + "/" + customerId);

        return response;
    }
}
