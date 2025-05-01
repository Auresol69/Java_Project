package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import DTO.KhachHangDTO;
public class KhachHangDAO implements DAOinterface<KhachHangDTO> {

    public static KhachHangDAO getInstance() {
        return new KhachHangDAO();
    }

    @Override
    public int insert(KhachHangDTO t) {
        int result = 0;
        String sql = "INSERT INTO customer (address, phone, name) VALUES (?, ?, ?)";
        MySQLConnect db = new MySQLConnect();

        try {
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, t.getAddress());
            ps.setString(2, t.getSdt());
            ps.setString(3, t.getHoten());

            result = ps.executeUpdate();
            ps.close();
            con.close();
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
        String sql = "UPDATE customer SET status = 0 WHERE macustomer = ?";
        MySQLConnect db = new MySQLConnect();

        try {
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, t);

            result = ps.executeUpdate();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            db.disConnect();
        }

        return result;
    }

    @Override
    public int update(KhachHangDTO t) {
        int result = 0;
        String sql = "UPDATE customer SET address = ?, phone = ?, name = ? WHERE macustomer = ?";
        MySQLConnect db = new MySQLConnect();

        try {
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, t.getAddress());
            ps.setString(2, t.getSdt());
            ps.setString(3, t.getHoten());
            ps.setInt(4, t.getMaKH());

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
    public ArrayList<KhachHangDTO> selectAll() {
        ArrayList<KhachHangDTO> result = new ArrayList<>();
        String sql = "SELECT * FROM customer WHERE status = 1";
        MySQLConnect db = new MySQLConnect();

        try {
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int macustomer = rs.getInt("macustomer");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                String name = rs.getString("name");
                Date ngaydangky = rs.getDate("ngaythamgia");
                result.add(new KhachHangDTO(macustomer,name,phone , address,ngaydangky));
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
    public KhachHangDTO selectedByID(int t) {
        KhachHangDTO result = null;
        String sql = "SELECT * FROM customer WHERE macustomer = ?";
        MySQLConnect db = new MySQLConnect();

        try {
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, t); // Quan trọng nhaaaa
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int macustomer = rs.getInt("macustomer");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                String name = rs.getString("name");

                result = new KhachHangDTO(macustomer, address, phone, name);
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
                    "WHERE TABLE_SCHEMA = 'sieuthi_mini' AND TABLE_NAME = 'customer'";
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
            ex.printStackTrace(); // hoặc log ra nếu muốn đẹp hơn
        } finally {
            db.disConnect();
        }

        return result;
    }
}

