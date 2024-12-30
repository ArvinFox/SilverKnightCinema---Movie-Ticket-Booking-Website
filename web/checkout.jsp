<%-- 
    Document   : checkout
    Created on : Dec 3, 2024, 6:00:39 PM
    Author     : sanuji
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Payment Summary | SilverKnight Cinema</title>
        <link rel="stylesheet" href="assets/css/checkOut.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    </head>
    <body>
        <jsp:include page="header.jsp"/>

        <!-- Back Button -->
        <a href="movieSeatSelection.jsp" class="back-button">
            <i class="fas fa-arrow-left"></i> Back
        </a>
        <!-- Payment Summary Heading -->
        <h1 class="payment-summary-heading">Payment Summary</h1>

        <div class="checkout-content">            
            <!-- Movie Details Section -->
            <div class="movie-details">
                <img src="${movie.posterUrl}" alt="${movie.title}">
                <div class="movie-info">
                    <p class="movie-name">${movie.title}</p>
                    <p>${showtime.formattedTime}</p>
                    <p>${showtime.formattedDate}</p>
                    <p>${hall.name} (${hall.cinema})</p>
                </div>
            </div>

            <!-- Checkout Box -->
            <div class="checkout-box">
                <p>Select Payment Method<p>
                <div class="payment-methods">
                    <img src="assets/images/checkout-img2.png" alt="Visa">
                    <img src="assets/images/checkout-img3.png" alt="MasterCard">
                    <img src="assets/images/checkout-img4.png" alt="PayPal">
                    <button class="add-method"><i class="fas fa-plus-circle"></i></button>
                </div>

                <form action="#" method="POST">
                    <label for="card-number">Card Number</label>
                    <input type="text" id="card-number" name="cardNumber" placeholder="1234 1234 1234">

                    <div class="cvc">
                        <div>
                            <label for="expiry-date">Expiration Date</label>
                            <input type="text" id="expiry-date" name="expiryDate" placeholder="MM / YY">
                        </div>
                        <div>
                            <label for="cvc">CVC</label>
                            <input type="text" id="cvc" name="cvc" placeholder="123">
                        </div>
                    </div>

                    <label for="name-on-card">Name on Card</label>
                    <input type="text" id="name-on-card" name="nameOnCard" placeholder="Perera A">

                    <div class="terms">
                        <input type="checkbox" id="terms" name="terms">
                        <label for="terms">I confirmed that I have read and agreed to the <a href="terms">Terms & Conditions</a> of this purchase.</label>
                    </div>                 

                    <button type="button" class="pay-now" onclick="checkOutfunction()">Pay Now</button>
                </form>
            </div>

            <div class="card-box">
                <div class="card-header">
                    <a href="cart?showtimeId=${showtimeId}"><i class="fas fa-plus"></i> </a>Add Snacks & Beverages
                </div>
                <table class="card-table">
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Qty</th>
                            <th>Price (Rs.)</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${cartItems}">
                            <tr>
                                <td>${item.itemName}</td>
                                <td>${item.qty}</td>
                                <td class="item-price">${item.price * item.qty}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <div class="card-summary">
                    <p><strong>Ticket Price</strong> <span class="ticket-price">Rs.${total}</span></p>
                    <p><strong>No. of Tickets</strong> <span class="ticket-count"><fmt:formatNumber value="${seatCount}" pattern="00"/></span></p>
                    <p><strong>Snacks</strong> <span class="snacks-price">Rs.000.00</span></p>
                    <p><strong>Sub Total</strong> <span class="subtotal">Rs.000.00</span></p>
                    <p><strong>Discounts</strong> <span class="discount">Rs.000.00</span></p>
                    <p class="total">Total Amount <span class="total-amount">Rs.000.00</span></p>
                </div>
            </div>    
        </div>


        <jsp:include page="footer.jsp" />

        <script>
            function checkOutfunction() {
                const urlParameters = new URLSearchParams(window.location.search);
                const showtimeId = urlParameters.get("showtimeId");

                location.replace("eticket?showtimeId=" + showtimeId);
            }

            const ticketPrice = document.querySelector('.ticket-price');
            const ticketCount = document.querySelector('.ticket-count');
            const snacksPrice = document.querySelector('.snacks-price');
            const subtotal = document.querySelector('.subtotal');
            const discounts = document.querySelector('.discount');
            const totalAmount = document.querySelector('.total-amount');

            const itemPrices = document.querySelectorAll('.item-price');
            if (itemPrices) {
                let totalPrice = 0;

                itemPrices.forEach(itemPrice => {
                    totalPrice += Number(itemPrice.innerHTML);
                });

                snacksPrice.innerHTML = 'Rs. ' + totalPrice + '.00';
            }

            const ticketPriceValue = extractNumber(ticketPrice.innerHTML);
            const snacksPriceValue = extractNumber(snacksPrice.innerHTML);
            const discountsValue = extractNumber(discounts.innerHTML);
            const totalAmountValue = extractNumber(totalAmount.innerHTML);

            const subtotalValue = ticketPriceValue + snacksPriceValue;
            subtotal.innerHTML = 'Rs.' + subtotalValue + '.00';
            totalAmount.innerHTML = 'Rs.' + (subtotalValue - discountsValue) + '.00';

            function extractNumber(value) {
                return parseFloat(value.replace(/Rs\.\s?/g, '')) || 0;
            }
        </script>
        <script src="assets/scripts/main.js"></script>
    </body>
</html>
