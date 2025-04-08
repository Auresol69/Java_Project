package com.sieuthimini.DTO;

public class StaffDTO {
    private String mastaff, address, tennhan, tennhanstaff, dienthoai;

    public String getMastaff() {
        return mastaff;
    }

    public void setMastaff(String mastaff) {
        this.mastaff = mastaff;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTennhan() {
        return tennhan;
    }

    public void setTennhan(String tennhan) {
        this.tennhan = tennhan;
    }

    public String getTennhanstaff() {
        return tennhanstaff;
    }

    public void setTennhanstaff(String tennhanstaff) {
        this.tennhanstaff = tennhanstaff;
    }

    public String getDienthoai() {
        return dienthoai;
    }

    public void setDienthoai(String dienthoai) {
        this.dienthoai = dienthoai;
    }

    public StaffDTO(String mastaff, String address, String tennhan, String tennhanstaff, String dienthoai) {
        this.mastaff = mastaff;
        this.address = address;
        this.tennhan = tennhan;
        this.tennhanstaff = tennhanstaff;
        this.dienthoai = dienthoai;
    }

    public StaffDTO() {
    }

}