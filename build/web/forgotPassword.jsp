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
        <jsp:include page="header.jsp"/>
        
        <section class="form-content">
            <h1 class="form-heading"> Forgot Password </h1> <br>
            <p class="password-para"> 
                Enter your email address. You will receive a link to create a new password via email.
            </p> 
            <div class="form">
                <form name="loginForm" method="POST" action="ForgotPasswordServlet">
                    <label for="text"> Email </label>
                    <br>
                    <input type="email" name="email" placeholder="Email" class="input-field" required/>
                    <br><br>
                    
                    <div class="btn">
                        <input type="submit" value="Continue" name="continue" id="button"/>
                    </div>
                    
                   <div class="link">
                        <div class="rememberMe"> </div>
                        <a href="login" class="link"> Back to login</a>
                    </div>
                </form>
            </div>
        </section>
        
        <jsp:include page="footer.jsp"/>
        
    </body>
</html>
