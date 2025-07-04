package com.lostfound.dao;

import com.lostfound.db.DBConnection;
import com.lostfound.model.FoundItem;
import com.lostfound.model.User;
import java.sql.*;
import java.util.*;

public class FoundItemDAO {

    public static void saveFoundItem(FoundItem item) throws Exception {
        Connection con = DBConnection.getConnection();
        String sql = "INSERT INTO found_items (item_name, description, location, date_found, user_id, image_path) VALUES (?, ?, ?, ?, ?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, item.getItemName());
        ps.setString(2, item.getDescription());
        ps.setString(3, item.getLocation());
        ps.setString(4, item.getDateFound());
        ps.setInt(5, item.getUserId());
         ps.setString(6, item.getImagePath());
        ps.executeUpdate();
    }

    public static List<FoundItem> getAllFoundItems() {
        List<FoundItem> list = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT * FROM found_items";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                FoundItem item = new FoundItem();
                item.setId(rs.getInt("id"));
                item.setItemName(rs.getString("item_name"));
                item.setDescription(rs.getString("description"));
                item.setLocation(rs.getString("location"));
                item.setDateFound(rs.getString("date_found"));
                item.setUserId(rs.getInt("user_id"));
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
            // don't load password unless needed
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return user;
}


    // ✅ Returns full FoundItem object based on ID
    public static FoundItem getFoundItemById(int itemId) {
        FoundItem item = null;
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT * FROM found_items WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, itemId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                item = new FoundItem();
                item.setId(rs.getInt("id"));
                item.setItemName(rs.getString("item_name"));
                item.setDescription(rs.getString("description"));
                item.setLocation(rs.getString("location"));
                item.setDateFound(rs.getString("date_found"));
                item.setUserId(rs.getInt("user_id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }
    public static List<FoundItem> getAllFoundItemsWithUser() {
        List<FoundItem> list = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            // SQL with JOIN to fetch the username along with the found item details
            String sql = "SELECT f.*, u.username FROM found_items f JOIN users u ON f.user_id = u.id ORDER BY f.date_found DESC";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                FoundItem item = new FoundItem();
                item.setId(rs.getInt("f.id"));
                item.setItemName(rs.getString("f.item_name"));
                item.setDescription(rs.getString("f.description"));
                item.setLocation(rs.getString("f.location"));
                item.setDateFound(rs.getString("f.date_found"));
                item.setUserId(rs.getInt("f.user_id"));
                item.setUsername(rs.getString("u.username"));  // Fetch the username from the users table
                list.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
}

    public static List<FoundItem> getFoundItemsByUser(int userId) {
    List<FoundItem> items = new ArrayList<>();
    try {
        Connection con = DBConnection.getConnection();
        String sql = "SELECT * FROM found_items WHERE user_id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            FoundItem item = new FoundItem();
            item.setId(rs.getInt("id"));
            item.setItemName(rs.getString("item_name"));
            item.setDescription(rs.getString("description"));
            item.setLocation(rs.getString("location"));
            item.setDateFound(rs.getString("date_found"));
            item.setUserId(rs.getInt("user_id"));
            items.add(item);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return items;
}


    // ✅ Returns only the owner's email for the given found item
    public static String getItemOwnerEmail(int itemId) {
        String email = null;
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT u.email FROM users u JOIN found_items f ON u.id = f.user_id WHERE f.id = ?";
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
    int ownerId = -1;

    String query = "SELECT user_id FROM found_items WHERE id = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {

        stmt.setInt(1, itemId);

        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                ownerId = rs.getInt("user_id");
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return ownerId;
}

}
