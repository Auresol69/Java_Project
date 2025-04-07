package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
        } catch (SQLException ex) {
            ex.printStackTrace(); // In lỗi nếu có
        }
        
        return result; // Trả về số bản ghi bị ảnh hưởng
    }

    @Override
    public int delete(KhachHangDTO t) {
        int result = 0;
        String sql = "INSERT INTO customer (address, phone, name) VALUES (?, ?, ?)"; // Giả sử bảng 'khachhang' có các trường 'ten', 'diachi', 'sdt'
        
        try (Connection con = MySQLConnect.getConnection(); // Lấy kết nối
             PreparedStatement ps = con.prepareStatement(sql)) {
             
            // Thiết lập tham số cho PreparedStatement
            ps.setString(1, t.getAddress());
            ps.setString(2, t.getSdt());
            ps.setString(3, t.getHoten());
            
            result = ps.executeUpdate(); // Thực thi câu lệnh insert
        } catch (SQLException ex) {
            ex.printStackTrace(); // In lỗi nếu có
        }
        
        return result; // Trả về số bản ghi bị ảnh hưởng
    }

    @Override
    public int update(KhachHangDTO t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public ArrayList<KhachHangDTO> selectALl() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectALl'");
    }

    @Override
    public KhachHangDTO selectedByID(String t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectedByID'");
    }

    @Override
    public int getAutoIncrement() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAutoIncrement'");
    }
    
}
