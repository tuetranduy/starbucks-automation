package org.fw.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ExcelReader {
    private final String fileLocation;
    private Workbook workbook;
    public ExcelReader(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public LinkedHashMap<Integer, List<String>> read() throws IOException {

        FileInputStream file = new FileInputStream(this.fileLocation);
        workbook = new XSSFWorkbook(file);

        LinkedHashMap<Integer, List<String>> data = new LinkedHashMap<>();

        try {
            Sheet sheet = workbook.getSheetAt(0);

            int i = 0;
            for (Row row : sheet) {
                data.put(i, new ArrayList<>());
                for (Cell cell : row) {
                    switch (cell.getCellType()) {
                        case STRING -> data.get(i).add(cell.getRichStringCellValue().getString());
                        case NUMERIC -> {
                            if (DateUtil.isCellDateFormatted(cell)) {
                                data.get(i).add(cell.getDateCellValue() + "");
                            } else {
                                data.get(i).add(cell.getNumericCellValue() + "");
                            }
                        }
                        case BOOLEAN -> data.get(i).add(cell.getBooleanCellValue() + "");
                        case FORMULA -> data.get(i).add(cell.getCellFormula() + "");
                        default -> data.get(i).add(" ");
                    }
                }
                i++;
            }
        } catch (Exception ioe) {
            ioe.printStackTrace();
        }

        return data;
    }

    public void write(int rowIndex, int cellIndex, String value) {
        Sheet sheet = workbook.getSheetAt(0);
        Row row = sheet.getRow(rowIndex);

        Cell cell = row.createCell(cellIndex);
        cell.setCellValue(value);
    }

    public void save() throws IOException {
        FileOutputStream outputStream = new FileOutputStream(fileLocation);
        workbook.write(outputStream);
        workbook.close();
    }
}
