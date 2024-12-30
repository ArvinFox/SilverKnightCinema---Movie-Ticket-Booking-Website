<%-- 
    Document   : addShowtime
    Created on : Dec 14, 2024, 8:36:30 PM
    Author     : Umindu Haputhanthri
--%>

<%@page import="java.util.List"%>
<%@page import="model_classes.Hall"%>
<%@page import="dao_classes.HallDAO"%>
<%@page import="model_classes.Cinema"%>
<%@page import="dao_classes.CinemaDAO"%>
<%@page import="model_classes.Movie"%>
<%@page import="dao_classes.MovieDAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:if test="${sessionScope.username == null}">
    <c:redirect url="/admin/login" />
</c:if>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Add Showtime - Silver Knight Cinema</title>
        <link rel="stylesheet" href="../assets/css/adminStyles.css">
    </head>
    <body>
        <div class="main-content sub-content">
            <h1>Add New Showtime</h1>
            
            <form id="showtimeForm" action="manageShowtimes" method="POST">
                <input type="hidden" name="action" value="add">
                
                <%
                    HallDAO hallDAO = new HallDAO();
                    CinemaDAO cinemaDAO = new CinemaDAO();
                    List<Hall> hallList = hallDAO.getAllHalls();
                    for (Hall hall : hallList) {
                        Cinema cinema = cinemaDAO.getCinemaById(hall.getCinemaId());
                        hall.setCinema(cinema.getName());
                    }
                    request.setAttribute("hallList", hallList);
                %>

                <div class="form-group">
                    <label for="hall">Hall</label>
                    <select id="hall" name="hall" required>
                        <c:forEach var="hall" items="${hallList}">
                            <option value="${hall.hallId}">${hall.name} (${hall.cinema})</option>
                        </c:forEach>
                    </select>
                </div>

                <%
                    MovieDAO movieDAO = new MovieDAO();
                    List<Movie> nowShowingMovieList = movieDAO.getNowShowingMovies();
                    request.setAttribute("nowShowingMovieList", nowShowingMovieList);
                %>
                
                <div class="form-group">
                    <label for="movie">Movie</label>
                    <select id="movie" name="movie" required>
                        <c:forEach var="movie" items="${nowShowingMovieList}">
                            <option value="${movie.movieId}">${movie.title}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label for="showDate">Show Date</label>
                    <input type="date" id="showDate" name="showDate" required>
                </div>

                <div class="form-group">
                    <label for="showTime">Show Time</label>
                    <input type="time" id="showTime" name="showTime" required>
                </div>

                <button type="submit" class="action-btn add-btn-link font-16">Add Showtime</button>
            </form>
        </div>

        <script src="../assets/scripts/admin.js"></script>
    </body>
</html>
