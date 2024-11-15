<%-- 
    Document   : navigation
    Created on : Nov 14, 2024, 11:48:39 AM
    Author     : Udani Indrachapa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Navigation</title>
        <link rel="stylesheet" href="assets/css/style.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
        <script src="https://kit.fontawesome.com/16735b712d.js" crossorigin="anonymous"></script> 
    </head>
    <body>
        <nav>
            <div class="logo">
                <a href="#" > <img src="assets/images/logo.png" alt="Logo"/> </a>
            </div>
            <ul id="menuList">
                <li class="navLink"> <a> Home </a></li> <!-- class="active" -->
                <li class="navLink"> <a> Movies </a></li>
                <li class="navLink"> <a> Offers & Discounts </a></li>
                <li class="navLink"> <a> About Us </a></li>
                <li class="navLink"> <a> Contact Us </a></li>
                <li> <input type="submit" value="Book Now" name="login" disabled="disabled" /> </li>   
            </ul>
            <div class="menu-icon">
                <i class="fa-solid fa-bars"  onclick="toggleMenu()"></i>
            </div>
        </nav>
        
        <script>
            let menuList = document.getElementById("menuList");
            menuList.style.maxHeight = "0px";
            
            function toggleMenu(){
                if(menuList.style.maxHeight === "0px")
                {
                    menuList.style.maxHeight = "300px";
                }
                else
                {
                    menuList.style.maxHeight = "0px";
                }
            }
        </script>
    </body>
</html>
