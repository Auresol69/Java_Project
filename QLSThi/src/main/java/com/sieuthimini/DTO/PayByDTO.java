package com.sieuthimini.DTO;

public class PayByDTO {
    String mapayby, paybyname, address, detail;

    public String getMapayby() {
        return mapayby;
    }

    public void setMapayby(String mapayby) {
        this.mapayby = mapayby;
    }

    public String getPaybyname() {
        return paybyname;
    }

    public void setPaybyname(String paybyname) {
        this.paybyname = paybyname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public PayByDTO(String mapayby, String paybyname, String address, String detail) {
        this.mapayby = mapayby;
        this.paybyname = paybyname;
        this.address = address;
        this.detail = detail;
    }

    public PayByDTO() {
    }

}
