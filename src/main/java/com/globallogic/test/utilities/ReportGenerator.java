package com.globallogic.test.utilities;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

public class ReportGenerator {
    public static HashMap<String, Object[]> testResulltDetails = new HashMap<String, Object[]>();
public static int passCounter=0;
    public static int failCounter=0;
    @Test
    public File reportGeneration() {
        File file = null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
            Date date = new Date();
            String latername = formatter.format(date);
            file = new File( PropertiesUtils.getProperty("outputFolder.path")+ latername + ".xlsx");
            File template_File = new File("src\\main\\java\\reportTemplete\\TestResult_Report_Temlate.xlsx");
            Workbook wb = new XSSFWorkbook(new FileInputStream(template_File));
            Sheet sheet1;
            Set<String> keyset = testResulltDetails.keySet();
            Row row;
            Cell cell;
            int rownum = 1;
            sheet1 = wb.getSheetAt(0);
            for (String key : keyset) {
                row = sheet1.createRow(rownum++);
                Object[] objArr = testResulltDetails.get(key);
                int cellnum = 0;
                for (Object obj : objArr) {
                    cell = row.createCell(cellnum++);
                    if (obj instanceof String) {
                        cell.setCellValue((String) obj);
                        if(((String) obj).equalsIgnoreCase("pass")){
                            passCounter++;
                        }
                        else     if(((String) obj).equalsIgnoreCase("fail")){
                            failCounter++;
                        }
                    }
                }
            }
            try {
                FileOutputStream out = new FileOutputStream(file);
                wb.write(out);
                out.close();
                System.out.println("Successfully saved Selenium WebDriver TestNG result to Excel File!!!");
                testResulltDetails = null;
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return file;
    }

}