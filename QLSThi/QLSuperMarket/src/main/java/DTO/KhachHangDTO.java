package DTO;

import java.util.Date;

public class KhachHangDTO {
    
    private int maKH;
    private String hoten;
    private String sdt;
    private String address;
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public KhachHangDTO() {
    }

    public KhachHangDTO (int maKH,String hoten, String sdt, String address,  Date date){
        this.maKH = maKH;
        this.address = address;
        this.sdt = sdt;
        this.hoten = hoten;
        this.date = date;
    }
    public KhachHangDTO (int maKH,String hoten, String sdt, String address){
        this.maKH = maKH;
        this.address = address;
        this.sdt = sdt;
        this.hoten = hoten;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    
}
