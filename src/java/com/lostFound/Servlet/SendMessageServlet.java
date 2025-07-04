package com.lostfound.servlet;


import com.lostfound.dao.MessageDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/SendMessageServlet")
public class SendMessageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int senderId = Integer.parseInt(request.getParameter("senderId"));
        int receiverId = Integer.parseInt(request.getParameter("receiverId"));
        int itemId = Integer.parseInt(request.getParameter("itemId"));
        String message = request.getParameter("message");

        MessageDAO.saveMessage(senderId, receiverId, message, itemId);
        response.sendRedirect("chat.jsp?itemId=" + itemId + "&receiverId=" + receiverId);
    }
}
