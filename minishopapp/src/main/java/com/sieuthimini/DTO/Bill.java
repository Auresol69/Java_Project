package com.sieuthimini.DTO;

public class Bill {
    private String mabill, mapayby, macustomer, ngaymua;
    private Integer tongtien;

    public String getMabill() {
        return mabill;
    }

    public void setMabill(String mabill) {
        this.mabill = mabill;
    }

    public String getMapayby() {
        return mapayby;
    }

    public void setMapayby(String mapayby) {
        this.mapayby = mapayby;
    }

    public String getMacustomer() {
        return macustomer;
    }

    public void setMacustomer(String macustomer) {
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

    public Bill(String mabill, String mapayby, String macustomer, String ngaymua, Integer tongtien) {
        this.mabill = mabill;
        this.mapayby = mapayby;
        this.macustomer = macustomer;
        this.ngaymua = ngaymua;
        this.tongtien = tongtien;
    }

    public Bill() {
    }

}
