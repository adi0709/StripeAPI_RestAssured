package apiTestingFramework;

import apiTestingFramework.APIs.CreateCustomerApi;
import apiTestingFramework.APIs.DeleteCustomerApi;
import apiTestingFramework.listeners.ExtentListeners;
import apiTestingFramework.pojo.Customer;
import apiTestingFramework.setup.BaseTest;
import apiTestingFramework.utilities.DataUtil;
import apiTestingFramework.utilities.ExcelWriter;
import apiTestingFramework.utilities.TestUtil;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Hashtable;
import static org.hamcrest.Matchers.*;

public class DeleteCustomer extends BaseTest {
    Customer createCustomerData = new Customer("Abcd", "RandomEmail@gmail.com", "Adding a random new customer");
    static String createdCustomerId;

    @BeforeTest
    void createCustomer() throws IOException {
        Response createCustomerResponse = CreateCustomerApi.createCustomerFromPojo(createCustomerData.getName(), createCustomerData.getEmail(), createCustomerData.getDescription());
        JSONObject jsonObject = new JSONObject(createCustomerResponse.asString());
        createdCustomerId = (jsonObject.get("id")).toString();
        System.out.println(createdCustomerId);

        ExcelWriter writeToExcel = new ExcelWriter();
        writeToExcel.updateCustomerId(createdCustomerId);
    }

    @Test
    public void deleteCreatedCustomer(){
        DeleteCustomerApi.deleteCreatedCustomer(createdCustomerId)
                        .then()
                                .statusCode(200)
                .body("id", is(notNullValue()))
                .body("id", equalTo(createdCustomerId))
                .body("object", equalTo("customer"))
                .body("deleted", equalTo(true));
    }

    @Test(dataProviderClass = DataUtil.class, dataProvider = "Dp1")
    public void deleteCustomer(Hashtable<String, String>data){
        Response response = DeleteCustomerApi.deleteCustomer(data);
        response.prettyPrint();
        System.out.println("Deleted Id"+response.jsonPath().get("id").toString());

        Assert.assertTrue(TestUtil.jsonHasKey(response.asString(), "id"));
        Object deletedId= TestUtil.getJsonKeyValue(response.asString()  ,"id");
        Object deleteTrue = TestUtil.getJsonKeyValue(response.asString(), "deleted");
        ExtentListeners.testReport.get().info(data.toString());

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(deletedId, data.get("id"), "Id doesn't match with the actual id");
        Assert.assertTrue((Boolean) deleteTrue);
    }
}
