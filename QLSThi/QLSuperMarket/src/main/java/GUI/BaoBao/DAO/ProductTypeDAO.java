package GUI.BaoBao.DAO;

import java.util.List;

import GUI.BaoBao.DTO.ProductTypeDTO;

public class ProductTypeDAO {
    private DataBase db = new DataBase();

    public ProductTypeDTO getTenLoaiSanPham(int masp) {
        List<Object[]> data = db.selectQuery(
                "SELECT * FROM producttype RIGHT JOIN product ON producttype.maloaisp = product.maloaisp WHERE masp = ? ",
                masp);
        if (data.isEmpty())
            return null;
        Object[] row = data.get(0);
        return new ProductTypeDTO(Integer.parseInt(row[0].toString()), row[1].toString());
    }

}
