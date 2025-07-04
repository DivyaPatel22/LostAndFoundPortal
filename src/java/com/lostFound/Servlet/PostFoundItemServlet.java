package com.lostfound.servlet;

import com.lostfound.model.FoundItem;
import com.lostfound.dao.FoundItemDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;

@MultipartConfig(
    fileSizeThreshold = 1024 * 1024, // 1MB
    maxFileSize = 1024 * 1024 * 5,   // 5MB
    maxRequestSize = 1024 * 1024 * 10 // 10MB
)
public class PostFoundItemServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            res.sendRedirect("login.jsp");
            return;
        }

        try {
            com.lostfound.model.User user = (com.lostfound.model.User) session.getAttribute("user");

            // Handle image upload
//            Part filePart = req.getPart("image"); // Make sure form has enctype="multipart/form-data"
//            String fileName = extractFileName(filePart);
//            String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";
//
//            // Ensure directory exists
//            File uploadDir = new File(uploadPath);
//            if (!uploadDir.exists()) uploadDir.mkdir();
//
//            // Save the file
//            String filePath = uploadPath + File.separator + fileName;
//            filePart.write(filePath);
//
//            // Create relative path to save in DB (used in <img src=...>)
//            String imagePath = "uploads/" + fileName;

            // Set item fields
            FoundItem item = new FoundItem();
            item.setItemName(req.getParameter("itemName"));
            item.setDescription(req.getParameter("description"));
            item.setLocation(req.getParameter("location"));
            item.setDateFound(req.getParameter("dateFound"));
            item.setUserId(user.getId());
            item.setUsername(user.getUsername()); // Optional
//            item.setImagePath(imagePath); // ✅ Save image path in DB

            FoundItemDAO.saveFoundItem(item);
            res.sendRedirect("viewFoundItems.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            res.getWriter().println("Error posting found item.");
        }
    }

    // Helper method to extract file name
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        for (String token : contentDisp.split(";")) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length() - 1);
            }
        }
        return "";
    }
}
