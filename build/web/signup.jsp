<%-- 
    Document   : signup
    Created on : Nov 16, 2024, 9:42:03 AM
    Author     : Inushi
--%>

<%@page contentType ="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Sign-Up | SilverKnight Cinema</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="assets/css/forms.css">
</head>

<body>
    
        <section class="navigation">
            <jsp:include page="header.jsp"/>
        </section>
        
        <section class="form-content">
            <div class="form">
                <form name="loginForm" method="POST">
                    <h1 class="form-heading"> Sign Up </h1> <br>
                    <label for="text"> First Name </label>
                    <br>
                    <input type="text" name="fname" placeholder="Enter your first name" class="input-field" required/>
                    <br><br>
                    <label for="text"> Last Name </label>
                    <br>
                    <input type="text" name="lname" placeholder="Enter your last name" class="input-field" required/>
                    <br><br>
                    <label for="text"> Gender </label>
                    <br>
                    <select class="input-field" required>
                        <option value=""> Select your gender </option>
                        <option value="male">Male</option>
                        <option value="female">Female</option>
                    </select>
                    <br><br>
                    <label for="text"> Contact Number </label>
                    <br>
                    <input type="tel" name="contact_no" placeholder="Enter your contact number" class="input-field" required/>
                    <br><br>
                    <label for="text"> Email </label>
                    <br>
                    <input type="email" name="email" placeholder="Email" class="input-field" required/>
                    <br><br>
                    <label for="text"> Password </label>
                    <br>
                    <input type="password" name="password" placeholder="Enter your password" class="input-field" required/>
                    <br><br>
                    <label for="text"> Confirm Password </label>
                    <br>
                    <input type="password" name="confirm_password" placeholder="Confirm your password" class="input-field" required/>
                    <br><br>
                    
                    <div class="btn">
                        <input type="submit" value="Sign Up" name="signup" id="button"/>
                    </div>
                    
                    <p class="account-para">
                        Existing User?
                        <a href="login.jsp" class="link"> Login </a>
                    </p>
                </form>
             </div>
        </section>
        
        <section id="footer">
            <jsp:include page="footer.jsp"/>
        </section>

</body>
</html>


