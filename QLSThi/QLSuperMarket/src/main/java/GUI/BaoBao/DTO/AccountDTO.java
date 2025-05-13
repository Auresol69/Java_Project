package GUI.BaoBao.DTO;

import GUI.BaoBao.BUS.StaffBUS;

public class AccountDTO {
    private String username, password, email;
    private boolean status;
    private int maaccount, mastaff, powergroupid;

    public int getMaaccount() {
        return maaccount;
    }

    public void setMaaccount(int maaccount) {
        this.maaccount = maaccount;
    }

    public int getMastaff() {
        return mastaff;
    }

    public void setMastaff(int mastaff) {
        this.mastaff = mastaff;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPowergroupid() {
        return powergroupid;
    }

    public void setPowergroupid(int powergroupid) {
        this.powergroupid = powergroupid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public AccountDTO(int maaccount, int mastaff, String username, String password, int powergroupid,
            String email, boolean status) {
        this.maaccount = maaccount;
        this.mastaff = mastaff;
        this.username = username;
        this.password = password;
        this.powergroupid = powergroupid;
        this.email = email;
        this.status = status;
    }

    public AccountDTO() {

    }

    public String getTenNhanVien(int id) {
        return new StaffBUS().getStaff(id);
    }

    @Override
    public String toString() {
        return (getMastaff() + "-" + getTenNhanVien(this.mastaff));
    }
}