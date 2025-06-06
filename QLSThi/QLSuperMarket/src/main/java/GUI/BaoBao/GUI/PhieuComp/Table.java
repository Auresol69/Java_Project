package GUI.BaoBao.GUI.PhieuComp;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import GUI.BaoBao.DTO.*;

public class Table extends JPanel {

    private String[] columnNames = {
            "Mã Phiếu nhập",
            "Nhà cung cấp",
            "Nhân viên nhập",
            "Ngày nhập",
            "Tổng tiền",
            "Trạng thái"
    };
    JTable table;
    JScrollPane scrollPane;
    DefaultTableModel model;

    public Table() {
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        table = new JTable();

        model = new DefaultTableModel(columnNames, 0);

        table.setModel(model);

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        sorter.setComparator(4, (o1, o2) -> {
            Double d1 = (o1 instanceof Number) ? ((Number) o1).doubleValue() : Double.parseDouble(o1.toString());
            Double d2 = (o2 instanceof Number) ? ((Number) o2).doubleValue() : Double.parseDouble(o2.toString());
            return d1.compareTo(d2);
        });

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
                        ef.getTongtien(), ef.getTrangThai() });
            }
        }
    }
}
