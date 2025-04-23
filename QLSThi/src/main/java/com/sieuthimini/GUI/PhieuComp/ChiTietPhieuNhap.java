package com.sieuthimini.GUI.PhieuComp;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;

import com.sieuthimini.GUI.PhieuComp.ChiTietPhieuNhapComp.Button;
import com.sieuthimini.GUI.PhieuComp.ChiTietPhieuNhapComp.Info;
import com.sieuthimini.GUI.PhieuComp.ChiTietPhieuNhapComp.Title;

public class ChiTietPhieuNhap extends JDialog {

    private Title title;
    private Info info;
    private Table table;
    private Button button;

    public ChiTietPhieuNhap(Frame parent) {
        super(parent, "Thông tin phiếu nhập", true);

        this.setLayout(new BorderLayout());

        title = new Title();
        info = new Info();
        table = new Table();
        button = new Button();

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.add(info);
        centerPanel.add(table);

        this.add(title, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(button, BorderLayout.SOUTH);

        this.pack();
        this.setLocationRelativeTo(parent);
    }
}
