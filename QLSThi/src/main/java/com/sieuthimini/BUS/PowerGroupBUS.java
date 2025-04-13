package com.sieuthimini.BUS;

import com.sieuthimini.DAO.Func;
import com.sieuthimini.DAO.PowerGroupDAO;

public class PowerGroupBUS {
    private final PowerGroupDAO powerGroupDAO = new PowerGroupDAO();
    private final Func funcDAO = new Func();
    // private final ArrayList<PowerGroupDTO> listPowerGroupDTO;

    public PowerGroupDAO getPowerGroupDAO() {
        return powerGroupDAO;
    }

    public Func getFuncDAO() {
        return funcDAO;
    }

    // public ArrayList<PowerGroupDTO> getListPowerGroupDTO() {
    // return listPowerGroupDTO;
    // }

}
