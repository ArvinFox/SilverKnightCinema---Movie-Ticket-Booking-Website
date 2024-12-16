<%-- 
    Document   : footer
    Created on : Nov 14, 2024, 11:50:07 AM
    Author     : Udani Indrachapa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="assets/css/headerAndFooter.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
        <script src="https://kit.fontawesome.com/16735b712d.js" crossorigin="anonymous"></script>
        <title>Footer</title>
    </head>
    <body>
        <footer>
            <div class="container">
                <!-- contact us column-->
                <div class="footer-col contact-us">
                    <h3> Contact Us </h3>
                    <ul>
                        <li> 
                            <p>
                                <i class="fa-solid fa-location-dot" id="contact"></i>
                                  367  Hill-Crest Lane, Bambalapitiya <br>(Head Office)
                            </p>
                        </li>
                        <li>  
                            <p>
                                <i class="fa-solid fa-phone" id="contact"></i>
                                   +94 11 260 3632
                            </p>
                        </li>
                        <li> 
                            <p>
                                <i class="fa-solid fa-phone" id="contact"></i>
                                   +94 11 260 3634
                            </p>
                        </li>
                        <li>  
                            <p>
                                <i class="fa-solid fa-envelope" id="contact"></i>
                                   silverknight@gmail.com
                            </p> 
                        </li>
                    </ul>
                </div>

                <!-- Navigation column-->
                <div class="footer-col navigations">
                    <h3> Navigation </h3>
                    <ul>
                        <li> <a href="index.jsp">Home </a> </li>
                        <li> <a href="movies.jsp">Movies </a> </li>
                        <li> <a href="locations.jsp">Locations </a> </li>
                        <li> <a href="dealsAndOffers">Deals and Offers </a> </li>
                    </ul>
                </div>

                <!-- Quick Links column-->
                <div class="footer-col quick-links">
                    <h3> Quick Links </h3>
                    <ul>
                        <li> <a href="faq.jsp">FAQs </a> </li>
                        <li> <a href="aboutUs.jsp">About Us </a> </li>
                        <li> <a href="contactUs.jsp">Contact Us</a> </li>
                        <li> <a href="termsAndConditions.jsp">Terms & Conditions </a> </li>
                    </ul>
                </div>

                <!-- Update new movies column-->
                <div class="footer-col update-movies">
                    <h3>Stay Updated for New Movies</h3>
                    <div class="movieUpdate-box">
                        <form name="movieUpdate" method="POST">
                            <input type="email" name="email" placeholder="Enter your email here" id="footer-emil"/>
                            <button id="butSubscribe"> Subscribe</button>
                        </form>
                    </div>
                            
                    <div class="social-media">
                        <i class="fa fa-facebook"></i>
                        <i class="fa fa-twitter"></i>
                        <i class="fa fa-instagram"></i>
                    </div>
                </div>
            </div>
                
            <div class="footer-bottom">
                <p class="footer-p"> &copy; Silver Knight Cinema. All rights reserved. Developed by Group AA (Batch 12 UOP - NSBM) </p>
            </div>
        </footer>
    </body>
</html>
