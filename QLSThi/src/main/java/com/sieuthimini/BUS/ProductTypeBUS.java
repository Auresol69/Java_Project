package com.sieuthimini.BUS;

import com.sieuthimini.DAO.ProductTypeDAO;

public class ProductTypeBUS {

    public String getTenLoaiSanPham(int masp) {
        if (masp == -1)
            return null;
        return new ProductTypeDAO().getTenLoaiSanPham(masp).getTenloaisp();
    }

}
