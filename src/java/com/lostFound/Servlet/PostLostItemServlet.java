package com.lostfound.servlet;

import com.lostfound.model.LostItem;
import com.lostfound.model.User;
import com.lostfound.dao.LostItemDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class PostLostItemServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            res.sendRedirect("login.jsp");
            return;
        }

        try {
            User user = (User) session.getAttribute("user");

            LostItem item = new LostItem();
            item.setItemName(req.getParameter("itemName"));
            item.setDescription(req.getParameter("description"));
            item.setLocation(req.getParameter("location"));
            item.setDateLost(req.getParameter("dateLost"));
            item.setUserId(user.getId()); // store user_id in DB
            item.setUsername(user.getUsername()); // optional: not stored, just useful in code

            LostItemDAO.saveLostItem(item);

            // Optionally you can set this as an attribute if you want to show it on next page
            // req.setAttribute("postedItem", item);
            // req.getRequestDispatcher("viewLostItems.jsp").forward(req, res);

            res.sendRedirect("viewLostItems.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            res.getWriter().println("Error posting lost item.");
        }
    }
}
