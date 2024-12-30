<%-- 
    Document   : foodsAndBeverages
    Created on : Dec 10, 2024, 8:30:03 AM
    Author     : Udani Indrachapa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Food & Beverages </title>
        <link rel="stylesheet" href="assets/css/style.css">
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div class="banner-foods">
            <h1 class="banner-title"> Food & Beverages </h1>
            <div class="home-link">
                <a href="checkout.jsp"> Checkout </a>
                <span> /</span>
                <span>Food & Beverages </span>
            </div>
        </div> 
        
        <section class="food-section">
            <div class="container-food">
                <div class="food-tabs">
                  <h3 class="active">Beverage</h3>
                  <h3>PopCorn</h3>
                  <h3>Juice</h3>
                  <h3>Snack</h3>
                </div>
                <div class="tab-content active">
                    <c:if test="${not empty foodList}">
                        <c:forEach var="food" items="${foodList}">
                            <c:if test="${food.stock != 0}">
                                <div class="item">
                                    <input type="hidden" value="${food.itemId}" name="itemId">
                                    <img src="${food.itemUrl}" alt="Sprite"/>
                                    <div class="item-info">
                                        <h4 class="item-title">${food.itemName}</h4>
                                        <p class="price">${food.price}</p>
                                        <label> QTY </label>
                                        <input type="number"  min="1" value="1" data-stock="${food.stock}" name="qty">
                                        <br>
                                        <span class="error-message" style="color: red;display: none;">Current Stock: ${food.stock}</span> <!-- Display stock info if qty is grater than stock-->
                                        <br>
                                        <button class="add-to-cart"  id="butCart"> Add to Cart </button>
                                    </div>
                                </div>
                            </c:if>
                        </c:forEach>
                    </c:if>
                    
                    <c:if test="${empty foodList}">
                        <h3 style="text-align: center; color: red; font-size: 20px;">No Foods Available</h3>
                    </c:if>
                </div>
                
                <div class="tab-content">
                    <!-- PopCorn Content -->
                </div>
                <div class="tab-content">
                  <!-- Juice Content -->
                </div>
                <div class="tab-content">
                  <!-- Snack Content -->
                </div>
                
                <div class="cart-box">
                    <div class="cart-header">Cart</div>
                    
                    <table class="cart-table">
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>Qty</th>
                                <th>Price</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${cartItems}">
                                <tr>
                                    <td>${item.itemName}</td>
                                    <td>${item.qty}</td>
                                    <td>${item.price}</td>
                                    <td><button class="remove-from-cart" data-item-id="${item.itemId}"><i class="fa-solid fa-trash"></i></button></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <div class="cart-summary">
                        <p class="total">Total Amount <span>Rs.000.00</span></p>
                    </div>
                    <button id="payNow" onclick="sendCartToServlet()"> Checkout </button>
                </div>
            </div>
        </section>
        
        <jsp:include page="footer.jsp" /> 
        <script>
            let cart = [];

            const cartItemRows = document.querySelectorAll(".cart-table tbody tr");
            
            if (cartItemRows && cartItemRows.length > 0) {
                cartItemRows.forEach(row => {
                    const cells = row.querySelectorAll("td");

                    const removeIcon = cells[3].querySelector("button");
                    const itemId = removeIcon.getAttribute("data-item-id");

                    cart.push({
                        itemId: itemId,
                        itemName: cells[0].textContent,
                        price: parseFloat(cells[2].textContent),
                        qty: parseInt(cells[1].textContent)
                    });
                });

                updateCart();
            }

            let foodTabs = document.querySelectorAll(".food-tabs h3");
            let tabContents = document.querySelectorAll(".tab-content");

            foodTabs.forEach((tab, index) => {
                tab.addEventListener("click", () => {
                    foodTabs.forEach((tab) => tab.classList.remove("active"));
                    tabContents.forEach((content) => content.classList.remove("active"));

                    tab.classList.add("active");
                    tabContents[index]?.classList.add("active");

                    fetchFoods(tab.textContent.toUpperCase());
                });
            });
            
            async function fetchFoods(itemType) {
                try {
                    const response = await fetch("cart?itemType=" + itemType);
                    if (!response.ok) {
                        throw new Error(`HTTP error! status: ${response.status}`);
                    }
                    const data = await response.json();
                    updateFoodItems(data);
                } catch (error) {
                    console.error("Error fetching food items:", error);
                }
            }

            function updateFoodItems(items) {
                const tabContent = document.querySelector(".tab-content.active");
                if (tabContent) {
                    tabContent.innerHTML = "";
                    let html = '';
                    if(items.length === 0)
                    {
                        tabContent.innerHTML = '<h3 style="text-align: center; color: red; font-size: 20px;">No Foods Available</h3>' ;
                        return;
                        
                    }
                    items.forEach(function(item) {
                        if(item.stock !== 0)
                        {
                            html += '<div class="item">' +
                                    '<input type="hidden" value="' + item.itemId + '" name="itemId">' +
                                    '<img src="' + item.itemUrl + '" alt="' + item.itemName + '" />' +
                                    '<div class="item-info">' +
                                        '<h4 class="item-title">' + item.itemName + '</h4>' +
                                        '<p class="price">Rs. ' + item.price + '</p>' +
                                        '<label>QTY</label>' +
                                        '<input type="number" min="1" value="1" data-stock="' + item.stock + '" name="qty">' +
                                        '<br>' +
                                        '<span class="error-message" style="color: red; display: none;">Current Stock: ' + item.stock + '</span>' +
                                        '<br>' +
                                        '<button class="add-to-cart" id="butCart">Add to Cart</button>' +
                                    '</div>' +
                                '</div>';
                        }  
                    });
                    tabContent.innerHTML = html;
                      
                    checkStock();
                }
            }
            
            function checkStock() {
                let addToCartButtons = document.querySelectorAll('.add-to-cart');

                addToCartButtons.forEach(button => {
                    button.addEventListener('click', function () {
                        let item = this.closest('.item');
                        const itemId = item.querySelector('input[name="itemId"]').value;
                        const cartItem = cart.find(item => item.itemId === itemId);
                        let qtyInput = item.querySelector('input[name="qty"]');
                        let availableStock = parseInt(qtyInput.getAttribute('data-stock'));  
                        let enteredQty = parseInt(qtyInput.value); // to get the entered quantity

                        let outOfStock = enteredQty > availableStock;

                        if(cartItem)
                        {
                          outOfStock = (enteredQty + cartItem.qty) > availableStock;
                        }

                        let error = item.querySelector('.error-message');

                        if (outOfStock) {
                            error.style.display = 'inline';
                            qtyInput.value = availableStock;  // Reset to available stock
                            qtyInput.focus();
                            return;
                        }
                        else{
                            error.style.display = 'none';
                        }

                        addToCart(item, enteredQty);

                        updateCart();
                    });
                });
            }

            function addToCart(item, qty) {
                let itemId = item.querySelector('input[name="itemId"]').value;
                let itemName = item.querySelector('.item-title').textContent;
                let itemPrice = parseFloat(item.querySelector('.price').textContent.replace('Rs. ', ''));

                let existingItem = cart.find(cartItem => cartItem.itemId === itemId);

                if (existingItem) {
                    existingItem.qty += qty;
                } else {
                    cart.push({
                        itemId: itemId,
                        itemName: itemName,
                        price: itemPrice,
                        qty: qty
                    });
                }
            }

            function updateCart() {
                let cartTable = document.querySelector('.cart-table tbody');
                let cartSummaryTotal = document.querySelector('.cart-summary .total span');
                let totalAmount = 0;

                cartTable.innerHTML = "";

                cart.forEach(item => {
                    let row = '<tr>' +
                                '<td>' + item.itemName + '</td>' +
                                '<td>' + item.qty + '</td>' +
                                '<td>' + (item.price * item.qty) + '</td>' +
                                '<td><button class="remove-from-cart" data-item-id="' + item.itemId + '"><i class="fa-solid fa-trash"></i></button></td>' +
                              '</tr>';
                    cartTable.innerHTML += row;
                    totalAmount += item.price * item.qty;
                });

                cartSummaryTotal.textContent = 'Rs. ' + totalAmount.toFixed(2);
                initializeRemoveButtons();               
            }

            function initializeRemoveButtons() {
                const removeButtons = document.querySelectorAll('.remove-from-cart');
                removeButtons.forEach(button => {
                    button.addEventListener('click', () => {
                        let itemId = button.getAttribute('data-item-id');
                        removeFromCart(itemId);
                        updateCart();
                    });
                });
            }

            function removeFromCart(itemId) {
                cart = cart.filter(item =>  item.itemId !== itemId);
            }
            
            document.addEventListener("DOMContentLoaded", (event) => {
                checkStock();
            });

            async function sendCartToServlet() {
                let cartData = cart.map(item => ({
                    itemId: item.itemId,
                    itemName: item.itemName,
                    qty: item.qty,
                    price: item.price
                }));

                const urlParameters = new URLSearchParams(window.location.search);
                const showtimeId = urlParameters.get("showtimeId");
                
                const response = await fetch("cart?showtimeId=" +showtimeId, {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify(cartData)
                });

                if(response.ok)
                {
                    window.location.href = "checkout?showtimeId=" +showtimeId;
                }
                else{
                    console.error("failed");
                }
            }
        </script>
        <script src="assets/scripts/main.js"></script>
    </body>
</html>