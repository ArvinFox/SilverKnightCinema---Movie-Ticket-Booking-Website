<%-- 
    Document   : viewHall
    Created on : Dec 14, 2024, 8:32:34 PM
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
        <title>Hall Details - Silver Knight Cinema</title>
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
            <h1>Hall Details</h1>

            <form id="editForm" action="manageHalls" method="POST">
                <input type="hidden" name="action" value="update">
                
                <div class="hall-details entity-details">
                    <img src="../${hall.hallUrl}" alt="${hall.name}" height="200px">
                    <input type="hidden" name="posterUrl" value="${hall.hallUrl}">

                    <p><strong>Hall ID:</strong></p>
                    <input type="text" class="form-control" value="${hall.hallId}" disabled>
                    <input type="hidden" name="hallId" value="${hall.hallId}">

                    <p><strong>Hall Name:</strong></p>
                    <input type="text" class="form-control editable" name="name" value="${hall.name}" disabled>
                    
                    <p><strong>Hall Type:</strong></p>
                    <div id="hall-type-container">
                        <input type="text" class="form-control editable" value="${hall.type.dbValue}" disabled>
                    </div> 

                    <p><strong>Capacity:</strong></p>
                    <input type="number" class="form-control editable" name="capacity" value="${hall.capacity}" step="1" min="0" disabled>
                   <p><strong>Location:</strong></p>
                    <input type="text" class="form-control editable" name="location" value="${hall.location}" disabled>
                </div>
            
                <div class="action-buttons hidden">
                    <div>
                        <button type="button" class="action-btn cancel-button" onclick="toggleEditMode(false, 'hall')">Cancel</button>
                        <button type="button" class="action-btn reset-button" onclick="resetChanges('hall')">Reset</button>
                    </div>
                    <div>
                        <button type="submit" class="action-btn save-changes-button">Save Changes</button>
                    </div>
                </div>
            </form>

            <button class="action-btn edit-btn" onclick="toggleEditMode(true, 'hall')">Edit Hall</button>
        </div>
        
        <script src="../assets/scripts/admin.js"></script>
    </body>
</html>
