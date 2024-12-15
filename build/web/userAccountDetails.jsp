<%-- 
    Document   : userAccountDetails
    Created on : Nov 23, 2024, 12:57:52 PM
    Author     : arvin
--%>

<%@page import="java.util.Base64"%>
<%@page import="java.security.SecureRandom"%>
<%@page import="dao_classes.UserDAO"%>
<%@page import="model_classes.User"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="styles.css">
    <title>Account Details</title>
</head>
<body>
    <div class="user-details-container">
        <h1>Personal Information</h1>
        <div class="user-details-sub-container">
            <c:if test="${user != null}">
                <div class="form-row">
                    <label class="user-detail-names" for="firstName">First Name</label>
                    <span class="colon">:</span>
                    <label class="user-db-details" id="dbFirstName">${user.firstName}</label>
                </div>
                <div class="form-row">
                    <label class="user-detail-names" for="lastName">Last Name</label>
                    <span class="colon">:</span>
                    <label class="user-db-details" id="dbLastName">${user.lastName}</label>
                </div>
            </c:if>
            <hr>
        </div>
        <h1>Password</h1>
        <div class="user-details-sub-container">
            <div class="form-row">
                    <%
                        User user = (User) session.getAttribute("user");
                        String token = generateToken();
                        long expiryTime = System.currentTimeMillis() + (5 * 60 * 1000);

                        // Store token and expiry in the database
                        UserDAO userDao = new UserDAO();
                        userDao.storeResetToken(user.getUserId(), token, expiryTime);
                    %>
                <form method="POST" action="resetPassword.jsp?token=<%= token %>">
                    <input type="submit" id="changePassword" value="Change Current Password">
                </form>
            </div>
            <hr>
        </div>
        <h1>Contact Information</h1>
        <div class="user-details-sub-container">
            <div class="form-row">
                <label class="user-detail-names" for="email">Email</label>
                <span class="colon">:</span>
                <label class="user-db-details" id="dbEmail">${user.email}</label>
            </div>
            <div class="form-row">
                <label class="user-detail-names" for="contact">Contact Number</label>
                <span class="colon">:</span>
                <label class="user-db-details" id="dbContact">${user.contactNumber}</label>
            </div>
            <form id="otpForm">
                <div class="form-row">
                        <input type="button" id="changeContact" value="Edit Contact">
                </div>
                <div class="form-row hidden">
                    <label class="user-detail-names" for="newContact">Enter your new contact</label>
                    <span class="colon">:</span>
                    <input type="number" id="newContactNumber" name="newContactNumber">
                    <input type="button" id="changeContactSendOTP" value="Send OTP">
                </div>
                <div class="form-row hidden">
                    <label class="user-detail-names" for="newContact">Enter OTP Code</label>
                    <span class="colon">:</span>
                    <input type="number" id="enteredOtp">
                    <input type="button" id="changeContactResendOTP" value="Resend OTP">
                    <br>
                    <label class="user-detail-names" for="OTP countdown"><!-- for the countdown --></label>
                    <input type="button" id="saveNewContact" value="Save Changes">
                </div>
            </form>
        </div>
        <hr>
    </div>
            
    <%!
        private String generateToken() {
            SecureRandom secureRandom = new SecureRandom();
            byte[] randomBytes = new byte[24];
            secureRandom.nextBytes(randomBytes);
            return Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
        }
    %>
    <script src="scripts.js"></script>
</body>
</html>