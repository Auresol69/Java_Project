package com.sieuthimini.BUS;

import java.util.ArrayList;
import java.util.List;

import com.sieuthimini.DAO.SupplierDAO;
import com.sieuthimini.DTO.SupplierDTO;

public class SupplierBUS {

    public List<SupplierDTO> getNhaCungCap() {
        List<Object[]> data = new SupplierDAO().getNhaCungCap();
        ArrayList<SupplierDTO> aSupplierDTOs = new ArrayList<>();
        for (Object[] object : data) {
            aSupplierDTOs.add(new SupplierDTO(object[0].toString(), object[1].toString(), object[2].toString(),
                    object[3].toString(), object[4].toString()));
        }
        return aSupplierDTOs;
    }
}
