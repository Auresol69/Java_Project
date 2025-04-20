package com.sieuthimini.GUI.PhieuComp.newPhieuNhapComp;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.sieuthimini.BUS.AccountBUS;
import com.sieuthimini.BUS.DetailEntryFormBUS;
import com.sieuthimini.BUS.SupplierBUS;
import com.sieuthimini.DTO.AccountDTO;
import com.sieuthimini.DTO.SupplierDTO;
import com.sieuthimini.ExtendClasses.MessageBox;
import com.sieuthimini.GUI.PhieuNhap;

import javax.swing.BoxLayout;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;

public class TongTien extends JPanel implements ActionListener {
    JLabel gianhapLabel, manhanviennhapLabel, nhacungcapLabel;
    JTextField gianhapField;
    JComboBox<AccountDTO> manhanviennhapComboBox;
    JComboBox<SupplierDTO> nhacungcapComboBox;
    JLabel totalAmount;
    JButton nhapHang;
    TableSanPham tableSanPham;
    JFrame parent;

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

    public TongTien(JFrame parent) {
        this.parent = parent;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        setUpLabel(manhanviennhapLabel = new JLabel("Mã nhân viên nhập:"));
        setUpComponent(manhanviennhapComboBox = new JComboBox<>());
        getNhanVienComboBox();
        setUpLabel(gianhapLabel = new JLabel("Giá nhập:"));
        setUpField(gianhapField = new JTextField());
        setUpLabel(nhacungcapLabel = new JLabel("Nhà cung cấp:"));
        setUpComponent(nhacungcapComboBox = new JComboBox<>());
        getNhaCungCapComboBox();
        this.add(Box.createVerticalGlue());
        setUpLabel(totalAmount = new JLabel("Tổng tiền: 0"));
        totalAmount.setFont(new Font("Segoe UI", Font.BOLD, 15));
        totalAmount.setForeground(Color.RED);

        nhapHang = new JButton("Nhập hàng");
        nhapHang.setFocusable(false);
        nhapHang.addActionListener(this);
        this.add(totalAmount);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(nhapHang);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
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

    // bug
    public void getNhanVienComboBox() {
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