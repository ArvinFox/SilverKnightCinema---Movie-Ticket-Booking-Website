<%-- 
    Document   : userAccountDetails
    Created on : Nov 23, 2024, 12:57:52 PM
    Author     : arvin
--%>

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
            <div class="form-row">
                <label class="user-detail-names" for="firstName">First Name</label>
                <span class="colon">:</span>
                <label class="user-db-details" id="dbFirstName">Hettige Arvin</label>
            </div>
            <div class="form-row">
                <label class="user-detail-names" for="lastName">Last Name</label>
                <span class="colon">:</span>
                <label class="user-db-details" id="dbLastName">Premathilake</label>
            </div>
            <div class="form-row">
                <label class="user-detail-names" for="gender">Gender</label>
                <span class="colon">:</span>
                <label class="user-db-details" id="dbGender">Male</label>
            </div>
            <hr>
        </div>
        <h1>Password</h1>
        <div class="user-details-sub-container">
            <div class="form-row">
                <form method="POST" action="#">
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
                <label class="user-db-details" id="dbEmail">arvinpremathilake@gmail.com</label>
            </div>
            <div class="form-row">
                <label class="user-detail-names" for="contact">Contact Number</label>
                <span class="colon">:</span>
                <label class="user-db-details" id="dbContact">0715247414</label>
            </div>
            <form method="#" action="#">
            <div class="form-row">
                    <input type="button" id="changeContact" value="Edit Contact">
            </div>
            <div class="form-row hidden">
                <label class="user-detail-names" for="newContact">Enter your new contact</label>
                <span class="colon">:</span>
                <input type="number" id="newContactNumber">
                <input type="button" id="changeContactSendOTP" value="Send OTP">
            </div>
            <div class="form-row hidden">
                <label class="user-detail-names" for="newContact">Enter OTP Code</label>
                <span class="colon">:</span>
                <input type="number" id="newContactNumber">
                <input type="button" id="changeContactResendOTP" value="Resend OTP">
                <br>
                <label class="user-detail-names" for="OTP countdown"><!-- for the countdown --></label>
                <input type="submit" id="saveNewContact" value="Save Changes">
            </div>
            </form>
        </div>
        <hr>
    </div>
    <script src="scripts.js"></script>
</body>
</html>