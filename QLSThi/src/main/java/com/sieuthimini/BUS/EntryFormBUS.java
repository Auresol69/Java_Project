package com.sieuthimini.BUS;

import com.sieuthimini.DAO.EntryFormDAO;
import com.sieuthimini.DTO.AccountDTO;
import com.sieuthimini.DTO.SupplierDTO;

public class EntryFormBUS {
    public EntryFormBUS() {

    }

    public boolean createEntryForm(SupplierDTO supplierDTO, AccountDTO accountDTO) {
        if (supplierDTO == null || accountDTO == null) {
            System.out.println("Lỗi: Thiếu thông tin nhà cung cấp hoặc tài khoản.");
            return false;
        }

        if (supplierDTO.getMancc() == 0) {
            System.out.println("Lỗi: ID nhà cung cấp không được để trống.");
            return false;
        }

        if (accountDTO.getMaaccount() == 0) {
            System.out.println("Lỗi: Tên người dùng không được để trống.");
            return false;
        }

        new EntryFormDAO().createEntryForm(supplierDTO, accountDTO);

        return true;
    }

}
