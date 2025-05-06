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
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setLayout(new BorderLayout(10, 10));
        this.setBackground(Color.gray);
    }
}
