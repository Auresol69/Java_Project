package com.sieuthimini.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase {
    private final String URL = "jdbc:mysql://localhost:3306/sieuthimini";
    private final String USER = "root";
    private final String PASSWORD = "";

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

    public void insertQuery(String sql, Object... params) {
        try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Insert thành công!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}