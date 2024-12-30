<%-- 
    Document   : manageShowtimes
    Created on : Dec 14, 2024, 8:33:44 PM
    Author     : Umindu Haputhanthri
--%>

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
        <title>Manage Showtimes - Silver Knight Cinema</title>
        <link rel="stylesheet" href="../assets/css/adminStyles.css">
    </head>
    <body>
        <%@ include file="../adminTemplates/header.jsp" %>
        <%@ include file="../adminTemplates/sidebar.jsp" %>

        <div class="main-content" id="mainContent">
            <h1>Manage Showtimes</h1>
            
            <button id="openModal" class="action-btn add-btn-link font-16" data-action="add" data-type="Showtime">Add New Showtime</button>
            
            <div class="filter-header" id="filterHeader">
                <h2>Filter Options</h2>
                <span class="toggle-icon" id="toggleIcon">&#9660;</span> <!-- Down arrow icon -->
            </div>
            
            <div class="search-container" id="filterOptions">
                <form id="searchForm" action="manageShowtimes" method="get">
                    <label for="hall">Hall:</label>
                    <input type="hidden" name="hallId" id="hallId">
                    <select name="hall" id="hall">
                        <option value="any">All Halls</option>
                        <c:forEach var="hall" items="${hallList}">
                            <option data-hall-id="${hall.hallId}" value="${hall.cinema}" ${hall.cinema == param.hall ? "selected" : ""}>${hall.name} (${hall.cinema})</option>
                        </c:forEach>
                    </select>

                    <label for="movie">Movie:</label>
                    <input type="hidden" name="movieId" id="movieId">
                    <select name="movie" id="movie">
                        <option value="any">All Movies</option>
                        <c:forEach var="movie" items="${nowShowingMovieList}">
                            <option data-movie-id="${movie.movieId}" value="${movie.title}" ${movie.title == param.movie ? "selected" : ""}>${movie.title}</option>
                        </c:forEach>
                    </select>
                    
                    <label for="showDate">Show Date:</label>
                    <input type="date" name="showDate" id="showDate" 
                               value="${param.showDate != null ? param.showDate : ''}">
                    
                    <label for="showTime">Show Time:</label>
                    <input type="time" name="showTime" id="showTime" 
                               value="${param.showTime != null ? param.showTime : ''}">

                    <div class="buttons-container">
                        <button type="button" class="reset-btn" ${empty param.hall && empty param.movie && empty param.showDate && empty param.showTime ? 'disabled' : ''} onclick="window.location.href = 'manageShowtimes'">Reset Filters</button>
                        <button type="button" onclick="validateSearchInput()">Search</button>
                    </div>
                </form>
            </div>
                    
            <c:if test="${not empty showtimeList}">
                <table class="showtimes-table">
                    <thead>
                        <tr>
                            <th>Showtime ID</th>
                            <th>Movie</th>
                            <th>Hall</th>
                            <th>Show Date</th>
                            <th>Show Time</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="showtime" items="${showtimeList}">
                            <tr>
                                <td>${showtime.showtimeId}</td>
                                <td>${showtime.movieTitle}</td>
                                <td>${showtime.hallName}</td>
                                <td>${showtime.showDate}</td>
                                <td>${showtime.formattedTime}</td>
                                <td>
                                    <div style="display: flex;">
                                        <input type="submit" id="openModal" class="action-btn view-btn" data-id="${showtime.showtimeId}" data-action="view" data-type="Showtime" value="View" />
                                        <form action="manageShowtimes" method="POST" onsubmit="return confirmShowtimeDelete('${showtime.movieTitle}', '${showtime.hallName}', '${showtime.showtimeId}', '${showtime.showDate}', '${showtime.formattedTime}')">
                                            <input type="submit" class="action-btn delete-btn" value="Delete" />
                                            <input type="hidden" name="action" value="delete" />
                                            <input type="hidden" name="showtimeId" value="${showtime.showtimeId}"/>
                                        </form>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${empty showtimeList}">
                <p>No showtimes available.</p>
            </c:if>   
        </div>
                    
        <!-- Modal structure -->
        <div id="popupModal" class="modal">
          <div class="modal-content" id="modalContent">
            <button class="close-btn" id="closeModal">X</button>
            <!-- Dynamic content will load here -->
          </div>
        </div>

        <%@ include file="../adminTemplates/footer.jsp" %>
        
        <script>
            function validateSearchInput() {
                const hallInput = document.getElementById('hall');
                const movieInput = document.getElementById('movie');
                const showDateInput = document.getElementById('showDate');
                const showTimeInput = document.getElementById('showTime');

                if (hallInput.value.trim() === "any" && movieInput.value.trim() === "any" && !showDateInput.value.trim() && !showTimeInput.value.trim()) {
                    hallInput.classList.add("input-error");
                    movieInput.classList.add("input-error");
                    showDateInput.classList.add("input-error");
                    showTimeInput.classList.add("input-error");

                    setTimeout(() => {
                        hallInput.classList.remove("input-error");
                        movieInput.classList.remove("input-error");
                        showDateInput.classList.remove("input-error");
                        showTimeInput.classList.remove("input-error");
                    }, 300);

                    return false;
                }
                
                const selectedHall = hallInput.options[hallInput.selectedIndex];
                const selectedMovie = movieInput.options[movieInput.selectedIndex];

                const hallIdElement = document.getElementById('hallId');
                hallIdElement.value = selectedHall.getAttribute("data-hall-id");

                const movieIdElement = document.getElementById('movieId');
                movieIdElement.value = selectedMovie.getAttribute("data-movie-id");

                document.getElementById("searchForm").submit();
            }
        </script>
        <script src="../assets/scripts/admin.js"></script>
    </body>
</html>
