<%-- 
    Document   : login
    Created on : Nov 16, 2024, 11:00:59 AM
    Author     : Udani Indrachapa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login | SilverKnight Cinema</title>
        <link rel="stylesheet" href="assets/css/forms.css">
    </head>
    <body>
        <section class="navigation">
            <jsp:include page="navigation.jsp"/>
        </section>
        
        <section class="form-content">
            <div class="form">
                <form name="loginForm" method="POST">
                    <h1 class="form-heading"> Login </h1> <br>
                    <label for="text"> Email </label>
                    <br>
                    <input type="email" name="email" placeholder="Email" id="email" required/>
                    <br><br>
                    
                    <label for="text"> Password </label>
                    <br>
                    <input type="password" name="password" placeholder="Password" id="email" required/>
                    <br><br>
                    
                    <div class="div">
                        <div class="rememberMe">
                            Remember Me 
                            <input type="checkbox" name="rememberMe" style="margin-left: 8px;">
                        </div>
                        <a href="forgotPassword.jsp" id="link"> Forgot Password?</a>
                    </div>
                    
                    <br><br>
                    <div class="btn">
                        <input type="submit" value="Login" name="login" id="button"/>
                    </div>
                    
                    <p id="account-para">
                        Need an account?
                        <a href="signup.jsp" id="link"> Create a account </a>
                    </p>
                </form>
             </div>
        </section>
        
        <section id="footer">
            <jsp:include page="footer.jsp"/>
        </section>
    </body>
</html>
