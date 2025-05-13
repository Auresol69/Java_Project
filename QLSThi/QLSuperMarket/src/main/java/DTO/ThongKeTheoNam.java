package DTO;

public class ThongKeTheoNam {
    private int nam;
    private int chiphi;
    private int loinhuan;
    private int doanhthu;
    public ThongKeTheoNam(int nam, int chiphi, int loinhuan, int doanhthu) {
        this.nam = nam;
        this.chiphi = chiphi;
        this.loinhuan = loinhuan;
        this.doanhthu = doanhthu;
    }
    public int getNam() {
        return nam;
    }
    public void setNam(int nam) {
        this.nam = nam;
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
        result = prime * result + nam;
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
        ThongKeTheoNam other = (ThongKeTheoNam) obj;
        if (nam != other.nam)
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
        return "ThongKeTheoNam [nam=" + nam + ", chiphi=" + chiphi + ", loinhuan=" + loinhuan + ", doanhthu=" + doanhthu
                + "]";
    }
    
}
