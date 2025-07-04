<%@ page import="com.lostfound.model.FoundItem" %>
<%@ page import="com.lostfound.model.LostItem" %>
<%@ page import="com.lostfound.model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.lostfound.dao.FoundItemDAO" %>
<%@ page import="com.lostfound.dao.LostItemDAO" %>
<%@ page session="true" %>
<%@ include file="navbar.jsp" %>

<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    List<FoundItem> foundItems = FoundItemDAO.getAllFoundItemsWithUser();
    List<LostItem> lostItems = LostItemDAO.getAllLostItemsWithUser();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>All Posted Items</title>
    <style>
        body {
            margin: 0;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #4C6BFF, #00c6ff, #ff6f61);
            background-size: 300% 300%;
            animation: gradientMove 10s ease infinite;
            color: #333;
        }

        .dashboard-container {
            max-width: 900px;
            margin: 50px auto;
            background: #fff;
            padding: 30px;
            border-radius: 16px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.2);
            animation: fadeIn 0.5s ease-out;
        }

        h2 {
            font-size: 2.2rem;
            color: #333;
            text-align: center;
            margin-bottom: 10px;
        }

        .user-welcome {
            text-align: center;
            color: #4C6BFF;
            font-weight: bold;
            font-size: 1.2rem;
            margin-bottom: 30px;
        }

        .post-feed {
            display: flex;
            flex-direction: column;
            gap: 20px;
        }

        .post-card {
            background-color: #fefefe;
            border-radius: 12px;
            padding: 20px;
            box-shadow: 0 6px 16px rgba(0, 0, 0, 0.1);
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }

        .post-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 12px 30px rgba(0, 0, 0, 0.15);
        }

        .post-type {
            font-size: 0.85rem;
            font-weight: bold;
            text-transform: uppercase;
            color: #fff;
            background-color: #4C6BFF;
            display: inline-block;
            padding: 5px 10px;
            border-radius: 8px;
            margin-bottom: 10px;
        }

        .post-type.lost {
            background-color: #ff6f61;
        }

        .post-card h3 {
            font-size: 1.4rem;
            margin-bottom: 8px;
            color: #333;
        }

        .post-card p {
            font-size: 1rem;
            color: #555;
        }

        .post-date {
            margin-top: 10px;
            font-size: 0.85rem;
            color: #888;
        }

        .logout-btn {
            display: block;
            text-align: center;
            margin-top: 40px;
            color: #4C6BFF;
            font-weight: bold;
            text-decoration: none;
        }

        .logout-btn:hover {
            text-decoration: underline;
        }

        @keyframes gradientMove {
            0% { background-position: 0% 50%; }
            50% { background-position: 100% 50%; }
            100% { background-position: 0% 50%; }
        }

        @keyframes fadeIn {
            0% { opacity: 0; transform: translateY(10px); }
            100% { opacity: 1; transform: translateY(0); }
        }
    </style>
</head>
<body>

<div class="dashboard-container">
    <h2>All Posted Items</h2>
    <p class="user-welcome">Hello, <%= user.getUsername() %></p>

    <div class="post-feed">

        <% for (FoundItem item : foundItems) { %>
            <div class="post-card">
                <div class="post-type">Found Item</div>
                <h3><%= item.getItemName() %></h3>
                <p><%= item.getDescription() %></p>
                <p><strong>Posted by:</strong> <%= item.getUsername() %></p>
                <div class="post-date">Posted on: <%= item.getDateFound() %></div>
            </div>
        <% } %>

        <% for (LostItem item : lostItems) { %>
            <div class="post-card">
                <div class="post-type lost">Lost Item</div>
                <h3><%= item.getItemName() %></h3>
                <p><%= item.getDescription() %></p>
                <p><strong>Posted by:</strong> <%= item.getUsername() %></p>
                <div class="post-date">Posted on: <%= item.getDateLost() %></div>
            </div>
        <% } %>

        <% if (foundItems.isEmpty() && lostItems.isEmpty()) { %>
            <p style="text-align:center; font-size: 1.1rem; color: #777;">No items have been posted yet.</p>
        <% } %>

    </div>

    <a href="logout.jsp" class="logout-btn">Logout</a>
</div>

</body>
</html>
