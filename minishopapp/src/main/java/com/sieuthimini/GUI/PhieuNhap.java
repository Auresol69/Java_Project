package com.sieuthimini.GUI;

import javax.swing.*;
import java.awt.*;
import com.sieuthimini.GUI.PhieuNhapComp.*;

public class PhieuNhap extends JPanel {

    ChucNang chucNang = new ChucNang();
    NhapLieu nhapLieu = new NhapLieu();
    Table table = new Table();

    public PhieuNhap() {
        this.setLayout(new BorderLayout());
        this.add(chucNang, BorderLayout.NORTH);
        this.add(nhapLieu, BorderLayout.WEST);
    }
}
