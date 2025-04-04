package com.sieuthimini.GUI;

import com.sieuthimini.GUI.PhieuComp.ChucNang;
import com.sieuthimini.GUI.PhieuComp.Xuat;
import com.sieuthimini.GUI.PhieuComp.Table;

public class PhieuXuat extends Phieu {
    public PhieuXuat() {
        chucNang = new ChucNang();
        thaoTac = new Xuat();
        table = new Table();
        super.addComponent();
    }
}
