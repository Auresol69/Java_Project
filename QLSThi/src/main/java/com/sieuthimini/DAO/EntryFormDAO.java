package com.sieuthimini.DAO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.sieuthimini.DTO.AccountDTO;
import com.sieuthimini.DTO.EntryFormDTO;
import com.sieuthimini.DTO.StaffDTO;
import com.sieuthimini.DTO.SupplierDTO;
import com.toedter.calendar.JDateChooser;

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

    public List<EntryFormDTO> searchEntryForm(StaffDTO staff, SupplierDTO supplier, JDateChooser fromDate,
            JDateChooser toDate, String fromMoney, String toMoney) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        List<Object> myArrays = new ArrayList<>();
        List<EntryFormDTO> result = new ArrayList<>();
        String sql = "SELECT * FROM entry_form WHERE 1=1";

        if (staff != null) {
            sql += "AND maaccount = ?";
            myArrays.add(staff.getMastaff());
        }
        if (supplier != null) {
            sql += "AND supplier = ?";
            myArrays.add(supplier.getMancc());
        }
        if (fromDate != null) {
            String fromDateStr = sdf.format(fromDate.getDate());
            sql += "AND ngaynhap >= ?";
            myArrays.add(fromDateStr);
        }
        if (toDate != null) {
            String toDateStr = sdf.format(toDate.getDate());
            sql += "AND ngaynhap <= ?";
            myArrays.add(toDateStr);
        }
        Object[] arr = myArrays.toArray();

        List<Object[]> rawData = db.selectQuery(sql, arr);

        return result;
    }
}
