package com.sieuthimini.DAO;

public class PayByDAO {
    DataBase db = new DataBase();

    public int createPayBy(String name, String detailss) {
        return db.insertQuery("INSERT INTO payby(paybyname, details) VALUES (?, ?)", name, detailss);
    }
}
