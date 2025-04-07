package com.sieuthimini.GUI.PhieuComp;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.sieuthimini.DAO.DataBase;

public class Table extends JPanel {

    private String[] columnNames = {
            "maphieunhap",
            "ngaynhap",
            "mancc",
            "maaccount",
            "masp",
            "dongianhap",
            "ngayhethan",
            "soluongnhap"
    };
    JTable table;
    JScrollPane scrollPane;

    public Table() {
        this.setLayout(new BorderLayout());
        table = new JTable();
        DataBase db = new DataBase();
        List<Object[]> data = db.selectQuery("SELECT ef.maphieunhap, ef.ngaynhap, ef.mancc, ef.maaccount,\r\n" + //
                "               def.masp, def.dongianhap, def.ngayhethan, def.soluongnhap\r\n" + //
                "        FROM entry_form ef\r\n" + //
                "        JOIN detail_entry_form def ON ef.maphieunhap = def.maphieunhap");
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
