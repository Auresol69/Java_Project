package com.sieuthimini.DAO;

import java.util.List;

public class ProductDAO {

    public List<Object[]> getProductByInfo(String masp, String tensp, String maloaisp) {
        DataBase db = new DataBase();
        return db.selectQuery("SELECT * FROM product WHERE masp = ? AND tensp = ? AND maloaisp = ?",
                masp, tensp, maloaisp);
    }

    // public void insertProduct(Object[] row) {
    // DataBase db = new DataBase();
    // db.executeQuery("INSERT INTO product (...) VALUES (?, ?, ...)", row);
    // }
}