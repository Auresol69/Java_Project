package com.sieuthimini.GUI.PhieuComp.newPhieuNhapComp;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.sieuthimini.DAO.TimkiemSanPhamDAO;
import com.sieuthimini.DAO.DataBase;
import com.sieuthimini.DTO.ProductDTO;
import com.sieuthimini.DTO.ProductTypeDTO;

import java.awt.*;
import java.util.List;

public class TimkiemSanPham extends JPanel implements ListSelectionListener {
    JTextField sortSanPham;
    JTable table;
    JScrollPane scrollPane;
    JButton addSanPham, nhapExcel;
    private Timer searchTimer;
    InputSanPham inputSanPham;

    private String[] columnNames = { "masp", "tensp", "soluong", "dongiasanpham", "maloaisp", "mancc", "img" };
    private DefaultTableModel model;

    public TimkiemSanPham(InputSanPham inputSanPham) {
        this.inputSanPham = inputSanPham;

        this.setLayout(new BorderLayout());

        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        scrollPane = new JScrollPane(table);

        loadDataToTable();

        if (table.getColumnCount() > 3) {
            table.removeColumn(table.getColumnModel().getColumn(3));
        }

        table.setAutoCreateRowSorter(true);

        // Đẩy dữ liệu vào inputSanPham
        table.getSelectionModel().addListSelectionListener(this);

        sortSanPham = new JTextField("Nhập id sản phẩm, tên sản phẩm...");
        sortSanPham.setForeground(Color.GRAY);

        // sortSanPham.addFocusListener(new FocusAdapter() {
        // @Override
        // public void focusGained(FocusEvent e) {
        // if (sortSanPham.getText().equals("Nhập id sản phẩm, tên sản phẩm...")) {
        // sortSanPham.setText("");
        // sortSanPham.setForeground(Color.BLACK);
        // }
        // }

        // @Override
        // public void focusLost(FocusEvent e) {
        // if (sortSanPham.getText().isEmpty()) {
        // sortSanPham.setText("Nhập id sản phẩm, tên sản phẩm...");
        // sortSanPham.setForeground(Color.GRAY);
        // }
        // }
        // });

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
        List<ProductDTO> results = new TimkiemSanPhamDAO().searchSanPham(keyword);

        model.setRowCount(0); // xoá dữ liệu cũ

        for (ProductDTO sp : results) {
            model.addRow(new Object[] {
                    sp.getMasp(),
                    sp.getMaloaisp(),
                    sp.getSoluong(),
                    sp.getDongiasanpham(),
                    sp.getMancc(),
                    sp.getTensp(),
                    sp.getImg()
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

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting() && e.getSource() == table.getSelectionModel()) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int modelRow = table.convertRowIndexToModel(selectedRow);
                inputSanPham.maSanPhamField.setText(model.getValueAt(modelRow, 0).toString());
                inputSanPham.tenSanPhamField.setText(model.getValueAt(modelRow, 1).toString());
                setSelectedLoaiSanPham(model.getValueAt(modelRow, 4).toString());
            }
        }
    }

    public void setSelectedLoaiSanPham(String maLoaiDaChon) {
        for (int i = 0; i < inputSanPham.loaiSanPhamComboBox.getItemCount(); i++) {
            ProductTypeDTO item = inputSanPham.loaiSanPhamComboBox.getItemAt(i);
            if (item.getMaloaisp().equals(maLoaiDaChon)) {
                inputSanPham.loaiSanPhamComboBox.setSelectedItem(item);
                break;
            }
        }
    }
}
