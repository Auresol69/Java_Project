package com.sieuthimini.GUI.PhieuComp.newPhieuNhapComp;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sieuthimini.BUS.AccountBUS;
import com.sieuthimini.BUS.DetailEntryFormBUS;
import com.sieuthimini.BUS.SupplierBUS;
import com.sieuthimini.DTO.AccountDTO;
import com.sieuthimini.DTO.SupplierDTO;
import com.sieuthimini.ExtendClasses.MessageBox;
import com.sieuthimini.GUI.PhieuNhap;

import javax.swing.JButton;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;

public class TongTien extends JPanel implements ActionListener {
    JLabel gianhapLabel, manhanviennhapLabel, nhacungcapLabel, loinhuanLabel;
    JTextField gianhapField, loinhuanField;
    JComboBox<AccountDTO> manhanviennhapComboBox;
    JComboBox<SupplierDTO> nhacungcapComboBox;
    JLabel totalAmount;
    JButton nhapHang;
    TableSanPham tableSanPham;
    JFrame parent;

    public TongTien(JFrame parent) {
        this.parent = parent;

        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        manhanviennhapLabel = new JLabel("Mã nhân viên nhập:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        this.add(manhanviennhapLabel, gbc);

        manhanviennhapComboBox = new JComboBox<>();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        this.add(manhanviennhapComboBox, gbc);
        getNhanVienComboBox();

        gianhapLabel = new JLabel("Giá nhập:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        this.add(gianhapLabel, gbc);

        gianhapField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        this.add(gianhapField, gbc);

        loinhuanLabel = new JLabel("Lợi nhuận (%):");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        this.add(loinhuanLabel, gbc);

        loinhuanField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        this.add(loinhuanField, gbc);

        nhacungcapLabel = new JLabel("Nhà cung cấp:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        this.add(nhacungcapLabel, gbc);

        nhacungcapComboBox = new JComboBox<>();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        this.add(nhacungcapComboBox, gbc);
        getNhaCungCapComboBox();

        totalAmount = new JLabel("Tổng tiền: 0");
        totalAmount.setFont(new Font("Segoe UI", Font.BOLD, 15));
        totalAmount.setForeground(Color.RED);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(totalAmount, gbc);

        nhapHang = new JButton("Nhập hàng");
        nhapHang.setFocusable(false);
        nhapHang.addActionListener(this);
        gbc.gridy = 5;
        this.add(nhapHang, gbc);
    }

    public JLabel getGianhapLabel() {
        return gianhapLabel;
    }

    public void setGianhapLabel(JLabel gianhapLabel) {
        this.gianhapLabel = gianhapLabel;
    }

    public JLabel getManhanviennhapLabel() {
        return manhanviennhapLabel;
    }

    public void setManhanviennhapLabel(JLabel manhanviennhapLabel) {
        this.manhanviennhapLabel = manhanviennhapLabel;
    }

    public JLabel getNhacungcapLabel() {
        return nhacungcapLabel;
    }

    public void setNhacungcapLabel(JLabel nhacungcapLabel) {
        this.nhacungcapLabel = nhacungcapLabel;
    }

    public JTextField getGianhapField() {
        return gianhapField;
    }

    public void setGianhapField(JTextField gianhapField) {
        this.gianhapField = gianhapField;
    }

    public JComboBox<AccountDTO> getManhanviennhapComboBox() {
        return manhanviennhapComboBox;
    }

    public void setManhanviennhapComboBox(JComboBox<AccountDTO> manhanviennhapComboBox) {
        this.manhanviennhapComboBox = manhanviennhapComboBox;
    }

    public JComboBox<SupplierDTO> getNhacungcapComboBox() {
        return nhacungcapComboBox;
    }

    public void setNhacungcapComboBox(JComboBox<SupplierDTO> nhacungcapComboBox) {
        this.nhacungcapComboBox = nhacungcapComboBox;
    }

    public JLabel getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(JLabel totalAmount) {
        this.totalAmount = totalAmount;
    }

    public JButton getNhapHang() {
        return nhapHang;
    }

    public void setNhapHang(JButton nhapHang) {
        this.nhapHang = nhapHang;
    }

    public JLabel getLoinhuanLabel() {
        return loinhuanLabel;
    }

    public void setLoinhuanLabel(JLabel loinhuanLabel) {
        this.loinhuanLabel = loinhuanLabel;
    }

    public JTextField getLoinhuanField() {
        return loinhuanField;
    }

    public void setLoinhuanField(JTextField loinhuanField) {
        this.loinhuanField = loinhuanField;
    }

    public void getNhanVienComboBox() {
        manhanviennhapComboBox.removeAllItems();
        List<AccountDTO> data = new AccountBUS().getNhanVien();
        for (AccountDTO accountDTO : data) {
            manhanviennhapComboBox.addItem(accountDTO);
        }
    }

    public void getNhaCungCapComboBox() {
        List<SupplierDTO> data = new SupplierBUS().getNhaCungCap();
        for (SupplierDTO supplierDTO : data) {
            nhacungcapComboBox.addItem(supplierDTO);
        }
    }

    public TableSanPham getTableSanPham() {
        return tableSanPham;
    }

    public void setTableSanPham(TableSanPham tableSanPham) {
        this.tableSanPham = tableSanPham;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nhapHang) {
            float loinhuanValue;
            try {
                loinhuanValue = Float.parseFloat(getLoinhuanField().getText());
            } catch (NumberFormatException numberFormate) {
                loinhuanValue = -1f;
            }
            if (getLoinhuanField().getText().isEmpty() || loinhuanValue < 0 || loinhuanValue > 100) {
                MessageBox.showError("Hãy nhập % lợi nhuận hợp lệ!!!");
            } else {
                if (tableSanPham.getTable().getRowCount() == 0) {
                    MessageBox.showError("Không có sản phẩm để nhập!!!");
                } else {
                    if (MessageBox.showConfirmDialog("Bạn có chắc chắn muốn nhập hàng không?",
                            "Xác nhận nhập hàng") == JOptionPane.YES_OPTION) {
                        // Gọi hàm nhập hàng ở đây
                        int maphieunhap = tableSanPham.createEntryForm();
                        for (int i = 0; i < tableSanPham.table.getRowCount(); i++) {
                            new DetailEntryFormBUS().createDetailEntryForm(maphieunhap,
                                    Integer.parseInt(tableSanPham.model.getValueAt(i, 2).toString()),
                                    Integer.parseInt(tableSanPham.model.getValueAt(i, 4).toString()),
                                    Integer.parseInt(tableSanPham.model.getValueAt(i, 0).toString()));
                        }
                        MessageBox.showOkDialog("Đã nhập hàng.", "Thành công");
                        parent.setContentPane(new PhieuNhap(this.parent));
                        parent.revalidate();
                        parent.repaint();

                    } else {
                        MessageBox.showOkDialog("Đã hủy nhập hàng", "Thất bại");
                    }
                }
            }
        }
    }
}