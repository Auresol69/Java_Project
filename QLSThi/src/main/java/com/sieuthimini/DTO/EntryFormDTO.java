package com.sieuthimini.DTO;

public class EntryFormDTO {
    private String ngaynhap;

    private int maaccount, maphieunhap, mancc;

    private float loinhuan;

    private double tongtien;

    public double getTongtien() {
        return tongtien;
    }

    public void setTongtien(double tongtien) {
        this.tongtien = tongtien;
    }

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

    public float getLoinhuan() {
        return loinhuan;
    }

    public void setLoinhuan(float loinhuan) {
        this.loinhuan = loinhuan;
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

    public EntryFormDTO(int maaccount, int mancc, int maphieunhap, String ngaynhap, float loinhuan) {
        this.maaccount = maaccount;
        this.mancc = mancc;
        this.maphieunhap = maphieunhap;
        this.ngaynhap = ngaynhap;
        this.loinhuan = loinhuan;
    }

    public EntryFormDTO(int maaccount, int maphieunhap, int mancc, String ngaynhap, float loinhuan, double tongtien) {
        this.ngaynhap = ngaynhap;
        this.maaccount = maaccount;
        this.maphieunhap = maphieunhap;
        this.mancc = mancc;
        this.loinhuan = loinhuan;
        this.tongtien = tongtien;
    }

    public EntryFormDTO() {
    }

    public EntryFormDTO(int mancc, int maaccount, float loinhuan) {
        this.mancc = mancc;
        this.maaccount = maaccount;
        this.loinhuan = loinhuan;
    }
}
