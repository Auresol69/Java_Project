package com.sieuthimini.GUI.PhieuComp.newPhieuNhapComp;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.sieuthimini.ExtendClasses.DeleteInput;

public class TableSanPham extends JPanel implements ListSelectionListener {
    JTable table = new JTable();
    DefaultTableModel model;
    private String[] columnNames = { "masp", "tensp", "soluong", "dongiasanpham", "tenloaisp", "ncc", "gianhap" };
    InputSanPham inputSanPham;
    TimkiemSanPham timkiemSanPham;
    TongTien tongTien;

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public void setModel(DefaultTableModel model) {
        this.model = model;
    }

    public String[] getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(String[] columnNames) {
        this.columnNames = columnNames;
    }

    public InputSanPham getInputSanPham() {
        return inputSanPham;
    }

    public void setInputSanPham(InputSanPham inputSanPham) {
        this.inputSanPham = inputSanPham;
    }

    public TimkiemSanPham getTimkiemSanPham() {
        return timkiemSanPham;
    }

    public TongTien getTongTien() {
        return tongTien;
    }

    public void setTongTien(TongTien tongTien) {
        this.tongTien = tongTien;
    }

    public void setTimkiemSanPham(TimkiemSanPham timkiemSanPham) {
        this.timkiemSanPham = timkiemSanPham;
    }

    public TableSanPham(InputSanPham inputSanPham, TongTien tongTien) {
        this.inputSanPham = inputSanPham;
        this.tongTien = tongTien;
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);

        table.setAutoCreateRowSorter(true);

        JScrollPane scrollPane = new JScrollPane(table);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.getSelectionModel().addListSelectionListener(this);

        this.add(scrollPane);
    }

    public int getIndexChoosedRow() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int modelRow = table.convertRowIndexToModel(selectedRow);
            return modelRow;
        }
        return -1;
    }

    public Object getValueAtChoosedRow(int columnIndex) {
        int row = getIndexChoosedRow();
        if (row != -1)
            return table.getValueAt(row, columnIndex);
        return null;
    }

    public void setValueAtChoosedRow(Object object, int row, int column) {
        if (row != -1) {
            table.setValueAt(object, row, column);
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting() && e.getSource() == table.getSelectionModel()) {
            inputSanPham.suaButton.setEnabled(true);
            inputSanPham.xoaButton.setEnabled(true);
            timkiemSanPham.addSanPham.setEnabled(false);
            timkiemSanPham.nhapExcel.setEnabled(false);

            new DeleteInput(inputSanPham, tongTien).Delete();

            inputSanPham.maSanPhamField.setText(getValueAtChoosedRow(0).toString());
            inputSanPham.tenSanPhamField.setText(getValueAtChoosedRow(1).toString());
            inputSanPham.soluongSanPhamField.setText(getValueAtChoosedRow(2).toString());
            inputSanPham.giaSanPhamField.setText(getValueAtChoosedRow(3).toString());
            inputSanPham.loaiSanPhamComboBox.setSelectedItem(getValueAtChoosedRow(4));

            tongTien.nhacungcapComboBox.setSelectedItem(getValueAtChoosedRow(5));
            tongTien.gianhapField.setText(getValueAtChoosedRow(6).toString());
        }
    }

    public void updateRow() {
        table.getSelectionModel().removeListSelectionListener(this);

        int row = getIndexChoosedRow();
        setValueAtChoosedRow(inputSanPham.getMaSanPhamField().getText(), row, 0);
        setValueAtChoosedRow(inputSanPham.getTenSanPhamField().getText(), row, 1);
        setValueAtChoosedRow(inputSanPham.getSoluongSanPhamField().getText(), row, 2);
        setValueAtChoosedRow(inputSanPham.getGiaSanPhamField().getText(), row, 3);
        setValueAtChoosedRow(inputSanPham.getLoaiSanPhamComboBox().getSelectedItem(), row, 4);
        setValueAtChoosedRow(tongTien.getNhacungcapComboBox().getSelectedItem(), row, 5);
        setValueAtChoosedRow(tongTien.getGianhapField().getText(), row, 6);
        table.clearSelection();
        table.getSelectionModel().addListSelectionListener(this);

        inputSanPham.getXoaButton().setEnabled(false);
        inputSanPham.getSuaButton().setEnabled(false);
        timkiemSanPham.getAddSanPham().setEnabled(true);
        timkiemSanPham.getNhapExcel().setEnabled(true);
    }

    public void deleteRow() {

        table.getSelectionModel().removeListSelectionListener(this);

        model.removeRow(getIndexChoosedRow());

        table.clearSelection();
        table.getSelectionModel().addListSelectionListener(this);

        inputSanPham.getXoaButton().setEnabled(false);
        inputSanPham.getSuaButton().setEnabled(false);
        timkiemSanPham.getAddSanPham().setEnabled(true);
        timkiemSanPham.getNhapExcel().setEnabled(true);
    }
}