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
    <jsp:include page="header.jsp"/>
        
        <section class="form-content">
            <div class="form">
                <form name="loginForm" method="POST">
                    <h1 class="form-heading"> Sign Up </h1> <br>
                    <label for="text"> First Name </label>
                    <br>
                    <input type="text" name="fname" placeholder="First Name" class="input-field" required/>
                    <br><br>
                    <label for="text"> Last Name </label>
                    <br>
                    <input type="text" name="lname" placeholder="Last Name" class="input-field" required/>
                    <br><br>
                    <label for="text"> Contact Number </label>
                    <br>
                    <input type="tel" name="contact_no" placeholder="(000) 000-0000" maxlength="10" class="input-field" onkeyPress="onlyNumbers(event)" required/>
                    <br><br>
                    <label for="text"> Email </label>
                    <br>
                    <input type="email" name="email" placeholder="silverknightcinema@gmail.com" class="input-field" required/>
                    <br><br>
                    <label for="text"> Password </label>
                    <br>
                    <input type="password" name="password" placeholder="Password" maxlength="12" class="input-field" required/>
                    <br><br>
                    <label for="text"> Confirm Password </label>
                    <br>
                    <input type="password" name="confirm_password" placeholder="Confirm Password" maxlength="12" class="input-field" required/>
                    <br><br>
                    
                    <div class="btn">
                        <input type="submit" value="Sign Up" name="signup" id="button"/>
                    </div>
                    
                    <p class="account-para">
                        Existing User?
                        <a href="login" class="link"> Login </a>
                    </p>
                </form>
             </div>
        </section>
        
    <jsp:include page="footer.jsp"/>
    
    <!-- Javascript for Allow only numbers in contact input field  -->
        <script>
            function onlyNumbers(e){
                var contact = (e.which) ? e.which : e.keyCode;
                if(contact > 31 && (contact < 48 || contact > 57)){
                    e.preventDefault();
                }
            }
        </script>

</body>
</html>


