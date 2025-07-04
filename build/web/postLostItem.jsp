<%@ page session="true" %>
<%@ include file="navbar.jsp" %>

<%
    if (session.getAttribute("user") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Post Lost Item</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #FFF3E0, #FFECB3);
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            flex-direction: column;
            overflow-x: hidden;
        }

        h2 {
            margin-top: 30px;
            font-size: 2.2rem;
            background: linear-gradient(90deg, #FF7043, #FF8A65);
            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;
            text-align: center;
            margin-bottom: 30px;
        }

        form {
            background: rgba(255, 255, 255, 0.5);
            backdrop-filter: blur(20px);
            -webkit-backdrop-filter: blur(20px);
            border: 1px solid rgba(255, 255, 255, 0.2);
            border-radius: 20px;
            padding: 30px 40px;
            box-shadow: 0 8px 30px rgba(0, 0, 0, 0.1);
            width: 90%;
            max-width: 650px;
            animation: fadeInUp 0.8s ease-out;
        }

        label {
            font-weight: bold;
            color: #333;
            margin-bottom: 5px;
            display: block;
        }

        input[type="text"],
        input[type="date"],
        textarea {
            width: 100%;
            padding: 12px;
            margin-bottom: 20px;
            border-radius: 12px;
            border: 1px solid #ccc;
            font-size: 16px;
            background-color: rgba(255, 255, 255, 0.8);
            transition: 0.3s ease;
        }

        input[type="text"]:focus,
        input[type="date"]:focus,
        textarea:focus {
            outline: none;
            border-color: #FF7043;
            box-shadow: 0 0 0 4px rgba(255, 112, 67, 0.2);
        }

        input[type="file"] {
            margin-bottom: 20px;
        }

        input[type="submit"] {
            background: linear-gradient(to right, #FF7043, #FF8A65);
            color: white;
            padding: 14px 25px;
            border: none;
            border-radius: 30px;
            cursor: pointer;
            font-size: 16px;
            font-weight: bold;
            width: 100%;
            transition: all 0.3s ease;
            box-shadow: 0 4px 14px rgba(255, 112, 67, 0.4);
        }

        input[type="submit"]:hover {
            transform: scale(1.03);
            box-shadow: 0 6px 20px rgba(255, 112, 67, 0.6);
        }

        @keyframes fadeInUp {
            from {
                opacity: 0;
                transform: translateY(40px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        @media (max-width: 768px) {
            form {
                padding: 20px;
            }

            h2 {
                font-size: 1.8rem;
            }
        }
    </style>
</head>
<body>
    <h2>Post a Lost Item</h2>
    <form action="PostLostItemServlet" method="post">
        <label for="itemName">Item Name:</label>
        <input type="text" id="itemName" name="itemName" required>

        <label for="description">Description:</label>
        <textarea id="description" name="description" rows="4" required></textarea>

        <label for="location">Location:</label>
        <input type="text" id="location" name="location" required>

        <label for="dateLost">Date Lost:</label>
        <input type="date" id="dateLost" name="dateLost" required>

        <input type="submit" value="Submit">
    </form>
</body>
</html>
