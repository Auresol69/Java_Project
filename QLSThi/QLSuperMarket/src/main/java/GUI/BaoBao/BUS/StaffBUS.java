package GUI.BaoBao.BUS;

import GUI.BaoBao.DAO.StaffDAO;
import GUI.BaoBao.DTO.StaffDTO;

public class StaffBUS {
    public String getStaff(int id) {
        if (id == -1)
            return null;
        StaffDTO staffDTO = new StaffDAO().getStaff(id);
        return staffDTO.getTennhanstaff();
    }
}
