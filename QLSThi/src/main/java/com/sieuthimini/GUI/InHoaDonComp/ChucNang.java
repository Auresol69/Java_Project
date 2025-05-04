package com.sieuthimini.GUI.InHoaDonComp;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.sieuthimini.ExtendClasses.GetImagePNG;

public class ChucNang extends JPanel implements ActionListener {

    private JButton inHoaDonButton, tienMatButton, chuỵenKhoanButton, hienThiSanPhamButton, quetMaButton;

    private void setUpButton(JButton button, String text, String imgName) {
        button.setText(text);
        ImageIcon icon = new ImageIcon(new GetImagePNG().getImage(imgName, 60));
        if (icon != null) {
            button.setIcon(icon);
        }
        button.setPreferredSize(new Dimension(120, 120));
        button.setFocusable(false);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.BOTTOM);
        button.addActionListener(this);

        this.add(button);
    }

    public ChucNang() {
        this.setLayout(new BorderLayout());

        JPanel chucNangPanel = new JPanel(new GridLayout(0, 3, 5, 5));

        setUpButton(inHoaDonButton = new JButton(), "In hóa đơn", "cart-plus-solid.png");
        chucNangPanel.add(inHoaDonButton);
        this.add(chucNangPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
