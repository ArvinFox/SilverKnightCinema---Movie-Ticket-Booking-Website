<%-- 
    Document   : login
    Created on : Dec 5, 2024, 11:19:35 AM
    Author     : Umindu Haputhanthri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:if test="${sessionScope.username != null}">
    <c:redirect url="dashboard" />
</c:if>
            
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Admin Login - Silver Knight Cinema</title>
        <link rel="stylesheet" href="../assets/styles/adminStyles.css">
    </head>
    <body>
        <div class="login-container">
            <h2>Admin Login</h2>
            <form action="login" method="POST">
                <label>Username</label>
                <input type="text" name="username" placeholder="Username" required>
                
                <label>Password</label>
                <input type="password" name="password" placeholder="Password" required>
                
                <button type="submit">Login</button>
                <p><%= request.getAttribute("errorMessage") != null ? request.getAttribute("errorMessage") : "" %></p>
            </form>
        </div>
    </body>
</html>
