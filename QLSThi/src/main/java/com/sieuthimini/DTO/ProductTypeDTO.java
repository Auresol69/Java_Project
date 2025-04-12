package com.sieuthimini.DTO;

public class ProductTypeDTO {
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

    public ProductTypeDTO(String maloaisp, String tenloaisp) {
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
