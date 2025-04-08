package com.sieuthimini.GUI.PhieuComp.newPhieuNhapComp;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import com.sieuthimini.DAO.DAOTimkiemSanPham;
import com.sieuthimini.DAO.DataBase;
import com.sieuthimini.DTO.ProductDTO;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.List;

public class TimkiemSanPham extends JPanel {
    JTextField sortSanPham;
    JTable table;
    JScrollPane scrollPane;
    JButton addSanPham, nhapExcel;
    private Timer searchTimer;

    private String[] columnNames = { "masp", "tensp", "soluong", "dongiasanpham", "maloaisp", "mancc", "img" };
    private DefaultTableModel model;

    public TimkiemSanPham() {
        this.setLayout(new BorderLayout());

        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        scrollPane = new JScrollPane(table);

        loadDataToTable();

        if (table.getColumnCount() > 3) {
            table.removeColumn(table.getColumnModel().getColumn(3));
        }

        sortSanPham = new JTextField("Nhập id sản phẩm, tên sản phẩm...");
        sortSanPham.setForeground(Color.GRAY);

        sortSanPham.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (sortSanPham.getText().equals("Nhập id sản phẩm, tên sản phẩm...")) {
                    sortSanPham.setText("");
                    sortSanPham.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (sortSanPham.getText().isEmpty()) {
                    sortSanPham.setText("Nhập id sản phẩm, tên sản phẩm...");
                    sortSanPham.setForeground(Color.GRAY);
                }
            }
        });

        searchTimer = new Timer(300, e -> search());
        searchTimer.setRepeats(false);

        sortSanPham.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                restartSearchTimer();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                restartSearchTimer();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                restartSearchTimer();
            }

            private void restartSearchTimer() {
                if (searchTimer.isRunning())
                    searchTimer.restart();
                else
                    searchTimer.start();
            }
        });

        this.add(sortSanPham, BorderLayout.NORTH);
        this.add(getChucNang(), BorderLayout.SOUTH);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    private void loadDataToTable() {
        DataBase db = new DataBase();
        List<Object[]> data = db.selectQuery("SELECT * FROM product");
        model.setRowCount(0); // xoá dữ liệu cũ

        for (Object[] row : data) {
            model.addRow(row);
        }
    }

    private void search() {
        String keyword = sortSanPham.getText().trim();
        List<ProductDTO> results = new DAOTimkiemSanPham().searchSanPham(keyword);

        model.setRowCount(0); // xoá dữ liệu cũ

        for (ProductDTO sp : results) {
            model.addRow(new Object[] {
                    sp.getMasp(),
                    sp.getMaloaisp(),
                    sp.getMancc(),
                    sp.getTensp(),
                    sp.getImg(),
                    sp.getSoluong(),
                    sp.getDongiasanpham()
            });
        }
    }

    public JPanel getChucNang() {
        JPanel chucNang = new JPanel(new FlowLayout());
        addSanPham = new JButton("Thêm sản phẩm");
        nhapExcel = new JButton("Nhập từ Excel");
        chucNang.add(addSanPham);
        chucNang.add(nhapExcel);
        return chucNang;
    }
}
