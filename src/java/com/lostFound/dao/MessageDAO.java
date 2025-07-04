package com.lostfound.dao;

import com.lostfound.db.DBConnection;
import com.lostfound.model.Message;
import java.sql.*;
import java.util.*;

public class MessageDAO {

   public static List<Message> getChatMessages(int itemId, int senderId, int receiverId) {
    List<Message> messages = new ArrayList<>();
    String sql = "SELECT * FROM messages WHERE item_id = ? AND ((sender_id = ? AND receiver_id = ?) OR (sender_id = ? AND receiver_id = ?)) ORDER BY timestamp ASC";
    
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, itemId);
        stmt.setInt(2, senderId);
        stmt.setInt(3, receiverId);
        stmt.setInt(4, receiverId);
        stmt.setInt(5, senderId);
        
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Message message = new Message();
                message.setSenderId(rs.getInt("sender_id"));
                message.setReceiverId(rs.getInt("receiver_id"));
                message.setMessage(rs.getString("message"));
                message.setTimestamp(rs.getTimestamp("timestamp"));
                messages.add(message);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Handle exceptions appropriately
    }
    return messages;
   }


    public static void saveMessage(int senderId, int receiverId, String messageContent, int itemId) {
        String sql = "INSERT INTO messages (sender_id, receiver_id, message_content, item_id, timestamp) VALUES (?, ?, ?, ?, NOW())";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, senderId);
            stmt.setInt(2, receiverId);
            stmt.setString(3, messageContent);
            stmt.setInt(4, itemId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static List<Message> getChatMessagesBetweenUsers(int user1Id, int user2Id) {
    List<Message> messages = new ArrayList<>();
    String sql = "SELECT * FROM messages WHERE " +
                 "(sender_id = ? AND receiver_id = ?) OR (sender_id = ? AND receiver_id = ?) " +
                 "ORDER BY timestamp ASC";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, user1Id);
        stmt.setInt(2, user2Id);
        stmt.setInt(3, user2Id);
        stmt.setInt(4, user1Id);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Message msg = new Message();
            msg.setSenderId(rs.getInt("sender_id"));
            msg.setReceiverId(rs.getInt("receiver_id"));
            msg.setContent(rs.getString("content"));
            msg.setTimestamp(rs.getTimestamp("timestamp"));
            messages.add(msg);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return messages;
    }

   public static void sendMessage(int senderId, int receiverId, int itemId, String messageContent) {
    try (Connection conn = DBConnection.getConnection()) {
        String sql = "INSERT INTO messages (sender_id, receiver_id, item_id, content, timestamp) VALUES (?, ?, ?, ?, NOW())";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, senderId);
        stmt.setInt(2, receiverId);
        stmt.setInt(3, itemId); // << pass itemId here
        stmt.setString(4, messageContent);
        stmt.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
}


}
