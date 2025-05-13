package GUI.BaoBao.BUS;

import java.util.ArrayList;
import java.util.List;

import GUI.BaoBao.DAO.AccountDAO;
import GUI.BaoBao.DTO.AccountDTO;

public class AccountBUS {
    public ArrayList<AccountDTO> getNhanVien() {
        ArrayList<AccountDTO> accounts = new ArrayList<>();
        List<Object[]> rawAccounts = new AccountDAO().getNhanVien();
        for (Object[] object : rawAccounts) {
            accounts.add(
                    new AccountDTO(Integer.parseInt(object[0].toString()), Integer.parseInt(object[1].toString()),
                            object[2].toString(),
                            object[3].toString(), Integer.parseInt(object[4].toString()),
                            object[6] == null ? "Non email" : object[6].toString(),
                            Boolean.parseBoolean(object[5].toString())));
        }
        return accounts;
    }

}
