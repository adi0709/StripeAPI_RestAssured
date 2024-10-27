package apiTestingFramework.utilities;
import apiTestingFramework.setup.BaseTest;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelWriter extends BaseTest {

    public void updateCustomerId(String customerId) {
        String filePath = "./src/test/resources/Excel/testData.xlsx";
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);  // Assuming the data is in the first sheet
            boolean foundDeleteCustomer = false;

            // Loop through rows to find "deleteCustomer" test and update customerId
            for (Row row : sheet) {
                Cell cell = row.getCell(0);
                if (cell != null && cell.getCellType() == CellType.STRING) {
                    if ("DeleteCustomer".equals(cell.getStringCellValue())) {
                        foundDeleteCustomer = true;
                        continue;  // Move to the next row after "deleteCustomer"
                    }
                }

                if (foundDeleteCustomer) {
                    int rowNum = row.getRowNum();
                    int idRowNum = rowNum +1;
                    Row idRow = sheet.getRow(idRowNum);
                    Cell customerIdCell = idRow.getCell(0);
                    //Setting the value of the customer Id
                    customerIdCell.setCellValue(customerId);
                    break;  // Exit loop after updating the cell
                }
            }

            // Write the changes to the file
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                workbook.write(fos);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
