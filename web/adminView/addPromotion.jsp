<%-- 
    Document   : addPromotion
    Created on : Dec 11, 2024, 11:17:16 PM
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
        <title>Add Promotion - Silver Knight Cinema</title>
        <link rel="stylesheet" href="../assets/css/adminStyles.css">
    </head>
    <body>
        <div class="main-content sub-content">
            <h1>Add New Promotion</h1>
            
            <form id="promotionForm" action="managePromotions" method="POST">
                <input type="hidden" name="action" value="add">
                
                <div class="form-group">
                    <label for="name">Name</label>
                    <input type="text" id="name" name="name" required>
                </div>

                <div class="form-group">
                    <label for="description">Description</label>
                    <textarea id="description" name="description" required></textarea>
                </div>
                
                <div class="form-group">
                    <label for="code">Promo Code</label>
                    <input type="text" id="code" name="code" required>
                </div>

                <div class="form-group">
                    <label for="discount">Discount (%)</label>
                    <input type="number" id="discount" name="discount" step="0.10" min="0" max="100" required>
                </div>

                <div class="form-group">
                    <label for="startDate">Start Date</label>
                    <input type="date" id="startDate" name="startDate" required>
                </div>
                
                <div class="form-group">
                    <label for="endDate">End Date</label>
                    <input type="date" id="endDate" name="endDate" required>
                </div>
                
                <div class="form-group">
                    <label for="posterUrl">Poster</label>
                    <input type="file" id="poster" accept="image/*" required>
                    <input type="hidden" id="posterPath" name="posterUrl">
                </div>
                
                <div class="form-group">
                    <label for="status">Status</label>
                    <select id="status" name="status">
                        <option value="active">Active</option>
                        <option value="inactive">Inactive</option>
                    </select>
                </div>

                <button type="submit" class="action-btn add-btn-link font-16" onclick="uploadPoster('promotion')">Add Promotion</button>
            </form>
        </div>

        <script src="../assets/scripts/admin.js"></script>
    </body>
</html>
