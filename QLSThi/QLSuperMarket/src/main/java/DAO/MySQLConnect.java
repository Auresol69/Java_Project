package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Minh Minion (refactored by ChatGPT)
 */
public class MySQLConnect {
    private static final String user = "root";
    private static final String password = "";
    private static final String url = "jdbc:mysql://localhost:3307/sieuthi_mini?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";  

    public static Connection getConnection() {
        Connection result = null;
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            result = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Can not connect to the database","ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return result;
    }

    public static void closeConnection(Connection c) {
        try {
            if (c!=null)
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Kiểm tra kết nối
        Connection conn = MySQLConnect.getConnection();
        if (conn != null) {
            System.out.println("Kết nối thành công!");
            MySQLConnect.closeConnection(conn);  // Đóng kết nối sau khi sử dụng
        } else {
            System.out.println("Kết nối thất bại!");
        }
    }
        
}
