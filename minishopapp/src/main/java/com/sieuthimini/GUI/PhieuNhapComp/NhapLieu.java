package com.sieuthimini.GUI.PhieuNhapComp;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

public class NhapLieu extends JPanel {

    JLabel suplierLabel, staffLabel, fromDateLabel, toDateLabel, fromMoneyLabel, toMoneyLabel;
    JTextField fromField, toField, fromMoneyField, toMoneyField;
    JPanel fromDatePanel, toDatePanel;
    JDateChooser fromDateChooser, toDateChooser;
    JComboBox suplierComboBox, staffComboBox;

    private void setUpLabel(JLabel label) {
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        label.setPreferredSize(new Dimension(120, 50));
        // label.setFont(new Font(null));
    }

    private void setMaxWidth() {

    }

    private void setUpField() {
    }

    public NhapLieu() {
        this.setLayout(new FlowLayout());
        setUpLabel(suplierLabel = new JLabel("Nhà cung cấp:"));
        this.add(suplierLabel);

    }
}
