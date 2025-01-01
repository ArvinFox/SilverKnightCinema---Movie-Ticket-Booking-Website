<%-- 
    Document   : header
    Created on : Nov 14, 2024, 11:48:39 AM
    Author     : Udani Indrachapa
--%>

<%@page import="model_classes.User"%>
<%@page import="dao_classes.UserDAO"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Navigation</title>
        <link rel="stylesheet" href="assets/css/headerAndFooter.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
        <script src="https://kit.fontawesome.com/16735b712d.js" crossorigin="anonymous"></script> 
    </head>
    <body>
        <%
            UserDAO userDao = new UserDAO();
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie c : cookies) {
                    if ("email".equals(c.getName())) {
                        User user = userDao.getUserByEmail(c.getValue());
                        request.setAttribute("user", user);
                        break;
                    }
                }
            }
        %>
        
        <%
            String currentURI = request.getRequestURI();
            String activePage = "";
            if (currentURI.contains("home") || currentURI.contains("index")) {
                activePage = "home";
            } else if (currentURI.contains("movies")) {
                activePage = "movies";
            } else if (currentURI.contains("locations")) {
                activePage = "locations";
            } else if (currentURI.contains("offers") || currentURI.contains("Offers")) {
                activePage = "offers";
            }
        %>

        <nav>
            <div class="logo">
                <a href="#" > <img src="assets/images/logo.png" alt="Logo"/> </a>
            </div>
            <ul class="navUl" id="menuList">
                <li class="navLink"> 
                    <a href="home" class="<%= "home".equals(activePage) ? "active" : "" %>"> Home </a>
                </li>
                <li class="navLink"> 
                    <a href="movies" class="<%= "movies".equals(activePage) ? "active" : "" %>"> Movies </a>
                </li>
                <li class="navLink"> 
                    <a href="locations" class="<%= "locations".equals(activePage) ? "active" : "" %>"> Locations </a>
                </li>
                <li class="navLink"> 
                    <a href="offers" class="<%= "offers".equals(activePage) ? "active" : "" %>"> Deals & Offers </a>
                </li>
                <c:if test="${user == null}">
                    <button class="butLogin" onclick="loginfunction()"> Login </button> 
                </c:if>

                <c:if test="${user != null}">
                    <div class="tooltip-container">
                        <button class="butLogin" onclick="loginfunction()" disabled> Hi, ${user.firstName}! </button> 
                        <div class="tooltip-content">
                            <ul id="tooltip-list">
                                <li class="tooltip-navLink"><a class="tooltip-link" href="profile">User Profile</a></li>
                                <li class="tooltip-navLink"><a class="tooltip-link" href="LogoutServlet">Logout</a></li>
                            </ul>
                        </div>                   
                    </div>
                </c:if>
            </ul>
            <div class="menu-icon">
                <i class="fa-solid fa-bars"  onclick="toggleMenu()"></i>
            </div>
        </nav>

        <script>
            let menuList = document.getElementById("menuList");
            menuList.style.maxHeight = "0px";

            function toggleMenu() {
                if (menuList.style.maxHeight === "0px")
                {
                    menuList.style.maxHeight = "300px";
                } else
                {
                    menuList.style.maxHeight = "0px";
                }
            }

            function loginfunction() {
                location.replace("login");
            }

            let lastScrollY = 0;
            const nav = document.querySelector('nav');

            window.addEventListener('scroll', () => {
                if (window.scrollY > lastScrollY) {
                    // User is scrolling down
                    nav.classList.add('hidden');
                } else {
                    // User is scrolling up
                    nav.classList.remove('hidden');
                }
                lastScrollY = window.scrollY;
            });
        </script>
    </body>
</html>
