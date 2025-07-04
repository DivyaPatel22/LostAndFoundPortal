<%@ include file="navbar.jsp" %>

<html>
<head>
    <title>Lost & Found Portal</title>
    <!-- Font Awesome for icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

    <style>
        /* Reset and base setup */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Segoe UI', sans-serif;
            background: #f9fafb;
            color: #2c3e50;
            min-height: 100vh;
            display: flex;
            flex-direction: column;
        }

        main {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 3rem;
            padding: 60px 10%;
            align-items: center;
            margin-top: 60px;
        }

        .hero-text {
            max-width: 600px;
        }

        h1 {
            font-size: 3rem;
            margin-bottom: 20px;
            color: #374151;
        }

        p.sub {
            font-size: 1.1rem;
            color: #6b7280;
            margin-bottom: 30px;
        }

        .actions a {
            display: inline-flex;
            align-items: center;
            gap: 10px;
            text-decoration: none;
            color: #fff;
            background-color: #3b82f6;
            padding: 12px 20px;
            border-radius: 8px;
            margin-right: 15px;
            font-weight: 500;
            transition: background-color 0.3s ease;
        }

        .actions a:hover {
            background-color: #2563eb;
        }

        .hero-image img {
            max-width: 100%;
            border-radius: 10px;
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.05);
        }

        footer {
            text-align: center;
            padding: 1rem;
            font-size: 0.9rem;
            color: #9ca3af;
            margin-top: auto;
        }

        @media (max-width: 768px) {
            main {
                grid-template-columns: 1fr;
                text-align: center;
                padding: 40px 5%;
            }

            .actions {
                justify-content: center;
            }

            .hero-image {
                order: -1;
                margin-bottom: 30px;
            }
        }
    </style>
</head>

<body>
    <!-- Navbar from navbar.jsp -->

    <main>
        <div class="hero-text">
            <h1>Welcome to the Lost & Found Portal</h1>
            <p class="sub">Lost something or found an item? Let's reconnect people with their belongings easily and securely.</p>
            <div class="actions">
                <a href="login.jsp"><i class="fas fa-sign-in-alt"></i> Login</a>
               
            </div>
        </div>

        <div class="hero-image">
            <!-- Updated image path -->
            <img src="images/img1.png" alt="Lost & Found Image">
        </div>
    </main>

    <footer>
        &copy; 2025 Lost & Found Portal. Built with care.
    </footer>
</body>
</html>
