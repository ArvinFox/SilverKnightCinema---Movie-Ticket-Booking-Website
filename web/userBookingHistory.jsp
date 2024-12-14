<%-- 
    Document   : userBookingHistory
    Created on : Nov 23, 2024, 12:57:20 PM
    Author     : arvin
--%>

<%@page import="dao_classes.ShowtimeDAO"%>
<%@page import="dao_classes.MovieDAO"%>
<%@page import="dao_classes.HallDAO"%>
<%@page import="model_classes.Booking"%>
<%@page import="model_classes.Showtime"%>
<%@page import="model_classes.Movie"%>
<%@page import="model_classes.Hall"%>
<%@page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="assets/css/style.css"/>
        <link rel="stylesheet" href="assets/css/forms.css"/>
        <title>Booking History</title>
    </head>
    <body>
        <%
            ShowtimeDAO showtimeDao = new ShowtimeDAO();
            MovieDAO movieDao = new MovieDAO();
            HallDAO hallDao = new HallDAO();
        %>
        <div class="user-booking-history-container">
            <%
                HttpSession sessionBooking = request.getSession();
                List<Booking> bookings = (List<Booking>) sessionBooking.getAttribute("bookings");
            %>
            <% if (bookings != null && !bookings.isEmpty()) { %>
                <table class="booking-table">
                    <thead>
                        <tr>
                            <td>Booking ID</td>
                            <td>Movie</td>
                            <td>Date</td>
                            <td>Time</td>
                            <td>Location</td>
                            <td>Payment Status</td>
                            <td>Change Time Slot</td>
                        </tr>
                    </thead>
                    <tbody>
                        <% 
                            for (Booking booking : bookings) { 
                            Showtime showtime = showtimeDao.getShowtimeById(booking.getShowtimeId());
                            Movie movie = movieDao.getMovieById(showtime.getMovieId());
                            Hall hall = hallDao.getHallById(showtime.getHallId());
                        %>
                            <tr>
                                <td><%= booking.getBookingId() %></td>
                                <td><%= movie.getTitle() %></td>
                                <td><%= booking.getBookingDate() %></td>
                                <td><%= showtime.getShowTime() %></td>
                                <td><%= hall.getLocation() %></td>
                                <td>
                                    <span class="<%= booking.getIsPaid() ? "paid" : "not-paid" %>">
                                        <%= booking.getIsPaid() ? "Paid" : "Not Paid" %>
                                    </span>
                                </td>
                                <td><input type="submit" value="Change"/></td>
                            </tr>
                        <% 
                            } 
                        %>
                    </tbody>
                </table>
            <% 
                } 
                else 
                { 
            %>
                <p style="text-align: center; font-size: 20px;">No data available.</p>
            <% 
                } 
            %>
        </div>
    </body>
</html>