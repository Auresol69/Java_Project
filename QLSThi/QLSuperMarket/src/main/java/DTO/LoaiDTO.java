package DTO;

public class LoaiDTO {
    private int maloaisp; // Mã loại sản phẩm
    private String tenloaisp; // Tên loại sản phẩm

    // Constructor không tham số
    public LoaiDTO() {
    }

    // Constructor đầy đủ tham số
    public LoaiDTO(int maloaisp, String tenloaisp) {
        this.maloaisp = maloaisp;
        this.tenloaisp = tenloaisp;
    }

    // Getter và Setter
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

    // Phương thức toString để hiển thị thông tin loại sản phẩm
    @Override
    public String toString() {
        return "LoaiDTO{" +
                "maloaisp=" + maloaisp +
                ", tenloaisp='" + tenloaisp + '\'' +
                '}';
    }
}