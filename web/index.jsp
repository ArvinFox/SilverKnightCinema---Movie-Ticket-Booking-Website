<%-- 
    Document   : index
    Created on : Dec 13, 2024, 12:07:08 AM
    Author     : Mahith Abeysinghe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home | SilverKnight Cinema</title>
        <link rel="icon" type="image/png" href="assets/images/favicons/favicon-light.png">
        
        <link rel="stylesheet" href="assets/css/home-css.css">
        
    </head>
    <body>
        <jsp:include page="home-HeaderAndFooter/header.jsp"/>

        <div class="main-page-background">
            <section class="behind-the-nav-bar"></section>

            <section class="carousel-section">
                <!-- carousel -->
                <div class="carousel-main-glass-card">
                    <div class="carousel">
                        <!-- list item -->
                        <div class="list">
                            <div class="item">
                                <img src="assets/images/home-images/aquaman-banner.jpg">
                                <div class="content">
                                    <div class="author">MAHITH</div>
                                    <div class="title">DESIGN SLIDER</div>
                                    <div class="topic">MOVIE</div>
                                    <div class="des">
                                        <!-- lorem 50 -->
<!--                                        Lorem ipsum dolor, sit amet consectetur adipisicing elit. Ut sequi, rem magnam nesciunt minima placeat, itaque eum neque officiis unde, eaque optio ratione aliquid assumenda facere ab et quasi ducimus aut doloribus non numquam. Explicabo, laboriosam nisi reprehenderit tempora at laborum natus unde. Ut, exercitationem eum aperiam illo illum laudantium?-->
                                    </div>
                                    <div class="buttons">
                                        <button>SEE MORE</button>
                                        <button>BUY TICKETS</button>
                                    </div>
                                </div>
                            </div>
                            <div class="item">
                                <img src="assets/images/home-images/spidernoway-banner.jpg">
                                <div class="content">
                                    <div class="author">MAHITH</div>
                                    <div class="title">DESIGN SLIDER</div>
                                    <div class="topic">MOVIE</div>
                                    <div class="des">
<!--                                        Lorem ipsum dolor, sit amet consectetur adipisicing elit. Ut sequi, rem magnam nesciunt minima placeat, itaque eum neque officiis unde, eaque optio ratione aliquid assumenda facere ab et quasi ducimus aut doloribus non numquam. Explicabo, laboriosam nisi reprehenderit tempora at laborum natus unde. Ut, exercitationem eum aperiam illo illum laudantium?-->
                                    </div>
                                    <div class="buttons">
                                        <button>SEE MORE</button>
                                        <button>SUBSCRIBE</button>
                                    </div>
                                </div>
                            </div>
                            <div class="item">
                                <img src="assets/images/home-images/drstrange-banner.jpg">
                                <div class="content">
                                    <div class="author">MAHITH</div>
                                    <div class="title">DESIGN SLIDER</div>
                                    <div class="topic">MOVIE</div>
                                    <div class="des">
<!--                                        Lorem ipsum dolor, sit amet consectetur adipisicing elit. Ut sequi, rem magnam nesciunt minima placeat, itaque eum neque officiis unde, eaque optio ratione aliquid assumenda facere ab et quasi ducimus aut doloribus non numquam. Explicabo, laboriosam nisi reprehenderit tempora at laborum natus unde. Ut, exercitationem eum aperiam illo illum laudantium?-->
                                    </div>
                                    <div class="buttons">
                                        <button>SEE MORE</button>
                                        <button>SUBSCRIBE</button>
                                    </div>
                                </div>
                            </div>
                            <div class="item">
                                <img src="assets/images/home-images/dispicableme-banner.jpg">
                                <div class="content">
                                    <div class="author">MAHITH</div>
                                    <div class="title">DESIGN SLIDER</div>
                                    <div class="topic">MOVIE</div>
                                    <div class="des">
<!--                                        Lorem ipsum dolor, sit amet consectetur adipisicing elit. Ut sequi, rem magnam nesciunt minima placeat, itaque eum neque officiis unde, eaque optio ratione aliquid assumenda facere ab et quasi ducimus aut doloribus non numquam. Explicabo, laboriosam nisi reprehenderit tempora at laborum natus unde. Ut, exercitationem eum aperiam illo illum laudantium?-->
                                    </div>
                                    <div class="buttons">
                                        <button>SEE MORE</button>
                                        <button>SUBSCRIBE</button>
                                    </div>
                                </div>
                            </div>
                        </div>



                        
                        <!-- list thumnail -->
                        <div class="thumbnail">
                            <div class="item">
                                <img src="assets/images/home-images/aquaman-poster.jpg">
                                <div class="content">
                                    <div class="title">
                                        Name Slider
                                    </div>
                                    <div class="description">
                                        Description
                                    </div>
                                </div>
                            </div>
                            <div class="item">
                                <img src="assets/images/home-images/spidernoway.jpg">
                                <div class="content">
                                    <div class="title">
                                        Name Slider
                                    </div>
                                    <div class="description">
                                        Description
                                    </div>
                                </div>
                            </div>
                            <div class="item">
                                <img src="assets/images/home-images/drstrange.jpeg">
                                <div class="content">
                                    <div class="title">
                                        Name Slider
                                    </div>
                                    <div class="description">
                                        Description
                                    </div>
                                </div>
                            </div>
                            <div class="item">
                                <img src="assets/images/home-images/dispicableMe-3.jpg">
                                <div class="content">
                                    <div class="title">
                                        Name Slider
                                    </div>
                                    <div class="description">
                                        Description
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- next prev -->
            
                        <div class="arrows">
                            <button id="prev"><</button>
                            <button id="next">></button>
                        </div>
                        <!-- time running -->
                        <div class="time"></div>
                    </div>
                </div>
            </section>
        
            <!-- ================================================ -->
             <!---- NOW-SHOWING-SECTION ---->
            <section>
                <div class="main-now-showing-card-section">
                    
                    <div class="now-showing-main-glass-card">
                        <div class="glass-div-topic-row">
                            <div class="main-card-topics">
                                <p>Now Showing</p>
                            </div>
                        </div>
                        
                        <div class="film-card-set">
                            <!-- ======== FILM-CARDS ======== -->
                            <%
                                int movieCount=0;
                            %>
                            <c:forEach var="movie" items="${nowShowingMovies}">
                                <%
                                    if(movieCount < 4) {
                                %>
                                    <div class="film-card">
                                        <!-- IMG -->
                                        <img class="film-card-poster-img" src="${movie.posterUrl}">

                                        <!-- FILM-CARD-INFO -->
                                        <div class="film-card-info">
                                            <div class="film-card-title">${movie.title}</div>
                                            <a href="movies?movieId=${movie.movieId}" class="film-card-moreInfo">More Info >></a>
                                            <div class="card-buttons">
                                                <button class="film-card-watchTrailer trailer-btn">Watch Trailer</button>
                                                <button class="film-card-buyTickets">Buy Tickets</button>
                                            </div>
                                        </div>
                                    </div>
                                <%
                                        movieCount++;
                                    }    
                                %>
                                
                            </c:forEach>
                             

                        </div>
                        
                    </div>
                </div>
                
            </section>

            <!---- COMING-SOON-SECTION ---->
            <section>
                <div class="main-coming-soon-card-section">
                    
                    <div class="coming-soon-main-glass-card">
                        <div class="glass-div-topic-row">
                            <div class="main-card-topics">
                                <p>Coming Soon</p>
                            </div>
                        </div>
                        
                        <div class="film-card-set">
                            <!-- ======== FILM-CARDS ======== -->
                            <%
                                int movieCount1=0;
                            %>
                            <c:forEach var="movie" items="${comingSoonMovies}">
                                <%
                                    if(movieCount1 < 4) {
                                %>
                                    <div class="film-card">
                                        <!-- IMG -->
                                        <img class="film-card-poster-img" src="${movie.posterUrl}">

                                        <!-- FILM-CARD-INFO -->
                                        <div class="film-card-info">
                                            <div class="film-card-title">${movie.title}</div>
                                            <a href="movies?movieId=${movie.movieId}" class="film-card-moreInfo">More Info >></a>
                                            <div class="card-buttons">
                                                <button class="film-card-watchTrailer trailer-btn">Watch Trailer</button>
                                                <button class="film-card-buyTickets">Buy Tickets</button>
                                            </div>
                                        </div>
                                    </div>
                                <%
                                        movieCount1++;
                                    }    
                                %>
                                
                            </c:forEach>
                             

                        </div>
                        
                    </div>
                </div>
                
            </section>

            <!---- DEALS-&-OFFERS-SECTION ---->
            <section>
                <div class="deals-and-offers-main-card-section">
                    
                    <div class="deals-and-offers-main-glass-card">
                        <div class="glass-div-topic-row">
                            <div class="main-card-topics">
                                <p>Deals & Offers</p>
                            </div>
                        </div>

                        <div class="deals-and-offers-card-set">
                            <!-- deals - section 1 (main) -->
                            <div class="deals-and-offers-section-1-main">
                                <!-- main-deals-card -->
                                <div class="deals-and-offers-main-card">
                                    <img class="deals-card-banner-img" src="assets/images/home-images/deals-banner-3.png">
                                </div>
                            </div>
                            <!-- deals - section 2 (sub) -->
                            <div class="deals-and-offers-section-2-sub">
                                <!-- sub-deal-cards -->
                                <div class="deals-and-offers-sub-card">
                                    <img class="deals-card-banner-img" src="assets/images/home-images/deals-banner-1.jpg">
                                </div>

                                <div class="deals-and-offers-sub-card">
                                    <img class="deals-card-banner-img" src="assets/images/home-images/deals-banner-2.png">
                                </div>
                            </div>
                        </div>
                       

                    </div>
                </div>
                
            </section>
        </div>


        <!-- ====================================================== -->








        <div class="trailer-popup" id="trailerPopup">
            <div class="popup-content">
              <span class="close-btn" id="closePopup">&times;</span>
              <iframe id="trailerVideo" allow="autoplay; encrypted-media" allowfullscreen></iframe>
            </div>
        </div>
        <jsp:include page="home-HeaderAndFooter/footer.jsp"/>
        <script src="assets/scripts/home-javaScript.js"></script>

    </body>
</html>
