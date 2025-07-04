<%@ include file="navbar.jsp" %>

<html>
<head>
    <title>Admin Dashboard</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: #f4f6f8;
            margin: 0;
            padding: 0;
        }

        h2 {
            background-color: #2c3e50;
            color: white;
            margin: 0;
            padding: 20px;
            text-align: center;
        }

        ul {
            list-style: none;
            padding: 0;
            margin: 40px auto;
            width: fit-content;
            text-align: center;
        }

        li {
            margin: 20px 0;
        }

        a {
            text-decoration: none;
            color: white;
            background-color: #3498db;
            padding: 12px 24px;
            border-radius: 6px;
            font-weight: bold;
            transition: background-color 0.3s, box-shadow 0.3s;
            display: inline-block;
        }

        a:hover {
            background-color: #2980b9;
            box-shadow: 0px 4px 12px rgba(0, 0, 0, 0.2);
        }
    </style>
</head>
<body>
    <h2>Admin Panel</h2>
    <ul>
        <li><a href="viewLostItems.jsp">View All Lost Items</a></li>
        <li><a href="viewFoundItems.jsp">View All Found Items</a></li>
    </ul>
</body>
</html>
