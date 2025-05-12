package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import DAO.MySQLConnect;
import DTO.ThongKeTheoTungNgay;
import DTO.ThongKeKhachHangDTO;
import DTO.ThongKeTheoNam;
import DTO.ThongKeTheoThang;
public class ThongKeDAO {
        public ThongKeDAO(){}
        public static ThongKeDAO getInstance(){
            return new ThongKeDAO();
        }
        public ArrayList<ThongKeKhachHangDTO> getAllKH(String start, String end) {
            ArrayList<ThongKeKhachHangDTO> result = new ArrayList<>();
            try {
                Connection con = MySQLConnect.getConnection();
        
                // Cập nhật câu truy vấn SQL bỏ chi phí
                String sql = "SELECT \n" +
                        "  dates.date AS ngay, \n" +
                        "  customer.macustomer, customer.name, \n" +
                        "  COALESCE(SUM(bp.soluong), 0) AS slban, \n" +
                        "  COALESCE(SUM(bp.dongiasanpham * bp.soluong), 0) AS doanhthu \n" +
                        "FROM (\n" +
                        "  SELECT DATE_ADD(? , INTERVAL c.number DAY) AS date\n" +
                        "  FROM (\n" +
                        "    SELECT a.number + b.number * 31 AS number\n" +
                        "    FROM (\n" +
                        "      SELECT 0 AS number UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9 UNION ALL SELECT 10 UNION ALL SELECT 11 UNION ALL SELECT 12 UNION ALL SELECT 13 UNION ALL SELECT 14 UNION ALL SELECT 15 UNION ALL SELECT 16 UNION ALL SELECT 17 UNION ALL SELECT 18 UNION ALL SELECT 19 UNION ALL SELECT 20 UNION ALL SELECT 21 UNION ALL SELECT 22 UNION ALL SELECT 23 UNION ALL SELECT 24 UNION ALL SELECT 25 UNION ALL SELECT 26 UNION ALL SELECT 27 UNION ALL SELECT 28 UNION ALL SELECT 29 UNION ALL SELECT 30\n" +
                        "    ) AS a\n" +
                        "    CROSS JOIN (\n" +
                        "      SELECT 0 AS number UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9 UNION ALL SELECT 10\n" +
                        "    ) AS b\n" +
                        "  ) AS c\n" +
                        "  WHERE DATE_ADD(? , INTERVAL c.number DAY) <= ? \n" +
                        ") AS dates\n" +
                        "LEFT JOIN bill b ON DATE(b.ngaymua) = dates.date\n" +
                        "LEFT JOIN customer ON b.macustomer = customer.macustomer\n" +
                        "LEFT JOIN bill_product bp ON b.mabill = bp.mabill\n" +
                        "GROUP BY dates.date, customer.macustomer, customer.name\n" +
                        "ORDER BY dates.date, customer.macustomer";
                
                PreparedStatement pst = con.prepareStatement(sql);
        
                // Chuyển yyyy-MM -> ngày đầu và cuối tháng
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate startDate = LocalDate.parse(start, formatter);
                LocalDate endDate = LocalDate.parse(end, formatter);
        
                pst.setDate(1, java.sql.Date.valueOf(startDate)); // base date
                pst.setDate(2, java.sql.Date.valueOf(startDate)); // add days
                pst.setDate(3, java.sql.Date.valueOf(endDate));   // until
        
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    int makh = rs.getInt("macustomer");
                    String tenkh = rs.getString("name");
                    Date ngay = rs.getDate("ngay");
                    int slban = rs.getInt("slban");
                    int doanhthu = rs.getInt("doanhthu");
                    
        
                    // Tạo đối tượng ThongKeKhachHang mà không sử dụng chi phí
                    ThongKeKhachHangDTO kh = new ThongKeKhachHangDTO(makh, tenkh, ngay, slban, doanhthu);
                    result.add(kh);
                }
        
            } catch (SQLException e) {
                e.printStackTrace();
            }
        
            return result;
        }
        
        
        public ArrayList<ThongKeTheoThang> getThongKeTheoThang(String start, String end) {
            ArrayList<ThongKeTheoThang> result = new ArrayList<>();
            try {
                Connection con = MySQLConnect.getConnection();

                String sql = "SELECT months.month AS thang,\n"
                + "       COALESCE(chiphi_table.chiphi, 0) AS chiphi,\n"
                + "       COALESCE(doanhthu_table.doanhthu, 0) AS doanhthu\n"
                + "FROM (\n"
                + "    SELECT DATE_FORMAT(DATE_ADD(?, INTERVAL n MONTH), '%Y-%m') AS month\n"
                + "    FROM (SELECT 0 n UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4\n"
                + "          UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9\n"
                + "          UNION ALL SELECT 10 UNION ALL SELECT 11 UNION ALL SELECT 12 UNION ALL SELECT 13 UNION ALL SELECT 14\n"
                + "          UNION ALL SELECT 15 UNION ALL SELECT 16 UNION ALL SELECT 17 UNION ALL SELECT 18 UNION ALL SELECT 19\n"
                + "          UNION ALL SELECT 20 UNION ALL SELECT 21 UNION ALL SELECT 22 UNION ALL SELECT 23 UNION ALL SELECT 24\n"
                + "          UNION ALL SELECT 25) AS numbers\n"
                + "    WHERE DATE_FORMAT(DATE_ADD(?, INTERVAL n MONTH), '%Y-%m') <= DATE_FORMAT(?, '%Y-%m')\n"
                + ") AS months\n"
                + "LEFT JOIN (\n"
                + "    SELECT DATE_FORMAT(entry_form.ngaynhap, '%Y-%m') AS month,\n"
                + "           SUM(detail_entry_form.dongianhap * detail_entry_form.soluongnhap) AS chiphi\n"
                + "    FROM entry_form\n"
                + "    JOIN detail_entry_form ON entry_form.maphieunhap = detail_entry_form.maphieunhap\n"
                + "    GROUP BY DATE_FORMAT(entry_form.ngaynhap, '%Y-%m')\n"
                + ") AS chiphi_table ON chiphi_table.month = months.month\n"
                + "LEFT JOIN (\n"
                + "    SELECT DATE_FORMAT(bill.ngaymua, '%Y-%m') AS month,\n"
                + "           SUM(bill_product.dongiasanpham * bill_product.soluong) AS doanhthu\n"
                + "    FROM bill\n"
                + "    JOIN bill_product ON bill.mabill = bill_product.mabill\n"
                + "    GROUP BY DATE_FORMAT(bill.ngaymua, '%Y-%m')\n"
                + ") AS doanhthu_table ON doanhthu_table.month = months.month\n"
                + "ORDER BY months.month;";


                PreparedStatement pst = con.prepareStatement(sql);

                // Chuyển đổi start/end từ yyyy-MM thành java.sql.Date đầu tháng
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
                LocalDate startDate = YearMonth.parse(start, formatter).atDay(1);
                LocalDate endDate = YearMonth.parse(end, formatter).atEndOfMonth();

                pst.setDate(1, java.sql.Date.valueOf(startDate)); // BASE ngày cộng tháng
                pst.setDate(2, java.sql.Date.valueOf(startDate)); // WHERE DATE_ADD(..., n MONTH)
                pst.setDate(3, java.sql.Date.valueOf(endDate));   // Giới hạn đến tháng cuối

                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    String thangStr = rs.getString("thang"); // Fix lỗi tại đây
                    int chiphi = rs.getInt("chiphi");
                    int doanhthu = rs.getInt("doanhthu");
                    int loinhuan = doanhthu - chiphi;
                
                    int thang = Integer.parseInt(thangStr.split("-")[1]); // nếu vẫn muốn dùng int
                    ThongKeTheoThang thongke = new ThongKeTheoThang(thang, chiphi, doanhthu, loinhuan);
                    result.add(thongke);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return result;
        }
        public ArrayList<ThongKeTheoTungNgay> getThongKeTheoTuNgayDenNgay(String start, String end){
            ArrayList<ThongKeTheoTungNgay> result = new ArrayList<>();
            try{
            Connection con = MySQLConnect.getConnection();
            String setStar = "SET @start_date = '" + start + "'";
            String setEnd = "SET @end_date = '" + end + "'  ;";
            String sqlSelect = "SELECT \n"
                    + "  dates.date AS ngay, \n"
                    + "  COALESCE(SUM(detail_entry_form.dongianhap * detail_entry_form.soluongnhap), 0) AS chiphi, \n"
                    + "  COALESCE(SUM(bill_product.dongiasanpham * bill_product.soluong), 0) AS doanhthu\n"
                    + "FROM (\n"
                    + "  SELECT DATE_ADD(@start_date, INTERVAL c.number DAY) AS date\n"
                    + "  FROM (\n"
                    + "    SELECT a.number + b.number * 31 AS number\n"
                    + "    FROM (\n"
                    + "      SELECT 0 AS number\n"
                    + "      UNION ALL SELECT 1\n"
                    + "      UNION ALL SELECT 2\n"
                    + "      UNION ALL SELECT 3\n"
                    + "      UNION ALL SELECT 4\n"
                    + "      UNION ALL SELECT 5\n"
                    + "      UNION ALL SELECT 6\n"
                    + "      UNION ALL SELECT 7\n"
                    + "      UNION ALL SELECT 8\n"
                    + "      UNION ALL SELECT 9\n"
                    + "      UNION ALL SELECT 10\n"
                    + "      UNION ALL SELECT 11\n"
                    + "      UNION ALL SELECT 12\n"
                    + "      UNION ALL SELECT 13\n"
                    + "      UNION ALL SELECT 14\n"
                    + "      UNION ALL SELECT 15\n"
                    + "      UNION ALL SELECT 16\n"
                    + "      UNION ALL SELECT 17\n"
                    + "      UNION ALL SELECT 18\n"
                    + "      UNION ALL SELECT 19\n"
                    + "      UNION ALL SELECT 20\n"
                    + "      UNION ALL SELECT 21\n"
                    + "      UNION ALL SELECT 22\n"
                    + "      UNION ALL SELECT 23\n"
                    + "      UNION ALL SELECT 24\n"
                    + "      UNION ALL SELECT 25\n"
                    + "      UNION ALL SELECT 26\n"
                    + "      UNION ALL SELECT 27\n"
                    + "      UNION ALL SELECT 28\n"
                    + "      UNION ALL SELECT 29\n"
                    + "      UNION ALL SELECT 30\n"
                    + "    ) AS a\n"
                    + "    CROSS JOIN (\n"
                    + "      SELECT 0 AS number\n"
                    + "      UNION ALL SELECT 1\n"
                    + "      UNION ALL SELECT 2\n"
                    + "      UNION ALL SELECT 3\n"
                    + "      UNION ALL SELECT 4\n"
                    + "      UNION ALL SELECT 5\n"
                    + "      UNION ALL SELECT 6\n"
                    + "      UNION ALL SELECT 7\n"
                    + "      UNION ALL SELECT 8\n"
                    + "      UNION ALL SELECT 9\n"
                    + "      UNION ALL SELECT 10\n"
                    + "    ) AS b\n"
                    + "  ) AS c\n"
                    + "  WHERE DATE_ADD(@start_date, INTERVAL c.number DAY) <= @end_date\n"
                    + ") AS dates\n"
                    + "LEFT JOIN entry_form ON DATE(entry_form.ngaynhap) = dates.date AND entry_form.status = 1\n "
                    + "LEFT JOIN detail_entry_form ON entry_form.maphieunhap = detail_entry_form.maphieunhap\n"
                    + "LEFT JOIN bill ON DATE(bill.ngaymua) = dates.date\n"
                    + "LEFT JOIN bill_product ON bill.mabill = bill_product.mabill\n"
                    + "GROUP BY dates.date\n"
                    + "ORDER BY dates.date;";

            PreparedStatement pstStart = con.prepareStatement(setStar);
            PreparedStatement pstEnd = con.prepareStatement(setEnd);
            PreparedStatement pstSelect = con.prepareStatement(sqlSelect);

            pstStart.execute();
            pstEnd.execute();
            ResultSet rs = pstSelect.executeQuery();
            while (rs.next()) {
                Date ngay = rs.getDate("ngay");
                int chiphi = rs.getInt("chiphi");
                int doanhthu = rs.getInt("doanhthu");
                int loinhuan = doanhthu - chiphi;
                ThongKeTheoTungNgay tn = new ThongKeTheoTungNgay(ngay, chiphi, doanhthu, loinhuan);
                result.add(tn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
        public static void main(String[] args) {
            ThongKeDAO thongKeDAO = new ThongKeDAO();
            ArrayList<ThongKeTheoThang> tkt = thongKeDAO.getThongKeTheoThang("2025-04", "2025-05");
            ArrayList<ThongKeTheoTungNgay> tkn = thongKeDAO.getThongKeTheoTuNgayDenNgay("2025-05-01", "2025-05-09");
            ArrayList<ThongKeKhachHangDTO>tknv = thongKeDAO.getAllKH("2025-05-01", "2025-05-09");
            // for (int i = 0 ; i< tkt.size();i++){
            //     System.out.println(tkt.get(i).toString());
            // }
            // for (int i = 0 ; i< tkn.size();i++){
            //     System.out.println(tkn.get(i).toString());
            // }
            for (int i = 0 ; i< tknv.size();i++){
                System.out.println(tknv.get(i).toString());
            }
        }
       
}
