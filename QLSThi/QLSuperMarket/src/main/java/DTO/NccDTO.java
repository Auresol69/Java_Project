package DTO;

public class NccDTO {
    private int mancc; // Mã nhà cung cấp
    private String tencc; // Tên nhà cung cấp
    private String diachi; // Địa chỉ nhà cung cấp
    private String dienthoai; // Số điện thoại nhà cung cấp
    private String sofax; // Số fax nhà cung cấp

    // Constructor không tham số
    public NccDTO() {
    }

    // Constructor đầy đủ tham số
    public NccDTO(int mancc, String tencc, String diachi, String dienthoai, String sofax) {
        this.mancc = mancc;
        this.tencc = tencc;
        this.diachi = diachi;
        this.dienthoai = dienthoai;
        this.sofax = sofax;
    }

    // Getter và Setter
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

    // Phương thức toString để hiển thị thông tin nhà cung cấp
    @Override
    public String toString() {
        return "NccDTO{" +
                "mancc=" + mancc +
                ", tencc='" + tencc + '\'' +
                ", diachi='" + diachi + '\'' +
                ", dienthoai='" + dienthoai + '\'' +
                ", sofax='" + sofax + '\'' +
                '}';
    }
}