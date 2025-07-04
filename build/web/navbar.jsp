<%@ page session="true" %>
<%@ page import="com.lostfound.model.User" %>

<%
    User navbarUser = (User) session.getAttribute("user");
    boolean isLoggedIn = navbarUser != null;
%>

<!-- Font Awesome -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

<style>
    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
    }

    .navbar {
        background: #4C6BFF;
        display: flex;
        flex-wrap: wrap;
        justify-content: center;
        gap: 12px;
        padding: 20px 10px;
        border-radius: 0 0 30px 30px;
        position: relative;
        box-shadow: 0 8px 20px rgba(0, 0, 0, 0.15);
        clip-path: ellipse(100% 80% at 50% 0%);
    }

    .nav-item {
        position: relative;
        display: flex;
        align-items: center;
        justify-content: center;
        text-decoration: none;
        padding: 10px 20px;
        border-radius: 25px;
        background: rgba(255, 255, 255, 0.1);
        color: white;
        font-size: 18px;
        transition: background 0.3s ease, transform 0.3s ease;
    }

    .nav-item:hover {
        background: rgba(255, 255, 255, 0.25);
        transform: scale(1.05);
    }

    .nav-item .icon {
        opacity: 1;
        transition: opacity 0.3s ease;
        margin-right: 10px;
    }

    .nav-item .label {
        opacity: 0;
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        white-space: nowrap;
        color: white;
        font-size: 1rem;
        transition: opacity 0.3s ease;
    }

    .nav-item:hover .icon {
        opacity: 0;
    }

    .nav-item:hover .label {
        opacity: 1;
    }

    @media (max-width: 768px) {
        .navbar {
            flex-direction: row;
            flex-wrap: wrap;
            justify-content: center;
        }

        .nav-item {
            margin: 5px;
            padding: 5px 15px;
        }
    }
</style>

<div class="navbar">
    <a href="index.jsp" class="nav-item">
        <span class="icon"><i class="fas fa-home"></i></span>
        <span class="label">Home</span>
    </a>

    <% if (isLoggedIn) { %>
        <!-- ? NEW Dashboard link -->
        <a href="userDashboard.jsp" class="nav-item">
            <span class="icon"><i class="fas fa-user-circle"></i></span>
            <span class="label">Dashboard</span>
        </a>
        <a href="viewFoundItems.jsp" class="nav-item">
            <span class="icon"><i class="fas fa-box-open"></i></span>
            <span class="label">Found Items</span>
        </a>
        <a href="viewLostItems.jsp" class="nav-item">
            <span class="icon"><i class="fas fa-search"></i></span>
            <span class="label">Lost Items</span>
        </a>
        <a href="postLostItem.jsp" class="nav-item">
            <span class="icon"><i class="fas fa-plus-circle"></i></span>
            <span class="label">Post Lost</span>
        </a>
        <a href="postFoundItem.jsp" class="nav-item">
            <span class="icon"><i class="fas fa-cloud-upload-alt"></i></span>
            <span class="label">Post Found</span>
        </a>
        
        <a href="LogoutServlet" class="nav-item">
            <span class="icon"><i class="fas fa-sign-out-alt"></i></span>
            <span class="label">Logout (<%= navbarUser.getUsername() %>)</span>
        </a>
    <% } else { %>
        <a href="login.jsp" class="nav-item">
            <span class="icon"><i class="fas fa-sign-in-alt"></i></span>
            <span class="label">Login</span>
        </a>
        <a href="register.jsp" class="nav-item">
            <span class="icon"><i class="fas fa-user-plus"></i></span>
            <span class="label">Register</span>
        </a>
    <% } %>
</div>
