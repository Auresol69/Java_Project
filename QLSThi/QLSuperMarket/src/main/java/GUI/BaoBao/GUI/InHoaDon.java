package GUI.BaoBao.GUI;

import javax.swing.*;

import GUI.BaoBao.GUI.InHoaDonComp.ChucNang;
import GUI.BaoBao.GUI.InHoaDonComp.SanPham;
import GUI.BaoBao.GUI.InHoaDonComp.Table;

import java.awt.*;

public class InHoaDon extends JPanel {

    JFrame parent;
    Table table = new Table();
    SanPham sanPham = new SanPham(table);
    ChucNang chucNang;

    public void addComponent() {
        chucNang = new ChucNang(table, parent);

        GridBagConstraints gbc = new GridBagConstraints();

        sanPham.setBackground(new Color(240, 247, 250));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 3;
        gbc.weightx = 2;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(sanPham, gbc);

        chucNang.setBackground(new Color(240, 247, 250));
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(chucNang, gbc);

        table.setBackground(new Color(240, 247, 250));
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        gbc.weightx = 1;
        gbc.weighty = 2;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(table, gbc);

    }

    public InHoaDon(JFrame parent) {
        this.parent = parent;
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setLayout(new GridBagLayout());
        this.setBackground(new Color(240, 247, 250));
        addComponent();

    }
}
