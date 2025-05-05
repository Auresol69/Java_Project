package DAO;

import DTO.ThongKeDoanhThuDTO;
import DTO.ThongKeKhachHangDTO;
import DTO.ThongKeNhaCungCapDTO;
import DTO.ThongKeTheoThangDTO;
import DTO.ThongKeTonKhoDTO;
import DTO.ThongKeTungNgayTrongThangDTO;
import DAO.MySQLConnect;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThongKeDAO {

    public static ThongKeDAO getInstance() {
        return new ThongKeDAO();
    }

    public static HashMap<Integer, ArrayList<ThongKeTonKhoDTO>> getThongKeTonKho(String text, Date timeStart, Date timeEnd) {
        HashMap<Integer, ArrayList<ThongKeTonKhoDTO>> result = new HashMap<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeEnd.getTime());
        // Đặt giá trị cho giờ, phút, giây và mili giây của Calendar
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        try {
            Connection con = MySQLConnect.getConnection();
            String sql = """
                         WITH nhap AS (
                           SELECT masp, SUM(soluongnhap) AS sl_nhap
                           FROM detail_entry_form
                           JOIN entry_form ON entry_form.maphieunhap = detail_entry_form.maphieunhap
                           WHERE ngaynhap BETWEEN ? AND ?
                           GROUP BY masp
                         ),
                         xuat AS (
                           SELECT masp, SUM(soluong) AS sl_xuat
                           FROM bill_product
                           JOIN bill ON bill.mabill = bill_product.mabill
                           WHERE ngaymua BETWEEN ? AND ?
                           GROUP BY masp
                         ),
                         nhap_dau AS (
                           SELECT detail_entry_form.masp, SUM(detail_entry_form.soluongnhap) AS sl_nhap_dau
                           FROM entry_form
                           JOIN detail_entry_form ON entry_form.maphieunhap = detail_entry_form.maphieunhap
                           WHERE entry_form.ngaynhap < ?
                           GROUP BY detail_entry_form.masp
                         ),
                         xuat_dau AS (
                           SELECT bill_product.masp, SUM(bill_product.soluong) AS sl_xuat_dau
                           FROM bill
                           JOIN bill_product ON bill.mabill = bill_product.mabill
                           WHERE bill.ngaymua < ?
                           GROUP BY bill_product.masp
                         ),
                         dau_ky AS (
                            SELECT
                                sp.masp,
                                COALESCE(nhap_dau.sl_nhap_dau, 0) - COALESCE(xuat_dau.sl_xuat_dau, 0) AS soluongdauky
                            FROM product sp
                            LEFT JOIN nhap_dau ON sp.masp = nhap_dau.masp
                            LEFT JOIN xuat_dau ON sp.masp = xuat_dau.masp
                        ),
                        temp_table AS (
                            SELECT
                                sp.masp,
                                sp.tensp,
                                dau_ky.soluongdauky,
                                COALESCE(nhap.sl_nhap, 0) AS soluongnhap,
                                COALESCE(xuat.sl_xuat, 0) AS soluongxuat,
                                (dau_ky.soluongdauky + COALESCE(nhap.sl_nhap, 0) - COALESCE(xuat.sl_xuat, 0)) AS soluongcuoiky
                            FROM dau_ky
                            LEFT JOIN nhap ON dau_ky.masp = nhap.masp
                            LEFT JOIN xuat ON dau_ky.masp = xuat.masp
                            JOIN product sp ON sp.masp = dau_ky.masp
                        )
                        SELECT * FROM temp_table
                        WHERE tensp LIKE ? OR masp LIKE ?
                        ORDER BY masp;""";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setTimestamp(1, new Timestamp(timeStart.getTime()));
            pst.setTimestamp(2, new Timestamp(calendar.getTimeInMillis()));
            pst.setTimestamp(3, new Timestamp(timeStart.getTime()));
            pst.setTimestamp(4, new Timestamp(calendar.getTimeInMillis()));
            pst.setTimestamp(5, new Timestamp(timeStart.getTime()));
            pst.setTimestamp(6, new Timestamp(timeStart.getTime()));
            pst.setString(7, "%" + text + "%");
            pst.setString(8, "%" + text + "%");

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int masp = rs.getInt("masp");
                String tensp = rs.getString("tensp");
                int soluongdauky = rs.getInt("soluongdauky");
                int soluongnhap = rs.getInt("soluongnhap");
                int soluongxuat = rs.getInt("soluongxuat");
                int soluongcuoiky = rs.getInt("soluongcuoiky");
                ThongKeTonKhoDTO p = new ThongKeTonKhoDTO(masp, tensp, soluongdauky, soluongnhap, soluongxuat, soluongcuoiky);
                result.computeIfAbsent(masp, k -> new ArrayList<>()).add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<ThongKeDoanhThuDTO> getDoanhThuTheoTungNam(int year_start, int year_end) {
        ArrayList<ThongKeDoanhThuDTO> result = new ArrayList<>();
        try {
            Connection con = MySQLConnect.getConnection();
            String sqlSetStartYear = "SET @start_year = ?;";
            String sqlSetEndYear = "SET @end_year = ?;";
            String sqlSelect = """
                     WITH RECURSIVE years(year) AS (
                        SELECT @start_year
                        UNION ALL
                        SELECT year + 1
                        FROM years
                        WHERE year < @end_year
                        )
                        SELECT 
                        years.year AS nam,
                        COALESCE(SUM(CASE WHEN YEAR(entry_form.ngaynhap) = years.year THEN detail_entry_form.dongianhap ELSE 0 END), 0) AS chiphi,
                        COALESCE(SUM(CASE WHEN YEAR(bill.ngaymua) = years.year THEN bill_product.dongiasanpham ELSE 0 END), 0) AS doanhthu
                        FROM years
                        LEFT JOIN entry_form ON YEAR(entry_form.ngaynhap) = years.year
                        LEFT JOIN detail_entry_form ON entry_form.maphieunhap = detail_entry_form.maphieunhap
                        LEFT JOIN bill ON YEAR(bill.ngaymua) = years.year
                        LEFT JOIN bill_product ON bill.mabill = bill_product.mabill
                        GROUP BY years.year
                        ORDER BY years.year;""";
            PreparedStatement pstStartYear = con.prepareStatement(sqlSetStartYear);
            PreparedStatement pstEndYear = con.prepareStatement(sqlSetEndYear);
            PreparedStatement pstSelect = con.prepareStatement(sqlSelect);

            pstStartYear.setInt(1, year_start);
            pstEndYear.setInt(1, year_end);

            pstStartYear.execute();
            pstEndYear.execute();

            ResultSet rs = pstSelect.executeQuery();
            while (rs.next()) {
                int thoigian = rs.getInt("nam");
                Long chiphi = rs.getLong("chiphi");
                Long doanhthu = rs.getLong("doanhthu");
                ThongKeDoanhThuDTO x = new ThongKeDoanhThuDTO(thoigian, chiphi, doanhthu, doanhthu - chiphi);
                result.add(x);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static ArrayList<ThongKeKhachHangDTO> getThongKeKhachHang(String text, Date timeStart, Date timeEnd) {
        ArrayList<ThongKeKhachHangDTO> result = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeEnd.getTime());
        // Đặt giá trị cho giờ, phút, giây và mili giây của Calendar
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        try {
            Connection con = MySQLConnect.getConnection();
            String sql = """
                          WITH kh AS (
                         SELECT customer.macustomer, customer.name , COUNT(bill.mabill ) AS tongsophieu, SUM(bill.tongtien) AS tongsotien
                         FROM customer
                         JOIN bill ON customer.macustomer = bill.macustomer
                         WHERE bill.ngaymua BETWEEN ? AND ? 
                         GROUP BY customer.macustomer, customer.name)
                         SELECT macustomer,name,COALESCE(kh.tongsophieu, 0) AS soluong ,COALESCE(kh.tongsotien, 0) AS total 
                         FROM kh WHERE name LIKE ? OR macustomer LIKE ?""";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setTimestamp(1, new Timestamp(timeStart.getTime()));
            pst.setTimestamp(2, new Timestamp(calendar.getTimeInMillis()));
            pst.setString(3, "%" + text + "%");
            pst.setString(4, "%" + text + "%");

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int makh = rs.getInt("macustomer");
                String tenkh = rs.getString("name");
                int soluong = rs.getInt("soluong");
                long tongtien = rs.getInt("total");
                ThongKeKhachHangDTO x = new ThongKeKhachHangDTO(makh, tenkh, soluong, tongtien);
                result.add(x);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static ArrayList<ThongKeNhaCungCapDTO> getThongKeNCC(String text, Date timeStart, Date timeEnd) {
        ArrayList<ThongKeNhaCungCapDTO> result = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeEnd.getTime());
        // Đặt giá trị cho giờ, phút, giây và mili giây của Calendar
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        try {
            Connection con = MySQLConnect.getConnection();
            String sql = """
                          WITH ncc AS (
                            SELECT 
                                supplier.mancc, 
                                supplier.tencc, 
                                COUNT(DISTINCT entry_form.maphieunhap) AS tongsophieu, 
                                SUM(detail_entry_form.dongianhap * detail_entry_form.soluongnhap) AS tongsotien
                            FROM supplier
                            JOIN entry_form ON supplier.mancc = entry_form.mancc
                            JOIN detail_entry_form ON entry_form.maphieunhap = detail_entry_form.maphieunhap
                            WHERE entry_form.ngaynhap BETWEEN ? AND ?
                            GROUP BY supplier.mancc, supplier.tencc
                            )
                            SELECT 
                            mancc, 
                            tencc, 
                            COALESCE(tongsophieu, 0) AS soluong,
                            COALESCE(tongsotien, 0) AS total
                            FROM ncc
                            WHERE tencc LIKE ? OR mancc LIKE ?""";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setTimestamp(1, new Timestamp(timeStart.getTime()));
            pst.setTimestamp(2, new Timestamp(calendar.getTimeInMillis()));
            pst.setString(3, "%" + text + "%");
            pst.setString(4, "%" + text + "%");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int mancc = rs.getInt("mancc");
                String tenncc = rs.getString("tencc");
                int soluong = rs.getInt("soluong");
                long tongtien = rs.getInt("total");
                ThongKeNhaCungCapDTO x = new ThongKeNhaCungCapDTO(mancc, tenncc, soluong, tongtien);
                result.add(x);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<ThongKeTheoThangDTO> getThongKeTheoThang(int nam) {
        ArrayList<ThongKeTheoThangDTO> result = new ArrayList<>();
        try {
            Connection con = MySQLConnect.getConnection();
            String sql = "SELECT months.month AS thang, \n"
            + "       COALESCE(SUM(detail_entry_form.dongianhap * detail_entry_form.soluongnhap), 0) AS chiphi,\n"
            + "       COALESCE(SUM(bill_product.dongiasanpham * bill_product.soluong), 0) AS doanhthu\n"
            + "FROM (\n"
            + "       SELECT 1 AS month UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4\n"
            + "       UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8\n"
            + "       UNION ALL SELECT 9 UNION ALL SELECT 10 UNION ALL SELECT 11 UNION ALL SELECT 12\n"
            + "     ) AS months\n"
            + "LEFT JOIN entry_form ON MONTH(entry_form.ngaynhap) = months.month AND YEAR(entry_form.ngaynhap) = ? \n"
            + "LEFT JOIN detail_entry_form ON entry_form.maphieunhap = detail_entry_form.maphieunhap\n"
            + "LEFT JOIN bill ON MONTH(bill.ngaymua) = months.month AND YEAR(bill.ngaymua) = ? \n"
            + "LEFT JOIN bill_product ON bill.mabill = bill_product.mabill\n"
            + "GROUP BY months.month\n"
            + "ORDER BY months.month;";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setInt(1, nam);
                pst.setInt(2, nam);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int thang = rs.getInt("thang");
                int chiphi = rs.getInt("chiphi");
                int doanhthu = rs.getInt("doanhthu");
                int loinhuan = doanhthu - chiphi;
                ThongKeTheoThangDTO thongke = new ThongKeTheoThangDTO(thang, chiphi, doanhthu, loinhuan);
                result.add(thongke);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<ThongKeTungNgayTrongThangDTO> getThongKeTungNgayTrongThang(int thang, int nam) {
        ArrayList<ThongKeTungNgayTrongThangDTO> result = new ArrayList<>();
        try {
            String ngayString = nam + "-" + thang + "-" + "01";
            Connection con = MySQLConnect.getConnection();
            String sql = "SELECT \n"
        + "  dates.date AS ngay, \n"
        + "  COALESCE(SUM(detail_entry_form.dongianhap * detail_entry_form.soluongnhap), 0) AS chiphi, \n"
        + "  COALESCE(SUM(bill_product.dongiasanpham * bill_product.soluong), 0) AS doanhthu\n"
        + "FROM (\n"
        + "  SELECT DATE('" + ngayString + "') + INTERVAL c.number DAY AS date\n"
        + "  FROM (\n"
        + "    SELECT 0 AS number UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4\n"
        + "    UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9\n"
        + "    UNION ALL SELECT 10 UNION ALL SELECT 11 UNION ALL SELECT 12 UNION ALL SELECT 13 UNION ALL SELECT 14\n"
        + "    UNION ALL SELECT 15 UNION ALL SELECT 16 UNION ALL SELECT 17 UNION ALL SELECT 18 UNION ALL SELECT 19\n"
        + "    UNION ALL SELECT 20 UNION ALL SELECT 21 UNION ALL SELECT 22 UNION ALL SELECT 23 UNION ALL SELECT 24\n"
        + "    UNION ALL SELECT 25 UNION ALL SELECT 26 UNION ALL SELECT 27 UNION ALL SELECT 28 UNION ALL SELECT 29\n"
        + "    UNION ALL SELECT 30\n"
        + "  ) AS c\n"
        + "  WHERE DATE('" + ngayString + "') + INTERVAL c.number DAY <= LAST_DAY('" + ngayString + "')\n"
        + ") AS dates\n"
        + "LEFT JOIN entry_form ON DATE(entry_form.ngaynhap) = dates.date\n"
        + "LEFT JOIN detail_entry_form ON entry_form.maphieunhap = detail_entry_form.maphieunhap\n"
        + "LEFT JOIN bill ON DATE(bill.ngaymua) = dates.date\n"
        + "LEFT JOIN bill_product ON bill.mabill = bill_product.mabill\n"
        + "GROUP BY dates.date\n"
        + "ORDER BY dates.date;";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Date ngay = rs.getDate("ngay");
                int chiphi = rs.getInt("chiphi");
                int doanhthu = rs.getInt("doanhthu");
                int loinhuan = doanhthu - chiphi;
                ThongKeTungNgayTrongThangDTO tn = new ThongKeTungNgayTrongThangDTO(ngay, chiphi, doanhthu, loinhuan);
                result.add(tn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<ThongKeTungNgayTrongThangDTO> getThongKe7NgayGanNhat() {
        ArrayList<ThongKeTungNgayTrongThangDTO> result = new ArrayList<>();
        try {
            Connection con = MySQLConnect.getConnection();
            String sql = """
                         WITH RECURSIVE dates(date) AS (
                            SELECT DATE_SUB(CURDATE(), INTERVAL 7 DAY)
                            UNION ALL
                            SELECT DATE_ADD(date, INTERVAL 1 DAY)
                            FROM dates
                            WHERE date < CURDATE()
                            )
                            SELECT 
                            dates.date AS ngay,
                            COALESCE(SUM(bill_product.dongiasanpham * bill_product.soluong), 0) AS doanhthu,
                            COALESCE(SUM(detail_entry_form.dongianhap * detail_entry_form.soluongnhap), 0) AS chiphi
                            FROM dates
                            LEFT JOIN bill ON DATE(bill.ngaymua) = dates.date
                            LEFT JOIN bill_product ON bill.mabill = bill_product.mabill
                            LEFT JOIN entry_form ON DATE(entry_form.ngaynhap) = dates.date
                            LEFT JOIN detail_entry_form ON entry_form.maphieunhap = detail_entry_form.maphieunhap
                            GROUP BY dates.date
                            ORDER BY dates.date;""";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Date ngay = rs.getDate("ngay");
                int chiphi = rs.getInt("chiphi");
                int doanhthu = rs.getInt("doanhthu");
                int loinhuan = doanhthu - chiphi;
                ThongKeTungNgayTrongThangDTO tn = new ThongKeTungNgayTrongThangDTO(ngay, chiphi, doanhthu, loinhuan);
                result.add(tn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<ThongKeTungNgayTrongThangDTO> getThongKeTuNgayDenNgay(String star, String end) {
        ArrayList<ThongKeTungNgayTrongThangDTO> result = new ArrayList<>();
        try {
            Connection con = MySQLConnect.getConnection();
            String setStar = "SET @start_date = '" + star + "'";
            String setEnd = "SET @end_date = '" + end + "'  ;";
            String sqlSelect = 
                "SELECT \n" +
                "  dates.date AS ngay, \n" +
                "  COALESCE(SUM(detail_entry_form.dongianhap * detail_entry_form.soluongnhap), 0) AS chiphi, \n" +
                "  COALESCE(SUM(bill_product.dongiasanpham * bill_product.soluong), 0) AS doanhthu\n" +
                "FROM (\n" +
                "  SELECT DATE_ADD(? , INTERVAL c.number DAY) AS date\n" + // dùng ? cho @start_date
                "  FROM (\n" +
                "    SELECT a.number + b.number * 31 AS number\n" +
                "    FROM (\n" +
                "      SELECT 0 AS number UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5\n" +
                "      UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9 UNION ALL SELECT 10 UNION ALL SELECT 11\n" +
                "      UNION ALL SELECT 12 UNION ALL SELECT 13 UNION ALL SELECT 14 UNION ALL SELECT 15 UNION ALL SELECT 16 UNION ALL SELECT 17\n" +
                "      UNION ALL SELECT 18 UNION ALL SELECT 19 UNION ALL SELECT 20 UNION ALL SELECT 21 UNION ALL SELECT 22 UNION ALL SELECT 23\n" +
                "      UNION ALL SELECT 24 UNION ALL SELECT 25 UNION ALL SELECT 26 UNION ALL SELECT 27 UNION ALL SELECT 28 UNION ALL SELECT 29\n" +
                "      UNION ALL SELECT 30\n" +
                "    ) AS a\n" +
                "    CROSS JOIN (\n" +
                "      SELECT 0 AS number UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4\n" +
                "      UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9 UNION ALL SELECT 10\n" +
                "    ) AS b\n" +
                "  ) AS c\n" +
                "  WHERE DATE_ADD(? , INTERVAL c.number DAY) <= ?\n" + // ? = @start_date, ? = @end_date
                ") AS dates\n" +
                "LEFT JOIN entry_form ON DATE(entry_form.ngaynhap) = dates.date\n" +
                "LEFT JOIN detail_entry_form ON entry_form.maphieunhap = detail_entry_form.maphieunhap\n" +
                "LEFT JOIN bill ON DATE(bill.ngaymua) = dates.date\n" +
                "LEFT JOIN bill_product ON bill.mabill = bill_product.mabill\n" +
                "GROUP BY dates.date\n" +
                "ORDER BY dates.date;";

            PreparedStatement pstStart = con.prepareStatement(setStar);
            PreparedStatement pstEnd = con.prepareStatement(setEnd);
            PreparedStatement pstSelect = con.prepareStatement(sqlSelect);
            pstSelect.setString(1,setStar );
            pstSelect.setString(2,setStar );
            pstSelect.setString(3, setEnd);
            pstStart.execute();
            pstEnd.execute();
            ResultSet rs = pstSelect.executeQuery();
            while (rs.next()) {
                Date ngay = rs.getDate("ngay");
                int chiphi = rs.getInt("chiphi");
                int doanhthu = rs.getInt("doanhthu");
                int loinhuan = doanhthu - chiphi;
                ThongKeTungNgayTrongThangDTO tn = new ThongKeTungNgayTrongThangDTO(ngay, chiphi, doanhthu, loinhuan);
                result.add(tn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}
