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


// package DAO;

// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.sql.Statement;
// import java.util.logging.Level;
// import java.util.logging.Logger;

// /**
// *
// * @author Minh Minion
// */
// public class MySQLConnect {
//    private String user = "root";
//    private String password="";
//    private String url="jdbc:mysql://localhost/sieuthimini?useUnicode=true&characterEncoding=UTF-8";
//    private Connection conn = null;
//    private Statement st = null;
   
//    public void Connect()
//    {
//         try {
//            Class.forName("com.mysql.jdbc.Driver");
//            conn = DriverManager.getConnection(url, user, password);
//        } catch (ClassNotFoundException | SQLException ex) {
// //            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    public void disConnect()
//    { 
//        try{
//            st.close();
//            conn.close();
//        }catch (SQLException E){}
//    }
   
//    public ResultSet executeQuery(String sql)
//    {
//        ResultSet rs = null;
//        try {
//            Connect();
//            st = conn.createStatement();
//            rs = st.executeQuery(sql);
//        } catch (SQLException ex) {
//            Logger.getLogger(MySQLConnect.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return rs;
//    }
   
//    public void executeUpdate(String sql)
//    {
//        try {
//            Connect();
//            st = conn.createStatement();
//            st.executeUpdate(sql);
//            disConnect();
//        } catch (SQLException ex) {
//            Logger.getLogger(MySQLConnect.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    public Connection getConnection()
//    {
//        Connect();
//        return conn;
//    }
//    public boolean isConnect()
//    {
//        return conn!=null?true:false;
//    }
// }
