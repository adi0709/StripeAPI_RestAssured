package apiTestingFramework;

import apiTestingFramework.APIs.DeleteCustomerApi;
import apiTestingFramework.listeners.ExtentListeners;
import apiTestingFramework.setup.BaseTest;
import apiTestingFramework.utilities.DataUtil;
import apiTestingFramework.utilities.TestUtil;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Hashtable;

public class DeleteCustomer extends BaseTest {

    @Test
    public void deleteCreatedCustomer(){
        Response response = DeleteCustomerApi.deleteCreatedCustomer();
        response.prettyPrint();
        Assert.assertEquals(response.statusCode(), 200);
        JSONObject jsonObject = new JSONObject(response.asString());
        Assert.assertTrue(jsonObject.has("id"));


        Object deletedId= jsonObject.get("id");
        Object deleteTrue = jsonObject.get("deleted");
        Assert.assertTrue((Boolean) deleteTrue);
        Object object = jsonObject.get("object");
        Assert.assertEquals(deletedId, DeleteCustomerApi.createdCustomerId, "Id doesn't match with the actual id");
        Assert.assertEquals(object, "customer");
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
