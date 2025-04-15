package DTO;

public class NhanVienDTO {
    private int maNV;
    private String hoten;
    private String sdt;
    private String address;
    private int status;

    public NhanVienDTO() {
    }

    public NhanVienDTO(int maNV, String hoten, String sdt, String address, int status) {
        this.maNV = maNV;
        this.hoten = hoten;
        this.sdt = sdt;
        this.address = address;
        this.status = status;
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        return true;
    }

    public int getColumnCount() {
        return getClass().getDeclaredFields().length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("NhanVienDTO{");
        sb.append("maNV=").append(maNV);
        sb.append(", hoten=").append(hoten);
        sb.append(", sdt=").append(sdt);
        sb.append(", address=").append(address);
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}
