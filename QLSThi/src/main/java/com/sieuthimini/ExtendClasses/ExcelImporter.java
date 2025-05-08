package com.sieuthimini.ExtendClasses;

import java.io.File;
import java.io.FileInputStream;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelImporter {
    public static void importExcelToJTable(JTable table) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn nơi nhập file Excel");

        int userSelection = fileChooser.showOpenDialog(null);
        if (userSelection != JFileChooser.APPROVE_OPTION)
            return;

        File fileToOpen = fileChooser.getSelectedFile();
        try (FileInputStream fis = new FileInputStream(fileToOpen); Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            // Removed column count check to allow import even if columns differ
            // if (sheet.getRow(0).getLastCellNum() != table.getColumnCount()) {
            // MessageBox.showError("Số cột không tương ứng!");
            // return;
            // }

            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0); // Clear existing rows

            // Optional: Check if columns match and update model columns if needed
            // For now, keep existing columns

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null)
                    continue;
                Object[] rowData = new Object[model.getColumnCount()];
                for (int j = 0; j < model.getColumnCount(); j++) {
                    Cell cell = row.getCell(j);
                    rowData[j] = getCellValue(cell);
                }
                model.addRow(rowData);
            }
            MessageBox.showInfo("Import Excel thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            MessageBox.showError("Lỗi khi import Excel!");
        }
    }

    private static Object getCellValue(Cell cell) {
        if (cell == null)
            return "";
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                double numericValue = cell.getNumericCellValue();
                return (int) numericValue;
            case BOOLEAN:
                return cell.getBooleanCellValue();
            default:
                return "";
        }
    }
}
