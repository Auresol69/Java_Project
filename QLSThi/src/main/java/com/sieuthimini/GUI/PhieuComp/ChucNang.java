package com.sieuthimini.GUI.PhieuComp;

import com.sieuthimini.BUS.EntryFormBUS;
import com.sieuthimini.DTO.EntryFormDTO;
import com.sieuthimini.ExtendClasses.ExcelExporter;
import com.sieuthimini.ExtendClasses.GetImagePNG;
import com.sieuthimini.ExtendClasses.MessageBox;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class ChucNang extends JPanel implements ActionListener {
    JButton themButton, chiTietButton, huyButton, xuatExcelButton;
    JComboBox<String> columnSort;
    JTextField timKiem;
    JButton refreshButton;
    JFrame parent;
    Table table;
    NhapLieu nhapLieu;

    private void setUpButton(JButton button, String text, String imgName) {
        button.setText(text);
        ImageIcon icon = new ImageIcon(new GetImagePNG().getImage(imgName, 45));
        if (icon != null) {
            button.setIcon(icon);
        }
        button.setPreferredSize(new Dimension(100, 100));
        button.setFocusable(false);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.BOTTOM);
        button.addActionListener(this);
        this.add(button);
    }

    public ChucNang(JFrame parent) {
        this.parent = parent;
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 15));
        this.setBackground(Color.white);
        this.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        setUpButton(themButton = new JButton(), "Thêm", "square-plus-solid.png");
        setUpButton(chiTietButton = new JButton(), "Thông tin", "circle-info-solid.png");
        setUpButton(huyButton = new JButton(), "Hủy", "circle-xmark-solid.png");
        setUpButton(xuatExcelButton = new JButton(), "Xuất Excel", "file-excel-solid.png");

        String options[] = { "tất cả", "mã phiếu", "nhân viên", "nhà cung cấp" };
        columnSort = new JComboBox<>(options);
        columnSort.addActionListener(this);
        this.add(columnSort);

        columnSort.addActionListener(e -> {
            if (nhapLieu.getSearchTimer().isRunning())
                nhapLieu.getSearchTimer().restart();
            else
                nhapLieu.getSearchTimer().start();
        });

        timKiem = new JTextField("Nhập nội dung...");
        timKiem.setForeground(Color.GRAY);
        timKiem.setSize(200, timKiem.getHeight());
        timKiem.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (timKiem.getText().equals("Nhập nội dung...")) {
                    timKiem.setText("");
                    timKiem.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (timKiem.getText().isEmpty()) {
                    timKiem.setText("Nhập nội dung...");
                    timKiem.setForeground(Color.GRAY);
                }
            }
        });
        this.add(timKiem);
        timKiem.getDocument().addDocumentListener(new DocumentListener() {

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
                if (nhapLieu.getSearchTimer().isRunning())
                    nhapLieu.getSearchTimer().restart();
                else
                    nhapLieu.getSearchTimer().start();
            }

        });

        setUpButton(refreshButton = new JButton(), "Làm mới", "arrows-rotate-solid.png");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == themButton) {
            parent.setContentPane(new newPhieuNhap(this.parent));
            parent.revalidate();
            parent.repaint();
        }
        if (e.getSource() == chiTietButton) {
            if (table.getTable().getSelectedRow() == -1) {
                MessageBox.showError("Vui lòng chọn 1 phiếu nhập!!!");
            } else {
                ChiTietPhieuNhap chiTietPhieuNhap = new ChiTietPhieuNhap(parent, table.getTable());
                chiTietPhieuNhap.setVisible(true);
            }
        }
        if (e.getSource() == refreshButton) {
            timKiem.setText("");
            columnSort.setSelectedIndex(0);
            nhapLieu.resetFilter();
        }
        if (e.getSource() == huyButton) {
            int selectedRow = table.getTable().getSelectedRow();
            if (selectedRow == -1) {
                MessageBox.showError("Vui lòng chọn phiếu nhập để hủy.");
                return;
            }

            int maphieunhap = Integer.parseInt(table.getTable().getValueAt(selectedRow, 0).toString());

            EntryFormBUS bus = new EntryFormBUS();
            EntryFormDTO dto = bus.getEntryFormByID(maphieunhap);
            bus.huyEntryForm(dto);
            nhapLieu.resetFilter();
        }
        if (e.getSource() == xuatExcelButton) {
            ExcelExporter.exportJTableToExcel(table.getTable());
        }
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public NhapLieu getNhapLieu() {
        return nhapLieu;
    }

    public void setNhapLieu(NhapLieu nhapLieu) {
        this.nhapLieu = nhapLieu;
    }

    public JComboBox<String> getColumnSort() {
        return columnSort;
    }

    public void setColumnSort(JComboBox<String> columnSort) {
        this.columnSort = columnSort;
    }

    public JTextField getTimKiem() {
        return timKiem;
    }

    public void setTimKiem(JTextField timKiem) {
        this.timKiem = timKiem;
    }
}
