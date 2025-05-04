package com.sieuthimini.GUI.InHoaDonComp;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class SanPham extends JPanel {
    public SanPham() {
        this.setLayout(new BorderLayout());

        JPanel productPanel = new JPanel(new GridLayout(0, 2, 10, 10));

        // Thêm các sản phẩm (ví dụ là nút)
        for (int i = 1; i <= 11; i++) {
            JButton sp = new JButton("Sản phẩm " + i);
            productPanel.add(sp);
        }

        JScrollPane scrollPane = new JScrollPane(productPanel);

        this.add(scrollPane);
    }
}
