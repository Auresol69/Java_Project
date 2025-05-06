package GUI.BaoBao.DAO;
 public class BillDAO {
     DataBase db = new DataBase();
 
     public Integer createBill(Integer macus, Integer maPay) {
         return db.insertQuery("INSERT INTO bill(macustomer, mapayby) VALUES (?, ?)", macus, maPay);
     }
 }