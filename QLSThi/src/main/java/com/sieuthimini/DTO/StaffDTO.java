package com.sieuthimini.DTO;

public class StaffDTO {
    private String address, tennhanstaff, dienthoai;
    private int mastaff;

    public int getMastaff() {
        return mastaff;
    }

    public void setMastaff(int mastaff) {
        this.mastaff = mastaff;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public StaffDTO(int mastaff, String address, String tennhanstaff, String dienthoai) {
        this.mastaff = mastaff;
        this.address = address;
        this.tennhanstaff = tennhanstaff;
        this.dienthoai = dienthoai;
    }

    public StaffDTO() {
    }

}