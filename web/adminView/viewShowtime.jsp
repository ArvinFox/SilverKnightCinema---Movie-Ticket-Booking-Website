<%-- 
    Document   : viewShowtime
    Created on : Dec 14, 2024, 8:31:15 PM
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
        <title>Showtime Details - Silver Knight Cinema</title>
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
            <h1>Showtime Details</h1>

            <form id="editForm" action="manageShowtimes" method="POST">
                <input type="hidden" name="action" value="update">
                
                <div class="showtime-details entity-details">
                    <p><strong>Showtime ID:</strong></p>
                    <input type="text" class="form-control" value="${showtime.showtimeId}" disabled>
                    <input type="hidden" name="showtimeId" value="${showtime.showtimeId}">

                    <p><strong>Hall:</strong></p>
                    <div id="hall-container">
                        <input type="text" class="form-control editable" value="${showtime.hallName}" disabled>
                    </div>
                    
                    <p><strong>Movie:</strong></p>
                    <div id="movie-container">
                        <input type="text" class="form-control editable" value="${showtime.movieTitle}" disabled>
                    </div> 

                    <p><strong>Show Date:</strong></p>
                    <input type="date" class="form-control editable" name="showDate" value="${showtime.showDate}" disabled>
                    
                   <p><strong>Show Time:</strong></p>
                    <input type="time" class="form-control editable" name="showTime" value="${showtime.showTime}" disabled>
                </div>
            
                <div class="action-buttons hidden">
                    <div>
                        <button type="button" class="action-btn cancel-button" onclick="
                            resetChanges('showtime');
                            toggleEditMode(false, 'showtime');
                        ">Cancel</button>
                        <button type="button" class="action-btn reset-button" onclick="resetChanges('showtime')">Reset</button>
                    </div>
                    <div>
                        <button type="submit" class="action-btn save-changes-button">Save Changes</button>
                    </div>
                </div>
            </form>

            <button class="action-btn edit-btn" onclick="toggleEditMode(true, 'showtime')">Edit Showtime</button>
        </div>
        
        <script src="../assets/scripts/admin.js"></script>
    </body>
</html>
