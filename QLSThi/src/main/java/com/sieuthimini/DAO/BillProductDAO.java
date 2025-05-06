package com.sieuthimini.DAO;

public class BillProductDAO {
    DataBase db = new DataBase();

    public int createBillProduct(int bill, int sp, int sl) {
        return db.insertQuery("INSERT INTO bill_product(mabill, masp, soluong) VALUES (?, ?, ?)", bill, sp, sl);

    }
}
