package DAO;

import DTO.NhomQuyenDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NhomQuyenDAO implements DAOinterface<NhomQuyenDTO> {

    public static NhomQuyenDAO getInstance() {
        return new NhomQuyenDAO();
    }

    @Override
    public int insert(NhomQuyenDTO t) {
        int result = 0;
        String sql = "INSERT INTO powergroup (powergroupname) VALUES (?)";
        MySQLConnect db = new MySQLConnect();

        try {
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, t.getTenNhomQuyen());
            result = ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            db.disConnect();
        }

        return result;
    }

    @Override
    public int delete(int t) {
        int result = 0;
        String sql = "UPDATE powergroup SET status = 0 WHERE powergroupid = ?";
        MySQLConnect db = new MySQLConnect();
        try {
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, t);
            result = ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            db.disConnect();
        }

        return result;
    }

    @Override
    public int update(NhomQuyenDTO t) {
        int result = 0;
        String sql = "UPDATE powergroup SET powergroupname = ?, last_updated = NOW() WHERE powergroupid = ?";
        MySQLConnect db = new MySQLConnect();

        try {
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, t.getTenNhomQuyen());
            ps.setInt(2, t.getMaNhomQuyen());

            result = ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            db.disConnect();
        }

        return result;
    }

    @Override
    public ArrayList<NhomQuyenDTO> selectAll() {
        ArrayList<NhomQuyenDTO> result = new ArrayList<>();
        String sql = "SELECT * FROM powergroup WHERE status = 1";
        MySQLConnect db = new MySQLConnect();

        try {
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("powergroupid");
                String name = rs.getString("powergroupname");

                result.add(new NhomQuyenDTO(id, name));
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            db.disConnect();
        }

        return result;
    }

    @Override
    public NhomQuyenDTO selectedByID(int t) {
        NhomQuyenDTO result = null;
        String sql = "SELECT * FROM powergroup WHERE powergroupid = ?";
        MySQLConnect db = new MySQLConnect();

        try {
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, t);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("powergroupid");
                String name = rs.getString("powergroupname");

                result = new NhomQuyenDTO(id, name);
            }

            rs.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            db.disConnect();
        }

        return result;
    }

    @Override
    public int getAutoIncrement() {
        int result = -1;
        String sql = "SELECT AUTO_INCREMENT FROM INFORMATION_SCHEMA.TABLES " +
                     "WHERE TABLE_NAME = 'powergroup' AND TABLE_SCHEMA = DATABASE()";
        Connection con = MySQLConnect.getConnection();
    
        if (con == null) {
            System.err.println("❌ Không thể kết nối đến cơ sở dữ liệu.");
            return result;
        }
    
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
    
            if (rs.next()) {
                result = rs.getInt("AUTO_INCREMENT");
                System.out.println("✅ AUTO_INCREMENT hiện tại là: " + result);
            } else {
                System.err.println("⚠️ Không tìm thấy bảng powergroup trong schema hiện tại.");
            }
    
            rs.close();
            pst.close();
            con.close(); // đóng kết nối sau khi dùng xong
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    
        return result;
    }
    
}
