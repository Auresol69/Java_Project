package DTO;

import java.util.Objects;

public class ChiTietQuyenDTO {
    private int manhomquyen;
    private int machucnang;
    private int permissionid;
    
    public int getPermissionid() {
        return permissionid;
    }

    public void setPermissionid(int permissionid) {
        this.permissionid = permissionid;
    }

    public ChiTietQuyenDTO() {
    }

    public ChiTietQuyenDTO(int manhomquyen, int machucnang, int permissionid) {
        this.manhomquyen = manhomquyen;
        this.machucnang = machucnang;
        this.permissionid = permissionid;
    }

    public int getManhomquyen() {
        return manhomquyen;
    }

    public void setManhomquyen(int manhomquyen) {
        this.manhomquyen = manhomquyen;
    }

    public int getMachucnang() {
        return machucnang;
    }

    public void setMachucnang(int machucnang) {
        this.machucnang = machucnang;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ChiTietQuyenDTO other = (ChiTietQuyenDTO) obj;
        return manhomquyen == other.manhomquyen && machucnang == other.machucnang && permissionid == other.permissionid;
    }
    @Override
    public String toString() {
        return "ChiTietQuyenDTO{" +
               "manhomquyen=" + manhomquyen +
               ", machucnang=" + machucnang +
               '}';
    }
}
