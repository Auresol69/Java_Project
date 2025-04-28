package com.sieuthimini.DAO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.sieuthimini.DTO.AccountDTO;
import com.sieuthimini.DTO.EntryFormDTO;
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

    public List<EntryFormDTO> searchEntryForm(AccountDTO accountDTO, SupplierDTO supplier, JDateChooser fromDate,
            JDateChooser toDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<Object> params = new ArrayList<>();
        List<EntryFormDTO> result = new ArrayList<>();

        StringBuilder sql = new StringBuilder(
                "SELECT ef.maphieunhap, ef.maaccount, ef.mancc, ef.ngaynhap, " +
                        "COALESCE(SUM(def.soluongnhap * def.dongianhap), 0) AS tong_tien_nhap " +
                        "FROM entry_form ef " +
                        "LEFT JOIN detail_entry_form def ON ef.maphieunhap = def.maphieunhap " +
                        "WHERE 1=1 ");

        if (accountDTO != null) {
            sql.append("AND ef.maaccount = ? ");
            params.add(accountDTO.getMaaccount());
        }
        if (supplier != null) {
            sql.append("AND ef.mancc = ? ");
            params.add(supplier.getMancc());
        }
        if (fromDate != null && fromDate.getDate() != null) {
            sql.append("AND DATE(ef.ngaynhap) >= ? ");
            params.add(sdf.format(fromDate.getDate()));
        }
        if (toDate != null && toDate.getDate() != null) {
            sql.append("AND DATE(ef.ngaynhap) <= ? ");
            params.add(sdf.format(toDate.getDate()));
        }

        sql.append("GROUP BY ef.maphieunhap, ef.maaccount, ef.mancc, ef.ngaynhap ");
        sql.append("ORDER BY ef.ngaynhap DESC");

        List<Object[]> rawData = db.selectQuery(sql.toString(), params.toArray());

        for (Object[] row : rawData) {
            int maphieunhap = (row[0] != null) ? Integer.parseInt(row[0].toString()) : 0;
            int maaccount = (row[1] != null) ? Integer.parseInt(row[1].toString()) : 0;
            int mancc = (row[2] != null) ? Integer.parseInt(row[2].toString()) : 0;
            String ngaynhap = (row[3] != null) ? row[3].toString() : "";
            double tongTienNhap = (row[4] != null) ? Double.parseDouble(row[4].toString()) : 0.0;

            result.add(new EntryFormDTO(maphieunhap, maaccount, mancc, ngaynhap, 0f, tongTienNhap));
        }

        return result;
    }

}
