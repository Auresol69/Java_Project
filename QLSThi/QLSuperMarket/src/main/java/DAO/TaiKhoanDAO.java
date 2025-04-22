package DAO;

import DTO.TaiKhoanDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TaiKhoanDAO {
    MySQLConnect db = new MySQLConnect();

    public static TaiKhoanDAO getInstance() {
        return new TaiKhoanDAO();
    }

    // Lấy danh sách tất cả tài khoản
    public ArrayList<TaiKhoanDTO> getAllTaiKhoan() {
        ArrayList<TaiKhoanDTO> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM account";
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                TaiKhoanDTO tk = new TaiKhoanDTO(
                    rs.getInt("maaccount"),
                    rs.getInt("mastaff"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getInt("powergroupid"),
                    rs.getBoolean("status")
                );
                list.add(tk);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Thêm tài khoản mới
    public int insert(TaiKhoanDTO t) {
        int result = 0;
        try {
            Connection con = MySQLConnect.getConnection();
            String sql = "INSERT INTO `account`(`maaccount`, `mastaff`, `username`, `password`, `powergroupid`, `status`) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, t.getMaAccount());          // maaccount (int)
            pst.setInt(2, t.getMaStaff());            // mastaff (int)
            pst.setString(3, t.getUsername());        // username
            pst.setString(4, t.getPassword());        // password
            pst.setInt(5, t.getPowerGroupId());       // powergroupid
            pst.setBoolean(6, t.getTrangThai());      // trangthai

            result = pst.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    // Cập nhật tài khoản
    public int updateTaiKhoan(TaiKhoanDTO t) {
        int result = 0;
        try {
            Connection con = MySQLConnect.getConnection();
            String sql = "UPDATE account SET mastaff = ?, password = ?, powergroupid = ?, trangthai = ? WHERE maaccount = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, t.getMaStaff());
            pst.setString(2, t.getPassword());
            pst.setInt(3, t.getPowerGroupId());
            pst.setBoolean(4, t.getTrangThai());
            pst.setInt(5, t.getMaAccount());

            result = pst.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    // Xóa tài khoản theo mã
    public boolean deleteTaiKhoan(int maAccount) {
        String sql = "DELETE FROM account WHERE maaccount = " + maAccount;
        try {
            db.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Tìm tài khoản theo username
    public TaiKhoanDTO findByUsername(String username) {
        TaiKhoanDTO tk = null;
        try {
            String sql = "SELECT * FROM account WHERE username = '" + username + "'";
            ResultSet rs = db.executeQuery(sql);
            if (rs.next()) {
                tk = new TaiKhoanDTO(
                    rs.getInt("maaccount"),
                    rs.getInt("mastaff"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getInt("powergroupid"),
                    rs.getBoolean("status")
                );
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tk;
    }
}
