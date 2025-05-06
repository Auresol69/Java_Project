package DTO;

public class TaiKhoanDTO {

    private int maAccount;
    private int maStaff;
    private String username;
    private String password;
    private int powerGroupId;
    private boolean trangthai;

    public TaiKhoanDTO() {
    }

    public TaiKhoanDTO(int maAccount, int maStaff, String username, String password, int powerGroupId, boolean trangthai) {
        this.maAccount = maAccount;
        this.maStaff = maStaff;
        this.username = username;
        this.password = password;
        this.powerGroupId = powerGroupId;
        this.trangthai = trangthai;
    }
    public TaiKhoanDTO(int maAccount, int maStaff, String username, String password, int powerGroupId, int tt) {
        this.maAccount = maAccount;
        this.maStaff = maStaff;
        this.username = username;
        this.password = password;
        this.powerGroupId = powerGroupId;
        this.trangthai = (tt != 0);
    }
    public TaiKhoanDTO( int maStaff, String username, String password, int powerGroupId, int tt) {
        this.maStaff = maStaff;
        this.username = username;
        this.password = password;
        this.powerGroupId = powerGroupId;
        this.trangthai = (tt != 0);
    }

    public int getMaAccount() {
        return maAccount;
    }

    public void setMaAccount(int maAccount) {
        this.maAccount = maAccount;
    }

    public int getMaStaff() {
        return maStaff;
    }

    public void setMaStaff(int maStaff) {
        this.maStaff = maStaff;
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

    public int getPowerGroupId() {
        return powerGroupId;
    }

    public void setPowerGroupId(int powerGroupId) {
        this.powerGroupId = powerGroupId;
    }

    public boolean getTrangThai() {
        return trangthai;
    }

    public void setTrangThai(boolean trangthai) {
        this.trangthai = trangthai;
    }
}
