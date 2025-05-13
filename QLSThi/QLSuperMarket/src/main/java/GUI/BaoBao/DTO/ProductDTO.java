package GUI.BaoBao.DTO;

import GUI.BaoBao.BUS.ProductTypeBUS;

public class ProductDTO {
    private String tensp, img;
    private Integer soluong, dongiasanpham;
    private int masp, maloaisp;

    public int getMasp() {
        return masp;
    }

    public void setMasp(int masp) {
        this.masp = masp;
    }

    public int getMaloaisp() {
        return maloaisp;
    }

    public void setMaloaisp(int maloaisp) {
        this.maloaisp = maloaisp;
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

    public ProductDTO(int masp, String tensp, Integer soluong,
            Integer dongiasanpham, int maloaisp, String img) {
        this.masp = masp;
        this.maloaisp = maloaisp;
        this.tensp = tensp;
        this.img = img;
        this.soluong = soluong;
        this.dongiasanpham = dongiasanpham;
    }

    public ProductDTO() {
    }

    public String getTenLoaiSanPham() {
        return new ProductTypeBUS().getTenLoaiSanPham(this.masp);
    }
}
