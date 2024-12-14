<%-- 
    Document   : manageFoods
    Created on : Dec 14, 2024, 8:35:19 PM
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
        <title>Manage Foods - Silver Knight Cinema</title>
        <link rel="stylesheet" href="../assets/css/adminStyles.css">
    </head>
    <body>
        <%@ include file="../adminTemplates/header.jsp" %>
        <%@ include file="../adminTemplates/sidebar.jsp" %>

        <div class="main-content" id="mainContent">
            <h1>Manage Foods</h1>
            
            <button id="openModal" class="action-btn add-btn-link font-16" data-action="add" data-type="FoodItem">Add New Food Item</button>
            
            <div class="filter-header" id="filterHeader">
                <h2>Filter Options</h2>
                <span class="toggle-icon" id="toggleIcon">&#9660;</span> <!-- Down arrow icon -->
            </div>
            
            <div class="search-container" id="filterOptions">
                <form id="searchForm" action="manageFoods" method="get">
                    <label for="itemName">Item Name:</label>
                    <input type="text" name="itemName" id="itemName" placeholder="Search by item name" 
                           value="${param.itemName != null ? param.itemName : ''}">

                    <label for="itemType">Item Type:</label>
                    <select name="itemType" id="itemType">
                        <option value="any">Any Type</option>
                        <c:forEach var="itemType" items="${itemTypes}">
                            <option value="${itemType.dbValue}" ${itemType.dbValue == param.itemType ? "selected" : ""}>${itemType.dbValue}</option>
                        </c:forEach>
                    </select>
                    
                    <label for="price">Price Range:</label>
                    <div class="range-inputs">
                        <input type="number" name="minPrice" id="minPrice" step="0.10" min="0" placeholder="Min Price" 
                               value="${param.minPrice != null ? param.minPrice : ''}">
                        <input type="number" name="maxPrice" id="maxPrice" step="0.10" min="0" placeholder="Max Price" 
                               value="${param.maxPrice != null ? param.maxPrice : ''}">
                    </div>

                    <label for="stock">Stock Range:</label>
                    <div class="range-inputs">
                        <input type="number" name="minStock" id="minStock" step="1" min="0" placeholder="Min Stock" 
                               value="${param.minStock != null ? param.minStock : ''}">
                        <input type="number" name="maxStock" id="maxStock" step="1" min="0" placeholder="Max Stock" 
                               value="${param.maxStock != null ? param.maxStock : ''}">
                    </div>

                    <div class="buttons-container">
                        <button type="button" class="reset-btn" ${empty param.itemName && empty param.itemType && empty param.minPrice && empty param.maxPrice && empty param.minStock && empty param.maxStock ? 'disabled' : ''} onclick="window.location.href = 'manageFoods'">Reset Filters</button>
                        <button type="button" onclick="validateSearchInput()">Search</button>
                    </div>
                </form>
            </div>
                    
            <c:if test="${not empty foodItemList}">
                <table class="food-items-table">
                    <thead>
                        <tr>
                            <th>Item ID</th>
                            <th>Item Name</th>
                            <th>Item Type</th>
                            <th>Stock</th>
                            <th>Price (Rs.)</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="foodItem" items="${foodItemList}">
                            <tr>
                                <td>${foodItem.itemId}</td>
                                <td>${foodItem.itemName}</td>
                                <td>${foodItem.itemType.dbValue}</td>
                                <td>${foodItem.stock}</td>
                                <td>${foodItem.price}</td>
                                <td>
                                    <div style="display: flex;">
                                        <input type="submit" id="openModal" class="action-btn view-btn" data-id="${foodItem.itemId}" data-action="view" data-type="FoodItem" value="View" />
                                        <form action="manageFoods" method="POST" onsubmit="return confirmFoodItemDelete('${foodItem.itemName}', '${foodItem.itemId}')">
                                            <input type="submit" class="action-btn delete-btn" value="Delete" />
                                            <input type="hidden" name="action" value="delete" />
                                            <input type="hidden" name="itemId" value="${foodItem.itemId}"/>
                                        </form>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${empty foodItemList}">
                <p>No items available.</p>
            </c:if>   
        </div>
                    
        <!-- Modal structure -->
        <div id="popupModal" class="modal">
          <div class="modal-content" id="modalContent">
            <button class="close-btn" id="closeModal">X</button>
            <!-- Dynamic content will load here -->
          </div>
        </div>

        <%@ include file="../adminTemplates/footer.jsp" %>
        
        <script>
            function validateSearchInput() {
                const itemNameInput = document.getElementById('itemName');
                const itemTypeInput = document.getElementById('itemType');
                const minPriceInput = document.getElementById('minPrice');
                const maxPriceInput = document.getElementById('maxPrice');
                const minStockInput = document.getElementById('minStock');
                const maxStockInput = document.getElementById('maxStock');

                if (!itemNameInput.value.trim() && itemTypeInput.value.trim() === "any" && !minPriceInput.value.trim() && !maxPriceInput.value.trim() && !minStockInput.value.trim() && !maxStockInput.value.trim()) {
                    itemNameInput.classList.add("input-error");
                    itemTypeInput.classList.add("input-error");
                    minPriceInput.classList.add("input-error");
                    maxPriceInput.classList.add("input-error");
                    minStockInput.classList.add("input-error");
                    maxStockInput.classList.add("input-error");

                    setTimeout(() => {
                        itemNameInput.classList.remove("input-error");
                        itemTypeInput.classList.remove("input-error");
                        minPriceInput.classList.remove("input-error");
                        maxPriceInput.classList.remove("input-error");
                        minStockInput.classList.remove("input-error");
                        maxStockInput.classList.remove("input-error");
                    }, 300);

                    return false;
                }

                document.getElementById("searchForm").submit();
            }
        </script>
        <script src="../assets/scripts/admin.js"></script>
    </body>
</html>
