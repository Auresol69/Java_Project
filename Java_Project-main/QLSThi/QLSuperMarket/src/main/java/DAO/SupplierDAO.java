package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierDAO {
    private MySQLConnect mySQL = new MySQLConnect();

    public boolean exists(String mancc) {
        try {
            String sql = "SELECT * FROM supplier WHERE mancc = ?";
            PreparedStatement ps = mySQL.getConnection().prepareStatement(sql);
            ps.setString(1, mancc);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // Trả về true nếu mã nhà cung cấp tồn tại
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false; // Trả về false nếu có lỗi hoặc không tìm thấy
    }
}