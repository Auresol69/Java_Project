package DTO;

import java.util.Objects;

public class NhaCungCapDTO {
    private int mancc ;
    private String tenncc;
    private String diachi;
    private String sdt;
    private String sofax;
    
    public NhaCungCapDTO(int mancc, String tenncc, String diachi, String sdt, String sofax) {
        this.mancc = mancc;
        this.tenncc = tenncc;
        this.diachi = diachi;
        this.sdt = sdt;
        this.sofax = sofax;
    }

    public NhaCungCapDTO(){}

    public int getMancc() {
        return mancc;
    }

    public void setMancc(int mancc) {
        this.mancc = mancc;
    }

    public String getTenncc() {
        return tenncc;
    }

    public void setTenncc(String tenncc) {
        this.tenncc = tenncc;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getSofax() {
        return sofax;
    }

    public void setSofax(String sofax) {
        this.sofax = sofax;
    }
    @Override
    public int hashCode(){
        int hash = 3;
        hash = 67 * hash + this.mancc;
        hash = 67 * hash + Objects.hashCode(this.tenncc);
        hash = 67 * hash + Objects.hashCode(this.diachi);
        hash = 67 * hash + Objects.hashCode(this.sofax);
        hash = 67 * hash + Objects.hashCode(this.sdt);
        return hash;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NhaCungCapDTO other = (NhaCungCapDTO) obj;
        return true;
    }
    @Override
    public String toString() {
        return "NhaCungCapDTO [mancc=" + mancc + ", tenncc=" + tenncc + ", diachi=" + diachi + ", sdt=" + sdt
                + ", sofax=" + sofax + "]";
    }
    
}
