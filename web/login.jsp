<%-- 
    Document   : login
    Created on : Nov 16, 2024, 11:00:59 AM
    Author     : Udani Indrachapa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login | SilverKnight Cinema</title>
        <link rel="stylesheet" href="assets/css/forms.css">
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        
        <section class="form-content">
            <div class="form">
                <form name="loginForm" method="POST" action="LoginServlet">
                    <h1 class="form-heading"> Login </h1> <br>
                    <label for="text"> Email </label>
                    <br>
                    <input type="email" name="email" placeholder="Email" class="input-field" required oninput="clearErrorMessage()"/>
                    <br><br>
                    
                    <label for="text"> Password </label>
                    <br>
                    <input type="password" name="password" placeholder="Password" maxlength="12" class="input-field" required oninput="clearErrorMessage()"/>
                    <br><br>
                    
                    <div class="div">
                        <div class="rememberMe">
                            Remember Me 
                            <input type="checkbox" name="rememberMe" style="margin-left: 8px;">
                        </div>
                        <a href="forgotPassword" class="link"> Forgot Password?</a>
                    </div>
                    
                    <!-- Display error message -->
                    <c:if test="${errorMessage != null}">
                        <div id="error-message"> ${errorMessage}  </div>
                    </c:if>
                    
                    <div class="btn">
                        <input type="submit" value="Login" name="login" id="button"/>
                    </div>
                    
                    <p class="account-para">
                        Need an account?
                        <a href="signup" class="link"> Create an account </a>
                    </p>
                </form>
             </div>
        </section>
        
        <jsp:include page="footer.jsp"/>
        
        <!-- to clear error message -->
        <script>
            function clearErrorMessage() {
                var errorMessage = document.getElementById('error-message');
                if (errorMessage) {
                    errorMessage.innerHTML = ''; 
                }
            }
        </script>
    </body>
</html>
