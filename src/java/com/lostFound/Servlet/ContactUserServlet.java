package com.lostfound.servlet;

import com.lostfound.model.User;
import com.lostfound.dao.LostItemDAO;
import com.lostfound.dao.FoundItemDAO;
import com.lostfound.dao.MessageDAO;
import com.lostfound.model.Message;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ContactUserServlet extends HttpServlet {
    // Handle GET request to display chat
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int itemId = Integer.parseInt(req.getParameter("itemId"));
        String itemType = req.getParameter("itemType");

        // Get item details based on whether it's lost or found
        int ownerId = 0;
        if (itemType.equals("lost")) {
            ownerId = LostItemDAO.getItemOwnerId(itemId);
        } else if (itemType.equals("found")) {
            ownerId = FoundItemDAO.getItemOwnerId(itemId);
        }

        // Get the current user from session
        User user = (User) req.getSession().getAttribute("user");
        int senderId = user.getId();

        // Get the chat messages between sender and receiver
        List<Message> chatMessages = MessageDAO.getChatMessages(itemId, senderId, ownerId);

        // Set chat messages in request scope
        req.setAttribute("chatMessages", chatMessages);
        req.setAttribute("itemId", itemId);
        req.setAttribute("itemType", itemType);

        // Forward to chat page
        req.getRequestDispatcher("/contactUser.jsp").forward(req, res);
    }

    // Handle POST request to send a new message
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    
    HttpSession session = request.getSession(false);
    if (session == null || session.getAttribute("userId") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    int senderId = (int) session.getAttribute("userId");
    int itemId = Integer.parseInt(request.getParameter("itemId"));
    String itemType = request.getParameter("itemType");
    String message = request.getParameter("message");

    // Fetch the receiver ID (i.e., the item's owner)
    int receiverId = new FoundItemDAO().getItemOwnerId(itemId); // adjust based on itemType
     receiverId = 0;
if ("lost".equals(itemType)) {
    receiverId = LostItemDAO.getItemOwnerId(itemId);
} else if ("found".equals(itemType)) {
    receiverId = FoundItemDAO.getItemOwnerId(itemId);
}

    // Save message
    MessageDAO.sendMessage(senderId, receiverId, itemId, message);


    // Load updated messages for display
    List<Message> chatMessages = MessageDAO.getChatMessages(itemId, senderId, receiverId);


    // Pass data to JSP
    request.setAttribute("chatMessages", chatMessages);
    request.setAttribute("itemId", itemId);
    request.setAttribute("itemType", itemType);
    request.setAttribute("ownerId", receiverId);

    RequestDispatcher dispatcher = request.getRequestDispatcher("contactUser.jsp");
    dispatcher.forward(request, response);
}



}
