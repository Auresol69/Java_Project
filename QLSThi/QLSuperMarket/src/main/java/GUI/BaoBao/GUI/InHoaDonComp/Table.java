package GUI.BaoBao.GUI.InHoaDonComp;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import GUI.BaoBao.DTO.ProductDTO;

public class Table extends JPanel {
    JLabel tongLabel;
    JButton huyDonButton;
    JTable table;

    private String[] columnNames = {
            "ID",
            "Tên",
            "Số lượng",
            "Giá",
            "Tổng cộng"
    };
    JScrollPane scrollPane;
    DefaultTableModel model;

    public Table() {
        this.setLayout(new BorderLayout());
        this.add(new JLabel("Hóa đơn"), BorderLayout.NORTH);

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

        JPanel bottom = new JPanel(new FlowLayout());
        bottom.add(tongLabel = new JLabel("Tổng cộng: 0"));
        bottom.add(huyDonButton = new JButton("Hủy đơn"));
        this.add(bottom, BorderLayout.SOUTH);
    }

    public void addSanPham(ProductDTO productDTO, Integer soLuong) {
        Integer donGia = productDTO.getDongiasanpham();
        Integer tongCong = donGia * soLuong;

        // Check if product ID already exists in the table
        for (int i = 0; i < model.getRowCount(); i++) {
            Object idObj = model.getValueAt(i, 0);
            if (idObj != null && idObj.equals(productDTO.getMasp())) {

                Integer existingSoLuong = (Integer) model.getValueAt(i, 2);
                Integer newSoLuong = existingSoLuong + soLuong;
                model.setValueAt(newSoLuong, i, 2);

                Integer newTongCong = donGia * newSoLuong;
                model.setValueAt(newTongCong, i, 4);
                return;
            }
        }

        // If product ID not found, add new row
        model.addRow(new Object[] { productDTO.getMasp(), productDTO.getTensp(), soLuong, productDTO.getDongiasanpham(),
                tongCong });
    }

}
