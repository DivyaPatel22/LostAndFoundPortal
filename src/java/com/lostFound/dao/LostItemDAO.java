package com.lostfound.dao;

import com.lostfound.db.DBConnection;
import com.lostfound.model.LostItem;
import com.lostfound.model.User;

import java.sql.*;
import java.util.*;

public class LostItemDAO {

    public static void saveLostItem(LostItem item) throws Exception {
        Connection con = DBConnection.getConnection();
        String sql = "INSERT INTO lost_items (item_name, description, location, date_lost, user_id) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, item.getItemName());
        ps.setString(2, item.getDescription());
        ps.setString(3, item.getLocation());
        ps.setString(4, item.getDateLost());
        ps.setInt(5, item.getUserId());
        ps.executeUpdate();
    }

    public static List<LostItem> getAllLostItems() {
        List<LostItem> list = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT * FROM lost_items";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                LostItem item = new LostItem();
                item.setId(rs.getInt("id"));
                item.setItemName(rs.getString("item_name"));
                item.setDescription(rs.getString("description"));
                item.setLocation(rs.getString("location"));
                item.setDateLost(rs.getString("date_lost"));
                item.setUserId(rs.getInt("user_id"));
                list.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<LostItem> getAllLostItemsWithUser() {
        List<LostItem> list = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            // SQL with JOIN to fetch the username along with the lost item details
            String sql = "SELECT l.*, u.username FROM lost_items l JOIN users u ON l.user_id = u.id ORDER BY l.date_lost DESC";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                LostItem item = new LostItem();
                item.setId(rs.getInt("l.id"));
                item.setItemName(rs.getString("l.item_name"));
                item.setDescription(rs.getString("l.description"));
                item.setLocation(rs.getString("l.location"));
                item.setDateLost(rs.getString("l.date_lost"));
                item.setUserId(rs.getInt("l.user_id"));
                item.setUsername(rs.getString("u.username"));  // Fetch the username from the users table
                list.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
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
                // password excluded for security
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public static LostItem getLostItemById(int itemId) {
        LostItem item = null;
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT * FROM lost_items WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, itemId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                item = new LostItem();
                item.setId(rs.getInt("id"));
                item.setItemName(rs.getString("item_name"));
                item.setDescription(rs.getString("description"));
                item.setLocation(rs.getString("location"));
                item.setDateLost(rs.getString("date_lost"));
                item.setUserId(rs.getInt("user_id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }

    public static List<LostItem> getLostItemsByUser(int userId) {
        List<LostItem> items = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT * FROM lost_items WHERE user_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                LostItem item = new LostItem();
                item.setId(rs.getInt("id"));
                item.setItemName(rs.getString("item_name"));
                item.setDescription(rs.getString("description"));
                item.setLocation(rs.getString("location"));
                item.setDateLost(rs.getString("date_lost"));
                item.setUserId(rs.getInt("user_id"));
                items.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }

    public static String getItemOwnerEmail(int itemId) {
        String email = null;
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT u.email FROM users u JOIN lost_items l ON u.id = l.user_id WHERE l.id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, itemId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                email = rs.getString("email");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return email;
    }

    public static int getItemOwnerId(int itemId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
