<%-- 
    Document   : contactUs
    Created on : Nov 16, 2024, 2:09:11 AM
    Author     : sanuji
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Contact Us | SilverKnight Cinema</title>
        <link rel="stylesheet" href="assets/css/style.css">
        
        <script src="https://kit.fontawesome.com/16735b712d.js" crossorigin="anonymous"></script>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
               
        <div class="banner-contact-us">
            <h1 class="banner-title-contact"> Contact Us </h1>
            <div class="home-link-contact">
                <a href="home"> Home </a>
                <span> /</span>
                <span>Contact Us </span>
            </div>
        </div>
        

        <section class="contact-details">
            <div class="detail-box">
                <div class="icon-circle">
                    <i class="fas fa-map-marker-alt"></i>
                </div>
                <div class="detail-text">
                <h3>Address</h3>
                <p>367 Hill-Crest Lane,<br> Bambalapitiya (Head Office)</p>
                </div>
            </div>
            <div class="detail-box">
                <div class="icon-circle">
                    <i class="fas fa-envelope"></i>
                </div>
                <div class="detail-text">
                <h3>Email Address</h3>
                <p> silverknight@yahoo.com<br> silverknight@gmail.com</p>
                </div>
            </div>
            <div class="detail-box">
                <div class="icon-circle">
                    <i class="fas fa-phone-alt"></i>
                </div>
                <div class="detail-text">
                <h3>Contact Number</h3>
                <p>+94 11 554 3345<br>+94 11 478 8991</p>
                </div>
            </div>
        </section>

        <section class="contact-form">
            <form action="Inquiries" method="POST">
                <div class="form-row">
                    <div class="input-group">
                        <label for="name">Name</label>
                        <input type="text" id="name" name="name" value="${name}" placeholder="Name" onkeypress="restrictAlphabeticalInput(event)" oninput="clearErrorMessage()" required>
                    </div>

                    <div class="input-group">
                        <label for="email">Email</label>
                        <input type="email" name="email" value="${email}" placeholder="Email" required oninput="clearErrorMessage()">
                    </div>
                </div>

                <div class="form-row">
                    <label for="subject">Subject</label>
                    <input type="text" name="subject" value="${subject}" placeholder="Subject" required oninput="clearErrorMessage()">
                </div>

                <div class="form-row">
                    <label for="message">Message</label>
                    <textarea name="message" placeholder="Message" oninput="clearErrorMessage()">${message}</textarea>
                </div>

                <!-- Success Message -->
                <c:if test="${not empty successMessage}">
                    <div style="color: green; text-align: center; font-size: 14px;" id="success-message">
                        <p>${successMessage}</p>
                    </div>
                </c:if>

                <!-- Error message -->
                <c:if test="${not empty errorMessage}">
                    <div style="color: red; text-align: center; font-size: 14px;" id="error-message">
                        <p>${errorMessage}</p>
                    </div>
                </c:if>

                <button type="submit" class="send-btn">Send Message</button>
            </form>
        </section>
        
        <section class="contact-map">
            <div id="map"><iframe src="https://www.google.com/maps/embed?pb=!1m23!1m12!1m3!1d63374.60956104252!2d79.85488229999997!3d6.90099600000001!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!4m8!3e6!4m0!4m5!1s0x3ae25961265f9517%3A0x70b4d5a5cf6c452e!2sBambalapitiya!3m2!1d6.900996!2d79.8548823!5e0!3m2!1sen!2slk!4v1731659333954!5m2!1sen!2slk" width="1050" height="400" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe></div>
        </section>
   
               
        <script type="text/javascript">
            // Alphabetical characters for the name field
            function restrictAlphabeticalInput(event) {
                var keyCode = event.which || event.keyCode;
                var char = String.fromCharCode(keyCode);
                if (!/[a-zA-Z]/.test(char)) {
                    event.preventDefault();  // Prevent non-alphabet characters
                }
            }
            
            // To clear error message
            function clearErrorMessage() {
                var errorMessage = document.getElementById('error-message');
                if (errorMessage) {
                    errorMessage.innerHTML = ''; 
                }
            }
            
            // JavaScript to hide success message after 10 seconds
            window.onload = function() {
                setTimeout(function() {
                    var successMessageDiv = document.getElementById("success-message");
                    if (successMessageDiv) {
                        successMessageDiv.style.display = 'none';
                    }
                }, 10000); 
             };
        </script>            

        
        <jsp:include page="footer.jsp" />

    </body>
</html>
