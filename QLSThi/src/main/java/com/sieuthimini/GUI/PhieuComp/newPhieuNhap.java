package com.sieuthimini.GUI.PhieuComp;

import javax.swing.*;
import com.sieuthimini.GUI.PhieuComp.newPhieuNhapComp.*;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class newPhieuNhap extends JPanel {
    public newPhieuNhap() {
        this.setLayout(new GridBagLayout());

        InputSanPham input = new InputSanPham();
        TableSanPham table = new TableSanPham();
        TongTien tongTien = new TongTien();

        TimkiemSanPham timKiem = new TimkiemSanPham(input, table, tongTien);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 0.4;

        this.add(timKiem, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.35;
        this.add(input, gbc);

        gbc.gridx = 2;
        gbc.weightx = 0.25;
        gbc.gridheight = 2;
        gbc.weighty = 1;
        this.add(tongTien, gbc);

        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.weightx = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        this.add(table, gbc);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Phiếu nhập");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(new newPhieuNhap());
        frame.setVisible(true);
    }
}
