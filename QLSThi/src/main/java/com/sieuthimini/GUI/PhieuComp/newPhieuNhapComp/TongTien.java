package com.sieuthimini.GUI.PhieuComp.newPhieuNhapComp;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.sieuthimini.BUS.SupplierBUS;
import com.sieuthimini.DTO.SupplierDTO;

import javax.swing.BoxLayout;
import javax.swing.JButton;

import java.awt.Dimension;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;

public class TongTien extends JPanel {
    JLabel maphieunhapLabel, manhanviennhapLabel, nhacungcapLabel;
    JTextField mapphieunhapField, manhanviennhapField;
    JComboBox<SupplierDTO> nhacungcapComboBox;
    JLabel totalAmount;
    JButton nhapHang;

    private void setUpLabel(JLabel label) {
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        label.setPreferredSize(new Dimension(120, 50));
        // label.setFont(new Font(null));
        this.add(label);
    }

    private void setUpField(JTextField field) {
        field.setColumns(20);
        field.setFocusable(true);
        field.setMaximumSize(new Dimension(400, 25));
        this.add(field);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
    }

    private void setUpComponent(JComponent comp) {
        comp.setFocusable(false);
        comp.setMaximumSize(new Dimension(400, 25));

        this.add(comp);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
    }

    public TongTien() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        setUpLabel(manhanviennhapLabel = new JLabel("Mã nhân viên nhập:"));
        setUpField(manhanviennhapField = new JTextField());
        setUpLabel(maphieunhapLabel = new JLabel("Mã phiếu nhập:"));
        setUpField(mapphieunhapField = new JTextField());
        setUpLabel(nhacungcapLabel = new JLabel("Nhà cung cấp:"));
        setUpComponent(nhacungcapComboBox = new JComboBox<>());
        getNhaCungCapComboBox();
        this.add(Box.createVerticalGlue());
        setUpLabel(totalAmount = new JLabel("Tổng tiền:"));

        nhapHang = new JButton("Nhập hàng");
        nhapHang.setFocusable(false);
        this.add(totalAmount);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(nhapHang);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
    }

    public void getNhaCungCapComboBox() {
        List<SupplierDTO> data = new SupplierBUS().getNhaCungCap();
        for (SupplierDTO supplierDTO : data) {
            nhacungcapComboBox.addItem(supplierDTO);
        }
    }
}