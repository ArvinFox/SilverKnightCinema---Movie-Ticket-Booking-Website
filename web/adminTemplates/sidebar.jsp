<%-- 
    Document   : sidebar
    Created on : Dec 3, 2024, 12:28:08 PM
    Author     : Umindu Haputhanthri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String currentPage = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1);
%>
<div class="admin-sidebar" id="adminSidebar">
    <button id="toggleSidebar" class="toggle-sidebar-btn">&#9776;</button>
    <nav>
        <ul>
            <li><a href="manageUsers" class="<%= "manageUsers.jsp".equals(currentPage) ? "selected" : "" %>">Manage Users</a></li>
            <li><a href="manageLanguages" class="<%= "manageLanguages.jsp".equals(currentPage) ? "selected" : "" %>">Manage Languages</a></li>
            <li><a href="manageGenres" class="<%= "manageGenres.jsp".equals(currentPage) ? "selected" : "" %>">Manage Genres</a></li>
            <li><a href="manageMovies" class="<%= "manageMovies.jsp".equals(currentPage) ? "selected" : "" %>">Manage Movies</a></li>
            <li><a href="managePromotions" class="<%= "managePromotions.jsp".equals(currentPage) ? "selected" : "" %>">Manage Promotions</a></li>
            <li><a href="manageHalls" class="<%= "manageHalls.jsp".equals(currentPage) ? "selected" : "" %>">Manage Halls</a></li>
            <li><a href="manageShowtimes" class="<%= "manageShowtimes.jsp".equals(currentPage) ? "selected" : "" %>">Manage Showtimes</a></li>
            <li><a href="manageSeats" class="<%= "manageSeats.jsp".equals(currentPage) ? "selected" : "" %>">Manage Seats</a></li>
            <li><a href="manageBookings" class="<%= "manageBookings.jsp".equals(currentPage) ? "selected" : "" %>">Manage Bookings</a></li>
            <li><a href="manageFoods" class="<%= "manageFoods.jsp".equals(currentPage) ? "selected" : "" %>">Manage Foods</a></li>
            <li><a href="manageInquiries" class="<%= "manageInquiries.jsp".equals(currentPage) ? "selected" : "" %>">Manage Inquiries</a></li>
        </ul>
    </nav>
</div>
