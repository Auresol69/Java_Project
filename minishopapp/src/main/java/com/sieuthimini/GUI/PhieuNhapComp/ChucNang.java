package com.sieuthimini.GUI.PhieuNhapComp;

import com.sieuthimini.ExtendClasses.GetImagePNG;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ChucNang extends JPanel implements ActionListener {
    JButton themButton, chiTietButton, huyButton, xuatExcelButton;
    JComboBox columnSort;
    JTextField timKiem;
    JButton refresh;

    public ChucNang() {
        this.setLayout(new FlowLayout());

        chiTietButton = new JButton("Chi tiết");
        huyButton = new JButton("Hủy");
        xuatExcelButton = new JButton("Xuất Excel");

        themButton = new JButton("Thêm", new ImageIcon(new GetImagePNG().getImage("square-plus-solid.png")));
        themButton.setPreferredSize(new Dimension(120, 120));
        themButton.setFocusable(false);
        themButton.setHorizontalTextPosition(JButton.CENTER);
        themButton.setVerticalTextPosition(JButton.BOTTOM);
        themButton.addActionListener(this);
        this.add(themButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == themButton) {
            System.out.println("Them moi");
        }
    }
}
