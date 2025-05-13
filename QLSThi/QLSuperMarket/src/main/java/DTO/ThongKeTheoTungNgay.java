package DTO;

import java.util.Date;

public class ThongKeTheoTungNgay {
    private Date date;
    private int chiphi;
    private int loinhuan;
    private int doanhthu;
    public ThongKeTheoTungNgay(Date date, int chiphi, int doanhthu, int loinhuan) {
        this.date = date;
        this.chiphi = chiphi;
        this.loinhuan = loinhuan;
        this.doanhthu = doanhthu;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
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
        result = prime * result + ((date == null) ? 0 : date.hashCode());
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
        ThongKeTheoTungNgay other = (ThongKeTheoTungNgay) obj;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
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
        return "ThongKeTheoTungNgay [date=" + date + ", chiphi=" + chiphi + ", loinhuan=" + loinhuan + ", doanhthu="
                + doanhthu + "]";
    }

}
