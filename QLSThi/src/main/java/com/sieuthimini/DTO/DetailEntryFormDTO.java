package com.sieuthimini.DTO;

import com.sieuthimini.BUS.ProductBUS;

public class DetailEntryFormDTO {
    private int maphieunhap, dongianhap, soluongnhap, masp;
    private String ngayhethan;

    public DetailEntryFormDTO(int maphieunhap, int dongianhap, int soluongnhap, int masp,
            String ngayhethan) {
        this.maphieunhap = maphieunhap;
        this.dongianhap = dongianhap;
        this.soluongnhap = soluongnhap;
        this.masp = masp;
        this.ngayhethan = ngayhethan;
    }

    public DetailEntryFormDTO(int maphieunhap, int dongianhap, int soluongnhap, int masp) {
        this.maphieunhap = maphieunhap;
        this.dongianhap = dongianhap;
        this.soluongnhap = soluongnhap;
        this.masp = masp;
    }

    public DetailEntryFormDTO() {
    }

    public int getMaphieunhap() {
        return maphieunhap;
    }

    public void setMaphieunhap(int maphieunhap) {
        this.maphieunhap = maphieunhap;
    }

    public int getDongianhap() {
        return dongianhap;
    }

    public void setDongianhap(int dongianhap) {
        this.dongianhap = dongianhap;
    }

    public int getSoluongnhap() {
        return soluongnhap;
    }

    public void setSoluongnhap(int soluongnhap) {
        this.soluongnhap = soluongnhap;
    }

    public int getMasp() {
        return masp;
    }

    public void setMasp(int masp) {
        this.masp = masp;
    }

    public String getNgayhethan() {
        return ngayhethan;
    }

    public void setNgayhethan(String ngayhethan) {
        this.ngayhethan = ngayhethan;
    }

    public String getTenSanPham() {
        return new ProductBUS().getTenSanPham(masp).getTensp();
    }
}
