/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.DanhMucChucNangDTO;
import DAO.MySQLConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Tran Nhat Sinh
 */
public class DanhMucChucNangDAO {

    public static DanhMucChucNangDAO getInstance() {
        return new DanhMucChucNangDAO();
    }

    public ArrayList<DanhMucChucNangDTO> selectAll() {
        ArrayList<DanhMucChucNangDTO> result = new ArrayList<>();
        MySQLConnect db = new MySQLConnect();
        try {
            Connection con = db.getConnection();
            String sql = "SELECT * FROM func";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int machucnang = rs.getInt("funcid");
                String tenchucnang = rs.getString("funcname");
                DanhMucChucNangDTO dvt = new DanhMucChucNangDTO(machucnang, tenchucnang);
                result.add(dvt);
            }
           db.disConnect();
           rs.close();
           pst.close();
        } catch (Exception e) {
        }
        return result;
    }
}
