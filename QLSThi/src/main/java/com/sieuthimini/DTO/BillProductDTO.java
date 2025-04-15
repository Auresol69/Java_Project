package com.sieuthimini.DTO;

public class BillProductDTO {
    private String mabill;
    private Integer soluong;
    private int masp;

    public int getMasp() {
        return masp;
    }

    public void setMasp(int masp) {
        this.masp = masp;
    }

    public String getMabill() {
        return mabill;
    }

    public void setMabill(String mabill) {
        this.mabill = mabill;
    }

    public Integer getSoluong() {
        return soluong;
    }

    public void setSoluong(Integer soluong) {
        this.soluong = soluong;
    }

    public BillProductDTO(int masp, String mabill, Integer soluong) {
        this.masp = masp;
        this.mabill = mabill;
        this.soluong = soluong;
    }

    public BillProductDTO() {
    }

}
