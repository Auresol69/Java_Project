package DTO;
public class SanPhamDTO {
    private String maSP;
    private String tenSP;
    private int soLuong;
    private double donGia;
    private String maLoai;
    private String maNCC;
    private String img;
    private int status;

    public SanPhamDTO() {}

    public SanPhamDTO(String maSP, String tenSP, int soLuong, double donGia,
                      String maLoai, String maNCC, String img) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.maLoai = maLoai;
        this.maNCC = maNCC;
        this.img = img;
    
    }

    // Getters and Setters
    public String getMaSP() { return maSP; }
    public void setMaSP(String maSP) { this.maSP = maSP; }

    public String getTenSP() { return tenSP; }
    public void setTenSP(String tenSP) { this.tenSP = tenSP; }

    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }

    public double getDonGia() { return donGia; }
    public void setDonGia(double donGia) { this.donGia = donGia; }

    public String getMaLoai() { return maLoai; }
    public void setMaLoai(String maLoai) { this.maLoai = maLoai; }

    public String getMaNCC() { return maNCC; }
    public void setMaNCC(String maNCC) { this.maNCC = maNCC; }

    public String getImg() { return img; }
    public void setImg(String img) { this.img = img; }

    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }
}
