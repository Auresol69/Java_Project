package DTO;


import java.util.Objects;


public class NhomQuyenDTO {
    private int maNhomQuyen;
    private String tenNhomQuyen;

    public NhomQuyenDTO() {
    }

    public NhomQuyenDTO(int maNhomQuyen, String tenNhomQuyen) {
        this.maNhomQuyen = maNhomQuyen;
        this.tenNhomQuyen = tenNhomQuyen;
    }

    public NhomQuyenDTO(String tenNhomQuyen) {
        this.tenNhomQuyen = tenNhomQuyen;
    }

    public int getMaNhomQuyen() {
        return maNhomQuyen;
    }

    public void setMaNhomQuyen(int maNhomQuyen) {
        this.maNhomQuyen = maNhomQuyen;
    }

    public String getTenNhomQuyen() {
        return tenNhomQuyen;
    }

    public void setTenNhomQuyen(String tenNhomQuyen) {
        this.tenNhomQuyen = tenNhomQuyen;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null) return false;

        if(getClass() != obj.getClass()) return false;

        final NhomQuyenDTO other = (NhomQuyenDTO) obj;
        if (this.maNhomQuyen != other.maNhomQuyen) return false;

        return Objects.equals(this.tenNhomQuyen, other.tenNhomQuyen);
    }

    @Override
    public String toString() {
        return "NhomQuyenDTO [maNhomQuyen=" + maNhomQuyen + ", tenNhomQuyen=" + tenNhomQuyen + "]";
    }

    
}
