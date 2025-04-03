package com.sieuthimini.GUI.PhieuNhapComp;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

public class NhapLieu extends JPanel {

    JLabel suplierLabel, staffLabel, fromDateLabel, toDateLabel, fromMoneyLabel, toMoneyLabel;
    JTextField fromMoneyField, toMoneyField;
    JDateChooser fromDateChooser, toDateChooser;
    JComboBox suplierComboBox, staffComboBox;

    private void setUpLabel(JLabel label) {
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        label.setPreferredSize(new Dimension(120, 50));
        // label.setFont(new Font(null));
        this.add(label);
    }

    private void setUpField(JTextField field) {
        field.setColumns(20);
        field.setFocusable(false);
        this.add(field);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
    }

    private void setUpComponent(JComponent comp) {
        comp.setFocusable(false);
        this.add(comp);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
    }

    public NhapLieu() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.white);
        this.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        setUpLabel(suplierLabel = new JLabel("Nhà cung cấp:"));
        setUpComponent(suplierComboBox = new JComboBox<>());
        setUpLabel(staffLabel = new JLabel("Nhân viên nhập:"));
        setUpComponent(staffComboBox = new JComboBox<>());
        setUpLabel(fromDateLabel = new JLabel("Từ ngày:"));
        setUpComponent(fromDateChooser = new JDateChooser());
        setUpLabel(toDateLabel = new JLabel("Đến ngày:"));
        setUpComponent(toDateChooser = new JDateChooser());
        setUpLabel(fromMoneyLabel = new JLabel("Từ giá trị:"));
        setUpField(toMoneyField = new JTextField());
        setUpLabel(toMoneyLabel = new JLabel("Đến giá trị:"));
        setUpField(toMoneyField = new JTextField());
    }
}
