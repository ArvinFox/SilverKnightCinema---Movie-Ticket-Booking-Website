<%-- 
    Document   : checkout
    Created on : Dec 3, 2024, 6:00:39 PM
    Author     : sanuj
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
        <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
          <title>Payment Summary</title>
            <link rel="stylesheet" href="assets/css/movies.css">
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

        <div class="content">            
            <!-- Movie Details Section -->
            <div class="movie-details">
                <img src="assets/images/checkout-img1.jpeg" alt="Jurassic World Dominion">
                <div class="movie-info">
                    <p class="movie-name">Jurassic World Dominion</p>
                    <p>4:30 PM</p>
                    <p>08 Nov. 2024</p>
                    <p>Movie World Galle</p>
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
                        <label for="terms">I confirmed that I have read and agreed to the <a href="termsAndConditions.jsp">Terms & Conditions</a> of this purchase.</label>
                    </div>                 

                    <button type="submit" class="pay-now">Pay Now</button>
                </form>
            </div>
            
            <div class="card-box">
                <div class="card-header">
                    <i class="fas fa-plus"></i> Add Snacks & Beverages
                </div>
            <table class="card-table">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Qty</th>
                        <th>Price</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- Rows -->
                </tbody>
            </table>
            <div class="card-summary">
                <p><strong>Ticket Price</strong> <span>Rs.000.00</span></p>
                <p><strong>No. of Tickets</strong> <span>00</span></p>
                <p><strong>Sub Total</strong> <span>Rs.000.00</span></p>
                <p><strong>Snacks</strong> <span>Rs.000.00</span></p>
                <p><strong>Discounts</strong> <span>Rs.000.00</span></p>
                <p class="total">Total Amount <span>Rs.000.00</span></p>
            </div>
        </div>    
    </div>
 
    
     <jsp:include page="footer.jsp" />
    </body>
</html>
