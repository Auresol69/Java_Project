package GUI.BaoBao.DAO;

import java.util.List;

import GUI.BaoBao.DTO.BillDTO;

public class BillDAO {
    DataBase db = new DataBase();

    public Integer createBill(Integer macus, Integer maPay) {
        return db.insertQuery("INSERT INTO bill(macustomer, mapayby) VALUES (?, ?)", macus, maPay);
    }

    public BillDTO getBillByID(int id) {

        List<Object[]> data = db.selectQuery("SELECT * FROM bill WHERE mabill = ?", id);
        if (!data.isEmpty()) {
            Object[] obj = data.get(0);
            return new BillDTO(Integer.parseInt(obj[0].toString()), Integer.parseInt(obj[2].toString()),
                    Integer.parseInt(obj[1].toString()),
                    obj[3].toString(), Integer.parseInt(obj[4].toString()));
        }
        return null;
    }
}