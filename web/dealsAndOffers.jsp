<%-- 
    Document   : dealsAndOffers
    Created on : Nov 29, 2024, 4:59:09 PM
    Author     : Kavindya De Silva
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Deals & Offers | SilverKnight Cinema</title>
    <link rel="stylesheet" href="assets/css/style.css">
</head>
<body>
    <jsp:include page="header.jsp"/>
        
    <div class="banner-deals-and-offers">
        <h1 class="banner-title-deals-and-offers"> Deals & Offers </h1>
        <div class="home-link-deals-and-offers">
            <a href="home"> Home </a>
            <span> /</span>
            <span>Deals & Offers </span>
        </div>
    </div>
    
    <section class="deals-and-offers">
        <h2 class="deals-h2"> 
            Dive into Exclusive Discounts, Early Bird Offers, and Curated Movie Selections. Your Ultimate Movie Adventure<br><span class="deals-span">STARTS NOW!</span>
        </h2>
      
        <c:if test="${not empty activePromotions}">
            <c:forEach var="promotion" items="${activePromotions}">
                <c:choose>
                    <c:when test="${promotion.promotionId % 2 == 0}">
                        <div class="deals-container">
                            <div class="deals-sub-container">
                                <!-- Title and Description Section -->
                                <div class="deals-content">
                                    <h2>${promotion.name}</h2>
                                    <p>${promotion.description}</p>
                                </div>
                                <!-- Image Section -->
                                <div class="deals-image">
                                    <img src="${promotion.posterUrl}" alt="${promotion.name}">
                                </div>
                            </div>
                        </div>
                    </c:when>

                    <c:otherwise>            
                            <div class="deals-container">
                                <div class="deals-sub-container">
                                    <!-- Image Section -->
                                    <div class="deals-image">
                                        <img src="${promotion.posterUrl}" alt="${promotion.name}">
                                    </div>
                                    <!-- Title and Description Section -->
                                    <div class="deals-content">
                                        <h2>${promotion.name}</h2>
                                        <p>${promotion.description}</p>
                                    </div>
                                </div>
                            </div>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </c:if>
        <c:if test="${empty activePromotions}">
                <h3 style="text-align: center; color: red; font-size: 20px;">No Promotions Available</h3>
        </c:if>
    </section>

    <jsp:include page="footer.jsp" />
    <script src="assets/scripts/main.js"></script>
</body>
</html>
