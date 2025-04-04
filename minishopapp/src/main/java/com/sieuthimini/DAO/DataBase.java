package com.sieuthimini.DAO;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBase {
    private static final String URL = "jdbc:mysql://localhost:3306/tree_shopping";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ResultSet SelectQuery() {
        try {
            Connection connection = getConnection();
            if (connection == null)
                return null;
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM customer";
            return statement.executeQuery(sql);

        } catch (SQLException e) {
            e.printStackTrace(); // In ra lỗi để debug
        }
        return null;
    }

    // Test
    // public static void main(String[] args) {
    // Connection connection = getConnection();
    // }
}
