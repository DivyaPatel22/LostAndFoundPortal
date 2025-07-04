<%@page import="com.lostfound.model.User"%>
<%@ page import="java.util.*, com.lostfound.model.Message" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Chat</title>
    <style>
        .chat-container {
            width: 70%;
            margin: auto;
            padding: 20px;
            border: 1px solid #ccc;
            background-color: #f9f9f9;
            max-height: 500px;
            overflow-y: auto;
        }

        .chat-message {
            padding: 10px;
            margin: 5px 0;
            border-radius: 5px;
            background-color: #e9e9e9;
            max-width: 60%;
        }

        .chat-message.sender {
            background-color: #d3f9d8;
            align-self: flex-end;
        }

        .chat-message.receiver {
            background-color: #f3f3f3;
            align-self: flex-start;
        }

        .chat-input {
            width: 100%;
            padding: 10px;
            margin-top: 20px;
        }

        .send-btn {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
        }

        .send-btn:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <h2>Chat with the Owner/Finder</h2>

    <div class="chat-container">
        <% 
            List<Message> chatMessages = (List<Message>) request.getAttribute("chatMessages");
            for (Message msg : chatMessages) {
        %>
        <div class="chat-message <%= msg.getSenderId() == ((User) session.getAttribute("user")).getId() ? "sender" : "receiver" %>">
            <%= msg.getMessage() %>
            <br><small><%= msg.getTimestamp() %></small>
        </div>
        <% } %>
    </div>

    <form action="ContactUserServlet" method="POST">
        <input type="hidden" name="itemId" value="<%= request.getAttribute("itemId") %>" />
        <input type="hidden" name="itemType" value="<%= request.getAttribute("itemType") %>" />
        <textarea name="message" class="chat-input" placeholder="Type your message here..." required></textarea>
        <button type="submit" class="send-btn">Send</button>
    </form>
</body>
</html>
