package com.lostfound.servlet;

import com.lostfound.model.User;
import com.lostfound.dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.*;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = UserDAO.login(username, password);
        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);                 // full user object
            session.setAttribute("userId", user.getId());       // only user ID for easier access
            res.sendRedirect("userDashboard.jsp");
        } else {
            res.getWriter().println("Login failed. Invalid credentials.");
        }
    }
}

