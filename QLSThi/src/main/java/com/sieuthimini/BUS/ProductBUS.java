package com.sieuthimini.BUS;

import java.util.List;

import com.sieuthimini.DAO.ProductDAO;
import com.sieuthimini.DTO.ProductDTO;

public class ProductBUS {

    private ProductDAO productDAO = new ProductDAO();

    public List<Object[]> checkSanPham(int masp, String tensp, int maloaisp) {
        return productDAO.getProductByInfo(masp, tensp, maloaisp);
    }

    public ProductDTO getTenSanPham(int id) {
        if (id == -1) {
            return null;
        }
        Object[] data = new ProductDAO().getTenSanPham(id);

        ProductDTO productDTO = new ProductDTO(Integer.parseInt(data[0].toString()), data[1].toString(),
                Integer.parseInt(data[2].toString()),
                Integer.parseInt(data[3].toString()), Integer.parseInt(data[4].toString()),
                Integer.parseInt(data[5].toString()),
                data[6].toString());
        return productDTO;
    }
}
