package apiTestingFramework;

import apiTestingFramework.APIs.UpdateCustomerApi;
import apiTestingFramework.pojo.Customer;
import apiTestingFramework.setup.BaseTest;
import apiTestingFramework.utilities.TestUtil;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UpdateCustomer extends BaseTest {
    Customer updateCustomerData = new Customer("Updated Name", "updatedName@gmail.com","Updated Description");

    @Test
    public void updateCustomer(){

        Response response = UpdateCustomerApi.UpdateCustomer(updateCustomerData.getName(), updateCustomerData.getEmail(), updateCustomerData.getDescription(), 14);
        response.prettyPrint();
        Assert.assertEquals(response.statusCode(),200);

        JSONObject jsonObject = new JSONObject(response.asString());

        Assert.assertTrue(TestUtil.jsonHasKey(response.asString(), "id"));
        Assert.assertTrue(TestUtil.jsonHasKey(response.asString(), "name"));
        Assert.assertTrue(TestUtil.jsonHasKey(response.asString(), "email"));
        Assert.assertTrue(TestUtil.jsonHasKey(response.asString(), "description"));

        Assert.assertEquals(jsonObject.get("name"), updateCustomerData.getName(), "The name of the user got updated to the new name");
        Assert.assertEquals(jsonObject.get("email"), updateCustomerData.getEmail(), "The email of the user got updated to the new email") ;
        Assert.assertEquals(jsonObject.get("description"), updateCustomerData.getDescription(), "The description of the user got updated to the new description");

    }
}
