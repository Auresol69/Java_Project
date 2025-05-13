package GUI.BaoBao.GUI.PhieuComp;

import javax.swing.*;
import GUI.BaoBao.GUI.PhieuComp.newPhieuNhapComp.*;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class newPhieuNhap extends JPanel {
    JFrame parent;

    public newPhieuNhap(JFrame parent) {
        this.parent = parent;

        this.setLayout(new GridBagLayout());

        InputSanPham input = new InputSanPham();
        TongTien tongTien = new TongTien(this.parent);
        TableSanPham table = new TableSanPham(input, tongTien);

        TimkiemSanPham timKiem = new TimkiemSanPham(input, table, tongTien);

        table.setTimkiemSanPham(timKiem);

        input.setTableSanPham(table);

        tongTien.setTableSanPham(table);

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
}
