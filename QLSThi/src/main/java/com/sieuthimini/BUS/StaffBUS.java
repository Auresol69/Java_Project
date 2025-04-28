package com.sieuthimini.BUS;

import com.sieuthimini.DAO.StaffDAO;
import com.sieuthimini.DTO.StaffDTO;

public class StaffBUS {
    public String getStaff(int id) {
        if (id == -1)
            return null;
        StaffDTO staffDTO = new StaffDAO().getStaff(id);
        return staffDTO.getTennhanstaff();
    }
}
