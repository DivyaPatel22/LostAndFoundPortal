package com.lostfound.servlet;

import com.lostfound.dao.FoundItemDAO;
import com.lostfound.model.FoundItem;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class ViewFoundItemsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        List<FoundItem> foundItems = FoundItemDAO.getAllFoundItems();
        req.setAttribute("foundItems", foundItems);
        RequestDispatcher dispatcher = req.getRequestDispatcher("viewFoundItems.jsp");
        dispatcher.forward(req, res);
    }
}
