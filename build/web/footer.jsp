<%-- 
    Document   : footer
    Created on : Nov 14, 2024, 11:50:07 AM
    Author     : Udani Indrachapa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="assets/css/style.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
        <script src="https://kit.fontawesome.com/16735b712d.js" crossorigin="anonymous"></script>
        <title>Footer</title>
    </head>
    <body>
        <c:if test="${unSubscribe}">
            <%
                request.removeAttribute("unSubscribe");
            %>
            <script>
                alert("You have unsubscribed from Silver Knight Cinema ..");
                window.location.href = "index.jsp";
            </script>
        </c:if>
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
                                   silverknightcinemas@gmail.com
                            </p> 
                        </li>
                    </ul>
                </div>

                <!-- Navigation column-->
                <div class="footer-col navigations">
                    <h3> Navigation </h3>
                    <ul>
                        <li> <a href="home">Home </a> </li>
                        <li> <a href="movies">Movies </a> </li>
                        <li> <a href="locations">Locations </a> </li>
                        <li> <a href="offers">Deals & Offers </a> </li>
                    </ul>
                </div>

                <!-- Quick Links column-->
                <div class="footer-col quick-links">
                    <h3> Quick Links </h3>
                    <ul>
                        <li> <a href="faq">FAQs </a> </li>
                        <li> <a href="about">About Us </a> </li>
                        <li> <a href="contact">Contact Us</a> </li>
                        <li> <a href="terms">Terms & Conditions </a> </li>
                    </ul>
                </div>

                <!-- Update new movies column-->
                <div class="footer-col update-movies">
                    <h3>Stay Updated for New Movies</h3>
                    <div class="movieUpdate-box">
                        <form name="movieUpdate" id="movieUpdate">
                            <input type="email" name="email" placeholder="Enter your email here" id="footer-email" required />
                            <button id="butSubscribe" type="button" onclick="sendEmailToServlet()"> Subscribe</button>
                        </form>
                    </div>
                </div>
            </div>
                
            <div class="footer-bottom">
                <p class="footer-p"> &copy; Silver Knight Cinema. All rights reserved. Developed by Group AA (Batch 12 UOP - NSBM) </p>
            </div>
        </footer>
        
        
        <script>
            async function sendEmailToServlet() {
                try {
                    const email = document.getElementById("footer-email").value.trim();
                    if (email) {
                        const response = await fetch("subscribe?email=" +email);

                        if(response.ok) {
                            alert("You have subscribed to Silver Knight Cinema..");
                            document.getElementById("footer-email").value = "";
                        }
                    } else {
                        alert("Please enter your email");
                    }
                    
                } catch(error) {
                    console.log("error: " +error);
                }
            }
        </script>
    </body>
</html>
