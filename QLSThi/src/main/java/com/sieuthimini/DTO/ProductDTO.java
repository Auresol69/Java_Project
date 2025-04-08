package com.sieuthimini.DTO;

public class ProductDTO {
    private String masp, maloaisp, mancc, tensp, img;
    private Integer soluong, dongiasanpham;

    public String getMasp() {
        return masp;
    }

    public void setMasp(String masp) {
        this.masp = masp;
    }

    public String getMaloaisp() {
        return maloaisp;
    }

    public void setMaloaisp(String maloaisp) {
        this.maloaisp = maloaisp;
    }

    public String getMancc() {
        return mancc;
    }

    public void setMancc(String mancc) {
        this.mancc = mancc;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getSoluong() {
        return soluong;
    }

    public void setSoluong(Integer soluong) {
        this.soluong = soluong;
    }

    public Integer getDongiasanpham() {
        return dongiasanpham;
    }

    public void setDongiasanpham(Integer dongiasanpham) {
        this.dongiasanpham = dongiasanpham;
    }

    public ProductDTO(String masp, String maloaisp, String mancc, String tensp, String img, Integer soluong,
            Integer dongiasanpham) {
        this.masp = masp;
        this.maloaisp = maloaisp;
        this.mancc = mancc;
        this.tensp = tensp;
        this.img = img;
        this.soluong = soluong;
        this.dongiasanpham = dongiasanpham;
    }

    public ProductDTO() {
    }
}
