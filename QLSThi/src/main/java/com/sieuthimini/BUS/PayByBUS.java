package com.sieuthimini.BUS;

import com.sieuthimini.DAO.PayByDAO;

public class PayByBUS {
    public Integer createPayBy(String name, String details) {
        if (!name.isEmpty() && !details.isEmpty()) {
            return new PayByDAO().createPayBy(name, details);
        }
        return null;
    }
}
