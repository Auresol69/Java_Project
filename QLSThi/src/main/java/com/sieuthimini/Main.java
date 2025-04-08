package com.sieuthimini;

import javax.swing.JFrame;
import com.sieuthimini.GUI.Phieu;
import com.sieuthimini.GUI.PhieuNhap;
import com.sieuthimini.GUI.PhieuXuat;

public class Main extends JFrame {

    Phieu phieuNhap = new PhieuNhap(this);
    Phieu phieuXuat = new PhieuXuat(this);

    public Main() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("SanPham");
        this.setLocationRelativeTo(null);

        this.add(phieuNhap);

        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) throws Exception {
        new Main();
    }
}
