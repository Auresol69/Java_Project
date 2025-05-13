package GUI.BaoBao.DTO;
public class BillProductDTO {
    private int mabill;
    private Integer soluong;
    private int masp;

    public int getMasp() {
        return masp;
    }

    public void setMasp(int masp) {
        this.masp = masp;
    }

    public int getMabill() {
        return mabill;
    }

    public void setMabill(int mabill) {
        this.mabill = mabill;
    }

    public Integer getSoluong() {
        return soluong;
    }

    public void setSoluong(Integer soluong) {
        this.soluong = soluong;
    }

    public BillProductDTO(int masp, int mabill, Integer soluong) {
        this.masp = masp;
        this.mabill = mabill;
        this.soluong = soluong;
    }

    public BillProductDTO() {
    }

}