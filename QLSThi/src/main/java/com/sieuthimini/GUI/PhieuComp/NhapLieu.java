package com.sieuthimini.GUI.PhieuComp;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.sieuthimini.BUS.EntryFormBUS;
import com.sieuthimini.DTO.EntryFormDTO;
import com.toedter.calendar.JDateChooser;

public abstract class NhapLieu extends JPanel {

    JLabel supplierLabel, staffLabel, fromDateLabel, toDateLabel, fromMoneyLabel, toMoneyLabel;
    JTextField fromMoneyField, toMoneyField;
    JDateChooser fromDateChooser, toDateChooser;
    JComboBox<String> supplierComboBox, staffComboBox;
    private Timer searchTimer;

    private Table table;

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

    public NhapLieu(String text) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.white);
        this.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        setUpLabel(supplierLabel = new JLabel(text));
        setUpComponent(supplierComboBox = new JComboBox<>());
        setUpLabel(staffLabel = new JLabel("Nhân viên nhập:"));
        setUpComponent(staffComboBox = new JComboBox<>());
        setUpLabel(fromDateLabel = new JLabel("Từ ngày:"));
        setUpComponent(fromDateChooser = new JDateChooser());
        setUpLabel(toDateLabel = new JLabel("Đến ngày:"));
        setUpComponent(toDateChooser = new JDateChooser());
        setUpLabel(fromMoneyLabel = new JLabel("Từ giá trị:"));
        setUpField(fromMoneyField = new JTextField());
        setUpLabel(toMoneyLabel = new JLabel("Đến giá trị:"));
        setUpField(toMoneyField = new JTextField());

        searchTimer = new Timer(300, e -> search());
        searchTimer.setRepeats(false);

        fromDateChooser.addPropertyChangeListener("date", evt -> {
            if (searchTimer.isRunning())
                searchTimer.restart();
            else
                searchTimer.start();
        });

        toDateChooser.addPropertyChangeListener("date", evt -> {
            if (searchTimer.isRunning())
                searchTimer.restart();
            else
                searchTimer.start();
        });

        supplierComboBox.addActionListener(e -> {
            if (searchTimer.isRunning())
                searchTimer.restart();
            else
                searchTimer.start();
        });

        staffComboBox.addActionListener(e -> {
            if (searchTimer.isRunning())
                searchTimer.restart();
            else
                searchTimer.start();
        });

        toMoneyField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                restartSearchTimer();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                restartSearchTimer();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                restartSearchTimer();
            }

            private void restartSearchTimer() {
                if (searchTimer.isRunning())
                    searchTimer.restart();
                else
                    searchTimer.start();
            }
        });

        fromMoneyField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                restartSearchTimer();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                restartSearchTimer();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                restartSearchTimer();
            }

            private void restartSearchTimer() {
                if (searchTimer.isRunning())
                    searchTimer.restart();
                else
                    searchTimer.start();
            }
        });

    }

    public void triggerSearch() {
        search();
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    private void search() {
        List<EntryFormDTO> results = new EntryFormBUS().searchEntryForm(null, null, fromDateChooser, toDateChooser);
        table.hienThiEntryForm(results);
    }
}
