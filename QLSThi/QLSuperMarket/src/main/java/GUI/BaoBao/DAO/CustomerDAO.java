package GUI.BaoBao.DAO;

import java.util.List;

import  GUI.BaoBao.DTO.CustomerDTO;

public class CustomerDAO {
    DataBase db = new DataBase();

    public CustomerDTO getCustomerByPhone(String phonenumber) {

        List<Object[]> data = db.selectQuery("SELECT * FROM customer WHERE phone = ?", phonenumber);
        if (data.isEmpty())
            return null;
        Object[] rawData = data.get(0);
        return new CustomerDTO(Integer.parseInt(rawData[0].toString()), rawData[1].toString(), rawData[2].toString(),
                rawData[3].toString());
    }

    public int themCustomer(String nameCus, String phoneCus, String addressCus) {
        return db.insertQuery("INSERT INTO customer(name,phone,address) VALUES (?,?,?)", nameCus, phoneCus, addressCus);
    }

    public CustomerDTO getCustomerByID(int id) {
        List<Object[]> data = db.selectQuery("SELECT * FROM customer WHERE macustomer = ?", id);
        if (data.isEmpty()) {
            return null;
        }
        Object[] rawData = data.get(0);
        return new CustomerDTO(Integer.parseInt(rawData[0].toString()), rawData[1].toString(), rawData[2].toString(),
                rawData[3].toString());
    }
}