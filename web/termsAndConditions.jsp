<%-- 
    Document   : termsAndConditions
    Created on : Nov 15, 2024, 9:40:26 AM
    Author     : Udani Indrachapa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Terms & Conditions | SilverKnight Cinema</title>
        <link rel="stylesheet" href="assets/css/style.css">
        
        <script src="https://kit.fontawesome.com/16735b712d.js" crossorigin="anonymous"></script>
    </head>
    <body>
        <jsp:include page="navigation.jsp"/>
        <div class="banner-terms">
            <h1 class="banner-title"> Terms & Conditions </h1>
            <div class="home-link">
                <a href="index.jsp"> Home </a>
                <span> /</span>
                <span>Terms & Conditions </span>
            </div>
        </div>
        
        <section class="content">
            <div class="terms-content">
                <ol class="list">
                    <li id="sublist">
                        <h1 id="list-heading"> Making Use of Our Website </h1>
                        <p id="list-para"> By using our website, you consent to abide by these basic rules. 
                            We completely understand if you need to take a time to review if 
                            something here isn't quite working for you.
                        </p>
                    </li>
                    <li id="sublist">
                        <h1 id="list-heading"> Creating an Account </h1>
                        <p id="list-para"> Registering is simple and enables us to customize your experience.
                            Just be sure that the information you give is correct, and keep your 
                            login credentials secure. This little action will have a big impact on 
                            how smoothly your reservations proceed.
                        </p>
                    </li>
                    <li id="sublist">
                        <h1 id="list-heading"> Booking Tickets </h1>
                        <p id="list-para"> Browse movies, pick your show times, and book your tickets whenever you 
                            like! Keep in mind that bookings are based on availability, and once 
                            they’re confirmed, they’re final.
                        </p>
                        <p id="list-para"> Ticket prices vary by movie, time, and location. We do our best to keep 
                            them clear so you know what to expect.
                        </p>
                    </li>
                    <li id="sublist">
                        <h1 id="list-heading"> Making Payments </h1>
                        <p id="list-para"> All payments are processed securely. We accept most major cards and 
                            popular digital payment options, so you can book with confidence.
                        </p>
                        <p id="list-para"> Your payment details are private, and we’re committed to keeping 
                            them secure.
                        </p>
                    </li>
                    <li id="sublist">
                        <h1 id="list-heading"> Refunds and Cancellations </h1>
                        <p id="list-para"> Tickets are generally non-refundable, but if a movie or show is canceled 
                            by the theater, we’ll ensure you get a refund.
                        </p>
                        <p id="list-para"> Your payment details are private, and we’re committed to keeping them 
                            secure.
                        </p>
                    </li>
                    <li id="sublist">
                        <h1 id="list-heading"> Privacy and Data Security </h1>
                        <p id="list-para"> We respect your privacy and only ask for the info we need to make your 
                            experience better. Your data stays with us and is never shared without 
                            your permission.
                        </p>
                    </li>
                    <li id="sublist">
                        <h1 id="list-heading"> Our Responsibility </h1>
                        <p id="list-para"> We work hard to make sure everything runs smoothly. However, if an 
                            unexpected error affects your booking, we’ll take care of it by refunding 
                            the ticket price.
                        </p>
                    </li>
                    <li id="sublist">
                        <h1 id="list-heading"> Keeping Things Up-to-Date </h1>
                        <p id="list-para"> Our terms may change from time to time to keep up with improvements to 
                            our site. Whenever they do, we’ll post the updates here.
                        </p>
                    </li>
                    <li id="sublist">
                        <h1 id="list-heading"> Legal Stuff </h1>
                        <p id="list-para"> These terms follow the laws of Sri Lanka. Any legal questions or issues 
                            would be resolved here.
                        </p>
                    </li>
                </ol>
            </div>
        </section>
        
        <jsp:include page="footer.jsp" />
    </body>
</html>
