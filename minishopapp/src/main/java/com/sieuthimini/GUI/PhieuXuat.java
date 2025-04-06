package com.sieuthimini.GUI;

import javax.swing.JFrame;

import com.sieuthimini.GUI.PhieuComp.ChucNang;
import com.sieuthimini.GUI.PhieuComp.Xuat;
import com.sieuthimini.GUI.PhieuComp.Table;

public class PhieuXuat extends Phieu {
    JFrame parent;

    public PhieuXuat(JFrame parent) {
        this.parent = parent;
        chucNang = new ChucNang(this.parent);
        thaoTac = new Xuat();
        table = new Table();
        super.addComponent();
    }
}
