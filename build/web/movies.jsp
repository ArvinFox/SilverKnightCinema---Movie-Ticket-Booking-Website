<%-- 
    Document   : movies
    Created on : Nov 18, 2024, 2:26:36 PM
    Author     : SASHEN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Movies | SilverKnight Cinema</title>
        <link rel="stylesheet" href="assets/css/movies.css">
    </head>
    <body>
        <jsp:include page="header.jsp"/>

        <div class="banner-movies">
            <h1 class="banner-title-movies"> Movies </h1>
            <div class="home-link-movies">
                <a href="home"> Home </a>
                <span> /</span>
                <span>Movies </span>
            </div>
        </div>

        <!--Movie type selection-->
        <section class="movie-types">
            <div class="ongoing movie-type selected">
                NOW SHOWING
            </div>
            <div class="divider"></div>
            <div class="coming-soon movie-type">
                COMING SOON
            </div>
        </section>

        <section class="movie-filters">
            <form class="filter-form">
                <div class="filters">
                    <div class="language-filter">
                        <select class="language-select common-box" id="language-select">
                            <option value="any">All Languages</option>
                            <c:forEach var="language" items="${languages}">
                                <option value="${language.languageId}">${language.language}</option>
                            </c:forEach>
                        </select>
                    </div>
                    
                    <div class="genre-filter">
                        <select class="genre-select common-box" id="genre-select">
                            <option value="any">All Genres</option>
                            <c:forEach var="genre" items="${genres}">
                                <option value="${genre.genreId}">${genre.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    
                    <div class="search-filter">
                        <div>
                            <input class="search-bar common-box" type="text" id="search-bar" placeholder="Search by movie title">
                        </div>
                        <button type="button" class="search-icon-btn">
                            <i class="fa fa-search"></i>
                        </button>
                    </div>
                </div>
            </form>
        </section>

        <section class="movie-list">
            <c:if test="${not empty movies}">
                <c:forEach var="movie" items="${movies}">
                    <div class="movie">
                        <a href="movies?movieId=${movie.movieId}">
                            <img src="${movie.posterUrl}" alt="${movie.title}">
                            <div class="movie-content">
                                <h4>${movie.title}</h4>
                                <p>NOW SCREENING</p>
                            </div>
                            <div class="movie-overlay">
                                <div class="overlay-content">
                                    <button class="movie-action trailer-btn" data-trailer="${movie.trailerUrl}">Watch Trailer</button>
                                    <button class="movie-action buy-ticket-btn" data-movie-id="${movie.movieId}">Buy Ticket</button>
                                    <button class="movie-action">More Info</button>
                                </div>
                            </div>
                        </a>
                    </div>
                </c:forEach>
            </c:if>
            <c:if test="${empty movies}">
                <p>No movies available.</p>
            </c:if>
        </section>
    </div>

    <div class="trailer-popup" id="trailerPopup">
        <div class="popup-content">
            <span class="close-btn" id="closePopup">&times;</span>
            <iframe id="trailerVideo" allow="autoplay; encrypted-media" allowfullscreen></iframe>
        </div>
    </div>

    <jsp:include page="footer.jsp"/>

    <script src="assets/scripts/main.js"></script>
    <script>
        document.querySelector(".search-icon-btn").addEventListener('click', async () => {
            const movies = await fetchFilteredMovies();
            updateMoviesSection(movies);
        });

        document.querySelector(".search-bar").addEventListener('keypress', async (event) => {
            if (event.key === "Enter") {
                event.preventDefault();
                document.querySelector(".search-icon-btn").click();
            }
        });

        document.querySelector(".language-select").addEventListener('change', async () => {
            const movies = await fetchFilteredMovies();
            updateMoviesSection(movies);
        });

        document.querySelector(".genre-select").addEventListener('change', async () => {
            const movies = await fetchFilteredMovies();
            updateMoviesSection(movies);
        });
        
        document.addEventListener('DOMContentLoaded', () => {
            initializeTrailerAndTicketButtons();
            playVideo();
        });
    </script>
</body>
</html>
