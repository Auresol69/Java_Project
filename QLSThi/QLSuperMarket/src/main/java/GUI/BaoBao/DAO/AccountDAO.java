package GUI.BaoBao.DAO;

import java.util.List;

public class AccountDAO {
    public List<Object[]> getNhanVien() {
        DataBase db = new DataBase();
        return db.selectQuery("SELECT * FROM account WHERE status = 1");
    }
}
