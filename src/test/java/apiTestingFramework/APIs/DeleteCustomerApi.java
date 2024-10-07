package apiTestingFramework.APIs;

import apiTestingFramework.setup.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

import java.util.Hashtable;

import static io.restassured.RestAssured.given;

public class DeleteCustomerApi extends BaseTest {

    public static String createdCustomerId;

    public static Response deleteCreatedCustomer(){

        //Creating a customer to be deleted
        Response createCustomerResponse =
                given()
                        .auth()
                        .basic(config.getProperty("secretKey"),"")
                        .formParam("name", "Abcd")
                        .formParam("email", "RandomEmail@gmail.com")
                        .formParam("description", "Adding a random new customer")
                        .post(config.getProperty("customerApiEndpoint"));
        JSONObject jsonObject= new JSONObject(createCustomerResponse.asString());
        createdCustomerId = (jsonObject.get("id")).toString();

        Response deleteCustomerResponse = given()
                .auth()
                .basic(config.getProperty("secretKey"),"")
                .delete(config.getProperty("deleteCustomerApiEndpoint")+"/"+createdCustomerId);
        return deleteCustomerResponse;
    }

    public static Response deleteCustomer(Hashtable<String, String> data){
        Response deleteCustomerResponse = given()
                .auth()
                .basic(config.getProperty("secretKey"),"")
                .delete(config.getProperty("deleteCustomerApiEndpoint")+"/"+data.get("id"));
        return deleteCustomerResponse;
    }
}