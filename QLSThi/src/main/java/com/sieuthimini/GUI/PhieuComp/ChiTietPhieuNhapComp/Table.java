package com.sieuthimini.GUI.PhieuComp.ChiTietPhieuNhapComp;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Table extends JPanel {
    JTable table;
    JScrollPane scrollPane;
    private String[] columnNames = { "mã sản phẩm", "tên sản phẩm", "số lượng", "đơn giá nhập", "ngày hết hạn" };
    private DefaultTableModel model;

    public Table() {
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        scrollPane = new JScrollPane(table);

        table.setAutoCreateRowSorter(true);

        // Giới hạn hiển thị 5 dòng
        int visibleRowCount = 5;
        int rowHeight = table.getRowHeight();
        int headerHeight = table.getTableHeader().getPreferredSize().height;
        int totalHeight = visibleRowCount * rowHeight + headerHeight;

        scrollPane.setPreferredSize(new Dimension(600, totalHeight));

        // Dùng BoxLayout theo chiều dọc để tránh bị giãn full
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(scrollPane);
    }
}
