package apiTestingFramework.utilities;

import apiTestingFramework.setup.BaseTest;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.Hashtable;

public class DataUtil extends BaseTest {

    //Data provider for the tests
    @DataProvider(name= "Dp1", parallel = true)
    public Object[][] getData(Method m){

        int rows = excel.getRowCount(Constants.DATA_SHEET);
        //int cols = excel.getColumnCount(Constants.DATA_SHEET);

        String testName = m.getName();
        //Find the test case start row
        int testCaseRowNum = 1;

        for (testCaseRowNum = 1; testCaseRowNum <= rows; testCaseRowNum++) {

            String testCaseName = excel.getCellData(Constants.DATA_SHEET, 0, testCaseRowNum);

            if (testCaseName.equalsIgnoreCase(testName)) {
                break;
            }

        }
        System.out.println("Test case starts from row num -->" + testCaseRowNum);

        //Checking total rows in test case
        int dataStartRowNum = testCaseRowNum + 2;
        int testRows = 0;

        while (!excel.getCellData(Constants.DATA_SHEET, 0, dataStartRowNum + testRows).equals("")) {

            testRows++;
        }
        System.out.println("Total rows of data are --->" + testRows);


        //Checking total number of cols in test case
        int colStartColNum = testCaseRowNum + 1;
        int testCols = 0;
        while (!excel.getCellData(Constants.DATA_SHEET, testCols, colStartColNum).equals("")) {

            testCols++;
        }

        System.out.println("Total columns are -->" + testCols);

        //Print test data

        Object[][] data = new Object[testRows][1];
        int i= 0;
        for (int rNum = dataStartRowNum; rNum < (dataStartRowNum + testRows); rNum++) {

            Hashtable<String,String> table = new Hashtable<String,String>();
            for (int cNum = 0; cNum < testCols; cNum++) {
                //Store data in the data Object
                //data[(rNum-dataStartRowNum)][cNum] = excel.getCellData(Constants.DATA_SHEET, cNum,rNum);
                String testData = excel.getCellData(Constants.DATA_SHEET, cNum, rNum);
                String colName = excel.getCellData(Constants.DATA_SHEET, cNum, colStartColNum);

                table.put(colName,testData);
            }
            data[i][0] = table;
            i++;
        }

        return data;
    }
}
