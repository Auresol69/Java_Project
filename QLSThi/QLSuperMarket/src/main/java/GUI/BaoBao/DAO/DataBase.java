package GUI.BaoBao.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import GUI.BaoBao.ExtendClasses.MessageBox;

public class DataBase {
    private final String URL = "jdbc:mysql://localhost:3306/sieuthi_mini";
    private final String USER = "root";
    private final String PASSWORD = "123";

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Object[]> selectQuery(String sql, Object... params) {
        List<Object[]> data = new ArrayList<>();

        try (Connection connection = getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);) {
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }
            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; ++i) {
                    row[i - 1] = rs.getObject(i);
                }
                data.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public int insertQuery(String sql, Object... params) {
        int generatedId = -1;
        ResultSet rs = null;

        try (Connection connection = getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Gán giá trị cho PreparedStatement
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }

            // Thực hiện câu lệnh INSERT
            int rowsInserted = stmt.executeUpdate();

            // Nếu có dòng được chèn, lấy khóa sinh ra
            if (rowsInserted > 0) {
                rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    generatedId = rs.getInt(1); // Lấy khóa chính tự động sinh ra
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Có thể cải tiến bằng cách log lỗi hoặc thông báo chi tiết hơn
        } finally {
            // Đảm bảo đóng ResultSet để tránh rò rỉ tài nguyên
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return generatedId;
    }

    public int updateQuery(String sql, Object... params) {
        int rowsAffected = 0;

        try (Connection connection = getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }
            rowsAffected = stmt.executeUpdate();
        } catch (SQLException e) {
            if ("45000".equals(e.getSQLState())) {
                MessageBox.showError("Thất bại: " + e.getMessage());
            } else {
                MessageBox.showError("Lỗi SQL: " + e.getMessage());
            }
        }
        return rowsAffected;
    }
}