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
            "Mã Phiếu nhập",
            "Nhà cung cấp",
            "Nhân viên nhập",
            "Ngày nhập",
            "Tổng tiền"
    };
    JTable table;
    JScrollPane scrollPane;

    public Table() {
        this.setLayout(new BorderLayout());
        table = new JTable();
        DataBase db = new DataBase();
        List<Object[]> data = db
                .selectQuery("SELECT \r\n" + //
                        "    ef.maphieunhap, \r\n" + //
                        "    sup.tencc, \r\n" + //
                        "    sta.tennhanstaff,\r\n" + //
                        "    ef.ngaynhap, \r\n" + //
                        "    SUM(def.soluongnhap * def.dongianhap) AS tong_tien_nhap\r\n" + //
                        "FROM \r\n" + //
                        "    entry_form ef\r\n" + //
                        "JOIN \r\n" + //
                        "    detail_entry_form def ON ef.maphieunhap = def.maphieunhap\r\n" + //
                        "JOIN \r\n" + //
                        "    supplier sup ON ef.mancc = sup.mancc\r\n" + //
                        "JOIN \r\n" + //
                        "\taccount acc ON ef.maaccount = acc.maaccount\r\n" + //
                        "JOIN \r\n" + //
                        "    staff sta ON acc.mastaff = sta.mastaff\r\n" + //
                        "GROUP BY \r\n" + //
                        "    ef.maphieunhap, sup.tencc, sta.tennhanstaff, ef.ngaynhap;\r\n" + //
                        "");
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
