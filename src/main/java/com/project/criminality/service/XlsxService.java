package com.project.criminality.service;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class XlsxService {

    public static HashMap<Integer, Integer> getPopulationMapFromXlsx(int ageRange) throws IOException {

        HashMap<Integer, Integer> populationMap = new HashMap<>();

        // File myFile = new File("C:\\Users\\user\\Dropbox\\Studium_HTW\\Semester\\SS_22\\Grundlagen sozialer Netze\\Projekt\\Daten\\01-BU-BV-TVBZ-deu-ab-1987_xls.xlsx");

        File myFile = new File(".\\populations.xlsx");
        FileInputStream fis = new FileInputStream(myFile);

        // Finds the workbook instance for XLSX file
        XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);

        // Return first sheet from the XLSX workbook
        XSSFSheet mySheet = myWorkBook.getSheetAt(1);

        // Get iterator to all the rows in current sheet
        Iterator<Row> rowIterator = mySheet.iterator();

// Traversing over each row of XLSX file
        for (int year = 1993; year <= 2021; year += 1) {
            Row row = rowIterator.next();

            // For each row, iterate through each columns
            Iterator<Cell> cellIterator = row.cellIterator();

            for (int currentAgeRange = 20; currentAgeRange <= 60; currentAgeRange += 10) {
                Cell cell = cellIterator.next();

                if (currentAgeRange == ageRange) {
                    int population = (int) cell.getNumericCellValue();
                    populationMap.put(year, population);
                }
            }
        }
        return populationMap;
    }
}
