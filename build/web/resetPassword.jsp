<%-- 
    Document   : confirmPassword
    Created on : Nov 16, 2024, 10:10:06 PM
    Author     : Laptop Outlet
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Reset Password | SilverKnight Cinema</title>
        <link rel="stylesheet" href="assets/css/forms.css">
    </head>
    <body>
        <section class="navigation">
            <jsp:include page="header.jsp"/>
        </section>
        
        <section class="form-content">
            <h1 class="form-heading"> Forgot Password </h1> <br>
            <p class="password-para"> Enter your new password below. </p> 
            <div class="form">
                <form name="loginForm" method="POST">
                    <label for="text"> Password </label>
                    <br>
                    <input type="password" name="password" placeholder="Password" class="input-field" required/>
                    <br><br>
                    
                    <label for="text"> Verify Password </label>
                    <br>
                    <input type="password" name="password" placeholder="Verify Password" class="input-field" required/>
                    <br><br>
                    
                    <div class="btn">
                        <input type="submit" value="Reset Password" name="resetPwd" id="butReset"/>
                    </div>
                </form>
            </div>
        </section>
        
        <section id="footer">
            <jsp:include page="footer.jsp"/>
        </section>
        
    </body>
</html>
