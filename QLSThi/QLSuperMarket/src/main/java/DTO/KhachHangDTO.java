package DTO;

import java.util.Date;

public class KhachHangDTO {
    
    private int maKH;
    private String hoten;
    private String sdt;
    private String rank;
    private int point;
    private Date ngaythamgia;

    public KhachHangDTO() {
    }

    public KhachHangDTO (int maKH, String hoten, String sdt, String rank, int point, Date ngaythamgia){
        this.maKH = maKH;
        this.hoten = hoten;
        this.sdt = sdt;
        this.rank = rank;
        this.point = point;
        this.ngaythamgia = ngaythamgia;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
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

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public Date getNgaythamgia() {
        return ngaythamgia;
    }

    public void setNgaythamgia(Date ngaythamgia) {
        this.ngaythamgia = ngaythamgia;
    }

    
}
