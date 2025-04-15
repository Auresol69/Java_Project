package com.sieuthimini.BUS;

import java.util.List;

import com.sieuthimini.DAO.ProductDAO;

public class ProductBUS {

    private ProductDAO productDAO = new ProductDAO();

    public List<Object[]> checkSanPham(int masp, String tensp, int maloaisp) {
        return productDAO.getProductByInfo(masp, tensp, maloaisp);
    }
}
