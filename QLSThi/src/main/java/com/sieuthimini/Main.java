package com.sieuthimini;

import javax.swing.JFrame;

import com.sieuthimini.GUI.InHoaDon;
import com.sieuthimini.GUI.Phieu;
import com.sieuthimini.GUI.PhieuNhap;

public class Main extends JFrame {

    Phieu phieuNhap = new PhieuNhap(this);
    InHoaDon inHoaDon = new InHoaDon(this);

    public Main() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("SanPham");
        this.setLocationRelativeTo(null);

        this.add(inHoaDon);

        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) throws Exception {
        new Main();
    }
}
