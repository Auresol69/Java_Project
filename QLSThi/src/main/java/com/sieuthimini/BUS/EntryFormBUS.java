package com.sieuthimini.BUS;

import com.sieuthimini.DAO.EntryFormDAO;
import com.sieuthimini.DTO.AccountDTO;
import com.sieuthimini.DTO.SupplierDTO;

public class EntryFormBUS {
    public EntryFormBUS() {

    }

    public int createEntryForm(SupplierDTO supplierDTO, AccountDTO accountDTO) {
        if (supplierDTO == null || accountDTO == null) {
            System.out.println("Lỗi: Thiếu thông tin nhà cung cấp hoặc tài khoản.");
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

        return new EntryFormDAO().createEntryForm(supplierDTO, accountDTO);

    }

}
