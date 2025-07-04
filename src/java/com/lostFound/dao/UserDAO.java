package com.lostfound.dao;

import com.lostfound.model.User;
import com.lostfound.db.DBConnection;
import java.sql.*;

public class UserDAO {

    public static boolean registerUser(User user) {
        try {
            Connection con = DBConnection.getConnection();
            String sql = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static User login(String username, String password) {
        User user = null;
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT * FROM users WHERE username=? AND password=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public static User getUserByUsernameAndPassword(String username, String password) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
     public static User getUserById(int id) {
        User user = null;
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT * FROM users WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                // Optional: don't load password here unless needed
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
