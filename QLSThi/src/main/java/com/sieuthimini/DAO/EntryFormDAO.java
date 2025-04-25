package com.sieuthimini.DAO;

import java.util.List;

import com.sieuthimini.DTO.AccountDTO;
import com.sieuthimini.DTO.EntryFormDTO;
import com.sieuthimini.DTO.SupplierDTO;

public class EntryFormDAO {
    DataBase db = new DataBase();

    public int createEntryForm(SupplierDTO supplierDTO, AccountDTO accountDTO, int loinhuan) {
        EntryFormDTO entryFormDTO = new EntryFormDTO(supplierDTO.getMancc(), accountDTO.getMastaff(), loinhuan);
        return db.insertQuery("INSERT INTO entry_form(mancc,maaccount, loinhuan) VALUES (?,?,?)",
                entryFormDTO.getMancc(),
                entryFormDTO.getMaaccount(),
                entryFormDTO.getLoinhuan());
    }

    public Object[] getEntryFormByID(int id) {

        List<Object[]> data = db.selectQuery("SELECT * FROM entry_form WHERE maphieunhap = ?", id);
        if (data.isEmpty())
            return null;
        return data.get(0);
    }
}
