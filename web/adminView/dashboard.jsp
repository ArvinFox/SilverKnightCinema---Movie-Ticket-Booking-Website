<%-- 
    Document   : dashboard
    Created on : Dec 10, 2024, 11:06:48 PM
    Author     : Mahith Abeysinghe
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
        <title>Admin Dashboard - Silver Knight Cinema</title>
        <link rel="stylesheet" href="../assets/css/adminStyles.css">
        <style>
            .admin-header .header-links .dashboard-link {
                color: #ffc107;
                border-bottom: 2px solid #ffc107;
            }
        </style>
    </head>
    <body>

        <%@ include file="../adminTemplates/header.jsp" %>
        <%@ include file="../adminTemplates/sidebar.jsp" %>

        <div class="main-content dashboard-content" id="mainContent">


            <h1>Welcome, Admin! (${username})</h1>

            <h2>Dashboard Statistics</h2>

            <div class="dashboard-stats">
                <div class="stat-box">
                    <p>Total Users:</p>
                    <h3>${totalUsers}</h3>
                </div>
                <div class="stat-box">
                    <p>Active Users:</p>
                    <h3>${totalActiveUsers}</h3>
                </div>
                <div class="stat-box">
                    <p>Total Guests:</p>
                    <h3>${totalGuests}</h3>
                </div>
                <div class="stat-box">
                    <p>Total Bookings:</p>
                    <h3>${totalBookings}</h3>
                </div>
                <div class="stat-box">
                    <p>Total Promotions:</p>
                    <h3>${totalPromotions}</h3>
                </div>
                <div class="stat-box">
                    <p>Active Promotions:</p>
                    <h3>${totalActivePromotions}</h3>
                </div>
                <div class="stat-box">
                    <p>Total Genres:</p>
                    <h3>${totalGenres}</h3>
                </div>
                <div class="stat-box">
                    <p>Total Languages:</p>
                    <h3>${totalLanguages}</h3>
                </div>
                <div class="stat-box">
                    <p>Total Movies:</p>
                    <h3>${totalMovies}</h3>
                </div>
                <div class="stat-box">
                    <p>Total Halls:</p>
                    <h3>${totalHalls}</h3>
                </div>
                <div class="stat-box">
                    <p>Total Food Items:</p>
                    <h3>${totalFoodItems}</h3>
                </div>
                <div class="stat-box">
                    <p>Total Inquiries:</p>
                    <h3>${totalInquiries}</h3>
                </div>
                <div class="stat-box">
                    <p>Total Admins:</p>
                    <h3>${totalAdmins}</h3>
                </div>
            </div>

            <div class="quick-action-links">
                <h2>Quick Actions</h2>
                <div class="quick-actions">
                    <button class="action-btn open-modal-btn" data-action="add" data-type="Movie">Add Movie</button>
                    <button class="action-btn open-modal-btn" data-action="add" data-type="Promotion">Create Promotion</button>
                    <button class="action-btn open-modal-btn" data-action="add" data-type="Showtime">Schedule Showtime</button>
                </div>
            </div>
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
            document.querySelectorAll(".open-modal-btn").forEach(button=>{
                button.addEventListener("click", ()=>{
                    const action = button.getAttribute("data-action");
                    const type = button.getAttribute("data-type");
                    const url = "../adminView/add" + type + ".jsp";
                    openModal(url, null, type);
                })
            })
        </script>
        <script src="../assets/scripts/admin.js"></script>
    </body>
</html>
