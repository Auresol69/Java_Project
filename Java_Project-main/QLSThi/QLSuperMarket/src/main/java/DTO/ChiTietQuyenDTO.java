package DTO;

import java.util.Objects;

public class ChiTietQuyenDTO {
    private int manhomquyen;
    private int machucnang;

    public ChiTietQuyenDTO() {
    }

    public ChiTietQuyenDTO(int manhomquyen, int machucnang) {
        this.manhomquyen = manhomquyen;
        this.machucnang = machucnang;
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
        return manhomquyen == other.manhomquyen && machucnang == other.machucnang;
    }


    @Override
    public String toString() {
        return "ChiTietQuyenDTO{" +
               "manhomquyen=" + manhomquyen +
               ", machucnang=" + machucnang +
               '}';
    }
}
