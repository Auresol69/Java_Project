package com.sieuthimini.GUI.PhieuComp.newPhieuNhapComp;

import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.sieuthimini.DAO.DataBase;

public class TableSanPham extends JPanel {
    JTable table = new JTable();

    private String[] columnNames = { "masp", "tensp", "soluong", "dongiasanpham", "maloaisp", "mancc", "img" };

    public TableSanPham() {

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        this.add(scrollPane);
    }
}
