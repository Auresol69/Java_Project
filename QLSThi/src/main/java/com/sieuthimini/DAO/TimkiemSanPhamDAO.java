package com.sieuthimini.DAO;

import java.util.ArrayList;
import java.util.List;

import com.sieuthimini.DTO.ProductDTO;

public class TimkiemSanPhamDAO {
    private DataBase db;

    public TimkiemSanPhamDAO() {
        db = new DataBase();
    }

    public List<ProductDTO> searchSanPham(String keyword) {
        String sql = "SELECT * FROM product WHERE masp LIKE ? OR tensp LIKE ?";
        String wildcardKeyword = "%" + keyword + "%";
        List<Object[]> rawData = db.selectQuery(sql, wildcardKeyword, wildcardKeyword);
        List<ProductDTO> result = new ArrayList<>();
        for (Object[] row : rawData) {
            result.add(new ProductDTO(
                    Integer.parseInt(row[0].toString()),
                    row[1].toString(),
                    Integer.parseInt(row[2].toString()),
                    Integer.parseInt(row[3].toString()),
                    Integer.parseInt(row[4].toString()),
                    Integer.parseInt(row[5].toString()),
                    row[6].toString()));
        }
        return result;
    }
}
