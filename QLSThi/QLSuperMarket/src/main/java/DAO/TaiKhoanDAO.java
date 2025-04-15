package DAO;

import DTO.TaiKhoanDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
public class TaiKhoanDAO {
    MySQLConnect db = new MySQLConnect();

    public static TaiKhoanDAO getInstance(){
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
                    rs.getString("maaccount"),
                    rs.getString("mastaff"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getInt("powergroupid"),
                    rs.getBoolean("trangthai") // Thêm dòng này
                );
                list.add(tk);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public static TaiKhoanDAO getInstance(){
        return new TaiKhoanDAO();
    }
    // Thêm tài khoản mới
    public boolean insertTaiKhoan(TaiKhoanDTO tk) {
        String sql = String.format(
            "INSERT INTO account (maaccount, mastaff, username, password, powergroupid, email, trangthai) " +
            "VALUES ('%s', '%s', '%s', '%s', '%s', '%s', %b)",
            tk.getMaAccount(), tk.getMaStaff(), tk.getUsername(),
            tk.getPassword(), tk.getPowerGroupId(), tk.getTrangThai()
        );
        try {
            db.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Cập nhật tài khoản
    public boolean updateTaiKhoan(TaiKhoanDTO tk) {
        String sql = String.format(
            "UPDATE account SET mastaff='%s', username='%s', password='%s', powergroupid='%s', email='%s', trangthai=%b " +
            "WHERE maaccount='%s'",
            tk.getMaStaff(), tk.getUsername(), tk.getPassword(),
            tk.getPowerGroupId(), tk.getTrangThai(), tk.getMaAccount()
        );
        try {
            db.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Xóa tài khoản theo mã
    public boolean deleteTaiKhoan(String maAccount) {
        String sql = "DELETE FROM account WHERE maaccount='" + maAccount + "'";
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
            String sql = "SELECT * FROM account WHERE username='" + username + "'";
            ResultSet rs = db.executeQuery(sql);
            if (rs.next()) {
                tk = new TaiKhoanDTO(
                    rs.getString("maaccount"),
                    rs.getString("mastaff"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getInt("powergroupid"),
                    rs.getBoolean("trangthai") // Thêm dòng này
                );
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tk;
    }
    public int insert(TaiKhoanDTO t) {
        int result = 0;
        try {
            Connection con = MySQLConnect.getConnection();
            String sql = "INSERT INTO `account`(`maaccount`, `mastaff`, `username`, `password`, `powergroupid`, `trangthai`) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getMaAccount());                          // maaccount (String)
            pst.setString(2, t.getMaStaff());                            // mastaff (String)
            pst.setString(3, t.getUsername());                           // username (String)
            pst.setString(4, t.getPassword());                           // password (String)
            pst.setInt(5, t.getPowerGroupId());                          // powergroupid (int)
            pst.setBoolean(6, t.getTrangThai());                         // trangthai (boolean)
    
            result = pst.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
}
