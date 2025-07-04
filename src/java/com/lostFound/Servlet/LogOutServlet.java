package com.lostfound.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/LogoutServlet") // Define the URL pattern for the servlet
public class LogOutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Constructor
    public LogOutServlet() {
        super();
    }

    // Process the HTTP GET request to log out
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get the current session
        HttpSession session = request.getSession(false); // Don't create a new session if none exists

        if (session != null) {
            // Invalidate the session, effectively logging out the user
            session.invalidate();
        }

        // Redirect the user to the home page or login page after logging out
        response.sendRedirect("index.jsp"); // You can redirect to any page, such as login.jsp
    }

    // You can also handle POST if needed, but usually, GET is sufficient for logging out
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
