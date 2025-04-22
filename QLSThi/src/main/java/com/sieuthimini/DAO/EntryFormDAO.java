package com.sieuthimini.DAO;

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
}
