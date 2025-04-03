package com.sieuthimini.GUI.PhieuNhapComp;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Table extends JPanel {

    private String[] columnNames = { "ID", "Tên", "Tuổi" }; // Example, mai mot flexible
    private Object[][] data = {
            { 1, "Nguyễn Văn A", 25 },
            { 2, "Trần Thị B", 30 },
            { 3, "Phạm Minh C", 22 }
    };
    JTable table;
    JScrollPane scrollPane;

    public Table() {
        this.setLayout(new BorderLayout());
        table = new JTable();
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        scrollPane = new JScrollPane(table);
        this.add(scrollPane);

    }
}
