package GUI.BaoBao.BUS;

import GUI.BaoBao.DAO.ProductTypeDAO;

public class ProductTypeBUS {

    public String getTenLoaiSanPham(int masp) {
        if (masp == -1)
            return null;
        return new ProductTypeDAO().getTenLoaiSanPham(masp).getTenloaisp();
    }

}
