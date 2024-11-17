<%-- 
    Document   : forgotPassword
    Created on : Nov 16, 2024, 2:10:19 PM
    Author     : Udani Indrachapa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Forgot Password | SilverKnight Cinema</title>
        <link rel="stylesheet" href="assets/css/forms.css">
    </head>
    <body>
        <section class="navigation">
            <jsp:include page="navigation.jsp"/>
        </section>
        
        <section class="form-content">
            <h1 class="form-heading"> Forgot Password </h1> <br>
            <p id="password-para"> 
                Enter your email address. You will receive a link to create a new password via email.
            </p> 
            <div class="form">
                <form name="loginForm" method="POST">
                    <label for="text"> Email </label>
                    <br>
                    <input type="email" name="email" placeholder="Email" id="email" required/>
                    <br><br>
                    
                    <div class="btn">
                        <input type="submit" value="Continue" name="continue" id="button"/>
                    </div>
                    
                   <div class="link">
                        <div class="rememberMe"> </div>
                        <a href="login.jsp" id="link"> Back to login</a>
                    </div>
                </form>
            </div>
        </section>
        
        <section id="footer">
            <jsp:include page="footer.jsp"/>
        </section>
        
    </body>
</html>
