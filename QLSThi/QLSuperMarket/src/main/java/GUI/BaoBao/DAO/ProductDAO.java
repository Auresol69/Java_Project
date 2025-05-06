package GUI.BaoBao.DAO;

import java.util.List;

import GUI.BaoBao.DTO.ProductDTO;

public class ProductDAO {

    public List<Object[]> getProductByInfo(int masp, String tensp, int maloaisp) {
        DataBase db = new DataBase();
        return db.selectQuery("SELECT * FROM product WHERE masp = ? AND tensp = ? AND maloaisp = ?",
                masp, tensp, maloaisp);
    }

    public ProductDTO getProductById(int masp) {
        DataBase db = new DataBase();
        List<Object[]> data = db.selectQuery("SELECT * FROM product WHERE masp = ?", masp);
        if (!data.isEmpty()) {
            Object[] obj = data.get(0);
            return new ProductDTO(Integer.parseInt(obj[0].toString()), obj[1].toString(),
                    Integer.parseInt(obj[2].toString()),
                    Integer.parseInt(obj[3].toString()), Integer.parseInt(obj[4].toString()), obj[5].toString());
        }
        return null;
    }

    public Object[] getTenSanPham(int id) {
        DataBase db = new DataBase();
        List<Object[]> data = db.selectQuery("SELECT * FROM product WHERE masp = ?", id);
        if (data.isEmpty()) {
            return null;
        }
        return data.get(0);
    }

    public List<Object[]> getProducts() {
        DataBase db = new DataBase();
        return db.selectQuery("SELECT * FROM product");
    }

    // public void insertProduct(Object[] row) {
    // DataBase db = new DataBase();
    // db.executeQuery("INSERT INTO product (...) VALUES (?, ?, ...)", row);
    // }
}