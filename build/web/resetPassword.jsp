<%-- 
    Document   : confirmPassword
    Created on : Nov 16, 2024, 10:10:06 PM
    Author     : Udani Indrachapa
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
        <jsp:include page="header.jsp"/>
        
        <section class="form-content">
            <h1 class="form-heading"> Forgot Password </h1> <br>
            <p class="password-para"> Enter your new password below. </p> 
            <div class="form">
                <form name="loginForm" method="POST" action="ResetPasswordServlet" onsubmit="return checkPassword()">
                    <input type="hidden" name="email" value="<%=request.getParameter("email")%>"> 
                    <label for="text"> Password </label>
                    <br>
                    <input type="password" name="new_password" placeholder="Password" maxlength="12" id="newPassword" class="input-field" required/>
                    <br><br>
                    
                    <label for="text"> Verify Password </label>
                    <br>
                    <input type="password" name="verify_password" placeholder="Verify Password" maxlength="12" id="verifyPassword" class="input-field" required/>
                    <br><br>
                    <p id="errorMessage"></p>
                    
                    <div class="btn">
                        <input type="submit" value="Reset Password" name="resetPwd" id="butReset"/>
                    </div>
                </form>
            </div>
        </section>
        
        <jsp:include page="footer.jsp"/>
        
        <script>
            function checkPassword()
            {
                let newPassword = document.getElementById("newPassword").value;
                let verifyPassword = document.getElementById("verifyPassword").value;
                let errorMessage = document.getElementById("errorMessage");

                if(newPassword !== verifyPassword)
                {
                    errorMessage.textContent = "password do not match.";
                    errorMessage.style.color = "red";
                    return false;
                }

                errorMessage.textContent = "";
                return true;
            }
        </script>
    </body>
</html>
