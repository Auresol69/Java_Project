package com.sieuthimini.DTO;

public class ProductType {
    private String maloaisp, tenloaisp;

    public String getMaloaisp() {
        return maloaisp;
    }

    public void setMaloaisp(String maloaisp) {
        this.maloaisp = maloaisp;
    }

    public String getTenloaisp() {
        return tenloaisp;
    }

    public void setTenloaisp(String tenloaisp) {
        this.tenloaisp = tenloaisp;
    }

    public ProductType(String maloaisp, String tenloaisp) {
        this.maloaisp = maloaisp;
        this.tenloaisp = tenloaisp;
    }

    public ProductType() {
    }

}
