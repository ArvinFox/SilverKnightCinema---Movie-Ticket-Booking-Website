<%-- 
    Document   : signup
    Created on : Nov 16, 2024, 9:42:03 AM
    Author     : Inushi
--%>

<%@page contentType ="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Sign-Up Page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="assets/css/styles.css">
</head>

<body class="signup-body">
    <jsp:include page="navigation.jsp"/>
    
    <form class="signup-form">
        <!-- Profile Picture -->
        <img src="signup.jpg" alt="User Picture">
        
        <!-- Sign Up Title -->
        <h2>Sign Up</h2>
        
        <!-- Form Fields -->
        <label for="first-name">First Name</label>
        <input type="text" id="first-name" placeholder="Enter your first name" required>
        
        <label for="last-name">Last Name</label>
        <input type="text" id="last-name" placeholder="Enter your last name" required>
        
        <label for="gender">Gender</label>
        <select id="gender" required>
            <option value="">Select your gender</option>
            <option value="male">Male</option>
            <option value="female">Female</option>
            <option value="other">Other</option>
        </select>
        
        <label for="contact">Contact Number</label>
        <input type="tel" id="contact" placeholder="Enter your contact number" pattern="[0-9]{10}" required>
        
        <label for="email">Email</label>
        <input type="email" id="email" placeholder="Enter your email" required>
        
        <label for="password">Password</label>
        <input type="password" id="password" placeholder="Enter your password" required>
        
        <label for="confirm-password">Confirm Password</label>
        <input type="password" id="confirm-password" placeholder="Confirm your password" required>
        
        <button type="submit">Sign Up</button>
        
         <p class="login-link">Existing User? <a href="#">Login</a></p>
    </form>
    <jsp:include page="footer.jsp"/>
</body>
</html>


