<%-- 
    Document   : ongoingMovieDetails
    Created on : Nov 18, 2024, 2:27:29 PM
    Author     : SASHEN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mufasa: The Lion King</title>
        <link rel="stylesheet" href="assets/css/movies.css">
    </head>
    <body>
        <div class="header">
        <div class="banner-wrapper">
            <!--Set Poster-->
            <img src="assets/images/posters/m1.jpg" alt="Mufasa poster" class="poster">
            <!--Set Banner-->
            <img src="assets/images/posters/home1.jpg" alt="Mufasa banner" class="banner">
            <!--Watch Trailer Link-->
            <a href="https://youtu.be/o17MF9vnabg" class="box">
                <button class="watch-trailer">Watch Trailer</button>
            </a>
        </div>
        <h1>MUFASA: The Lion King (2h 30min)</h1>
        <p class="status">SCREENING NOW</p>

        <!--Cinema hall selection-->
        <h2>Now Showing At</h2>
        <div class="cinema-selection">
            <button>MovieMood Cinema - Colombo</button>
            <button>Movie World - Galle</button>
            <button>CineHub Deluxe - Kandy</button>
        </div>

        <!--Showtime selection-->
        <div class="showtime">
            <h3>Showtimes</h3>
            <!--Set date for showtime-->
            <div class="showtime-selection">
                <label for="date">Date</label>
                <select class="showtime-box"id="date">
                    <option value="1">08 Nov 2024</option>
                    <option value="1">10 Nov 2024</option>
                    <option value="1">12 Nov 2024</option>
                    <option value="1">14 Nov 2024</option>
                    <option value="1">18 Nov 2024</option>
                </select>
            </div>
            <!--Set time for showtime-->
            <div class="showtime-selection">
                <label for="time">Time</label>
                <select class="showtime-box" id="time">
                    <option value="1">9.30 A.M</option>
                    <option value="1">12.30 P.M</option>
                    <option value="1">3.00 P.M</option>
                    <option value="1">6.30 A.M</option>
                </select>
            </div>
            <!--Set Cinema-->
            <div class="showtime-selection">
                <label for="cinema">Cinema</label>
                <select class="showtime-box" id="cinema">
                    <option value="1">MovieMood Cinema - Colombo</option>
                    <option value="1">Movie World - Galle</option>
                    <option value="1">CineHub Deluxe - Kandy</option>
                </select>
            </div>
            <button class="book-now">BOOK NOW</button>
        </div>

        <!--Synopsis-->
        <section class="synopsis">
            <h2 class="syno">Synopsis</h2>
            <p class="para">Lost and alone, orphaned cub Mufasa meets a sympathetic lion named Taka, the heir to a royal bloodline. The chance meeting sets in motion an expansive journey of an extraordinary group of misfits searching for their destinies.</p>
            <hr>
        </section>

        <!--Genre-->
        <section class="genre">
            <h2>Genre</h2>
            <div class="genres">
                <span>Action</span>
                <span>Adventure</span>
                <span>Drama</span>
                <span>Family</span>
            </div>
        </section>

        <!--Cast & Team details-->
        <section class="details">
            <!--Cast Details-->
            <div class="cast">
                <h3>Cast</h3>
                <p>Actors</p>
                <p>Characters</p>
            </div>
            <!--Team Details-->
            <div class="team">
                <h3>Team</h3>
                <p>Directed By</p>
                <p>Produced By</p>
                <p>Written By</p>
                <p>Music By</p>
            </div>
        </section>

        <div class="feedback">
            <h2>User Feedbacks</h2>
            <!--Feedback 1-->
            <div class="feedback-user">
                <h3>John Smith</h3>
                <p>"Jurassic World: Domination delivers non-stop thrills with epic dinosaur battles and heart-pounding adventure. A must watch that truly deserves 5 stars"</p>
            </div>
            <div class="rating">⭐⭐⭐⭐⭐</div>
            <!--Feedback 2-->
            <div class="feedback-user">
                <h3>Emma Johnson</h3>
                <p>"Jurassic World: Domination delivers non-stop thrills with epic dinosaur battles and heart-pounding adventure. A must watch that truly deserves 5 stars"</p>
            </div>
            <div class="rating">⭐⭐⭐⭐⭐</div>
            <!--Feedback 2-->
            <div class="feedback-user">
                <h3>John Smith</h3>
                <p>"Jurassic World: Domination delivers non-stop thrills with epic dinosaur battles and heart-pounding adventure. A must watch that truly deserves 5 stars"</p>
            </div>
            <div class="rating">⭐⭐⭐⭐⭐</div>
        </div>
        
    </div>
    <script src="assets/scripts/main.js"></script>
    </body>
</html>
