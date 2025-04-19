package com.sieuthimini.DTO;

public class EntryFormDTO {
    private String ngaynhap;

    private int maaccount, maphieunhap, mancc;

    public int getMaaccount() {
        return maaccount;
    }

    public void setMaaccount(int maaccount) {
        this.maaccount = maaccount;
    }

    public int getMancc() {
        return mancc;
    }

    public void setMancc(int mancc) {
        this.mancc = mancc;
    }

    public int getMaphieunhap() {
        return maphieunhap;
    }

    public void setMaphieunhap(int maphieunhap) {
        this.maphieunhap = maphieunhap;
    }

    public String getNgaynhap() {
        return ngaynhap;
    }

    public void setNgaynhap(String ngaynhap) {
        this.ngaynhap = ngaynhap;
    }

    public EntryFormDTO(int maaccount, int mancc, int maphieunhap, String ngaynhap) {
        this.maaccount = maaccount;
        this.mancc = mancc;
        this.maphieunhap = maphieunhap;
        this.ngaynhap = ngaynhap;
    }

    public EntryFormDTO() {
    }

    public EntryFormDTO(int mancc, int maaccount) {
        this.mancc = mancc;
        this.maaccount = maaccount;
    }
}
