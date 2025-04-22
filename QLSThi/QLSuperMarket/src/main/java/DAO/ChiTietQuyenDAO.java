package DAO;

import DTO.ChiTietQuyenDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ChiTietQuyenDAO implements ChiTietInterface<ChiTietQuyenDTO> {

    public static ChiTietQuyenDAO getInstance() {
        return new ChiTietQuyenDAO();
    }

    @Override
    public int delete(String powergroupId) {
        int result = 0;
        String sql = "DELETE FROM powergroup_func WHERE powergroupid = ?";
        MySQLConnect db = new MySQLConnect();

        try {
            Connection con = db.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, powergroupId);

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
    public int insert(ArrayList<ChiTietQuyenDTO> list) {
        int result = 0;
        String sql = "INSERT INTO powergroup_func (powergroupid, funcid) VALUES (?, ?)";
        MySQLConnect db = new MySQLConnect();

        try {
            Connection con = db.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);

            for (ChiTietQuyenDTO item : list) {
                pst.setInt(1, item.getManhomquyen()); // map sang powergroupid
                pst.setInt(2, item.getMachucnang()); // map sang funcid
                result += pst.executeUpdate();
            }

            pst.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            db.disConnect();
        }

        return result;
    }

    @Override
    public ArrayList<ChiTietQuyenDTO> selectAll(String powergroupId) {
        ArrayList<ChiTietQuyenDTO> result = new ArrayList<>();
        String sql = "SELECT * FROM powergroup_func_permission WHERE powergroupid = ?";
        MySQLConnect db = new MySQLConnect();
        try {
            Connection con = db.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, powergroupId);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int groupId = rs.getInt("powergroupid");
                int funcId = rs.getInt("funcid");
                result.add(new ChiTietQuyenDTO(groupId, funcId));
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

    @Override
    public int update(ArrayList<ChiTietQuyenDTO> list, String powergroupId) {
        int result = delete(powergroupId);
        if (result >= 0) { // vẫn insert dù chưa có dữ liệu cũ
            result = insert(list);
        }
        return result;
    }
}
