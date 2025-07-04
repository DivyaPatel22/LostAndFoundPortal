package com.lostfound.servlet;

import com.lostfound.dao.LostItemDAO;
import com.lostfound.model.LostItem;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class ViewLostItemsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        List<LostItem> lostItems = LostItemDAO.getAllLostItems();
        req.setAttribute("lostItems", lostItems);
        RequestDispatcher dispatcher = req.getRequestDispatcher("viewLostItems.jsp");
        dispatcher.forward(req, res);
    }
}
