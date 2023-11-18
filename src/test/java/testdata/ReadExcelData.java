package testdata;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadExcelData {
    XSSFWorkbook wb;
    XSSFSheet sheet;

    public ReadExcelData(String fileName, String sheetName) throws IOException {
        File file = new File(System.getProperty("user.dir")+"\\src\\test\\java\\testdata\\"+fileName);
        FileInputStream fin = new FileInputStream(file);
        wb = new XSSFWorkbook(fin);
        sheet = wb.getSheet(sheetName);
    }
    public Object[][] getData() {

        Object[][] data= new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

        for(int rowIndex=1;rowIndex<=sheet.getLastRowNum();rowIndex++){
            for(int colIndex=0;colIndex<sheet.getRow(rowIndex).getLastCellNum();colIndex++){
                data[rowIndex-1][colIndex] = sheet.getRow(rowIndex).getCell(colIndex).toString();
            }
        }
        return data;
    }
}
