package com.sieuthimini.BUS;

import com.sieuthimini.DAO.DetailEntryFormDAO;

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
}
