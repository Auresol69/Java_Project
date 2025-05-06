package GUI.BaoBao.DAO;

import java.util.List;

import GUI.BaoBao.DTO.StaffDTO;

public class StaffDAO {
    private DataBase db = new DataBase();

    public StaffDTO getStaff(int id) {
        List<Object[]> data = db.selectQuery("SELECT * FROM staff WHERE mastaff = ? ", id);
        if (data.isEmpty())
            return null;
        Object[] row = data.get(0);
        return new StaffDTO(Integer.parseInt(row[0].toString()), row[1].toString(), row[2].toString(),
                row[3].toString());
    }
}
