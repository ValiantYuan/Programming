package poi;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;


/**
 * @author : yuanqi
 * @since : 2020/8/7
 */
class Test {
    public static void main(String[] args) throws IOException {
        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream("/Users" +
            "/yuanqi/Downloads/test.xlsx"));
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        int end = sheet.getPhysicalNumberOfRows();
        int firstRow = sheet.getFirstRowNum() + 1;
        for (int i = firstRow; i < end; i++) {
            Row row = sheet.getRow(i);
            Cell cell = row.getCell(5);
            Hyperlink h = cell.getHyperlink();
            if (h == null) {
                System.out.println("row " + i + " has no hyper link");
            }
            else System.out.println(h.getLabel() + h.getAddress());

        }

    }
}
