package com.sieuthimini.DTO;

public class SupplierDTO {
    private String tencc, diachi, dienthoai, sofax;
    private int mancc;

    public int getMancc() {
        return mancc;
    }

    public void setMancc(int mancc) {
        this.mancc = mancc;
    }

    public String getTencc() {
        return tencc;
    }

    public void setTencc(String tencc) {
        this.tencc = tencc;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getDienthoai() {
        return dienthoai;
    }

    public void setDienthoai(String dienthoai) {
        this.dienthoai = dienthoai;
    }

    public String getSofax() {
        return sofax;
    }

    public void setSofax(String sofax) {
        this.sofax = sofax;
    }

    public SupplierDTO(int mancc, String tencc, String diachi, String dienthoai, String sofax) {
        this.mancc = mancc;
        this.tencc = tencc;
        this.diachi = diachi;
        this.dienthoai = dienthoai;
        this.sofax = sofax;
    }

    public SupplierDTO() {

    }

    @Override
    public String toString() {
        return this.getTencc();
    }
}
