package com.sieuthimini.DTO;

public class AccountDTO {
    private String maaccount, mastaff, username, password, powergroupid, email;
    private int status;

    public String getMaaccount() {
        return maaccount;
    }

    public void setMaaccount(String maaccount) {
        this.maaccount = maaccount;
    }

    public String getMastaff() {
        return mastaff;
    }

    public void setMastaff(String mastaff) {
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

    public String getPowergroupid() {
        return powergroupid;
    }

    public void setPowergroupid(String powergroupid) {
        this.powergroupid = powergroupid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public AccountDTO(String maaccount, String mastaff, String username, String password, String powergroupid,
            String email, int status) {
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

    @Override
    public String toString() {
        return this.mastaff;
    }
}