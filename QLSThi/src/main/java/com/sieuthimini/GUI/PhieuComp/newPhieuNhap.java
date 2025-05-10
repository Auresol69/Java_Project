package com.sieuthimini.GUI.PhieuComp;

import javax.swing.*;
import com.sieuthimini.GUI.PhieuComp.newPhieuNhapComp.*;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;

public class newPhieuNhap extends JPanel {
    JFrame parent;

    public newPhieuNhap(JFrame parent) {
        this.parent = parent;

        this.setLayout(new GridBagLayout());

        InputSanPham input = new InputSanPham();
        input.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        TongTien tongTien = new TongTien(this.parent);
        tongTien.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        TableSanPham table = new TableSanPham(input, tongTien);
        table.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        TimkiemSanPham timKiem = new TimkiemSanPham(input, table, tongTien);
        timKiem.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        timKiem.setPreferredSize(new java.awt.Dimension(400, 500));
        timKiem.setMinimumSize(new java.awt.Dimension(400, 300));

        table.setTimkiemSanPham(timKiem);

        input.setTableSanPham(table);

        tongTien.setTableSanPham(table);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(15, 15, 15, 15);

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
        gbc.weighty = 0.5;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        this.add(table, gbc);
    }
}
