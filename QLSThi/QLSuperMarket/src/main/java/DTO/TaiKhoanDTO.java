package DTO;

public class TaiKhoanDTO {
    private String maAccount;
    private String maStaff;
    private String username;
    private String password;
    private int powerGroupId;
    private String email;
    public TaiKhoanDTO() {
    }

    public TaiKhoanDTO(String maAccount, String maStaff, String username, String password, int powerGroupId, String email) {
        this.maAccount = maAccount;
        this.maStaff = maStaff;
        this.username = username;
        this.password = password;
        this.powerGroupId = powerGroupId;
        this.email = email;
    }

    public String getMaAccount() {
        return maAccount;
    }

    public void setMaAccount(String maAccount) {
        this.maAccount = maAccount;
    }

    public String getMaStaff() {
        return maStaff;
    }

    public void setMaStaff(String maStaff) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
