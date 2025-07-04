<%@ page import="java.util.*, com.lostfound.model.Message" %>
<%@ include file="navbar.jsp" %>

<%
    Integer loggedInUserId = (Integer) session.getAttribute("userId");
    
    if (loggedInUserId == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    String ownerIdStr = request.getParameter("ownerId");
    int ownerId = 0;
    if (ownerIdStr != null && !ownerIdStr.isEmpty()) {
        ownerId = Integer.parseInt(ownerIdStr);
    }

    int itemId = 0;
    if (request.getParameter("itemId") != null) {
        itemId = Integer.parseInt(request.getParameter("itemId"));
    }

    String itemType = request.getParameter("itemType");

    List<Message> chatMessages = (List<Message>) request.getAttribute("chatMessages");
%>

<html>
<head>
    <title>Chat with Item Owner</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #f4f6f8;
            margin: 0;
            padding: 0;
        }

        .chat-container {
            max-width: 600px;
            margin: 40px auto;
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            display: flex;
            flex-direction: column;
            height: 70vh;
            overflow: hidden;
        }

        .messages {
            flex: 1;
            overflow-y: auto;
            padding: 10px;
            margin-bottom: 20px;
            border-bottom: 1px solid #ddd;
        }

        .message {
            margin-bottom: 10px;
            padding: 8px 15px;
            border-radius: 18px;
            max-width: 80%;
        }

        .message.owner {
            background-color: #4CAF50;
            color: white;
            align-self: flex-start;
        }

        .message.user {
            background-color: #2196F3;
            color: white;
            align-self: flex-end;
        }

        .input-area {
            display: flex;
            align-items: center;
            justify-content: space-between;
        }

        .input-area input {
            width: 80%;
            padding: 12px 15px;
            border: 1px solid #ccc;
            border-radius: 18px;
            font-size: 16px;
        }

        .input-area button {
            background-color: #2c3e50;
            color: white;
            border: none;
            padding: 12px 20px;
            border-radius: 18px;
            font-size: 16px;
            cursor: pointer;
            margin-left: 10px;
        }

        .input-area button:hover {
            background-color: #1a252f;
        }
    </style>
</head>
<body>
    <div class="chat-container">
        <h2>Chat with Item Owner</h2>
        
        <!-- Displaying messages -->
        <div class="messages">
            <% if (chatMessages != null && !chatMessages.isEmpty()) {
                for (Message msg : chatMessages) {
                    boolean isSender = msg.getSenderId() == loggedInUserId;
            %>
                <div class="message <%= isSender ? "user" : "owner" %>">
                    <%= msg.getContent() %>
                </div>
            <%  }
               } else { %>
                <p>No messages yet. Start the conversation!</p>
            <% } %>
        </div>

        <!-- Input area for sending new messages -->
        <form action="ContactUserServlet" method="post">
            <input type="hidden" name="itemId" value="<%= itemId %>">
            <input type="hidden" name="itemType" value="<%= itemType %>">
            <input type="hidden" name="ownerId" value="<%= ownerId %>">
            
            <div class="input-area">
                <input type="text" name="message" placeholder="Type your message..." required>
                <button type="submit">Send</button>
            </div>
        </form>
    </div>
</body>
</html>
