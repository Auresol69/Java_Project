package com.sieuthimini.GUI.PhieuComp.newPhieuNhapComp;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;

public class TimkiemSanPham extends JPanel {
    JTextField sortSanPham;
    JTable table;
    JButton addSanPham, nhapExcel;
    JScrollPane scrollPane;

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
        addSanPham = new JButton("Thêm sản phẩm");
        this.add(addSanPham, BorderLayout.SOUTH);
        nhapExcel = new JButton("Nhập từ Excel");
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        scrollPane = new JScrollPane(table);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.add(new TimkiemSanPham());
        frame.pack();
        frame.setVisible(true);
    }
}
