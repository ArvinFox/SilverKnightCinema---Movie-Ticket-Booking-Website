<%-- 
    Document   : viewPromotion
    Created on : Dec 11, 2024, 11:17:21 PM
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
        <title>Promotion Details - Silver Knight Cinema</title>
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
            <h1>Promotion Details</h1>

            <form id="editForm" action="managePromotions" method="POST">
                <input type="hidden" name="action" value="update">
                
                <div class="promotion-details entity-details">
                    <img src="../${promotion.posterUrl}" alt="${promotion.name}" height="200px">
                    <input type="hidden" name="posterUrl" value="${promotion.posterUrl}">

                    <p><strong>Promotion ID:</strong></p>
                    <input type="text" class="form-control" value="${promotion.promotionId}" disabled>
                    <input type="hidden" name="promotionId" value="${promotion.promotionId}">

                    <p><strong>Name:</strong></p>
                    <input type="text" class="form-control editable" name="name" value="${promotion.name}" disabled>

                    <p><strong>Description:</strong></p>
                    <textarea class="form-control editable" name="description" rows="3" disabled>${promotion.description}</textarea>

                    <p><strong>Promo Code:</strong></p>
                    <input type="text" class="form-control editable" name="code" value="${promotion.code}" disabled>

                    <p><strong>Discount:</strong></p>
                    <input type="number" class="form-control editable" name="discount" value="${promotion.discount}" step="0.01" min="0" max="100" disabled>

                    <p><strong>Start Date:</strong></p>
                    <input type="date" class="form-control editable" name="startDate" value="${promotion.startDate}" disabled>
                    
                    <p><strong>End Date:</strong></p>
                    <input type="date" class="form-control editable" name="endDate" value="${promotion.endDate}" disabled>
                    
                    <p><strong>Status:</strong></p>
                    <div id="status-container">
                        <input type="text" class="form-control editable" value="${promotion.isActive ? "Active" : "Inactive"}" disabled>
                    </div> 
                </div>
            
                <div class="action-buttons hidden">
                    <div>
                        <button type="button" class="action-btn cancel-button" onclick="toggleEditMode(false, 'promotion')">Cancel</button>
                        <button type="button" class="action-btn reset-button" onclick="resetChanges('promotion')">Reset</button>
                    </div>
                    <div>
                        <button type="submit" class="action-btn save-changes-button">Save Changes</button>
                    </div>
                </div>
            </form>

            <button class="action-btn edit-btn" onclick="toggleEditMode(true, 'promotion')">Edit Promotion</button>
        </div>
        
        <script src="../assets/scripts/admin.js"></script>
    </body>
</html>
