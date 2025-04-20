package com.sieuthimini.DAO;

import com.sieuthimini.DTO.DetailEntryFormDTO;

public class DetailEntryFormDAO {
    private DataBase db = new DataBase();

    public void createDetailEntryForm(int maphieunhap, int soluongnhap, int dongianhap, int masp) {
        DetailEntryFormDTO detailEntryFormDTO = new DetailEntryFormDTO(maphieunhap, dongianhap, soluongnhap,
                masp);
        db.insertQuery("INSERT INTO detail_entry_form(maphieunhap,masp,dongianhap,soluongnhap) VALUES (?,?,?,?)",
                detailEntryFormDTO.getMaphieunhap(), detailEntryFormDTO.getMasp(), detailEntryFormDTO.getDongianhap(),
                detailEntryFormDTO.getSoluongnhap());
    }
}
