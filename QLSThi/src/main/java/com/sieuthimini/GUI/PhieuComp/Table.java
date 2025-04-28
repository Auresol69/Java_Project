package com.sieuthimini.GUI.PhieuComp;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.sieuthimini.DTO.EntryFormDTO;

public class Table extends JPanel {

    private String[] columnNames = {
            "Mã Phiếu nhập",
            "Nhà cung cấp",
            "Nhân viên nhập",
            "Ngày nhập",
            "Tổng tiền"
    };
    JTable table;
    JScrollPane scrollPane;
    DefaultTableModel model;

    public Table() {
        this.setLayout(new BorderLayout());
        table = new JTable();

        model = new DefaultTableModel(columnNames, 0);
        table.setModel(model);

        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        scrollPane = new JScrollPane(table);
        this.add(scrollPane);

    }

    public String[] getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(String[] columnNames) {
        this.columnNames = columnNames;
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    public void hienThiEntryForm(List<EntryFormDTO> entryFormDTOs) {
        if (entryFormDTOs == null) {
            entryFormDTOs = new ArrayList<>();
        } else {
            model.setRowCount(0);
            for (EntryFormDTO ef : entryFormDTOs) {
                model.addRow(new Object[] { ef.getMaphieunhap(), ef.getMancc(), ef.getMaaccount(), ef.getNgaynhap(),
                        ef.getTongtien() });
            }
        }
    }
}
