<%-- 
    Document   : faq
    Created on : Nov 16, 2024, 12:37:49 PM
    Author     : arvin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="assets/css/style.css">
        <title>FAQ | SilverKnight Cinema</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        
        <section class="faq-banner-section">
            <h1 class="banner-title">Frequently Asked Questions</h1>
            <div class="home-link">
                <a href="index.jsp"> Home </a>
                <span> /</span>
                <span>FAQs</span>
            </div>
        </section>

        <section class="faq-section">
            <div class="faq-container">
                <div class="tabs">
                    <button class="tab-btn active" data-tab="general">General Information</button>
                    <button class="tab-btn" data-tab="ticket">Ticket Bookings</button>
                </div>
                <!-----General Information----->
                <div class="faq-content" id="general">
                    <h2>General Information FAQs</h2>
                    <!--Question-->
                        
                    <div class="faq-item">
                        <button class="faq-question">
                            How can I find cinema locations near me?
                            <span class="arrow">&#9660;</span>
                        </button>

                        <!--Answer-->

                        <div class="faq-answer">
                            <p>You can check our locations page to find cinemas in your area along with their contact details.</p>
                        </div>
                    </div>

                    <!--Question-->

                    <div class="faq-item">
                        <button class="faq-question">
                            How do I create an account on this website?
                            <span class="arrow">&#9660;</span>
                        </button>

                        <!--Answer-->

                        <div class="faq-answer">
                            <p>You can visit our <a href="signup.jsp">"Sign Up"</a> page and provide your credentials in order to create an account.</p>
                        </div>
                    </div>

                    <!--Question-->

                    <div class="faq-item">
                        <button class="faq-question">
                            How can I reset my password?
                            <span class="arrow">&#9660;</span>
                        </button>

                        <!--Answer-->

                        <div class="faq-answer">
                            <p>Simply go to our login page and click on <a href="forgotPassword.jsp">"Forgot my password"</a> link. Then provide your email address or mobile number to get the OTP code for the password reset procedure.</p>
                        </div>
                    </div>
                </div>
                
                <!---Ticket Booking FAQs---->
                <div class="faq-content hidden" id="ticket">
                    <h2>Ticket Booking FAQs</h2>
                    <!--Question-->
                    <div class="faq-item">
                        <button class="faq-question">
                            How do I book a ticket?
                            <span class="arrow">&#9660;</span>
                        </button>

                        <!--Answer-->

                        <div class="faq-answer">
                            <p>You can directly click on <a href="">"Book Now"</a> or you can go to <a href="">"Movies"</a> page and select your preferred movie to book your ticket today!</p>
                        </div>
                    </div>
                    
                    <!--Question-->
                    
                    <div class="faq-item">
                        <button class="faq-question">
                            Can I reserve preferred seats?
                            <span class="arrow">&#9660;</span>
                        </button>

                        <!--Answer-->

                        <div class="faq-answer">
                            <p>Yes of course! When booking a ticket for a movie, on the checkout page you can select your preferred seats if they are still available.</p>
                        </div>
                    </div>
                    
                    <!--Question-->
                    
                    <div class="faq-item">
                        <button class="faq-question">
                            How do I redeem ticket offers?
                            <span class="arrow">&#9660;</span>
                        </button>

                        <!--Answer-->

                        <div class="faq-answer">
                            <p>"Available soon after deciding the future plans"</p>
                        </div>
                    </div>
                </div>
            </div>
            
        </section>
        
        <jsp:include page="footer.jsp"/>
        <script src="assets/scripts/main.js"></script>
        
    </body>
</html>
