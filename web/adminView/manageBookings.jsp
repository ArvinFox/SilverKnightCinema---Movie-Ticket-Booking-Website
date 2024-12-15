<%-- 
    Document   : manageBookings
    Created on : Dec 15, 2024, 9:24:50 PM
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
        <title>Manage Bookings - Silver Knight Cinema</title>
        <link rel="stylesheet" href="../assets/css/adminStyles.css">
    </head>
    <body>
        <%@ include file="../adminTemplates/header.jsp" %>
        <%@ include file="../adminTemplates/sidebar.jsp" %>

        <div class="main-content" id="mainContent">
            <h1>Manage Bookings</h1>
            
            <div class="filter-header" id="filterHeader">
                <h2>Filter Options</h2>
                <span class="toggle-icon" id="toggleIcon">&#9660;</span> <!-- Down arrow icon -->
            </div>
            
            <div class="search-container" id="filterOptions">
                <form id="searchForm" action="manageBookings" method="get">
                    <div class="flex">
                        <div>
                            <label for="movie">Movie:</label>
                            <input type="hidden" name="movieId" id="movieId">
                            <select name="movie" id="movie">
                                <option value="any">All Movies</option>
                                <c:forEach var="movie" items="${movieList}">
                                    <option data-movie-id="${movie.movieId}" value="${movie.title}" ${movie.title == param.movie ? "selected" : ""}>${movie.title}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div>
                            <label for="hall">Hall:</label>
                            <input type="hidden" name="hallId" id="hallId">
                            <select name="hall" id="hall">
                                <option value="any">All Halls</option>
                                <c:forEach var="hall" items="${hallList}">
                                    <option data-hall-id="${hall.hallId}" value="${hall.name}" ${hall.name == param.hall ? "selected" : ""}>${hall.name} - ${hall.location}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    
                    <label for="type">Type:</label>
                    <select name="type" id="type">
                        <option value="any">All Users</option>
                        <option value="Registered Users" ${param.type == 'Registered Users' ? 'selected' : ''}>Registered Users</option>
                        <option value="Guests" ${param.type == 'Guests' ? 'selected' : ''}>Guests</option>
                    </select>
                    
                    <label for="userInfo">Name or Email:</label>
                    <input type="text" name="userInfo" id="userInfo" placeholder="Search by name or email" 
                           value="${param.userInfo != null ? param.userInfo : ''}">

                    <label for="bookingDateFrom">Booking Date From:</label>
                    <input type="date" name="bookingDateFrom" id="bookingDateFrom" 
                           value="${param.bookingDateFrom != null ? param.bookingDateFrom : ''}">
                    
                    <label for="bookingDateTo">To:</label>
                    <input type="date" name="bookingDateTo" id="bookingDateTo" 
                           value="${param.bookingDateTo != null ? param.bookingDateTo : ''}">

                    <div class="buttons-container">
                        <button type="button" class="reset-btn" ${empty param.movie && empty param.hall && empty param.type && empty param.userInfo && empty param.bookingDateFrom && empty param.bookingDateTo ? 'disabled' : ''} onclick="window.location.href = 'manageBookings'">Reset Filters</button>
                        <button type="button" onclick="validateSearchInput()">Search</button>
                    </div>
                </form>
            </div>
                    
            <c:if test="${not empty bookingList}">
                <table class="bookings-table">
                    <thead>
                        <tr>
                            <th>Booking ID</th>
                            <th>User ID/Guest ID</th>
                            <th>Seats</th>
                            <th>Booking Date</th>
                            <th>Paid Status</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="booking" items="${bookingList}">
                            <tr>
                                <td>${booking.bookingId}</td>
                                <td>${booking.isUser ? booking.userId : booking.guestId} ${booking.isUser ? "[User]" : "[Guest]"}</td>
                                <td>${booking.bookedSeatsAsString}</td>
                                <td>${booking.bookingDate}</td>
                                <td>${booking.isPaid ? "Paid" : "Unpaid"}</td>
                                <td>
                                    <div style="display: flex;">
                                        <input type="submit" id="openModal" class="action-btn view-btn" data-id="${booking.bookingId}" data-action="view" data-type="Booking" value="View" />
                                        <form action="manageBookings" method="POST" onsubmit="return confirmBookingDelete('${booking.bookingId}')">
                                            <input type="submit" class="action-btn delete-btn" value="Delete" />
                                            <input type="hidden" name="action" value="delete" />
                                            <input type="hidden" name="bookingId" value="${booking.bookingId}"/>
                                        </form>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${empty bookingList}">
                <p>No bookings available.</p>
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
                const movieInput = document.getElementById('movie');
                const hallInput = document.getElementById('hall');
                const typeInput = document.getElementById('type');
                const userInfoInput = document.getElementById('userInfo');
                const bookingDateFromInput = document.getElementById('bookingDateFrom');
                const bookingDateToInput = document.getElementById('bookingDateTo');

                if (movieInput.value.trim() === "any" && hallInput.value.trim() === "any" && typeInput.value.trim() === "any" && !userInfoInput.value.trim() && !bookingDateFromInput.value.trim() && !bookingDateToInput.value.trim()) {
                    movieInput.classList.add("input-error");
                    hallInput.classList.add("input-error");
                    typeInput.classList.add("input-error");
                    userInfoInput.classList.add("input-error");
                    bookingDateFromInput.classList.add("input-error");
                    bookingDateToInput.classList.add("input-error");

                    setTimeout(() => {
                        hallInput.classList.remove("input-error");
                        movieInput.classList.remove("input-error");
                        typeInput.classList.remove("input-error");
                        userInfoInput.classList.remove("input-error");
                        bookingDateFromInput.classList.remove("input-error");
                        bookingDateToInput.classList.remove("input-error");
                    }, 300);

                    return false;
                }
                
                const selectedMovie = movieInput.options[movieInput.selectedIndex];
                const selectedHall = hallInput.options[hallInput.selectedIndex];

                const movieIdElement = document.getElementById('movieId');
                movieIdElement.value = selectedMovie.getAttribute("data-movie-id");

                const hallIdElement = document.getElementById('hallId');
                hallIdElement.value = selectedHall.getAttribute("data-hall-id");

                document.getElementById("searchForm").submit();
            }
        </script>
        <script src="../assets/scripts/admin.js"></script>
    </body>
</html>
