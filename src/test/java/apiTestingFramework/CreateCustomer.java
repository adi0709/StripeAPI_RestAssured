package apiTestingFramework;

import apiTestingFramework.APIs.CreateCustomerApi;
import apiTestingFramework.pojo.Customer;
import apiTestingFramework.setup.BaseTest;
import apiTestingFramework.utilities.DataUtil;
import apiTestingFramework.utilities.TestUtil;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Hashtable;

public class CreateCustomer extends BaseTest {

    @Test(dataProviderClass = DataUtil.class, dataProvider = "Dp1")
    public void validateCreateCustomer(Hashtable<String,String> data){

        Response response= CreateCustomerApi.createCustomer(data);
        //ExtentListeners.testReport.get().info(data.toString());
        response.prettyPrint();
        Assert.assertEquals(response.statusCode(),200);

        Assert.assertTrue(TestUtil.jsonHasKey(response.asString(), "id"));
        Assert.assertTrue(TestUtil.jsonHasKey(response.asString(), "name"));
        Assert.assertTrue(TestUtil.jsonHasKey(response.asString(), "email"));
        Assert.assertTrue(TestUtil.jsonHasKey(response.asString(), "description"));

    }

    @Test(dataProviderClass = DataUtil.class, dataProvider = "Dp1")
    public void inValidateCreateCustomer(Hashtable<String,String> data){

        Response response= CreateCustomerApi.invalidCreateCustomer(data);
        //ExtentListeners.testReport.get().info(data.toString());
        response.prettyPrint();
        Assert.assertEquals(response.statusCode(),401);
    }

    @Test
    public void createCustomer(){
        Customer createCustomerData = new Customer("Adi", "Adi@gmail.com", "New Description for a new user");
        CreateCustomerApi.createCustomerFromPojo(createCustomerData.getName(), createCustomerData.getEmail(), createCustomerData.getDescription())
                .then()
                .statusCode(200)
                .body("id", is(notNullValue()))
                .body("name", equalTo(createCustomerData.getName()))
                .body("description", equalTo(createCustomerData.getDescription()))
                .body("email", equalTo(createCustomerData.getEmail()));
    }


}
