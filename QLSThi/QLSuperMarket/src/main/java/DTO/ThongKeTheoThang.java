package DTO;

public class ThongKeTheoThang {
    private int thang;
    private int chiphi;
    private int loinhuan;
    private int doanhthu;
    public ThongKeTheoThang(int thang, int chiphi, int doanhthu, int loinhuan) {
        this.thang = thang;
        this.chiphi = chiphi;
        this.loinhuan = loinhuan;
        this.doanhthu = doanhthu;
    }
    public int getThang() {
        return thang;
    }
    public void setThang(int thang) {
        this.thang = thang;
    }
    public int getChiphi() {
        return chiphi;
    }
    public void setChiphi(int chiphi) {
        this.chiphi = chiphi;
    }
    public int getLoinhuan() {
        return loinhuan;
    }
    public void setLoinhuan(int loinhuan) {
        this.loinhuan = loinhuan;
    }
    public int getDoanhthu() {
        return doanhthu;
    }
    public void setDoanhthu(int doanhthu) {
        this.doanhthu = doanhthu;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + thang;
        result = prime * result + chiphi;
        result = prime * result + loinhuan;
        result = prime * result + doanhthu;
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ThongKeTheoThang other = (ThongKeTheoThang) obj;
        if (thang != other.thang)
            return false;
        if (chiphi != other.chiphi)
            return false;
        if (loinhuan != other.loinhuan)
            return false;
        if (doanhthu != other.doanhthu)
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "ThongKeTheoThang [thang=" + thang + ", chiphi=" + chiphi + ", loinhuan=" + loinhuan + ", doanhthu="
                + doanhthu + "]";
    }
}
