package GUI.BaoBao.GUI.PhieuComp.ChiTietPhieuNhapComp;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Title extends JPanel {
    private JLabel thongtinphieunhapLabel;

    public Title() {
        thongtinphieunhapLabel = new JLabel("Thông tin phiếu nhập");
        this.setBackground(new Color(128, 128, 128));
        thongtinphieunhapLabel.setForeground(new Color(255, 255, 255));
        this.add(thongtinphieunhapLabel);
        this.setSize(new Dimension(400, 200));
    }
}
