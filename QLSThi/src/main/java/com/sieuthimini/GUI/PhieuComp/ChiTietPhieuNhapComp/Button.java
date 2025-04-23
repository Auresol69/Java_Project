package com.sieuthimini.GUI.PhieuComp.ChiTietPhieuNhapComp;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Button extends JPanel {
    private JButton xuatfilePDFButton, huyboButton;

    public Button() {
        this.setLayout(new FlowLayout());

        xuatfilePDFButton = new JButton("Xuất file PDF");
        huyboButton = new JButton("Hủy bỏ");

        this.add(xuatfilePDFButton);
        this.add(huyboButton);
    }
}
