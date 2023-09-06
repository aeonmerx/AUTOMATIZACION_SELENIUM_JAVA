package data;

import org.testng.annotations.DataProvider;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class TestDataExcel {

    @DataProvider(name = "userTestDataExcel")
    public static Object[][] userTestData() throws IOException {
        String filePath = "src/test/resources/datos/DataSet.xlsx"; // Cambia esto a la ruta real de tu archivo Excel
        
        FileInputStream inputStream = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0); // Hoja en el índice 0 (primera hoja)

        int rowCount = sheet.getLastRowNum();
        int columnCount = sheet.getRow(0).getLastCellNum();

        Object[][] data = new Object[rowCount][columnCount];

        for (int i = 1; i <= rowCount; i++) { // Comienza desde la segunda fila (índice 1)
            Row row = sheet.getRow(i);

            for (int j = 0; j < columnCount; j++) {
                Cell cell = row.getCell(j);
                data[i - 1][j] = cell.toString();
            }
        }

        workbook.close();
        inputStream.close();

        return data;
    }
}
