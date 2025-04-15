
package DAO;
import DTO.SanPhamDTO;
import DAO.ProductTypeDAO;
import DAO.SupplierDAO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SanPhamDAO {
    private  MySQLConnect mySQL = new MySQLConnect();
    public SanPhamDAO() {

    }
    public ArrayList<SanPhamDTO> list()
    {
        ArrayList<SanPhamDTO> sp = new ArrayList<>();
        try {
            String sql = "SELECT * FROM product";
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next())
            {
                String maSP = rs.getString("masp");
                String tenSP = rs.getString("tensp");
                int sl = rs.getInt("soluong");
                int gia = rs.getInt("dongiasanpham");
                String maLoai = rs.getString("maloaisp");
                String maNcc = rs.getString("mancc");
                String IMG = rs.getString("img");
                SanPhamDTO s = new SanPhamDTO(maSP, tenSP, sl, gia, maLoai, maNcc, IMG);
                sp.add(s);
            }
            rs.close();
            mySQL.disConnect();

        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sp;
    }
    public boolean add(SanPhamDTO sp) {
        try {
            String sql = "INSERT INTO product (masp, tensp, soluong, dongiasanpham, maloaisp, mancc, img) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = mySQL.getConnection().prepareStatement(sql);
            ps.setString(1, sp.getMaSP());
            ps.setString(2, sp.getTenSP());
            ps.setInt(3, sp.getSoLuong());
            ps.setDouble(4, sp.getDonGia());
            ps.setString(5, sp.getMaLoai());
            ps.setString(6, sp.getMaNCC());
            ps.setString(7, sp.getImg());
            
            int rows = ps.executeUpdate();  // Số dòng bị ảnh hưởng
            return rows > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Lỗi khi thêm sản phẩm: " + ex.getMessage());
            return false;
        }
    }
    

    public void delete(String idSP) {
        String sql = "DELETE FROM product WHERE masp='" + idSP + "'";
        System.out.println(sql);
        mySQL.executeUpdate(sql);
    }

    public boolean set(SanPhamDTO sp) {
        try {
            String sql = "UPDATE product SET tensp = ?, soluong = ?, dongiasanpham = ?, maloaisp = ?, mancc = ?, img = ? WHERE masp = ?";
            PreparedStatement ps = mySQL.getConnection().prepareStatement(sql);
            ps.setString(1, sp.getTenSP());
            ps.setInt(2, sp.getSoLuong());
            ps.setDouble(3, sp.getDonGia());
            ps.setString(4, sp.getMaLoai());
            ps.setString(5, sp.getMaNCC());
            ps.setString(6, sp.getImg());
            ps.setString(7, sp.getMaSP());
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Lỗi khi cập nhật sản phẩm: " + ex.getMessage());
            return false;
        }
    }

    public void ExportExcelDatabase(){
        try{
            String sql = "SELECT * FROM product WHERE 1";
            ResultSet rs = mySQL.executeQuery(sql);
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("productExcel");


            XSSFFont font = workbook.createFont();
            font.setFontHeightInPoints((short) 12);
            font.setBold(true);

            XSSFCellStyle style = workbook.createCellStyle();
            style.setFont(font);

            XSSFRow row = sheet.createRow(0);
            XSSFCell cell;

            cell = row.createCell(0);
            cell.setCellValue("MASP");
            cell.setCellStyle(style);
            cell = row.createCell(1);
            cell.setCellValue("TENSP");
            cell.setCellStyle(style);
            cell = row.createCell(2);
            cell.setCellValue("SOLUONG");
            cell.setCellStyle(style);
            cell = row.createCell(3);
            cell.setCellValue("GIA");
            cell.setCellStyle(style);
            cell = row.createCell(4);
            cell.setCellValue("MALOAI");
            cell.setCellStyle(style);
            cell = row.createCell(5);
            cell.setCellValue("MANCC");
            cell.setCellStyle(style);
            cell = row.createCell(6);
            cell.setCellValue("IMG");
            cell.setCellStyle(style);
            int i = 1;

            while(rs.next()){
                row = sheet.createRow(i);
                cell = row.createCell(0);
                cell.setCellValue(rs.getString("masp"));
                cell = row.createCell(1);
                cell.setCellValue(rs.getString("tensp"));
                cell = row.createCell(2);
                cell.setCellValue(rs.getInt("soluong"));
                cell = row.createCell(3);
                cell.setCellValue(rs.getInt("dongiasanpham"));
                cell = row.createCell(4);
                cell.setCellValue(rs.getString("maloaisp"));
                cell = row.createCell(5);
                cell.setCellValue(rs.getString("mancc"));
                cell = row.createCell(6);
                cell.setCellValue(rs.getString("img"));

                i++;
            }

            for(int colNum = 0;colNum < row.getLastCellNum();colNum++) {
                sheet.autoSizeColumn((short) (colNum));
            }
            // Kiểm tra và tạo thư mục report
        File directory = new File("./report");
        if (!directory.exists()) {
            directory.mkdirs(); // Tạo thư mục nếu chưa tồn tại
        }

            FileOutputStream out = new FileOutputStream(new File("./report/sanphamdb.xlsx"));
            workbook.write(out);
            out.close();
            System.out.println("Xuat file thanh cong");

        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void ImportExcelDatabase(File file){
        try{
            FileInputStream in = new FileInputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook(in);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Row row;
            for(int i = 1; i <= sheet.getLastRowNum(); i++){
                row = sheet.getRow(i);
                String maSP = row.getCell(0).getStringCellValue();
                String tenSP = row.getCell(1).getStringCellValue();
                int sl = (int) row.getCell(2).getNumericCellValue();
                int gia = (int) row.getCell(3).getNumericCellValue();
                String maLoai = row.getCell(4).getStringCellValue();
                String maNsx = row.getCell(5).getStringCellValue();
                String IMG = row.getCell(6).getStringCellValue();

                String sql_check = "SELECT * FROM product WHERE masp='"+maSP+"'";
                ResultSet rs = mySQL.executeQuery(sql_check);
                if(!rs.next()){
                    String sql = "INSERT INTO product VALUES (";
                    sql += "'"+maSP+"',";
                    sql += "N'"+tenSP+"',";
                    sql += "'"+sl+"',";
                    sql += "'"+gia+"',";
                    sql += "'"+maLoai+"',";
                    sql += "'"+maNsx+"',";
                    sql += "'"+IMG+"')";
                    System.out.println(sql);
                    mySQL.executeUpdate(sql);
                }
                else{
                    String sql = "UPDATE sanpham SET ";
                    sql += "tensp='"+tenSP+"', ";
                    sql += "soluong='"+sl+"', ";
                    sql += "dongiasanpham='"+gia+"', ";
                    sql += "maloaisp='"+maLoai+"', ";
                    sql += "mancc='"+maNsx+"', ";
                    sql += "img='"+IMG+"' ";
                    sql += "WHERE masp='"+maSP+"'";
                    System.out.println(sql);
                    mySQL.executeUpdate(sql);
                }
            }
            in.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
