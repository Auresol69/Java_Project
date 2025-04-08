package com.sieuthimini.DAO;

import java.util.ArrayList;
import java.util.List;

import com.sieuthimini.DTO.ProductDTO;

public class DAOTimkiemSanPham {
    private DataBase db;

    public DAOTimkiemSanPham() {
        db = new DataBase();
    }

    public List<ProductDTO> searchSanPham(String keyword) {
        String sql = "SELECT * FROM product WHERE masp LIKE ? OR tensp LIKE ?";
        String wildcardKeyword = "%" + keyword + "%";
        List<Object[]> rawData = db.selectQuery(sql, wildcardKeyword, wildcardKeyword);
        List<ProductDTO> result = new ArrayList<>();
        for (Object[] row : rawData) {
            result.add(new ProductDTO(
                    row[0].toString(),
                    row[1].toString(),
                    row[2].toString(),
                    row[3].toString(),
                    row[4].toString(),
                    Integer.parseInt(row[5].toString()),
                    Integer.parseInt(row[6].toString())));
        }
        return result;
    }
}
