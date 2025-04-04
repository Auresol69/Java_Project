package com.sieuthimini.DTO;

public class Customer {
    private String macustomer, address, phone, name;

    public String getMacustomer() {
        return macustomer;
    }

    public void setMacustomer(String macustomer) {
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

    public Customer(String macustomer, String address, String phone, String name) {
        this.macustomer = macustomer;
        this.address = address;
        this.phone = phone;
        this.name = name;
    }

    public Customer() {
    }

}
