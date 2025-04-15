package DAO;

import DTO.NhanVienDTO;
import java.sql.*;
import java.util.ArrayList;


public class NhanVienDAO implements DAOinterface<NhanVienDTO> {

    public static NhanVienDAO getInstance() {
        return new NhanVienDAO();
    }

    @Override
    public int insert(NhanVienDTO nv) {
        int result = 0;
        String sql = "INSERT INTO staff (tenstaff, dienthoai, address, status) VALUES (?, ?, ?, ?)";
        MySQLConnect db = new MySQLConnect();

        try {
            Connection con = db.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, nv.getHoten());
            pst.setString(2, nv.getSdt());
            pst.setString(3, nv.getAddress());
            pst.setInt(4, nv.getStatus());

            result = pst.executeUpdate();
            pst.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            db.disConnect();
        }

        return result;
    }

    @Override
    public int update(NhanVienDTO nv) {
        int result = 0;
        String sql = "UPDATE staff SET tenstaff=?, dienthoai=?, address=?, status=? WHERE mastaff=?";
        MySQLConnect db = new MySQLConnect();

        try {
            Connection con = db.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, nv.getHoten());
            pst.setString(2, nv.getSdt());
            pst.setString(3, nv.getAddress());
            pst.setInt(4, nv.getStatus());
            pst.setInt(5, nv.getMaNV());

            result = pst.executeUpdate();
            pst.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            db.disConnect();
        }

        return result;
    }

    @Override
    public int delete(String id) {
        int result = 0;
        String sql = "UPDATE staff SET status = -1 WHERE mastaff = ?";
        MySQLConnect db = new MySQLConnect();

        try {
            Connection con = db.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, id);

            result = pst.executeUpdate();
            pst.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            db.disConnect();
        }

        return result;
    }

    @Override
    public ArrayList<NhanVienDTO> selectAll() {
        ArrayList<NhanVienDTO> result = new ArrayList<>();
        String sql = "SELECT * FROM staff WHERE status = 1";
        MySQLConnect db = new MySQLConnect();

        try {
            Connection con = db.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                NhanVienDTO nv = new NhanVienDTO(
                        rs.getInt("mastaff"),
                        rs.getString("tenstaff"),
                        rs.getString("dienthoai"),
                        rs.getString("address"),
                        rs.getInt("status")
                );
                result.add(nv);
            }

            rs.close();
            pst.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.disConnect();
        }

        return result;
    }

    @Override
    public NhanVienDTO selectedByID(int id) {
        NhanVienDTO result = null;
        String sql = "SELECT * FROM staff WHERE mastaff = ?";
        MySQLConnect db = new MySQLConnect();

        try {
            Connection con = db.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                result = new NhanVienDTO(
                        rs.getInt("mastaff"),
                        rs.getString("tenstaff"),
                        rs.getString("dienthoai"),
                        rs.getString("address"),
                        rs.getInt("status")
                );
            }

            rs.close();
            pst.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.disConnect();
        }

        return result;
    }

}
