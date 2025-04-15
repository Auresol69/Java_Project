package com.sieuthimini.BUS;

import java.util.ArrayList;
import java.util.List;

import com.sieuthimini.DAO.AccountDAO;
import com.sieuthimini.DTO.AccountDTO;

public class AccountBUS {
    public ArrayList<AccountDTO> getNhanVien() {
        ArrayList<AccountDTO> accounts = new ArrayList<>();
        List<Object[]> rawAccounts = new AccountDAO().getNhanVien();
        for (Object[] object : rawAccounts) {
            accounts.add(
                    new AccountDTO(Integer.parseInt(object[0].toString()), object[1].toString(), object[2].toString(),
                            object[3].toString(), object[4].toString(), object[5].toString(),
                            Boolean.parseBoolean(object[6].toString())));
        }
        return accounts;
    }

}
