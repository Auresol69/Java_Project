package com.sieuthimini.GUI;

import javax.swing.*;
import java.awt.*;
import com.sieuthimini.GUI.PhieuNhapComp.*;

public class PhieuNhap extends JPanel {

    ChucNang chucNang = new ChucNang();
    NhapLieu nhapLieu = new NhapLieu();
    Table table = new Table();

    public PhieuNhap() {
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setLayout(new BorderLayout(10, 10));
        this.setBackground(new Color(213, 236, 246));
        this.add(chucNang, BorderLayout.NORTH);
        this.add(nhapLieu, BorderLayout.WEST);
        this.add(table);
    }
}
