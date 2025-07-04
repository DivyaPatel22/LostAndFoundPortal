<%@ page import="java.util.*, com.lostfound.dao.LostItemDAO, com.lostfound.model.LostItem" %>
<%@ include file="navbar.jsp" %>

<html>
<head>
    <title>Lost Items</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #f4f6f8;
            margin: 0;
            padding: 0;
            color: #333;
        }

        h2 {
            text-align: center;
            margin: 40px 0 20px;
            color: #2c3e50;
        }

        table {
            margin: 0 auto 60px;
            width: 90%;
            border-collapse: collapse;
            background-color: #ffffff;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            border-radius: 8px;
            overflow: hidden;
        }

        th, td {
            padding: 15px 20px;
            text-align: left;
            border-bottom: 1px solid #e0e0e0;
        }

        th {
            background-color: #34495e;
            color: white;
            text-transform: uppercase;
            font-size: 0.9em;
        }

        tr:hover {
            background-color: #f2f2f2;
        }

        .contact-btn {
            background-color: #27ae60;
            color: white;
            border: none;
            padding: 8px 14px;
            border-radius: 4px;
            cursor: pointer;
            font-size: 14px;
            transition: background-color 0.3s;
        }

        .contact-btn:hover {
            background-color: #1e8449;
        }

        footer {
            text-align: center;
            padding: 15px;
            background-color: #34495e;
            color: white;
            position: fixed;
            bottom: 0;
            width: 100%;
            font-size: 0.95em;
        }
    </style>
</head>
<body>
    <h2>Lost Items</h2>
    <table>
        <tr>
            <th>Item Name</th>
            <th>Description</th>
            <th>Location</th>
            <th>Date Lost</th>
            <th>Action</th>
        </tr>
        <%
            List<LostItem> lostItems = LostItemDAO.getAllLostItems();
            for (LostItem item : lostItems) {
        %>
        <tr>
            <td><%= item.getItemName() %></td>
            <td><%= item.getDescription() %></td>
            <td><%= item.getLocation() %></td>
            <td><%= item.getDateLost() %></td>
            <td>
                <a href="contactUser?itemId=<%= item.getId() %>&itemType=lost">
                    <button class="contact-btn">Contact Owner</button>
                </a>
            </td>
        </tr>
        <% } %>
    </table>
    <footer>
        Lost and Found System &copy; 2025
    </footer>
</body>
</html>
