package DTO;

public class SanPhamDTO {
    private int masp; // Mã sản phẩm
    private String tensp; // Tên sản phẩm
    private int soluong; // Số lượng
    private int dongiasanpham; // Đơn giá sản phẩm
    private int maloaisp; // Mã loại sản phẩm
    private String img; // Đường dẫn ảnh sản phẩm

    // Constructor không tham số
    public SanPhamDTO() {
    }

    // Constructor đầy đủ tham số
    public SanPhamDTO(int masp, String tensp, int soluong, int dongiasanpham, int maloaisp, String img) {
        this.masp = masp;
        this.tensp = tensp;
        this.soluong = soluong;
        this.dongiasanpham = dongiasanpham;
        this.maloaisp = maloaisp;
        
        this.img = img;
    }

    public SanPhamDTO(int masp, String tensp, int soluong, int dongiasanpham, int maloaisp) {
        this.masp = masp;
        this.tensp = tensp;
        this.soluong = soluong;
        this.dongiasanpham = dongiasanpham;
        this.maloaisp = maloaisp;
    }

    public SanPhamDTO(int id, String text,int maloaisp, String imagePath) {
        this.masp = id;
        this.tensp = text;
        this.maloaisp = maloaisp;
        this.img = imagePath;
    }

    // Getter và Setter
    public int getMasp() {
        return masp;
    }

    public void setMasp(int masp) {
        this.masp = masp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getDongiasanpham() {
        return dongiasanpham;
    }

    public void setDongiasanpham(int dongiasanpham) {
        this.dongiasanpham = dongiasanpham;
    }

    public int getMaloaisp() {
        return maloaisp;
    }

    public void setMaloaisp(int maloaisp) {
        this.maloaisp = maloaisp;
    }



    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    // Phương thức toString để hiển thị thông tin sản phẩm
    @Override
    public String toString() {
        return "SanPhamDTO{" +
                "masp=" + masp +
                ", tensp='" + tensp + '\'' +
                ", soluong=" + soluong +
                ", dongiasanpham=" + dongiasanpham +
                ", maloaisp=" + maloaisp +
                ", img='" + img + '\'' +
                '}';
    }
}