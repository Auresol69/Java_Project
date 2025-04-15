package DTO;

import java.util.Date;

public class NhanVienDTO {
    private int maNV;
    private String hoten;
    private boolean gioitinh;
    private String sdt;
    private String email;
    private Date ngaysinh;

    public NhanVienDTO() {
    }

    public NhanVienDTO(int maNV, String hoten, boolean gioitinh, String sdt, String email, Date ngaysinh) {
        this.maNV = maNV;
        this.hoten = hoten;
        this.gioitinh = gioitinh;
        this.sdt = sdt;
        this.email = email;
        this.ngaysinh = ngaysinh;
    }

    

}
