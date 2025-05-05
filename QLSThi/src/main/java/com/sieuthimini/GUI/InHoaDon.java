package com.sieuthimini.GUI;

import javax.swing.*;

import com.sieuthimini.GUI.InHoaDonComp.ChucNang;
import com.sieuthimini.GUI.InHoaDonComp.SanPham;
import com.sieuthimini.GUI.InHoaDonComp.Table;

import java.awt.*;

public class InHoaDon extends JPanel {
    ChucNang chucNang = new ChucNang();
    Table table = new Table();
    SanPham sanPham = new SanPham(table);

    public void addComponent() {
        GridBagConstraints gbc = new GridBagConstraints();

        sanPham.setBackground(Color.RED);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 3;
        gbc.weightx = 2;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(sanPham, gbc);

        chucNang.setBackground(Color.GREEN);
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(chucNang, gbc);

        table.setBackground(Color.BLUE);
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        gbc.weightx = 1;
        gbc.weighty = 2;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(table, gbc);

    }

    public InHoaDon() {
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.gray);
        addComponent();

    }
}
