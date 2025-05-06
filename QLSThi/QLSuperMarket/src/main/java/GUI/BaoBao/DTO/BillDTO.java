package GUI.BaoBao.DTO;

public class BillDTO {
    private String mapayby, macustomer, ngaymua;
    private Integer tongtien;
    private int mabill;

    public int getMabill() {
        return mabill;
    }

    public void setMabill(int mabill) {
        this.mabill = mabill;
    }

    public String getMapayby() {
        return mapayby;
    }

    public void setMapayby(String mapayby) {
        this.mapayby = mapayby;
    }

    public String getMacustomer() {
        return macustomer;
    }

    public void setMacustomer(String macustomer) {
        this.macustomer = macustomer;
    }

    public String getNgaymua() {
        return ngaymua;
    }

    public void setNgaymua(String ngaymua) {
        this.ngaymua = ngaymua;
    }

    public Integer getTongtien() {
        return tongtien;
    }

    public void setTongtien(Integer tongtien) {
        this.tongtien = tongtien;
    }

    public BillDTO(int mabill, String mapayby, String macustomer, String ngaymua, Integer tongtien) {
        this.mabill = mabill;
        this.mapayby = mapayby;
        this.macustomer = macustomer;
        this.ngaymua = ngaymua;
        this.tongtien = tongtien;
    }

    public BillDTO() {
    }

}
