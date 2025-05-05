package com.sieuthimini.GUI.InHoaDonComp;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.sieuthimini.BUS.ProductBUS;
import com.sieuthimini.DTO.ProductDTO;
import com.sieuthimini.GUI.InHoaDonComp.SanPhamComp.Order;

public class SanPham extends JPanel {

    private Table table;

    public SanPham(Table table) {
        this.table = table;

        this.setLayout(new BorderLayout());

        JPanel productPanel = new JPanel(new GridLayout(0, 2, 10, 10));

        List<ProductDTO> list = new ProductBUS().getProducts();

        // Thêm các sản phẩm (ví dụ là nút)
        for (ProductDTO productDTO : list) {
            productPanel.add(new Order(productDTO, table));
        }

        JScrollPane scrollPane = new JScrollPane(productPanel);

        this.add(scrollPane);
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

}
