package com.sieuthimini.GUI;

import javax.swing.JFrame;

import com.sieuthimini.GUI.PhieuComp.ChucNang;
import com.sieuthimini.GUI.PhieuComp.Nhap;
import com.sieuthimini.GUI.PhieuComp.Table;

public class PhieuNhap extends Phieu {
    JFrame parent;

    public PhieuNhap(JFrame parent) {
        this.parent = parent;
        chucNang = new ChucNang(this.parent);
        thaoTac = new Nhap();
        table = new Table();
        super.addComponent();
    }
}
