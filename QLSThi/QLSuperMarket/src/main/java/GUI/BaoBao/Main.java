package GUI.BaoBao;

import javax.swing.*;
import java.awt.*;

import GUI.BaoBao.GUI.InHoaDon;
import GUI.BaoBao.GUI.Phieu;
import GUI.BaoBao.GUI.PhieuNhap;
import GUI.*;
public class Main extends JPanel {

    Phieu phieuNhap;
    InHoaDon inHoaDon;
    QLSieuThi q;
    public Main(QLSieuThi q) {
        this.q = q;
        // Thiết lập layout cho Main JPanel
        this.setLayout(new BorderLayout());

        // Khởi tạo các JPanel con
        phieuNhap = new PhieuNhap(q);
        inHoaDon = new InHoaDon(q);
        // Thêm chúng vào các vị trí khác nhau trên layout
        this.add(phieuNhap, BorderLayout.CENTER);
        // this.add(inHoaDon, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("SanPham");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new Main(new QLSieuThi()));
        frame.setSize(800, 600); // hoặc frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
