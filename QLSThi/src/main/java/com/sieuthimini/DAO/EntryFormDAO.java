package com.sieuthimini.DAO;

import com.sieuthimini.DTO.AccountDTO;
import com.sieuthimini.DTO.EntryFormDTO;
import com.sieuthimini.DTO.SupplierDTO;

public class EntryFormDAO {
    DataBase db = new DataBase();

    public void createEntryForm(SupplierDTO supplierDTO, AccountDTO accountDTO) {
        EntryFormDTO entryFormDTO = new EntryFormDTO(supplierDTO.getMancc(), accountDTO.getMastaff());
        db.insertQuery("INSERT INTO entry_form(mancc,maaccount) VALUES (?,?)", entryFormDTO.getMancc(),
                entryFormDTO.getMaaccount());
    }
}
