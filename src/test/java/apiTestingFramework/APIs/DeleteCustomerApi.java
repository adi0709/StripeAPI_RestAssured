package apiTestingFramework.APIs;

import apiTestingFramework.setup.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

import java.util.Hashtable;

import static io.restassured.RestAssured.given;

public class DeleteCustomerApi extends BaseTest {

    public static String createdCustomerId;

    public static Response deleteCreatedCustomer(String createdCustomerId){
        Response deleteCustomerResponse = given()
                .auth()
                .basic(config.getProperty("secretKey"),"")
                .log().all()
                .when()
                .delete(config.getProperty("deleteCustomerApiEndpoint")+"/"+createdCustomerId);
        deleteCustomerResponse.prettyPrint();
        return deleteCustomerResponse;
    }

    public static Response deleteCustomer(Hashtable<String, String> data){
        Response deleteCustomerResponse = given()
                .auth()
                .basic(config.getProperty("secretKey"),"")
                .log().all()
                .when()
                .delete(config.getProperty("deleteCustomerApiEndpoint")+"/"+data.get("id"));
        return deleteCustomerResponse;
    }
}
