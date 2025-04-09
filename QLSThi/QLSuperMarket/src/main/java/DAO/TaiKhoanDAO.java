package DAO;

import DTO.TaiKhoanDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TaiKhoanDAO {
    MySQLConnect db = new MySQLConnect();

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
                    rs.getString("email")
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
    public boolean insertTaiKhoan(TaiKhoanDTO tk) {
        String sql = String.format(
            "INSERT INTO account (maaccount, mastaff, username, password, powergroupid, email) " +
            "VALUES ('%s', '%s', '%s', '%s', '%s', '%s')",
            tk.getMaAccount(), tk.getMaStaff(), tk.getUsername(),
            tk.getPassword(), tk.getPowerGroupId(), tk.getEmail()
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
            "UPDATE account SET mastaff='%s', username='%s', password='%s', powergroupid='%s', email='%s' " +
            "WHERE maaccount='%s'",
            tk.getMaStaff(), tk.getUsername(), tk.getPassword(),
            tk.getPowerGroupId(), tk.getEmail(), tk.getMaAccount()
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
                    rs.getString("email")
                );
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tk;
    }
}
