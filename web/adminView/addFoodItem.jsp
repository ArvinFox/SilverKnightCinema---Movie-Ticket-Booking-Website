<%-- 
    Document   : addFoodItem
    Created on : Dec 14, 2024, 8:38:47 PM
    Author     : Umindu Haputhanthri
--%>

<%@page import="model_classes.Food"%>
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
        <title>Add Food Item - Silver Knight Cinema</title>
        <link rel="stylesheet" href="../assets/css/adminStyles.css">
    </head>
    <body>
        <div class="main-content sub-content">
            <h1>Add New Food Item</h1>
            
            <form id="foodItemForm" action="manageFoods" method="POST">
                <input type="hidden" name="action" value="add">
                
                <div class="form-group">
                    <label for="itemName">Item Name</label>
                    <input type="text" id="itemName" name="itemName" required>
                </div>
                
                <%
                    Food.ItemType[] itemTypes = Food.ItemType.values();
                    request.setAttribute("itemTypes", itemTypes);
                %>

                <div class="form-group">
                    <label for="itemType">Item Type</label>
                    <select id="itemType" name="itemType" required>
                        <c:forEach var="itemType" items="${itemTypes}">
                            <option value="${itemType.dbValue}">${itemType.dbValue}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label for="price">Price (Rs.)</label>
                    <input type="number" id="price" name="price" step="0.10" min="0" required>
                </div>
                
                <div class="form-group">
                    <label for="stock">Stock</label>
                    <input type="number" id="stock" name="stock" step="1" min="0" required>
                </div>

                <div class="form-group">
                    <label for="posterUrl">Item Image</label>
                    <input type="file" id="poster" accept="image/*" required>
                    <input type="hidden" id="posterPath" name="posterUrl">
                </div>

                <button type="submit" class="action-btn add-btn-link font-16" onclick="uploadPoster('foodItem')">Add Food Item</button>
            </form>
        </div>

        <script src="../assets/scripts/admin.js"></script>
    </body>
</html>
