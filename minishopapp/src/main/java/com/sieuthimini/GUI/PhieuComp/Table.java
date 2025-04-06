package com.sieuthimini.GUI.PhieuComp;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.sieuthimini.DAO.DataBase;

public class Table extends JPanel {

    private String[] columnNames = { "masp", "tensp", "soluong", "dongiasanpham", "maloaisp", "mancc", "img" };

    JTable table;
    JScrollPane scrollPane;

    public Table() {
        this.setLayout(new BorderLayout());
        table = new JTable();
        DataBase db = new DataBase();
        List<Object[]> data = db.selectQuery("product");
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        for (Object[] row : data) {
            model.addRow(row);
        }
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        scrollPane = new JScrollPane(table);
        this.add(scrollPane);

    }
}
