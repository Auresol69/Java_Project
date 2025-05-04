package DTO;

import java.util.Objects;

public class ThongKeTonKhoDTO {
    int masp;
    String tensanpham;
    int tondauky;
    int nhaptrongky;
    int xuattrongky;
    int toncuoiky;

    public ThongKeTonKhoDTO() {
    }

    public ThongKeTonKhoDTO(int masp, String tensanpham, int tondauky, int nhaptrongky, int xuattrongky, int toncuoiky) {
        this.masp = masp;
        this.tensanpham = tensanpham;
        this.tondauky = tondauky;
        this.nhaptrongky = nhaptrongky;
        this.xuattrongky = xuattrongky;
        this.toncuoiky = toncuoiky;
    }

    public int getMasp() {
        return masp;
    }

    public void setMasp(int masp) {
        this.masp = masp;
    }

    public String getTensanpham() {
        return tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }

    public int getTondauky() {
        return tondauky;
    }

    public void setTondauky(int tondauky) {
        this.tondauky = tondauky;
    }

    public int getNhaptrongky() {
        return nhaptrongky;
    }

    public void setNhaptrongky(int nhaptrongky) {
        this.nhaptrongky = nhaptrongky;
    }

    public int getXuattrongky() {
        return xuattrongky;
    }

    public void setXuattrongky(int xuattrongky) {
        this.xuattrongky = xuattrongky;
    }

    public int getToncuoiky() {
        return toncuoiky;
    }

    public void setToncuoiky(int toncuoiky) {
        this.toncuoiky = toncuoiky;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ThongKeTonKhoDTO other = (ThongKeTonKhoDTO) obj;
        return masp == other.masp &&
               tondauky == other.tondauky &&
               nhaptrongky == other.nhaptrongky &&
               xuattrongky == other.xuattrongky &&
               toncuoiky == other.toncuoiky &&
               Objects.equals(tensanpham, other.tensanpham);
    }

    @Override
    public String toString() {
        return "ThongKeTonKhoDTO{" +
                "masp=" + masp +
                ", tensanpham='" + tensanpham + '\'' +
                ", tondauky=" + tondauky +
                ", nhaptrongky=" + nhaptrongky +
                ", xuattrongky=" + xuattrongky +
                ", toncuoiky=" + toncuoiky +
                '}';
    }
}
