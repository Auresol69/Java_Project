package com.sieuthimini.GUI.PhieuComp;

import com.sieuthimini.ExtendClasses.GetImagePNG;

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

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class ChucNang extends JPanel implements ActionListener {
    JButton themButton, chiTietButton, huyButton, xuatExcelButton;
    JComboBox<String> columnSort;
    JTextField timKiem;
    JButton refreshButton;
    JFrame parent;

    private void setUpButton(JButton button, String text, String imgName) {
        button.setText(text);
        ImageIcon icon = new ImageIcon(new GetImagePNG().getImage(imgName));
        if (icon != null) {
            button.setIcon(icon);
        }
        button.setPreferredSize(new Dimension(120, 120));
        button.setFocusable(false);
        if (!button.equals(refreshButton)) {
            button.setHorizontalTextPosition(JButton.CENTER);
            button.setVerticalTextPosition(JButton.BOTTOM);
        } else {
            button.setHorizontalTextPosition(JButton.RIGHT);
        }
        button.addActionListener(this);
        this.add(button);
    }

    public ChucNang(JFrame parent) {
        this.parent = parent;
        this.setLayout(new FlowLayout());
        this.setBackground(Color.white);
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        setUpButton(themButton = new JButton(), "Thêm", "square-plus-solid.png");
        setUpButton(chiTietButton = new JButton(), "Thông tin", "circle-info-solid.png");
        setUpButton(huyButton = new JButton(), "Hủy", "circle-xmark-solid.png");
        setUpButton(xuatExcelButton = new JButton(), "Xuất Excel", "file-excel-solid.png");

        String options[] = { "tất cả", "mã phiếu", "nhân viên", "nhà cung cấp" };
        columnSort = new JComboBox<>(options);
        columnSort.addActionListener(this);
        this.add(columnSort);

        timKiem = new JTextField("Nhập nội dung...");
        timKiem.setForeground(Color.GRAY);

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

        setUpButton(refreshButton = new JButton(), "Làm mới", "arrows-rotate-solid.png");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == themButton) {
            parent.setContentPane(new newPhieuNhap());
            parent.revalidate();
            parent.repaint();
        }
    }
}
