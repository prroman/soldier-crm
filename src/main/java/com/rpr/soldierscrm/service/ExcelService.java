package com.rpr.soldierscrm.service;

import com.rpr.soldierscrm.repository.SoldierRepository;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
@AllArgsConstructor
public class ExcelService {

    private final SoldierRepository soldierRepository;

    public ByteArrayOutputStream generateExcel() throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        Sheet sheet = workbook.createSheet("Persons");
        sheet.setColumnWidth(0, 6000);
        sheet.setColumnWidth(1, 4000);

        Row header = sheet.createRow(0);

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFFont font = workbook.createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 16);
        font.setBold(true);
        headerStyle.setFont(font);

        Cell headerCell = header.createCell(0);
        headerCell.setCellValue("Загальна кілкість солдат");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(1);
        headerCell.setCellValue("Хворих");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(2);
        headerCell.setCellValue("Здорових");
        headerCell.setCellStyle(headerStyle);

        CellStyle style = workbook.createCellStyle();
        style.setWrapText(true);

        Row row = sheet.createRow(2);
        Cell cell = row.createCell(0);
        cell.setCellValue(10);
        cell.setCellStyle(style);

        cell = row.createCell(1);
        cell.setCellValue(20);
        cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue(20);
        cell.setCellStyle(style);

        workbook.write(baos);
        workbook.close();
        return baos;
    }

    public void writeSpecificCell() throws IOException {
        FileInputStream file = new FileInputStream(new File("D:\\Workspace\\IntellijIDEAWorkspace\\soldier-crm\\02_stroyova.xlsx"));
        Workbook workbook = new XSSFWorkbook(file);
        //creating a Sheet object to retrieve the object
        Sheet sheet = workbook.getSheetAt(0);

        Row r = sheet.getRow(14); // 10-1
        if (r == null) {
            // First cell in the row, create
            r = sheet.createRow(14);
        }

        Cell c = r.getCell(6); // 4-1
        if (c == null) {
            // New cell
            c = r.createCell(6, CellType.NUMERIC);
        }

        FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();

        int value = 0;
        if (c != null) {
            CellType cellType = formulaEvaluator.evaluateFormulaCell(c);
            switch (cellType) {
                case BOOLEAN:
                    System.out.println(c.getBooleanCellValue());
                    break;
                case NUMERIC:
                    System.out.println(c.getNumericCellValue());
                    break;
                case _NONE:
                    System.out.println("Here");
                    value = (int) c.getNumericCellValue();
                    System.out.println(c.getNumericCellValue());
                    break;
                case STRING:
                    System.out.println(c.getStringCellValue());
                    break;
                case BLANK:
                    break;
                case ERROR:
                    System.out.println(c.getErrorCellValue());
                    break;

                // CELL_TYPE_FORMULA will never occur
                case FORMULA:
                    break;
            }
        }
        c.setCellValue(value + 1 + "xs");

        //print to console
/*        for (Row row : sheet) {     //iteration over row using for each loop{
            for (Cell cell : row)    //iteration over cell using for each loop
            {
                switch (formulaEvaluator.evaluateInCell(cell).getCellType()) {
                    case NUMERIC:   //field that represents numeric cell type
                        //getting the value of the cell as a number
                        System.out.print(cell.getNumericCellValue() + "\t\t");
                        break;
                    case STRING:    //field that represents string cell type
                        //getting the value of the cell as a string
                        System.out.print(cell.getStringCellValue() + "\t\t");
                        break;
                }
            }
            System.out.println();
        }*/

        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        String fileLocation = path.substring(0, path.length() - 1) + "temp.xlsx";

        FileOutputStream outputStream = new FileOutputStream(fileLocation);
        workbook.write(outputStream);
        workbook.close();
    }

    public static void main(String[] args) throws IOException {
        ExcelService excelService = new ExcelService(null);
        excelService.writeSpecificCell();
    }

}
