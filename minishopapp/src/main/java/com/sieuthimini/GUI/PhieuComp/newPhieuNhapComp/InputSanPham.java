package com.sieuthimini.GUI.PhieuComp.newPhieuNhapComp;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InputSanPham extends JPanel {

    JLabel maSanPhamLabel, tenSanPhamLabel, soluongSanPhamLabel, giaSanPhamLabel, loaiSanPhamLabel;
    JTextField maSanPhamField, tenSanPhamField;
    JComboBox loaiSanPhamComboBox;
    JTextField soluongSanPhamField, giaSanPhamField;

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
        String[] loaiSanPhamItems = { "Sản phẩm 1", "Sản phẩm 2", "Sản phẩm 3" };
        loaiSanPhamComboBox = new JComboBox<>(loaiSanPhamItems);

        soluongSanPhamLabel = new JLabel("Số lượng");
        soluongSanPhamField = new JTextField(15);

        giaSanPhamLabel = new JLabel("Giá sản phẩm");
        giaSanPhamField = new JTextField(15);

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

        gbc.gridx = 1;
        this.add(getPanel(giaSanPhamLabel, giaSanPhamField), gbc);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.add(new InputSanPham());
        frame.setVisible(true);
    }
}
