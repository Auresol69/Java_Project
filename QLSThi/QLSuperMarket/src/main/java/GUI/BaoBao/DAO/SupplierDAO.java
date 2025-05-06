package GUI.BaoBao.DAO;

import java.util.List;

public class SupplierDAO {
    public List<Object[]> getNhaCungCap() {
        DataBase db = new DataBase();
        return db.selectQuery("SELECT * FROM supplier");
    }
}
