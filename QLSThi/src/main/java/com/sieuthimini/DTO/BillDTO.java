package com.sieuthimini.DTO;

public class BillDTO {
    private int mapayby, macustomer;
    private String ngaymua;
    private Integer tongtien;
    private int mabill;

    public int getMabill() {
        return mabill;
    }

    public void setMabill(int mabill) {
        this.mabill = mabill;
    }

    public int getMapayby() {
        return mapayby;
    }

    public void setMapayby(int mapayby) {
        this.mapayby = mapayby;
    }

    public int getMacustomer() {
        return macustomer;
    }

    public void setMacustomer(int macustomer) {
        this.macustomer = macustomer;
    }

    public String getNgaymua() {
        return ngaymua;
    }

    public void setNgaymua(String ngaymua) {
        this.ngaymua = ngaymua;
    }

    public Integer getTongtien() {
        return tongtien;
    }

    public void setTongtien(Integer tongtien) {
        this.tongtien = tongtien;
    }

    public BillDTO(int mabill, int mapayby, int macustomer, String ngaymua, Integer tongtien) {
        this.mabill = mabill;
        this.mapayby = mapayby;
        this.macustomer = macustomer;
        this.ngaymua = ngaymua;
        this.tongtien = tongtien;
    }

    public BillDTO() {
    }

}
