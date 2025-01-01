<%-- 
    Document   : index
    Created on : Dec 13, 2024, 12:07:08 AM
    Author     : Mahith Abeysinghe
--%>

<%@page import="model_classes.Hall"%>
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
                            <%
                                int movieCarousel=0;
                            %>
                            <c:choose>
                                <c:when test="${not empty nowShowingMovies}">
                                    <c:forEach var="movie" items="${nowShowingMovies}">
                                            <%
                                                if(movieCarousel < 4) {
                                            %>
                                    <div class="item">
                                            <img src="${movie.detailedPosterUrl}">
                                        <div class="content">
                                            <div class="title">${movie.title}</div>
                                            <div class="des">
                                                <p class="movie-synopsis">
                                                    ${movie.synopsis}
                                                </p>
                                            </div>
                                            <div class="buttons">
                                                <a href="movies?movieId=${movie.movieId}" class="film-card-moreInfo">Buy Tickets</a>
                                            </div>
                                        </div>

                                    </div>
                                        <%
                                                movieCarousel++;
                                            }    
                                        %>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <h1>No Data Available.</h1>
                                </c:otherwise>
                            </c:choose>
                        </div>
        
                        <!-- list thumbnail -->
                        <div class="thumbnail">
                            <%
                                int movieThumb=0;
                            %>
                                <c:forEach var="movie" items="${nowShowingMovies}">
                                    <%
                                        if(movieThumb < 4) {
                                    %>
                                        <div class="item">
                                            <img src="${movie.posterUrl}">
                                        </div>
                            <%
                                movieThumb++;
                                }    
                            %>
                                </c:forEach>
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
                                                <button class="watch-trailer trailer-btn film-card-watchTrailer trailer-btn" data-trailer="${movie.trailerUrl}">Watch Trailer</button>
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
                <div class="main-now-showing-card-section">
                    
                    <div class="now-showing-main-glass-card">
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
                                                <button class="watch-trailer trailer-btn film-card-watchTrailer trailer-btn" data-trailer="${movie.trailerUrl}">Watch Trailer</button>
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
