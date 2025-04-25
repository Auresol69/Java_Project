package com.sieuthimini.BUS;

import java.util.ArrayList;

import com.sieuthimini.DAO.DetailEntryFormDAO;
import com.sieuthimini.DTO.DetailEntryFormDTO;

public class DetailEntryFormBUS {
    public boolean createDetailEntryForm(int maphieunhap, int soluong, int dongianhap, int masp) {
        if (maphieunhap == 0 || masp == 0) {
            System.out.println("Lỗi: Thiếu thông tin nhà cung cấp hoặc sản phẩm.");
            return false;
        }
        if (soluong <= 0 || dongianhap <= 0) {
            System.out.println("Lỗi: Số lượng nhập hoặc giá nhập không hợp lệ");
            return false;
        }
        new DetailEntryFormDAO().createDetailEntryForm(maphieunhap, soluong, dongianhap, masp);
        return true;
    }

    public ArrayList<DetailEntryFormDTO> getDetailEntryFormByID(int id) {
        ArrayList<DetailEntryFormDTO> detailEntryFormDTOs = null;

        if (id == -1) {
            return null;
        } else {
            detailEntryFormDTOs = new DetailEntryFormDAO().getDetailEntryFormByID(id);
        }

        return detailEntryFormDTOs;
    }
}
