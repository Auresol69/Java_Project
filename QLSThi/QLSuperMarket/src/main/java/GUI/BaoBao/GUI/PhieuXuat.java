package GUI.BaoBao.GUI;

import javax.swing.JFrame;

import GUI.BaoBao.GUI.PhieuComp.*;

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
