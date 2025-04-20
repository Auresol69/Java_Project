package com.sieuthimini.DAO;

import com.sieuthimini.DTO.AccountDTO;
import com.sieuthimini.DTO.EntryFormDTO;
import com.sieuthimini.DTO.SupplierDTO;

public class EntryFormDAO {
    DataBase db = new DataBase();

    public int createEntryForm(SupplierDTO supplierDTO, AccountDTO accountDTO) {
        EntryFormDTO entryFormDTO = new EntryFormDTO(supplierDTO.getMancc(), accountDTO.getMastaff());
        return db.insertQuery("INSERT INTO entry_form(mancc,maaccount) VALUES (?,?)", entryFormDTO.getMancc(),
                entryFormDTO.getMaaccount());
    }
}
