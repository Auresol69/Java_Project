package DAO;

import DTO.LoaiDTO;
import java.sql.*;
import java.util.ArrayList;

public class LoaiDAO implements DAOinterface<LoaiDTO> {

    public static LoaiDAO getInstance() {
        return new LoaiDAO();
    }

    @Override
    public int insert(LoaiDTO t) {
        int result = 0;
        String sql = "INSERT INTO producttype (tenloaisp) VALUES (?)";
        MySQLConnect db = new MySQLConnect();

        try {
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, t.getTenloaisp());

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
        String sql = "DELETE FROM producttype WHERE maloaisp = ?";
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
    public int update(LoaiDTO t) {
        int result = 0;
        String sql = "UPDATE producttype SET tenloaisp = ? WHERE maloaisp = ?";
        MySQLConnect db = new MySQLConnect();

        try {
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, t.getTenloaisp());
            ps.setInt(2, t.getMaloaisp());

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
    public ArrayList<LoaiDTO> selectAll() {
        ArrayList<LoaiDTO> result = new ArrayList<>();
        String sql = "SELECT * FROM producttype";
        MySQLConnect db = new MySQLConnect();

        try {
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int maloaisp = rs.getInt("maloaisp");
                String tenloaisp = rs.getString("tenloaisp");

                result.add(new LoaiDTO(maloaisp, tenloaisp));
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
    public LoaiDTO selectedByID(int t) {
        LoaiDTO result = null;
        String sql = "SELECT * FROM producttype WHERE maloaisp = ?";
        MySQLConnect db = new MySQLConnect();

        try {
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, t);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int maloaisp = rs.getInt("maloaisp");
                String tenloaisp = rs.getString("tenloaisp");

                result = new LoaiDTO(maloaisp, tenloaisp);
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
                     "WHERE TABLE_SCHEMA = 'sieuthi_mini' AND TABLE_NAME = 'producttype'";
        MySQLConnect db = new MySQLConnect();

        try {
            Connection con = db.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                result = rs.getInt("AUTO_INCREMENT");
            }

            rs.close();
            pst.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            db.disConnect();
        }

        return result;
    }
}