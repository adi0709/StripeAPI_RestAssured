package apiTestingFramework.APIs;

import apiTestingFramework.pojo.Customer;
import apiTestingFramework.setup.BaseTest;
import io.restassured.response.Response;

import java.util.Hashtable;

import static io.restassured.RestAssured.given;

public class CreateCustomerApi extends BaseTest {

    public static Response createCustomer(Hashtable<String,String> data){
        Response response= given()
                .auth()
                .basic(config.getProperty("secretKey"),"")
                .formParam("name", data.get("name"))
                .formParam("email", data.get("email"))
                .formParam("description", data.get("description"))
                .post(config.getProperty("customerApiEndpoint"));
        return response;
    }

    public static Response invalidCreateCustomer(Hashtable<String, String> data){
        Response response= given()
                .auth()
                .basic(config.getProperty("inValidSecretKey"),"")
                .formParam("name", data.get("name"))
                .formParam("email", data.get("email"))
                .formParam("description", data.get("description"))
                .post(config.getProperty("customerApiEndpoint"));

        return response;
    }

    public static Response createCustomerFromPojo(String username, String email, String description){
        Customer customer = new Customer(username, email, description);

        Response response= given()
                .auth()
                .basic(config.getProperty("secretKey"),"")
                .formParam("name", customer.getName())
                .formParam("email", customer.getEmail())
                .formParam("description", customer.getDescription())
                .log().all()
                .when()
                .post(config.getProperty("customerApiEndpoint"));

        return response;
    }
}
