package com.sieuthimini.DAO;

import java.util.List;

public class ProductDAO {

    public List<Object[]> getProductByInfo(int masp, String tensp, int maloaisp) {
        DataBase db = new DataBase();
        return db.selectQuery("SELECT * FROM product WHERE masp = ? AND tensp = ? AND maloaisp = ?",
                masp, tensp, maloaisp);
    }

    public Object[] getTenSanPham(int id) {
        DataBase db = new DataBase();
        List<Object[]> data = db.selectQuery("SELECT * FROM product WHERE masp = ?", id);
        if (data.isEmpty()) {
            return null;
        }
        return data.get(0);
    }
    // public void insertProduct(Object[] row) {
    // DataBase db = new DataBase();
    // db.executeQuery("INSERT INTO product (...) VALUES (?, ?, ...)", row);
    // }
}