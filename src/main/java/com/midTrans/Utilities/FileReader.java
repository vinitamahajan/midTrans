package com.midTrans.Utilities;


import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

    public List<String> getRowsfromFile(String FilePath) throws IOException
    {
        int noOfRows;
        List<String> FileRows = new ArrayList<String>();
        FileInputStream fis = new FileInputStream(new File(FilePath));
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(0);
        noOfRows = sheet.getLastRowNum();

        for(int rowNumber = 0; rowNumber <= sheet.getLastRowNum(); rowNumber++) {
            XSSFRow row = sheet.getRow(rowNumber);
            XSSFCell cell = row.getCell(0);
            //FileRows.stream().map(e->e.toString()).collect(Collectors.toList());
            FileRows.add(cell.toString());
        }
        return FileRows;
    }

}

