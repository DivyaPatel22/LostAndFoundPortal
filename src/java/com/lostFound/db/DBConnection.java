package com.lostfound.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/lostfound", "root", "123456");
        } catch (Exception e) {
            throw new SQLException("Database connection error", e);
        }
    }
}
