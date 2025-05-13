package DTO;

import java.util.Date;

public class ThongKeKhachHangDTO {
    private int maKhachHang;
    private String hoTen;
    private int slBan;
    private int doanhThu;
    private int loiNhuan;
    private Date ngay;

    public ThongKeKhachHangDTO(int maKhachHang, String hoTen, Date ngay, int slBan, int doanhThu) {
        this.maKhachHang = maKhachHang;
        this.hoTen = hoTen;
        this.ngay = ngay;
        this.slBan = slBan;
        this.doanhThu = doanhThu;
        this.loiNhuan = doanhThu; // vì không có chi phí, coi toàn bộ doanh thu là lợi nhuận
    }

    public int getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(int maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public int getSlBan() {
        return slBan;
    }

    public void setSlBan(int slBan) {
        this.slBan = slBan;
    }

    public int getDoanhThu() {
        return doanhThu;
    }

    public void setDoanhThu(int doanhThu) {
        this.doanhThu = doanhThu;
        this.loiNhuan = doanhThu; // cập nhật lại lợi nhuận nếu doanh thu thay đổi
    }

    public int getLoiNhuan() {
        return loiNhuan;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    @Override
    public String toString() {
        return "ThongKeKhachHang [maKhachHang=" + maKhachHang + ", hoTen=" + hoTen + ", slBan=" + slBan +
               ", doanhThu=" + doanhThu + ", loiNhuan=" + loiNhuan + ", ngay=" + ngay + "]";
    }
}
