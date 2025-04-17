package com.sieuthimini.DTO;

public class DetailEntryForm {
    private int maphieunhap, dongianhap, giaban, soluongnhap;
    private String masp, ngayhethan;

    public DetailEntryForm(int maphieunhap, int dongianhap, int giaban, int soluongnhap, String masp,
            String ngayhethan) {
        this.maphieunhap = maphieunhap;
        this.dongianhap = dongianhap;
        this.giaban = giaban;
        this.soluongnhap = soluongnhap;
        this.masp = masp;
        this.ngayhethan = ngayhethan;
    }

    public DetailEntryForm() {
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

    public int getGiaban() {
        return giaban;
    }

    public void setGiaban(int giaban) {
        this.giaban = giaban;
    }

    public int getSoluongnhap() {
        return soluongnhap;
    }

    public void setSoluongnhap(int soluongnhap) {
        this.soluongnhap = soluongnhap;
    }

    public String getMasp() {
        return masp;
    }

    public void setMasp(String masp) {
        this.masp = masp;
    }

    public String getNgayhethan() {
        return ngayhethan;
    }

    public void setNgayhethan(String ngayhethan) {
        this.ngayhethan = ngayhethan;
    }
}
