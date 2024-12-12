<%-- 
    Document   : foodsAndBeverages
    Created on : Dec 10, 2024, 8:30:03 AM
    Author     : Udani Indrachapa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                <a href="index.jsp"> Home </a>
                <span> /</span>
                <span>Food & Beverages </span>
            </div>
        </div> 
        
        <section class="food-section">
            <div class="container-food">
                <div class="food-tabs">
                  <h3 class="active">Beverages</h3>
                  <h3>Pop Corns</h3>
                  <h3>Juice</h3>
                  <h3>Snacks</h3>
                </div>
                <div class="tab-content active">
                    <div class="item">
                        <img src="assets/images/baverage_1.png" alt="Sprite"/>
                        <div class="item-info">
                            <h4 class="item-title"> Sprite - 600ml </h4>
                            <p class="price"> LRK. 700.00</p>
                            <label> QTY </label>
                            <input type="number"  min="1" value="1">
                            <br>
                            <button class="add-to-cart"  id="butCart"> Add to Cart </button>
                        </div>
                    </div>
                    <div class="item">
                        <img src="assets/images/beverage_2.jpeg" alt="Sprite"/>
                        <div class="item-info">
                            <h4 class="item-title"> CocaCola - 600 ml</h4>
                            <p class="price"> LRK. 700.00</p>
                            <label > QTY </label>
                            <input type="number"  min="1" value="1">
                            <br>
                            <button class="add-to-cart"  id="butCart"> Add to Cart </button>
                        </div>
                    </div>
                    <div class="item">
                        <img src="assets/images/beverage_3.jpg" alt="Sprite"/>
                        <div class="item-info">
                            <h4 class="item-title"> Necto - 600ml</h4>
                            <p class="price"> LRK. 700.00</p>
                            <label> QTY </label>
                            <input type="number" min="1" value="1">
                            <br>
                            <button class="add-to-cart" id="butCart"> Add to Cart </button>
                        </div>
                    </div>
                    <div class="item">
                        <img src="assets/images/beverage_4.jpeg" alt="Sprite"/>
                        <div class="item-info">
                            <h4 class="item-title"> Orange Crush 600 ml</h4>
                            <p class="price"> LRK. 700.00</p>
                            <label> QTY </label>
                            <input type="number" min="1" value="1">
                            <br>
                            <button class="add-to-cart"  id="butCart"> Add to Cart </button>
                        </div>
                    </div>
                </div>
                
                <div class="cart-box">
                    <div class="cart-header">Cart</div>
                    <table class="cart-table">
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>Qty</th>
                                <th>Price</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- Rows -->
                            <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                            </tr>
                        </tbody>
                    </table>
                    <div class="cart-summary">
                        <p class="total">Total Amount <span>Rs.000.00</span></p>
                    </div>
		    <button id="payNow"> Pay Now </button>
                </div>
                    
                    
                <div class="tab-content">
                    <div class="item">
                        <img src="assets/images/popcorn_1.jpg" alt="Sprite"/>
                        <div class="item-info">
                            <h4 class="item-title"> PopCorn </h4>
                            <p class="price"> LRK. 300.00</p>
                            <label > QTY </label>
                            <input type="number" min="1" value="1">
                            <button class="add-to-cart"  id="butCart"> Add to Cart </button>
                        </div>
                    </div>
                    <div class="item">
                        <img src="assets/images/popcorn_2.jpeg" alt="Sprite"/>
                        <div class="item-info">
                            <h4 class="item-title"> Butter PopCorn</h4>
                            <p class="price"> LRK. 300.00</p>
                            <label> QTY </label>
                            <input type="number" min="1" value="1">
                            <button class="add-to-cart"  id="butCart"> Add to Cart </button>
                        </div>
                    </div>
                    <div class="item">
                        <img src="assets/images/popcorn_3.webp" alt="Sprite"/>
                        <div class="item-info">
                            <h4 class="item-title"> PopCorn </h4>
                            <p class="price"> LRK. 700.00</p>
                            <label> QTY </label>
                            <input type="number"  min="1" value="1">
                            <button class="add-to-cart"  id="butCart"> Add to Cart </button>
                        </div>
                    </div>
                </div>
                    
                <div class="tab-content">
                    <div class="item">
                        <img src="assets/images/juice_1.jpeg" alt="Sprite"/>
                        <div class="item-info">
                            <h4 class="item-title"> Orange Juice </h4>
                            <p class="price"> LRK. 300.00</p>
                            <label> QTY </label>
                            <input type="number" min="1" value="1">
                            <button class="add-to-cart"  id="butCart"> Add to Cart </button>
                        </div>
                    </div>
                    <div class="item">
                        <img src="assets/images/juice_2.jpg" alt="Sprite"/>
                        <div class="item-info">
                            <h4 class="item-title"> Orange Juice</h4>
                            <p class="price"> LRK. 500.00</p>
                            <label> QTY </label>
                            <input type="number"  min="1" value="1">
                            <button class="add-to-cart"  id="butCart"> Add to Cart </button>
                        </div>
                    </div>
                    <div class="item">
                        <img src="assets/images/juice_3.jpeg" alt="Sprite"/>
                        <div class="item-info">
                            <h4 class="item-title"> Kivi Juice</h4>
                            <p class="price"> LRK. 700.00</p>
                            <label > QTY </label>
                            <input type="number"  min="1" value="1">
                            <button class="add-to-cart"  id="butCart"> Add to Cart </button>
                        </div>
                    </div>
                    <div class="item">
                        <img src="assets/images/juice_4.jpeg" alt="Sprite"/>
                        <div class="item-info">
                            <h4 class="item-title"> Apple Juice</h4>
                            <p class="price"> LRK. 700.00</p>
                            <label> QTY </label>
                            <input type="number"  min="1" value="1">
                            <button class="add-to-cart"  id="butCart"> Add to Cart </button>
                        </div>
                    </div>
                </div>
                    
                <div class="tab-content">
                    <div class="item">
                        <img src="assets/images/snack_1.jpeg" alt="Sprite"/>
                        <div class="item-info">
                            <h4 class="item-title"> Chile Limon </h4>
                            <p class="price"> LRK. 300.00</p>
                            <label> QTY </label>
                            <input type="number"  min="1" value="1">
                            <button class="add-to-cart" id="butCart"> Add to Cart </button>
                        </div>
                    </div>
                    <div class="item">
                        <img src="assets/images/snack_2.jpeg" alt="Sprite"/>
                        <div class="item-info">
                            <h4 class="item-title"> Limon</h4>
                            <p class="price"> LRK. 300.00</p>
                            <label > QTY </label>
                            <input type="number"  min="1" value="1">
                            <button class="add-to-cart"  id="butCart"> Add to Cart </button>
                        </div>
                    </div>
                    <div class="item">
                        <img src="assets/images/snack_3.jpeg" alt="Sprite"/>
                        <div class="item-info">
                            <h4 class="item-title"> Barbecue</h4>
                            <p class="price"> LRK. 700.00</p>
                            <label> QTY </label>
                            <input type="number" min="1" value="1">
                            <button class="add-to-cart"  id="butCart"> Add to Cart </button>
                        </div>
                    </div>
                    <div class="item">
                        <img src="assets/images/snack_4.jpg" alt="Sprite"/>
                        <div class="item-info">
                            <h4 class="item-title"> Pringles - Hot & Spicy </h4>
                            <p class="price"> LRK. 700.00</p>
                            <label> QTY </label>
                            <input type="number" min="1" value="1">
                            <button class="add-to-cart"  id="butCart"> Add to Cart </button>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        
        <jsp:include page="footer.jsp" /> 
        <script>
            let tabs = document.querySelectorAll(".food-tabs h3");
            let tabContents = document.querySelectorAll(".tab-content");

            tabs.forEach((tab, index) => {
              tab.addEventListener("click", () => {
                tabContents.forEach((content) => {
                  content.classList.remove("active");
                });
                tabs.forEach((tab) => {
                  tab.classList.remove("active");
                });
                tabContents[index].classList.add("active");
                tabs[index].classList.add("active");
              });
            }); 
        </script>
        <script src="assets/scripts/main.js"></script>
        
    </body>
</html>
