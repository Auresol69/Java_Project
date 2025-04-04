package com.sieuthimini.DTO;

public class Supplier {
    private String mancc, tencc, diachi, dienthoai, sofax;

    public String getMancc() {
        return mancc;
    }

    public void setMancc(String mancc) {
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

    public Supplier(String mancc, String tencc, String diachi, String dienthoai, String sofax) {
        this.mancc = mancc;
        this.tencc = tencc;
        this.diachi = diachi;
        this.dienthoai = dienthoai;
        this.sofax = sofax;
    }

    public Supplier() {

    }
}
