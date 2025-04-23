package com.sieuthimini.GUI.PhieuComp.ChiTietPhieuNhapComp;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Info extends JPanel {
    private JLabel maphieuLabel, staffLabel, supplierLabel, timeLabel;
    private JTextField maphieuField, staffField, supplierField, timeField;

    public Info() {
        this.setLayout(new GridLayout(2, 4, 10, 10));

        maphieuLabel = new JLabel("Mã phiếu");
        staffLabel = new JLabel("Nhân viên");
        supplierLabel = new JLabel("Nhà cung cấp");
        timeLabel = new JLabel("Thời gian");

        maphieuField = new JTextField();
        staffField = new JTextField();
        supplierField = new JTextField();
        timeField = new JTextField();

        this.add(maphieuLabel);
        this.add(staffLabel);
        this.add(supplierLabel);
        this.add(timeLabel);
        this.add(maphieuField);
        this.add(staffField);
        this.add(supplierField);
        this.add(timeField);
    }
}
