package com.sieuthimini.GUI.PhieuComp.newPhieuNhapComp;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TableSanPham extends JPanel {
    JTable table = new JTable();

    private String[] columnNames = { "ID", "Tên", "Tuổi" }; // Example, mai mot flexible
    private Object[][] data = {
            { 1, "Nguyễn Văn A", 25 },
            { 2, "Trần Thị B", 30 },
            { 3, "Phạm Minh C", 22 }
    };

    public TableSanPham() {
        DefaultTableModel mode = new DefaultTableModel(data, columnNames);
        table = new JTable(mode);

        JScrollPane scrollPane = new JScrollPane(table);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        this.add(scrollPane);
    }
}
