package com.sieuthimini.DTO;

public class CustomerDTO {
    private String address, phone, name;
    private int macustomer;

    public int getMacustomer() {
        return macustomer;
    }

    public void setMacustomer(int macustomer) {
        this.macustomer = macustomer;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CustomerDTO(int macustomer, String address, String phone, String name) {
        this.macustomer = macustomer;
        this.address = address;
        this.phone = phone;
        this.name = name;
    }

    public CustomerDTO() {
    }

}
