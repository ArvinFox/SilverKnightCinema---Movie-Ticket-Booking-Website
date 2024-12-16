<%-- 
    Document   : movieSeatSelection
    Created on : Dec 2, 2024, 11:57:04 AM
    Author     : arvin
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Seat Selection</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
    <script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
    <link rel="stylesheet" href="assets/css/style.css"/>
</head>
<body>
    <jsp:include page="header.jsp"/>
    <div class="booking-details-top">
        <div class="booking-details-sub-top movie-title">
            <h1>${movie.title}</h1>
            <div class="timer" id="timer">05:00</div>
        </div>
        <div class="booking-details-sub-top movie-locations">
            <h5><i class='fas fa-map-marker-alt'>&nbsp;&nbsp;</i>${hall.name} - ${hall.location}</h5>
            <h5><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><i class="fa-solid fa-calendar-days"></i>&nbsp;&nbsp;Date : ${showtime.showDate}</h5><span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
            <c:choose>
                <c:when test="${hall.type == 'IMAX'}">
                    <h5>
                        <img src="./assets/images/hall_logos/imax.png" alt="IMAX Hall"/>
                    </h5>
                </c:when>
                <c:when test="${hall.type == '_3D'}">
                    <h5>
                        <img src="./assets/images/hall_logos/dolby3d.png" alt="3D Hall"/>
                    </h5>
                </c:when>
                <c:when test="${hall.type == '_2D'}">
                    <h5>
                        <img src="./assets/images/hall_logos/dolby2d.png" alt="2D Hall"/>
                    </h5>
                </c:when>
                <c:when test="${hall.type == 'VIP'}">
                    <h5>
                        <img src="./assets/images/hall_logos/vip.png" alt="VIP Hall"/>
                    </h5>
                </c:when>
                <c:otherwise>
                    <h5>No Hall Type Available</h5>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="booking-details-sub-top show-time">
            <h5>Show Time :&nbsp;&nbsp; </h5> <div class="dbshowtime"><fmt:formatDate value="${showtime.showTime}" pattern="hh:mm a"/>
        </div>
        </div>
    </div>
    <div class="seat-container-width">
        <div class="screen">Screen</div>
        <h2>Select Preferred Seats</h2>
        <hr>
        <div class="seat-booking-container">
            <c:set var="capacity" value="${hall.capacity}" />
            <c:set var="seatsInSections" value="6,10,6" />
            <c:set var="rowLetters" value="ABCDEFGHIJKLMNOPQRSTUVWXYZ" />
            <c:set var="totalRows" value="${capacity / 22}" />
            <c:set var="reservedSeats" value="${reservedSeatNumbers}" />

            <script defer type="text/javascript">
                var reservedSeats = [<c:forEach var="seat" items="${reservedSeatNumbers}" varStatus="loop">
                    "${seat}"<c:if test="${!loop.last}">,</c:if>
                </c:forEach>];
                console.log("Reserved Seats: ", reservedSeats);
            </script>

            <c:forEach var="rowIndex" begin="0" end="${totalRows - 1}">
                <c:if test="${rowIndex % 6 == 0}">
                    <div class="seat-container">
                </c:if>
                <c:set var="seatNumber" value="1" />
                <c:forEach var="sectionIndex" begin="0" end="2">
                    <c:set var="seatCount" value="${fn:split(seatsInSections, ',')[sectionIndex]}" />
                    <div class="seat-row">
                        <c:forEach var="seat" begin="1" end="${seatCount}">
                            <c:set var="seatId" value="${fn:substring(rowLetters, rowIndex, rowIndex + 1)}${seatNumber}" />
                            <c:set var="isReserved" value="${fn:contains(reservedSeats, seatId)}" />
                            <div class="seat ${isReserved ? 'reserved' : 'available'}" data-seat-id="${seatId}">
                                ${seatId}
                            </div>
                            <c:set var="seatNumber" value="${seatNumber + 1}" />
                        </c:forEach>
                    </div>
                </c:forEach>
                <c:if test="${(rowIndex + 1) % 6 == 0 || rowIndex == totalRows - 1}">
                    </div>
                </c:if>
            </c:forEach>
        </div>
        <div class="seat-colors">
            <div class="color-box-container">
                <div class="seat-colors-box selected-box"></div>Selected
            </div>
            <div class="color-box-container">
                <div class="seat-colors-box available-box"></div>Available
            </div>
            <div class="color-box-container">
                <div class="seat-colors-box reserved-box"></div>Reserved
            </div>
        </div>
        <div class="ticket-selection">
            Adult Tickets: 
            <button class="btn-movie-selection" id="adult-decrement" disabled>-</button>
            <span id="adult-count">0</span>
            <button class="btn-movie-selection" id="adult-increment">+</button>
            <br>
            Child Tickets: 
            <button class="btn-movie-selection" id="child-decrement" disabled>-</button>
            <span id="child-count">0</span>
            <button class="btn-movie-selection" id="child-increment">+</button>
        </div>
        <div class="ticket-total">
            <h5>Total Price: Rs.</h5><h5 id="ticketPrice">0</h5>
        </div>
        <div class="btn-movie-container">
            <button class="btn-navigate-back">Back</button>
            <button class="btn-navigate-continue btn-continue-checkout" disabled>Continue</button>
        </div>
    </div>
    <jsp:include page="footer.jsp"/>
    <script src="assets/scripts/main.js"></script>
</body>
</html>
