package com.sieuthimini.BUS;

import com.sieuthimini.DAO.EntryFormDAO;
import com.sieuthimini.DTO.AccountDTO;
import com.sieuthimini.DTO.EntryFormDTO;
import com.sieuthimini.DTO.SupplierDTO;

public class EntryFormBUS {
    public EntryFormBUS() {

    }

    public int createEntryForm(SupplierDTO supplierDTO, AccountDTO accountDTO, int loinhuan) {
        if (supplierDTO == null || accountDTO == null) {
            System.out.println("Lỗi: Thiếu thông tin nhà cung cấp hoặc tài khoản.");
            return -1;
        }
        if (loinhuan == 0) {
            System.out.println("Lỗi: % Lợi nhuận không được để trống.");
            return -1;
        }

        if (supplierDTO.getMancc() == 0) {
            System.out.println("Lỗi: ID nhà cung cấp không được để trống.");
            return -1;
        }

        if (accountDTO.getMaaccount() == 0) {
            System.out.println("Lỗi: Tên người dùng không được để trống.");
            return -1;
        }

        return new EntryFormDAO().createEntryForm(supplierDTO, accountDTO, loinhuan);

    }

    public EntryFormDTO getEntryFormByID(int id) {
        if (id == -1)
            return null;
        Object[] data = new EntryFormDAO().getEntryFormByID(id);
        return new EntryFormDTO(Integer.parseInt(data[3].toString()),
                Integer.parseInt(data[2].toString()),
                Integer.parseInt(data[0].toString()),
                data[1].toString(), Float.parseFloat(data[4].toString()));
    }
}
