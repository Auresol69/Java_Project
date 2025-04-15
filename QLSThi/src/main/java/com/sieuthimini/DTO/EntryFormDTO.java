package com.sieuthimini.DTO;

public class EntryFormDTO {
    private String mancc, maphieunhap, ngaynhap;

    private int maaccount;

    public int getMaaccount() {
        return maaccount;
    }

    public void setMaaccount(int maaccount) {
        this.maaccount = maaccount;
    }

    public String getMancc() {
        return mancc;
    }

    public void setMancc(String mancc) {
        this.mancc = mancc;
    }

    public String getMaphieunhap() {
        return maphieunhap;
    }

    public void setMaphieunhap(String maphieunhap) {
        this.maphieunhap = maphieunhap;
    }

    public String getNgaynhap() {
        return ngaynhap;
    }

    public void setNgaynhap(String ngaynhap) {
        this.ngaynhap = ngaynhap;
    }

    public EntryFormDTO(int maaccount, String mancc, String maphieunhap, String ngaynhap) {
        this.maaccount = maaccount;
        this.mancc = mancc;
        this.maphieunhap = maphieunhap;
        this.ngaynhap = ngaynhap;
    }

    public EntryFormDTO() {
    }

}
