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
                <p>Pay via Debit/Credit Card<p>
                <div class="payment-methods">
                    <img src="assets/images/checkout-img2.png" alt="Visa">
                    <img src="assets/images/checkout-img3.png" alt="MasterCard">
                </div>

                <form action="#" method="POST" onsubmit="return validateCardDetails()">
                    <label for="card-number">Card Number</label>
                    <input type="text" id="card-number" name="cardNumber" placeholder="XXXX XXXX XXXX XXXX" maxlength="19">

                    <div class="cvc">
                        <div>
                            <label for="expiry-date">Expiration Date</label>
                            <input type="text" id="expiry-date" name="expiryDate" placeholder="MM / YY">
                        </div>
                        <div>
                            <label for="cvc">CVC</label>
                            <input type="text" id="cvc" name="cvc" placeholder="XXX">
                        </div>
                    </div>

                    <label for="name-on-card">Name on Card</label>
                    <input type="text" id="name-on-card" name="nameOnCard" placeholder="Perera A">

                    <div class="terms">
                        <input type="checkbox" id="terms" name="terms">
                        <label for="terms">I confirmed that I have read and agreed to the <a href="terms" target="_blank">Terms & Conditions</a> of this purchase.</label>
                    </div>                 

                    <button type="submit" class="pay-now">Pay Now</button>
                </form>
                
                <div class="payment-separator">
                    <span>OR</span>
                </div>
                
                <div id="paypal-button-container"></div>
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

        <script src="assets/scripts/main.js"></script>
        <script>
            displayTotalSummary();
            
            document.getElementById('card-number').addEventListener('input', function (e) {
                let input = e.target.value;

                input = input.replace(/\D/g, '');

                input = input.replace(/(\d{4})(?=\d)/g, '$1-');

                e.target.value = input;
            });

            document.getElementById('card-number').addEventListener('keypress', function (e) {
                if (!/\d/.test(e.key)) {
                    e.preventDefault();
                }
            });
        </script>
        
        <script src="https://www.paypal.com/sdk/js?client-id=YOUR_CLIENT_ID&currency=USD&disable-funding=card,credit"></script>
        
        <script>
            const urlParameters = new URLSearchParams(window.location.search);
            const showtimeId = urlParameters.get("showtimeId");
            
            const totalAmount = document.querySelector('.total-amount').textContent.replace(/Rs\./g, '').trim();
            
            paypal.Buttons({
                createOrder: function (data, actions) {
                    return actions.order.create({
                        purchase_units: [{
                            amount: {
                                value: totalAmount
                            }
                        }]
                    });
                },
                onApprove: function (data, actions) {
                    return actions.order.capture().then(function (details) {
                        window.location.href = 'eticket?showtimeId=' + showtimeId + '&total=' + totalAmount;
                    });
                },
                onError: function (err) {
                    console.error(err);
                    alert('An error occurred during the payment process.');
                },
                onCancel: function (data) {
                    alert('Payment was canceled.');
                }
            }).render('#paypal-button-container');
        </script>
    </body>
</html>
