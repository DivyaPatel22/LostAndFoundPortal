package com.lostfound.servlet;

import com.lostfound.model.User;
import com.lostfound.dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AdminServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        
        // Check admin credentials
        User admin = UserDAO.getUserByUsernameAndPassword(username, password);
        if (admin != null && admin.getRole().equals("admin")) {
            req.getSession().setAttribute("user", admin);
            res.sendRedirect("adminDashboard.jsp");
        } else {
            res.sendRedirect("login.jsp?error=Invalid credentials");
        }
    }
}
