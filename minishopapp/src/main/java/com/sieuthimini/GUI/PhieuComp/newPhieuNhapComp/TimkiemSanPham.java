package com.sieuthimini.GUI.PhieuComp.newPhieuNhapComp;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class TimkiemSanPham extends JPanel {
    JTextField sortSanPham;
    JTable table;
    JScrollPane scrollPane;
    JButton addSanPham, nhapExcel;

    private String[] columnNames = { "ID", "Tên", "Tuổi" }; // Example, mai mot flexible
    private Object[][] data = {
            { 1, "Nguyễn Văn A", 25 },
            { 2, "Trần Thị B", 30 },
            { 3, "Phạm Minh C", 22 }
    };

    public TimkiemSanPham() {

        this.setLayout(new BorderLayout());

        sortSanPham = new JTextField();
        this.add(sortSanPham, BorderLayout.NORTH);
        this.add(getChucNang(), BorderLayout.SOUTH);
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        table = new JTable(model);
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
