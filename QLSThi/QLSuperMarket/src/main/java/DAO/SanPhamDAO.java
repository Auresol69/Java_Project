package DAO;

import DTO.SanPhamDTO;
import java.sql.*;
import java.util.ArrayList;

public class SanPhamDAO implements DAOinterface<SanPhamDTO> {

    public static SanPhamDAO getInstance() {
        return new SanPhamDAO();
    }

    @Override
    public int insert(SanPhamDTO t) {
        int result = 0;
        String sql = "INSERT INTO product (tensp, soluong, dongiasanpham, maloaisp, img) VALUES (?, ?, ?, ?, ?)";
        MySQLConnect db = new MySQLConnect();

        try {
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, t.getTensp());
            ps.setInt(2, t.getSoluong());
            ps.setInt(3, t.getDongiasanpham());
            ps.setInt(4, t.getMaloaisp());
            
            ps.setString(5, t.getImg());

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
        String sql = "DELETE FROM product WHERE masp = ?";
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
    public int update(SanPhamDTO t) {
        int result = 0;
        String sql = "UPDATE product SET tensp = ?, soluong = ?, dongiasanpham = ?, maloaisp = ?, img = ? WHERE masp = ?";
        MySQLConnect db = new MySQLConnect();

        try {
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, t.getTensp());
            ps.setInt(2, t.getSoluong());
            ps.setInt(3, t.getDongiasanpham());
            ps.setInt(4, t.getMaloaisp());
            ps.setString(5, t.getImg());
            

            result = ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            db.disConnect();
        }

        return result;
    }
    public boolean isLoaiSPExists(int maloaisp) {
        // Kiểm tra mã loại sản phẩm trong cơ sở dữ liệu
        String query = "SELECT COUNT(*) FROM producttype WHERE maloaisp = ?";
        MySQLConnect db = new MySQLConnect();
        try (Connection connection = db.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, maloaisp);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Trả về true nếu tồn tại
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Trả về false nếu không tồn tại
    }
    
    

    @Override
    public ArrayList<SanPhamDTO> selectAll() {
        ArrayList<SanPhamDTO> result = new ArrayList<>();
        String sql = "SELECT * FROM product";
        MySQLConnect db = new MySQLConnect();

        try {
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int masp = rs.getInt("masp");
                String tensp = rs.getString("tensp");
                int soluong = rs.getInt("soluong");
                int dongiasanpham = rs.getInt("dongiasanpham");
                int maloaisp = rs.getInt("maloaisp");
                
                String img = rs.getString("img");

                result.add(new SanPhamDTO(masp, tensp, soluong, dongiasanpham, maloaisp, img));
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
    public SanPhamDTO selectedByID(int t) {
        SanPhamDTO result = null;
        String sql = "SELECT * FROM product WHERE masp = ?";
        MySQLConnect db = new MySQLConnect();

        try {
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, t);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int masp = rs.getInt("masp");
                String tensp = rs.getString("tensp");
                int soluong = rs.getInt("soluong");
                int dongiasanpham = rs.getInt("dongiasanpham");
                int maloaisp = rs.getInt("maloaisp");
                
                String img = rs.getString("img");

                result = new SanPhamDTO(masp, tensp, soluong, dongiasanpham, maloaisp, img);
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
                     "WHERE TABLE_SCHEMA = 'sieuthi_mini' AND TABLE_NAME = 'product'";
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