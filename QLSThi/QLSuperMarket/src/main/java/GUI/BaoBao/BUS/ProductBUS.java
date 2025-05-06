package GUI.BaoBao.BUS;

import java.util.ArrayList;
import java.util.List;

import GUI.BaoBao.DAO.ProductDAO;
import GUI.BaoBao.DTO.ProductDTO;

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
                data[5].toString());
        return productDTO;
    }

    public List<ProductDTO> getProducts() {
        ArrayList<ProductDTO> productDTOs = new ArrayList<>();
        List<Object[]> data = productDAO.getProducts();
        for (Object[] objects : data) {
            productDTOs.add(new ProductDTO(Integer.parseInt(objects[0].toString()), objects[1].toString(),
                    Integer.parseInt(objects[2].toString()),
                    Integer.parseInt(objects[3].toString()),
                    Integer.parseInt(objects[4].toString()), objects[5].toString()));
        }
        return productDTOs;
    }
}
