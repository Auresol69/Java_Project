package com.sieuthimini.GUI.PhieuComp.newPhieuNhapComp;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sieuthimini.DAO.DataBase;
import com.sieuthimini.DTO.ProductTypeDTO;

public class InputSanPham extends JPanel implements ActionListener {

    JLabel maSanPhamLabel, tenSanPhamLabel, soluongSanPhamLabel, loaiSanPhamLabel;
    JTextField maSanPhamField, tenSanPhamField;
    JComboBox<ProductTypeDTO> loaiSanPhamComboBox;
    JTextField soluongSanPhamField;
    JButton suaButton, xoaButton;
    TableSanPham tableSanPham;

    public JPanel getPanel(JLabel label, JComponent component) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(label);
        panel.add(component);
        return panel;
    }

    public InputSanPham() {

        maSanPhamLabel = new JLabel("Mã sản phẩm");
        maSanPhamField = new JTextField(15);

        tenSanPhamLabel = new JLabel("Tên sản phẩm");
        tenSanPhamField = new JTextField(15);

        loaiSanPhamLabel = new JLabel("Loại sản phẩm");

        loaiSanPhamComboBox = new JComboBox<>();
        loadLoaiSanPham();

        soluongSanPhamLabel = new JLabel("Số lượng");
        soluongSanPhamField = new JTextField(15);

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST; // Căn trái toàn bộ

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        this.add(getPanel(maSanPhamLabel, maSanPhamField), gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        this.add(getPanel(tenSanPhamLabel, tenSanPhamField), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        this.add(getPanel(loaiSanPhamLabel, loaiSanPhamComboBox), gbc);
        gbc.gridwidth = 1; // Reset

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.5;
        this.add(getPanel(soluongSanPhamLabel, soluongSanPhamField), gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.weightx = 1;
        suaButton = new JButton("Sửa");
        suaButton.setEnabled(false);
        suaButton.addActionListener(this);
        this.add(suaButton, gbc);

        gbc.gridx = 1;
        xoaButton = new JButton("Xóa");
        xoaButton.setEnabled(false);
        xoaButton.addActionListener(this);
        this.add(xoaButton, gbc);
    }

    public void loadLoaiSanPham() {
        DataBase db = new DataBase();
        List<Object[]> data = db.selectQuery("SELECT * FROM producttype");
        for (Object[] row : data) {
            loaiSanPhamComboBox.addItem(new ProductTypeDTO(Integer.parseInt(row[0].toString()), row[1].toString()));
        }

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.add(new InputSanPham());
        frame.setVisible(true);
    }

    public JLabel getMaSanPhamLabel() {
        return maSanPhamLabel;
    }

    public void setMaSanPhamLabel(JLabel maSanPhamLabel) {
        this.maSanPhamLabel = maSanPhamLabel;
    }

    public JLabel getTenSanPhamLabel() {
        return tenSanPhamLabel;
    }

    public void setTenSanPhamLabel(JLabel tenSanPhamLabel) {
        this.tenSanPhamLabel = tenSanPhamLabel;
    }

    public JLabel getSoluongSanPhamLabel() {
        return soluongSanPhamLabel;
    }

    public void setSoluongSanPhamLabel(JLabel soluongSanPhamLabel) {
        this.soluongSanPhamLabel = soluongSanPhamLabel;
    }

    public JLabel getLoaiSanPhamLabel() {
        return loaiSanPhamLabel;
    }

    public void setLoaiSanPhamLabel(JLabel loaiSanPhamLabel) {
        this.loaiSanPhamLabel = loaiSanPhamLabel;
    }

    public JTextField getMaSanPhamField() {
        return maSanPhamField;
    }

    public void setMaSanPhamField(JTextField maSanPhamField) {
        this.maSanPhamField = maSanPhamField;
    }

    public JTextField getTenSanPhamField() {
        return tenSanPhamField;
    }

    public void setTenSanPhamField(JTextField tenSanPhamField) {
        this.tenSanPhamField = tenSanPhamField;
    }

    public JComboBox<ProductTypeDTO> getLoaiSanPhamComboBox() {
        return loaiSanPhamComboBox;
    }

    public void setLoaiSanPhamComboBox(JComboBox<ProductTypeDTO> loaiSanPhamComboBox) {
        this.loaiSanPhamComboBox = loaiSanPhamComboBox;
    }

    public JTextField getSoluongSanPhamField() {
        return soluongSanPhamField;
    }

    public void setSoluongSanPhamField(JTextField soluongSanPhamField) {
        this.soluongSanPhamField = soluongSanPhamField;
    }

    public JButton getSuaButton() {
        return suaButton;
    }

    public void setSuaButton(JButton suaButton) {
        this.suaButton = suaButton;
    }

    public JButton getXoaButton() {
        return xoaButton;
    }

    public void setXoaButton(JButton xoaButton) {
        this.xoaButton = xoaButton;
    }

    public TableSanPham getTableSanPham() {
        return tableSanPham;
    }

    public void setTableSanPham(TableSanPham tableSanPham) {
        this.tableSanPham = tableSanPham;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == suaButton) {
            tableSanPham.updateRow();
        } else if (e.getSource() == xoaButton) {
            tableSanPham.deleteRow();
        }
    }
}
