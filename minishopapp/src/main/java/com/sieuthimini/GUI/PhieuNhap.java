package com.sieuthimini.GUI;

import com.sieuthimini.GUI.PhieuComp.ChucNang;
import com.sieuthimini.GUI.PhieuComp.Nhap;
import com.sieuthimini.GUI.PhieuComp.Table;

public class PhieuNhap extends Phieu {
    public PhieuNhap() {
        chucNang = new ChucNang();
        thaoTac = new Nhap();
        table = new Table();
        super.addComponent();
    }
}
