package DAO;

import DTO.NccDTO;
import java.sql.*;
import java.util.ArrayList;

public class NccDAO implements DAOinterface<NccDTO> {

    public static NccDAO getInstance() {
        return new NccDAO();
    }

    @Override
    public int insert(NccDTO t) {
        int result = 0;
        String sql = "INSERT INTO supplier (tencc, diachi, dienthoai, sofax) VALUES (?, ?, ?, ?)";
        MySQLConnect db = new MySQLConnect();

        try {
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, t.getTencc());
            ps.setString(2, t.getDiachi());
            ps.setString(3, t.getDienthoai());
            ps.setString(4, t.getSofax());

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
        String sql = "DELETE FROM supplier WHERE mancc = ?";
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
    public int update(NccDTO t) {
        int result = 0;
        String sql = "UPDATE supplier SET tencc = ?, diachi = ?, dienthoai = ?, sofax = ? WHERE mancc = ?";
        MySQLConnect db = new MySQLConnect();

        try {
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, t.getTencc());
            ps.setString(2, t.getDiachi());
            ps.setString(3, t.getDienthoai());
            ps.setString(4, t.getSofax());
            ps.setInt(5, t.getMancc());

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
    public ArrayList<NccDTO> selectAll() {
        ArrayList<NccDTO> result = new ArrayList<>();
        String sql = "SELECT * FROM supplier";
        MySQLConnect db = new MySQLConnect();

        try {
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int mancc = rs.getInt("mancc");
                String tencc = rs.getString("tencc");
                String diachi = rs.getString("diachi");
                String dienthoai = rs.getString("dienthoai");
                String sofax = rs.getString("sofax");

                result.add(new NccDTO(mancc, tencc, diachi, dienthoai, sofax));
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
    public NccDTO selectedByID(int t) {
        NccDTO result = null;
        String sql = "SELECT * FROM supplier WHERE mancc = ?";
        MySQLConnect db = new MySQLConnect();

        try {
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, t);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int mancc = rs.getInt("mancc");
                String tencc = rs.getString("tencc");
                String diachi = rs.getString("diachi");
                String dienthoai = rs.getString("dienthoai");
                String sofax = rs.getString("sofax");

                result = new NccDTO(mancc, tencc, diachi, dienthoai, sofax);
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
                     "WHERE TABLE_SCHEMA = 'sieuthi_mini' AND TABLE_NAME = 'supplier'";
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