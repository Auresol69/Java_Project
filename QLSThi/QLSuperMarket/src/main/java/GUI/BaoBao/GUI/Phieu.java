package GUI.BaoBao.GUI;

import javax.swing.*;

import GUI.BaoBao.GUI.PhieuComp.*;

import java.awt.*;

public abstract class Phieu extends JPanel {

    ChucNang chucNang;
    NhapLieu thaoTac;
    Table table;

    public void addComponent() {

        chucNang.setTable(table);

        thaoTac.setTable(table);

        chucNang.setNhapLieu(thaoTac);

        thaoTac.setChucNang(chucNang);

        this.add(chucNang, BorderLayout.NORTH);
        this.add(thaoTac, BorderLayout.WEST);
        this.add(table);
    }

    public Phieu() {
        this.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        this.setLayout(new BorderLayout(15, 15));
        this.setBackground(new Color(240, 247, 250));
    }
}
