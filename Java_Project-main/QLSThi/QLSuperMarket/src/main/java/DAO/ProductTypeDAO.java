package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductTypeDAO {
    private MySQLConnect mySQL = new MySQLConnect();

    public boolean exists(String maloai) {
        try {
            String sql = "SELECT * FROM producttype WHERE maloaisp = ?";
            PreparedStatement ps = mySQL.getConnection().prepareStatement(sql);
            ps.setString(1, maloai);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // Trả về true nếu mã loại tồn tại
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false; // Trả về false nếu có lỗi hoặc không tìm thấy
    }
}