<%-- 
    Document   : eticket
    Created on : Dec 10, 2024, 10:58:04 PM
    Author     : Laptop Outlet
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>E-Ticket Download | SilverKnight Cinema</title>
        <link rel="stylesheet" href="assets/css/style.css">
        <script
            src="https://cdnjs.cloudflare.com/ajax/libs/html2pdf.js/0.10.2/html2pdf.bundle.min.js"
            integrity="sha512-MpDFIChbcXl2QgipQrt1VcPHMldRILetapBl5MPCA9Y8r7qvlwx1/Mc9hNTzY+kS5kX6PdoDq41ws1HiVNLdZA=="
            crossorigin="anonymous"
            referrerpolicy="no-referrer">
        </script>
    </head>
    <body>
        <div class="ticket-section" id="ticket">
                <div class="ticket-header"> Silver Knight Cinema </div>
                <c:if test="${not empty booking}">
                    <img src="${movie.posterUrl}" alt="${movie.title}" class="movie-img"/>
                    <h2 class="title-movie"> ${movie.title} </h2>
                    <table border="1" class="ticket-info">
                        <tbody>
                            <tr>
                                <td> Booking Id </td>
                                <td> ${booking.bookingId} </td>
                            </tr>
                            <tr>
                                <td> Date </td>
                                <td> ${showtime.showDate} </td>
                            </tr>
                            <tr>
                                <td> Location </td>
                                <td> ${hall.name} (${cinema.name} - ${cinema.location}) </td>
                            </tr>
                            <tr>
                                <td> Show Date </td>
                                <td> ${showtime.formattedDate} </td>
                            </tr>
                            <tr>
                                <td> Show Time </td>
                                <td> ${showtime.formattedTime} </td>
                            </tr>
                            <tr>
                                <td> Seat No. </td>
                                <td> ${bookedSeats} </td>
                            </tr>
                        </tbody>
                    </table>
                </c:if>
            </div>
        
        
        <script>
            function generatePDF() {
                const element = document.querySelector('#ticket');

                const options = {
                    filename : 'ticket.pdf',
                    margin: 1,
                    image: {type: 'jpeg', quality: 0.98},
                    html2canvas: {scale:2 },
                    jsPDF: {until: 'mm', format:[250,300], oriantation: 'portrait'},
                };

                html2pdf().set(options).from(element).save();
            }
        </script>
    </body>
</html>
