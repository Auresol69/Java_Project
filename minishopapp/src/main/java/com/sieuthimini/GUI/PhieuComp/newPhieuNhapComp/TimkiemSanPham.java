package com.sieuthimini.GUI.PhieuComp.newPhieuNhapComp;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.sieuthimini.DAO.DataBase;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

public class TimkiemSanPham extends JPanel {
    JTextField sortSanPham;
    JTable table;
    JScrollPane scrollPane;
    JButton addSanPham, nhapExcel;

    private String[] columnNames = { "masp", "tensp", "soluong", "dongiasanpham", "maloaisp", "mancc", "img" };

    public TimkiemSanPham() {
        this.setLayout(new BorderLayout());

        DataBase db = new DataBase();

        List<Object[]> data = db.selectQuery("SELECT * FROM product");

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Object[] row : data) {
            model.addRow(row);
        }

        table = new JTable(model);
        for (int i = 0; i < 4; ++i)
            table.removeColumn(table.getColumnModel().getColumn(3));

        sortSanPham = new JTextField();
        this.add(sortSanPham, BorderLayout.NORTH);
        this.add(getChucNang(), BorderLayout.SOUTH);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        scrollPane = new JScrollPane(table);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    public JPanel getChucNang() {
        JPanel chucNang = new JPanel();
        chucNang.setLayout(new FlowLayout());
        addSanPham = new JButton("Thêm sản phẩm");
        chucNang.add(addSanPham);
        nhapExcel = new JButton("Nhập từ Excel");
        chucNang.add(nhapExcel);
        return chucNang;
    }
}
