<%-- 
    Document   : movies
    Created on : Nov 18, 2024, 2:26:36 PM
    Author     : SASHEN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Movies</title>
        <link rel="stylesheet" href="assets/css/movies.css">
    </head>
    <body>
        <section class="movie-types">
        <div class="ongoing movie-type selected">
            ONGOING MOVIES
        </div>

        <div class="coming-soon movie-type">
            COMING SOON
        </div>
    </section>

    <section class="movies" id="movies">


        <div class="filters">
            <div class="language-filter">
                <label for="language-select">Language</label>
                <select class="language-select common-box" id="language-select">
                    <!-- Add options here -->
                    <option value="1">English</option>
                    <option value="1">Sinhala</option>
                    <option value="1">Tamil</option>
                </select>
            </div>
            <div class="genre-filter">
                <label for="genre-select">Genre</label>
                <select class="genre-select common-box" id="genre-select">
                    <!-- Add options here -->
                    <option value="1">Action</option>
                    <option value="1">Adventure</option>
                    <option value="1">Drama</option>
                </select>
            </div>
            <label for="search">Search</label>
            <input class="search-bar common-box" type="text" id="search-bar" onkeyup="mysearch()" placeholder="Search movie">
            </input>
        </div>
        <!--Ongoing Movie container-->
        <div class="movies-container">
            <!--container 1-->
            <a href="ongoingMovieDetails.jsp" class="box"> <!-- Link to a movie page -->
                <div class="box">
                    <div class="box-img">
                        <img src="assets/images/posters/m1.jpg" alt="">
                    </div>
                    <h3>Venom</h3>
                    <span>Now Screening</span>
                </div>
            </a>

            <!--container 2-->
            <a href="movie_page_1.html" class="box"> <!-- Link to a movie page -->
                <div class="box">
                    <div class="box-img">
                        <img src="assets/images/posters/m2.jpg" alt="">
                    </div>
                    <h3>Dunkrik</h3>
                    <span>Now Screening</span>
                </div>
            </a>

            <!--container 3-->
            <a href="movie_page_1.html" class="box"> <!-- Link to a movie page -->
                <div class="box">
                    <div class="box-img">
                        <img src="assets/images/posters/m3.jpg" alt="">
                    </div>
                    <h3>Batman</h3>
                    <span>Now Screening</span>
                </div>
            </a>

            <!--container 4-->
            <a href="movie_page_1.html" class="box"> <!-- Link to a movie page -->
                <div class="box">
                    <div class="box-img">
                        <img src="assets/images/posters/m4.jpg" alt="">
                    </div>
                    <h3>John Wick</h3>
                    <span>Now Screening</span>
                </div>
            </a>

            <!--container 5-->
            <a href="movie_page_1.html" class="box"> <!-- Link to a movie page -->
                <div class="box">
                    <div class="box-img">
                        <img src="assets/images/posters/m5.jpg" alt="">
                    </div>
                    <h3>Aquaman</h3>
                    <span>Now Screening</span>
                </div>
            </a>

            <!--container 6-->
            <a href="movie_page_1.html" class="box"> <!-- Link to a movie page -->
                <div class="box">
                    <div class="box-img">
                        <img src="assets/images/posters/m1.jpg" alt="">
                    </div>
                    <h3>Venom</h3>
                    <span>Now Screening</span>
                </div>
            </a>

            <!--container 7-->
            <a href="movie_page_1.html" class="box"> <!-- Link to a movie page -->
                <div class="box">
                    <div class="box-img">
                        <img src="assets/images/posters/m2.jpg" alt="">
                    </div>
                    <h3>Dunkrik</h3>
                    <span>Now Screening</span>
                </div>
            </a>

            <!--container 8-->
            <a href="movie_page_1.html" class="box"> <!-- Link to a movie page -->
                <div class="box">
                    <div class="box-img">
                        <img src="assets/images/posters/m3.jpg" alt="">
                    </div>
                    <h3>Batman</h3>
                    <span>Now Screening</span>
                </div>
            </a>

            <!--container 9-->
            <a href="movie_page_1.html" class="box"> <!-- Link to a movie page -->
                <div class="box">
                    <div class="box-img">
                        <img src="assets/images/posters/m4.jpg" alt="">
                    </div>
                    <h3>John Wick</h3>
                    <span>Now Screening</span>
                </div>
            </a>

            <!--container 10-->
            <a href="movie_page_1.html" class="box"> <!-- Link to a movie page -->
                <div class="box">
                    <div class="box-img">
                        <img src="assets/images/posters/m5.jpg" alt="">
                    </div>
                    <h3>Aquaman</h3>
                    <span>Now Screening</span>
                </div>
            </a>
        </div>
    </section>

    <script src="assets/scripts/main.js"></script>
    </body>
</html>
