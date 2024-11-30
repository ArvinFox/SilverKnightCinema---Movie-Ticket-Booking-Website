<%-- 
    Document   : dealsAndOffers
    Created on : Nov 29, 2024, 4:59:09 PM
    Author     : Kavindya De Silva
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            <div class="deal">
                <h3>Blockbuster Discounts
                <p>Enjoy <span class="highlight">20% off</span> on tickets for the latest blockbusters every                           weekend!</p></h3>
                <img src="assets/images/deals-and-offers-image1.png" alt="Blockbuster Image">
            </div>

            <div class="deal">
                <img src="assets/images/deals-and-offers-image2.jpeg" alt="Early Bird Image">
                <h3>Early Bird Specials
                <p>Get <span class="highlight">10% off</span> when you book your tickets a week in advance. Perfect                    for planning your movie nights!</p></h3>
            </div>

            <div class="deal">
                <h3>Combo Deals
                <p><span class="highlight">Buy 2 tickets and get 50% off</span> on the third. Bring your friends                       and family for an epic movie marathon!</p></h3>
                <img src="assets/images/deals-and-offers-image3.jpg" alt="Combo Image">
            </div>
        </section>
        
        <jsp:include page="footer.jsp" />
    </body>
</html>
