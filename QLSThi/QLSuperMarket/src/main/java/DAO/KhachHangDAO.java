package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import DTO.KhachHangDTO;
public class KhachHangDAO implements DAOinterface<KhachHangDTO>{

    public static KhachHangDAO getInstance() {
        return new KhachHangDAO();
    }

    @Override
    public int insert(KhachHangDTO t) {
        int result = 0;
        String sql = "INSERT INTO customer (address, phone, name) VALUES (?, ?, ?)"; // Giả sử bảng 'khachhang' có các trường 'ten', 'diachi', 'sdt'
        
        try (Connection con = MySQLConnect.getConnection(); // Lấy kết nối
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            // Thiết lập tham số cho PreparedStatement
            ps.setString(1, t.getAddress());
            ps.setString(2, t.getSdt());
            ps.setString(3, t.getHoten());
            
            result = ps.executeUpdate(); // Thực thi câu lệnh insert
            MySQLConnect.closeConnection(con);
        } catch (SQLException ex) {
            ex.printStackTrace(); // In lỗi nếu có
        }
        
        return result; // Trả về số bản ghi bị ảnh hưởng
    }

    @Override
    public int delete(String t) {
        int result = 0;
        String sql = "UPDATE customer SET status = 0 where macustomer = ?"; // Giả sử bảng 'khachhang' có các trường 'ten', 'diachi', 'sdt'
        
        try (Connection con = MySQLConnect.getConnection(); // Lấy kết nối
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            // Thiết lập tham số cho PreparedStatement
            ps.setString(1, t);
            
            result = ps.executeUpdate(); 
            MySQLConnect.closeConnection(con);
        } catch (SQLException ex) {
            ex.printStackTrace(); // In lỗi nếu có
        }
        
        return result; // Trả về số bản ghi bị ảnh hưởng
    }

    @Override
    public int update(KhachHangDTO t) {
        int result = 0;
        String sql = "UPDATE customer SET address = ?, phone = ?, name = ? WHERE macustomer = ?"; // Giả sử bảng 'khachhang' có các trường 'ten', 'diachi', 'sdt'
        
        try (Connection con = MySQLConnect.getConnection(); // Lấy kết nối
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            // Thiết lập tham số cho PreparedStatement
            ps.setString(1, t.getAddress());
            ps.setString(2, t.getSdt());
            ps.setString(3, t.getHoten());
            ps.setInt(4, t.getMaKH());
            
            result = ps.executeUpdate(); // Thực thi câu lệnh insert
            MySQLConnect.closeConnection(con);
        } catch (SQLException ex) {
            ex.printStackTrace(); // In lỗi nếu có
        }
        
        return result; // Trả về số bản ghi bị ảnh hưởng
    }

    @Override
    public ArrayList<KhachHangDTO> selectALl() {
        ArrayList<KhachHangDTO> result = new ArrayList<>();
        String sql = "SELECT * FROM customer WHERE status = 1"; // Chọn khách hàng còn hoạt động
        
        try (Connection con = MySQLConnect.getConnection(); // Lấy kết nối
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
                
            while (rs.next()) {
                int macustomer = rs.getInt("macustomer");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                String name = rs.getString("name");
                KhachHangDTO kh = new KhachHangDTO(macustomer, address, phone, name);
                result.add(kh);
            }
            MySQLConnect.closeConnection(con);
        } catch (SQLException ex) {
            ex.printStackTrace(); // In lỗi nếu có
        }
        
        return result; // Trả về danh sách khách hàng
    }

    @Override
    public KhachHangDTO selectedByID(String t) {
        KhachHangDTO result = null;
        String sql = "SELECT * FROM customer WHERE macustomer = ?";

        try (Connection con = MySQLConnect.getConnection(); // Lấy kết nối
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
            
           // Thiết lập tham số cho PreparedStatement
           ps.setString(1, t);
           
           while (rs.next()) {
            int macustomer = rs.getInt("macustomer");
            String address = rs.getString("address");
            String phone = rs.getString("phone");
            String name = rs.getString("name");
            result = new KhachHangDTO(macustomer, address, phone, name);
            }
        MySQLConnect.closeConnection(con);
        } catch (SQLException ex) {
            ex.printStackTrace(); // In lỗi nếu có
        }
        return result;
    }
    
}
