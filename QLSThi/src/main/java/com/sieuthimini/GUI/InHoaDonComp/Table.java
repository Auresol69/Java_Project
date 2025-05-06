package com.sieuthimini.GUI.InHoaDonComp;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.sieuthimini.DAO.ProductDAO;
import com.sieuthimini.DTO.CustomerDTO;
import com.sieuthimini.DTO.ProductDTO;
import com.sieuthimini.ExtendClasses.MessageBox;
import com.sieuthimini.DAO.DataBase;

public class Table extends JPanel {
    JLabel tongLabel;
    JButton huyDonButton;
    JTable table;
    public JComboBox<CustomerDTO> comboBox;

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
        JPanel panelTop = new JPanel(new FlowLayout());
        panelTop.add(new JLabel("Hóa đơn"));
        panelTop.add(comboBox = new JComboBox<>());
        this.add(panelTop, BorderLayout.NORTH);

        loadCustomers();

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

    private void loadCustomers() {
        comboBox.removeAllItems();
        comboBox.addItem(null);

        try {
            String sql = "SELECT * FROM customer";
            DataBase db = new DataBase();
            java.util.List<Object[]> results = db.selectQuery(sql);

            for (Object[] row : results) {
                Integer id = (Integer) row[0];
                String name = (String) row[1];
                String phone = (String) row[2];
                String address = (String) row[3];
                CustomerDTO customer = new CustomerDTO(id, name, phone, address);
                comboBox.addItem(customer);
            }
        } catch (Exception e) {
            MessageBox.showError("Lỗi khi tải danh sách khách hàng: " + e.getMessage());
        }
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
                if (newSoLuong <= productDTO.getSoluong()) {
                    model.setValueAt(newSoLuong, i, 2);

                    Integer newTongCong = donGia * newSoLuong;
                    model.setValueAt(newTongCong, i, 4);
                } else {
                    MessageBox.showError("Vượt quá số lượng trong kho");
                }

                return;
            }
        }

        // If product ID not found, add new row
        if (soLuong <= productDTO.getSoluong()) {
            model.addRow(
                    new Object[] { productDTO.getMasp(), productDTO.getTensp(), soLuong, productDTO.getDongiasanpham(),
                            tongCong });
        } else {
            MessageBox.showError("Vượt quá số lượng trong kho");
        }
    }

    public JLabel getTongLabel() {
        return tongLabel;
    }

    public void setTongLabel(JLabel tongLabel) {
        this.tongLabel = tongLabel;
    }

    public JButton getHuyDonButton() {
        return huyDonButton;
    }

    public void setHuyDonButton(JButton huyDonButton) {
        this.huyDonButton = huyDonButton;
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public String[] getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(String[] columnNames) {
        this.columnNames = columnNames;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public void setModel(DefaultTableModel model) {
        this.model = model;
    }

    public void updateSoLuong(int soluong, int rowSelected) {
        ProductDTO productDTO = new ProductDAO()
                .getProductById(Integer.parseInt(model.getValueAt(rowSelected, 0).toString()));
        if (soluong <= productDTO.getSoluong()) {
            model.setValueAt(soluong, rowSelected, 2);
        } else {
            MessageBox.showError("Vượt quá số lượng trong kho");
        }
    }

    public JComboBox<CustomerDTO> getComboBox() {
        return comboBox;
    }

    public void setComboBox(JComboBox<CustomerDTO> comboBox) {
        this.comboBox = comboBox;
    }

}
