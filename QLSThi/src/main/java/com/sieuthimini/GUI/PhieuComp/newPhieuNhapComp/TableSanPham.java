package com.sieuthimini.GUI.PhieuComp.newPhieuNhapComp;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import com.sieuthimini.BUS.EntryFormBUS;
import com.sieuthimini.DTO.AccountDTO;
import com.sieuthimini.DTO.SupplierDTO;
import com.sieuthimini.ExtendClasses.DeleteInput;

public class TableSanPham extends JPanel implements ListSelectionListener {
    JTable table = new JTable();
    DefaultTableModel model;
    private String[] columnNames = { "masp", "tensp", "soluong", "tenloaisp", "gianhap" };
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
        if (selectedRow != -1 && selectedRow < table.getRowCount()) {
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

    public void setValueAtChoosedRow(Object value, int row, int column) {
        if (row >= 0 && column >= 0 && row < table.getRowCount() && column < table.getColumnCount()) {
            table.setValueAt(value, row, column);
            ((AbstractTableModel) table.getModel()).fireTableCellUpdated(row, column);
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting() && e.getSource() == table.getSelectionModel()) {

            timkiemSanPham.getTable().getSelectionModel().removeListSelectionListener(timkiemSanPham);

            SwingUtilities.invokeLater(() -> {
                timkiemSanPham.getTable().clearSelection();
                timkiemSanPham.getTable().getSelectionModel().addListSelectionListener(timkiemSanPham);
            });

            inputSanPham.suaButton.setEnabled(true);
            inputSanPham.xoaButton.setEnabled(true);
            timkiemSanPham.addSanPham.setEnabled(false);
            timkiemSanPham.nhapExcel.setEnabled(false);

            new DeleteInput(inputSanPham, tongTien).Delete();

            inputSanPham.maSanPhamField.setText(getValueAtChoosedRow(0).toString());
            inputSanPham.tenSanPhamField.setText(getValueAtChoosedRow(1).toString());
            inputSanPham.soluongSanPhamField.setText(getValueAtChoosedRow(2).toString());
            inputSanPham.loaiSanPhamComboBox.setSelectedItem(getValueAtChoosedRow(3));
            tongTien.gianhapField.setText(getValueAtChoosedRow(4).toString());
        }
    }

    public void updateRow() {
        int row = getIndexChoosedRow();
        if (row == -1)
            return;

        table.getSelectionModel().removeListSelectionListener(this);

        setValueAtChoosedRow(inputSanPham.getMaSanPhamField().getText(), row, 0);
        setValueAtChoosedRow(inputSanPham.getTenSanPhamField().getText(), row, 1);
        setValueAtChoosedRow(inputSanPham.getSoluongSanPhamField().getText(), row, 2);
        setValueAtChoosedRow(inputSanPham.getLoaiSanPhamComboBox().getSelectedItem(), row, 3);
        setValueAtChoosedRow(tongTien.getGianhapField().getText(), row, 4);

        ((AbstractTableModel) table.getModel()).fireTableRowsUpdated(row, row);

        table.clearSelection();
        table.getSelectionModel().addListSelectionListener(this);

        inputSanPham.getXoaButton().setEnabled(false);
        inputSanPham.getSuaButton().setEnabled(false);
        timkiemSanPham.getAddSanPham().setEnabled(true);
        timkiemSanPham.getNhapExcel().setEnabled(true);

        updateTongTien();
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

        new DeleteInput(inputSanPham, tongTien).Delete();

        updateTongTien();
    }

    public boolean isSanPhamIdExist(Object id) {
        for (int i = 0; i < table.getRowCount(); i++) {
            Object cellValue = model.getValueAt(i, 0);
            if (cellValue != null && cellValue.toString().equals(id.toString())) {
                table.setRowSelectionInterval(i, i);
                table.scrollRectToVisible(table.getCellRect(i, 0, true));
                return true;
            }
        }
        return false;
    }

    public void updateTongTien() {
        int sum = 0;
        for (int i = 0; i < table.getRowCount(); i++) {
            Object valSoLuong = model.getValueAt(i, 2);
            Object valGiaNhap = model.getValueAt(i, 4);
            if (valSoLuong != null && valGiaNhap != null) {
                try {
                    int soLuong = Integer.parseInt(valSoLuong.toString());
                    int giaNhap = Integer.parseInt(valGiaNhap.toString());
                    sum += soLuong * giaNhap;
                } catch (NumberFormatException e) {
                    System.err.println("Dữ liệu sai định dạng ở dòng " + i);
                }
            } else {
                System.err.println("Thiếu dữ liệu ở dòng " + i);
            }
        }
        tongTien.getTotalAmount().setText(("Tổng tiền: " + sum));
    }

    public int createEntryForm() {
        return new EntryFormBUS().createEntryForm((SupplierDTO) tongTien.getNhacungcapComboBox().getSelectedItem(),
                (AccountDTO) tongTien.getManhanviennhapComboBox().getSelectedItem(),
                Integer.parseInt(tongTien.getLoinhuanField().getText()));
    }
}