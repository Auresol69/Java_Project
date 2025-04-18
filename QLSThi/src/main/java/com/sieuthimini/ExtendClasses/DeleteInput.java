package com.sieuthimini.ExtendClasses;

import com.sieuthimini.GUI.PhieuComp.newPhieuNhapComp.InputSanPham;
import com.sieuthimini.GUI.PhieuComp.newPhieuNhapComp.TongTien;

public class DeleteInput {
    InputSanPham inputSanPham;
    TongTien tongTien;

    public DeleteInput(InputSanPham inputSanPham, TongTien tongTien) {
        this.tongTien = tongTien;
        this.inputSanPham = inputSanPham;
    }

    public void Delete() {
        inputSanPham.getMaSanPhamField().setText("");
        inputSanPham.getTenSanPhamField().setText("");
        inputSanPham.getSoluongSanPhamField().setText("");

        tongTien.getGianhapField().setText("");
    }
}
