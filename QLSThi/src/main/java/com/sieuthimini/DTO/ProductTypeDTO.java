package com.sieuthimini.DTO;

public class ProductTypeDTO {
    private String tenloaisp;
    private int maloaisp;

    public int getMaloaisp() {
        return maloaisp;
    }

    public void setMaloaisp(int maloaisp) {
        this.maloaisp = maloaisp;
    }

    public String getTenloaisp() {
        return tenloaisp;
    }

    public void setTenloaisp(String tenloaisp) {
        this.tenloaisp = tenloaisp;
    }

    public ProductTypeDTO(int maloaisp, String tenloaisp) {
        this.maloaisp = maloaisp;
        this.tenloaisp = tenloaisp;
    }

    public ProductTypeDTO() {
    }

    @Override
    public String toString() {
        return this.tenloaisp;
    }

}
