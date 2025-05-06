package GUI.BaoBao.GUI;

import javax.swing.JFrame;

import GUI.BaoBao.GUI.PhieuComp.*;

public class PhieuNhap extends Phieu {
    JFrame parent;

    public PhieuNhap(JFrame parent) {
        this.parent = parent;
        chucNang = new ChucNang(this.parent);
        thaoTac = new Nhap();
        table = new Table();
        super.addComponent();

        ((Nhap) thaoTac).triggerSearch();

    }
}
