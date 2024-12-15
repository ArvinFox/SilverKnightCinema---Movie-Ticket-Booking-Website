<%-- 
    Document   : viewBooking
    Created on : Dec 15, 2024, 9:23:21 PM
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
        <title>Movie Details - Silver Knight Cinema</title>
        <link rel="stylesheet" href="../assets/css/adminStyles.css">
        <style>
            .sub-content h1 {
                text-align: center;
                color: #222;
                font-size: 2rem;
                margin-bottom: 20px;
            }
        </style>
    </head>
    <body>
        <div class="main-content sub-content">
            <h1>Booking Details</h1>

            <form action="manageBookings" method="POST">
                <div class="booking-details entity-details">
                    <p><strong>Booking ID:</strong></p>
                    <input type="text" class="form-control" value="${booking.bookingId}" disabled>

                    <p><strong>${booking.isUser ? "User ID:" : "Guest ID:"}</strong></p>
                    <input type="text" class="form-control editable" value="${booking.isUser ? booking.userId : booking.guestId}" disabled>

                    <p><strong>Showtime:</strong></p>
                    <table class="showtime-details-table">
                        <thead>
                            <tr>
                                <th>Showtime ID</th>
                                <th>Movie</th>
                                <th>Hall</th>
                                <th>Show Date</th>
                                <th>Show Time</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>${showtime.showtimeId}</td>
                                <td>${showtime.movieTitle}</td>
                                <td>${showtime.hallName}</td>
                                <td>${showtime.showDate}</td>
                                <td>${showtime.formattedTime}</td>
                            </tr>
                        </tbody>
                    </table>
                    

                    <c:if test="${booking.promotionId == 0}">
                        <p><strong>Promotion:</strong></p>
                        <input type="text" class="form-control editable" value="No promotions applied" disabled>
                    </c:if>
                    <c:if test="${booking.promotionId != 0}">
                        <p><strong>Promotion [ID: ${booking.promotionId}]:</strong></p>
                        <input type="text" class="form-control editable" value="${booking.promotionId}" disabled>
                    </c:if>

                    <p><strong>Booked Seats:</strong></p>
                    <input type="text" class="form-control editable" value="${booking.bookedSeatsAsString}" disabled>
                    
                    <p><strong>Booking Date:</strong></p>
                    <input type="date" class="form-control editable" value="${booking.bookingDate}" disabled>
                    
                    <p><strong>Items Added:</strong></p>
                    <c:if test="${empty orders}">
                        <input type="text" class="form-control editable" value="No items added" disabled>
                    </c:if>
                    <c:if test="${not empty orders}">
                        <div id="order-container">
                            <table id="order-table">
                                <thead>
                                    <tr>
                                        <th>Item ID</th>
                                        <th>Item Name</th>
                                        <th>Quantity</th>
                                        <th>Unit Price (Rs.)</th>
                                        <th>Total Price (Rs.)</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="order" items="${orders}">
                                        <tr>
                                            <td>${order.itemId}</td>
                                            <td>${order.itemName}</td>
                                            <td>${order.quantity}</td>
                                            <td>${order.pricePerItem}</td>
                                            <td>${order.totalPrice}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </c:if>
                    
                    <p><strong>Booking Price (Rs.):</strong></p>
                    <input type="text" class="form-control editable" value="${booking.totalPrice}" disabled>
                    
                    <p><strong>Paid Status:</strong></p>
                    <input type="text" class="form-control editable" value="${booking.isPaid ? "Paid" : "Unpaid"}" disabled>
                </div>
            </form>
        </div>
        
        <script src="../assets/scripts/admin.js"></script>
    </body>
</html>
