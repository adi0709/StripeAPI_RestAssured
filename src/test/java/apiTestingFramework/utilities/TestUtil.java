package apiTestingFramework.utilities;

import apiTestingFramework.listeners.ExtentListeners;
import org.json.JSONObject;

public class TestUtil {

    public static boolean jsonHasKey(String json, String key){
        JSONObject jsonObject = new JSONObject(json);
        ExtentListeners.testReport.get().info("Validating the presence of "+key+" in the response");
        return jsonObject.has(key);
    }

    public static Object getJsonKeyValue(String json, String key){
        JSONObject jsonObject = new JSONObject(json);
        ExtentListeners.testReport.get().info("Getting the value of "+key+" from the response");
        return jsonObject.get(key);
    }
}
