package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import DTO.NhaCungCapDTO;
public class NhaCungCapDAO implements DAOinterface<NhaCungCapDTO>{
    public static NhaCungCapDAO getInstance(){
        return new NhaCungCapDAO();
    }
    @Override 
    public int insert(NhaCungCapDTO t){
        int result = 0;
        try {
            Connection con = MySQLConnect.getConnection();
            String sql = "INSERT INTO `supplier` (`mancc`,`tencc`,`diachi`,`dienthoai`,`sofax`,`status`) VALUES (?,?,?,?,?,1)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getMancc());
            pst.setString(2,t.getTenncc());
            pst.setString(3, t.getDiachi());
            pst.setString(4,t.getSdt());
            pst.setString(5,t.getSdt());
            result = pst.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(NhaCungCapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    @Override
    public int delete(int t){
        int result = 0;
        try {
            Connection con = MySQLConnect.getConnection();
            String sql = "UPDATE supplier SET status = 0 WHERE mancc = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1,t);
            result = pst.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(NhaCungCapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    @Override
    public int update(NhaCungCapDTO t) {
        int result = 0 ;
        try {
            Connection con = MySQLConnect.getConnection();
            String sql = "UPDATE `supplier` SET `tencc`=?,`diachi`=?,`dienthoai`=?,`sofax`=? WHERE `mancc`= ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t.getTenncc());
            pst.setString(2, t.getDiachi());
            pst.setString(3, t.getSdt());
            pst.setString(4, t.getSofax());
            pst.setInt(5, t.getMancc());
            result = pst.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(NhaCungCapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    @Override
    public ArrayList<NhaCungCapDTO> selectAll() {
        ArrayList<NhaCungCapDTO> result = new ArrayList<>();
        try {
            Connection con = MySQLConnect.getConnection();
            String sql = "SELECT * FROM supplier WHERE status = 1";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int mancc = rs.getInt("mancc");
                String tenncc = rs.getString("tencc");
                String diachi = rs.getString("diachi");
                String sdt = rs.getString("dienthoai");
                String sofax = rs.getString("sofax");
                NhaCungCapDTO ncc = new NhaCungCapDTO(mancc, tenncc, diachi, sdt,sofax);
                result.add(ncc);
            }
            con.close();
        } catch (Exception e) {
        }
        return result;
    }

    @Override
    public NhaCungCapDTO selectedByID(int t) {
        NhaCungCapDTO result = null;
        try {
            Connection con = MySQLConnect.getConnection();
            String sql = "SELECT * FROM supplier WHERE mancc=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int mancc = rs.getInt("mancc");
                String tenncc = rs.getString("tencc");
                String diachi = rs.getString("diachi");
                String sofax = rs.getString("sofax");
                String sdt = rs.getString("sdt");
                
                result = new NhaCungCapDTO(mancc,tenncc,diachi,sdt,sofax);
            }
            con.close();
        } catch (Exception e) {
        }
        return result;
    }

    @Override
    public int getAutoIncrement() {
        int result = -1;
        try {
            Connection con = MySQLConnect.getConnection();
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'sieuthi_mini' AND   TABLE_NAME   = 'supplier'";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs2 = pst.executeQuery(sql);
            if (!rs2.isBeforeFirst() ) {
                System.out.println("No data");
            } else {
                while ( rs2.next() ) {
                    result = rs2.getInt("AUTO_INCREMENT");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhaCungCapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
