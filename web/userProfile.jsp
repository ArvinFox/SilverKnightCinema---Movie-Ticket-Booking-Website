<%-- 
    Document   : userProfile
    Created on : Nov 23, 2024, 12:56:09 PM
    Author     : arvin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="assets/css/style.css"/>
        <title>Profile</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        
        <section class="profile-banner-section">
            <h1 class="banner-title">User Profile</h1>
            <div class="home-link">
                <a href="home"> Home </a>
                <span> /</span>
                <span>Profile</span>
            </div>
        </section>
        <section class="user-profile-section">
            <div class="user-profile-sub-nav">
                <ul>
                    <li><a href="" class="account-details user-nav-link" onclick="loadContent('userAccountDetails.jsp'); return false;">Account Details</a></li>
                                
                    <li><a href="" class="booking-history user-nav-link" onclick="loadContent('userBookingHistory.jsp'); return false;">Booking History</a></li>      
                </ul>
            </div>
        </section>
        
        <div id="user-content">
            <!-- When user clicks on the links the relevant pages will be displayed here -->
        </div>
        
        <jsp:include page="footer.jsp"/>

        <script src="assets/scripts/main.js"></script>
        <script>
            const accDetails = document.querySelector(".account-details");
            const bookingDetails = document.querySelector(".booking-history");
            // Loads the Account Details as the default loaded screen when the page is loaded for the first time
            document.addEventListener('DOMContentLoaded', async () => {
                await loadContent("userAccountDetails.jsp");
                accDetails.style.color = "black";
                bookingDetails.style.color = "rgb(104, 104, 104)";

                adjustFooter();
            });

            accDetails.addEventListener('click', async () => {
                await loadContent("userAccountDetails.jsp");
                accDetails.style.color = "black";
                bookingDetails.style.color = "rgb(104, 104, 104)";

                adjustFooter();
            });

            bookingDetails.addEventListener('click', async () => {
                await loadContent("userBookingHistory.jsp");
                bookingDetails.style.color = "black";
                accDetails.style.color = "rgb(104, 104, 104)";

                adjustFooter();
            });
        </script>   
    </body>
</html>


