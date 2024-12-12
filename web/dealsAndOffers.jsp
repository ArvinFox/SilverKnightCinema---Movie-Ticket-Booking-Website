<%-- 
    Document   : dealsAndOffers
    Created on : Nov 29, 2024, 4:59:09 PM
    Author     : Kavindya De Silva
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Deals And Offers | SilverKnight Cinema</title>
        <link rel="stylesheet" href="assets/css/style.css">
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        
        <div class="banner-deals-and-offers">
            <h1 class="banner-title-deals-and-offers"> Deals And Offers </h1>
            <div class="home-link-deals-and-offers">
                <a href="index.jsp"> Home </a>
                <span> /</span>
                <span>Deals And Offers </span>
            </div>
        </div>
        
        <section class="deals-and-offers">
            <h2 class="deals-h2"> Dive into Exclusive Discounts, Early Bird Offers, and Curated Movie Selections.               Your Ultimate Movie Adventure<span class="deals-span">  STARTS NOW!</span></h2>
            
            <c:if test="${not empty activePromotion}">
                <c:forEach var="promotion" items="${activePromotion}">
                    <c:choose>
                        <c:when test="${promotion.promotionId % 2 == 0}">
                           <div class="deal">
                                <h3>${promotion.name}
                                <p> ${promotion.description} </p>
                                </h3>
                                <img src="${promotion.posterUrl}" alt="${promotion.name}">
                            </div> 
                        </c:when>
                        
                        <c:otherwise>
                            <div class="deal">
                                <img src="${promotion.posterUrl}" alt="${promotion.name}"> 
                                <h3>${promotion.name}
                                <p>${promotion.description}</p>
                                </h3>
                            </div> 
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </c:if>
            
            <c:if test="${empty activePromotion}">
                <h3 style="text-align: center; color: red; font-size: 20px;">No Promotions Available</h3>
            </c:if>
              
        </section>
        
        <jsp:include page="footer.jsp" />
        <script src="assets/scripts/main.js"></script>
    </body>
</html>
