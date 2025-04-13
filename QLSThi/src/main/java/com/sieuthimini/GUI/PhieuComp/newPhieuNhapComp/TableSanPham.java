package com.sieuthimini.GUI.PhieuComp.newPhieuNhapComp;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TableSanPham extends JPanel {
    JTable table = new JTable();
    DefaultTableModel model;
    private String[] columnNames = { "masp", "tensp", "soluong", "dongiasanpham", "tenloaisp", "ncc" };

    public TableSanPham() {

        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);

        table.setAutoCreateRowSorter(true);

        JScrollPane scrollPane = new JScrollPane(table);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        this.add(scrollPane);
    }
}
