<%-- 
    Document   : viewFoodItem
    Created on : Dec 14, 2024, 8:33:06 PM
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
        <title>Food Item Details - Silver Knight Cinema</title>
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
            <h1>Item Details</h1>

            <form id="editForm" action="manageFoods" method="POST">
                <input type="hidden" name="action" value="update">
                
                <div class="food-item-details entity-details">
                    <img src="../${foodItem.itemUrl}" alt="${foodItem.itemName}" width="200px">
                    <input type="hidden" name="posterUrl" value="${foodItem.itemUrl}">

                    <p><strong>Item ID:</strong></p>
                    <input type="text" class="form-control" value="${foodItem.itemId}" disabled>
                    <input type="hidden" name="itemId" value="${foodItem.itemId}">

                    <p><strong>Item Name:</strong></p>
                    <input type="text" class="form-control editable" name="itemName" value="${foodItem.itemName}" disabled>

                    <p><strong>Item Type:</strong></p>
                    <div id="item-type-container">
                        <input type="text" class="form-control editable" value="${foodItem.itemType.dbValue}" disabled>
                    </div> 

                    <p><strong>Price (Rs.):</strong></p>
                    <input type="number" class="form-control editable" name="price" value="${foodItem.price}" step="0.10" min="0" disabled>

                    <p><strong>Stock:</strong></p>
                    <input type="number" class="form-control editable" name="stock" value="${foodItem.stock}" step="1" min="0" disabled>
                </div>
            
                <div class="action-buttons hidden">
                    <div>
                        <button type="button" class="action-btn cancel-button" onclick="toggleEditMode(false, 'foodItem')">Cancel</button>
                        <button type="button" class="action-btn reset-button" onclick="resetChanges('foodItem')">Reset</button>
                    </div>
                    <div>
                        <button type="submit" class="action-btn save-changes-button">Save Changes</button>
                    </div>
                </div>
            </form>

            <button class="action-btn edit-btn" onclick="toggleEditMode(true, 'foodItem')">Edit Item</button>
        </div>
        
        <script src="../assets/scripts/admin.js"></script>
    </body>
</html>
