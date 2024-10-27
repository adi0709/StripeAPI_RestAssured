package apiTestingFramework.setup;

import apiTestingFramework.listeners.ExtentListeners;
import io.restassured.RestAssured;
import org.testng.TestNG;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import apiTestingFramework.utilities.ExcelReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {

    public static Properties config = new Properties();
    private FileInputStream fis;
    public static ExcelReader excel= new ExcelReader("./src/test/resources/Excel/testData.xlsx");

    @BeforeSuite
    public void setUp(){

        try {
            fis = new FileInputStream("./src/test/resources/properties/config.properties");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            config.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        RestAssured.basePath= config.getProperty("basePath");
        RestAssured.baseURI= config.getProperty("baseURI");
    }

    @AfterSuite
    public void tearDown() throws IOException {

        fis.close();
    }
}
