<%-- 
    Document   : managePromotions
    Created on : Dec 11, 2024, 11:17:10 PM
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
        <title>Manage Promotions - Silver Knight Cinema</title>
        <link rel="stylesheet" href="../assets/css/adminStyles.css">
    </head>
    <body>
        <%@ include file="../adminTemplates/header.jsp" %>
        <%@ include file="../adminTemplates/sidebar.jsp" %>
        
        <div class="main-content" id="mainContent">
            <h1>Manage Promotions</h1>
            
            <button id="openModal" class="action-btn add-btn-link font-16" data-action="add" data-type="Promotion">Add New Promotion</button>
            
            <div class="filter-header" id="filterHeader">
                <h2>Filter Options</h2>
                <span class="toggle-icon" id="toggleIcon">&#9660;</span> <!-- Down arrow icon -->
            </div>
            
            <div class="search-container" id="filterOptions">
                <form id="searchForm" action="managePromotions" method="get">
                    <label for="name">Name:</label>
                    <input type="text" name="name" id="name" placeholder="Search by name" 
                           value="${param.name != null ? param.name : ''}">
                    
                    <label for="discount">Discount:</label>
                    <div class="range-inputs">
                        <input type="number" name="minDiscount" id="minDiscount" step="0.10" min="0" max="100" placeholder="Min Discount" 
                               value="${param.minDiscount != null ? param.minDiscount : ''}">
                        <input type="number" name="maxDiscount" id="maxDiscount" step="0.10" min="0" max="100" placeholder="Max Discount" 
                               value="${param.maxDiscount != null ? param.maxDiscount : ''}">
                    </div>

                    <label for="startDate">Start Date:</label>
                    <input type="date" name="startDate" id="startDate"
                           value="${param.startDate != null ? param.startDate : ''}">

                    <label for="endDate">End Date:</label>
                    <input type="date" name="endDate" id="endDate"
                           value="${param.endDate != null ? param.endDate : ''}">

                    <label for="status">Status:</label>
                    <select name="status" id="status">
                        <option value="any">Any Status</option>
                        <option value="Active" ${param.status == 'Active' ? 'selected' : ''}>Active</option>
                        <option value="Inactive" ${param.status == 'Inactive' ? 'selected' : ''}>Inactive</option>
                    </select>

                    <div class="buttons-container">
                        <button type="button" class="reset-btn" ${empty param.name && empty param.startDate && empty param.endDate && empty param.status ? 'disabled' : ''} onclick="window.location.href = 'managePromotions'">Reset Filters</button>
                        <button type="button" onclick="validateSearchInput()">Search</button>
                    </div>
                </form>
            </div>
                    
            <c:if test="${not empty promotionList}">
                <table class="promotions-table">
                    <thead>
                        <tr>
                            <th>Promotion ID</th>
                            <th>Name</th>
                            <th>Discount (%)</th>
                            <th>Status</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="promotion" items="${promotionList}">
                            <tr>
                                <td>${promotion.promotionId}</td>
                                <td>${promotion.name}</td>
                                <td>${promotion.discount}</td>
                                <td>${promotion.isActive ? "Active" : "Inactive"}</td>
                                <td>
                                    <div style="display: flex;">
                                        <input type="submit" id="openModal" class="action-btn view-btn" data-id="${promotion.promotionId}" data-action="view" data-type="Promotion" value="View" />
                                        <form action="managePromotions" method="POST" onsubmit="return confirmPromotionDelete('${promotion.name}', '${promotion.promotionId}')">
                                            <input type="submit" class="action-btn delete-btn" value="Delete" />
                                            <input type="hidden" name="action" value="delete" />
                                            <input type="hidden" name="promotionId" value="${promotion.promotionId}"/>
                                        </form>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${empty promotionList}">
                <p>No promotions available.</p>
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
                const nameInput = document.getElementById('name');
                const minDiscountInput = document.getElementById('minDiscount');
                const maxDiscountInput = document.getElementById('maxDiscount');
                const startDateInput = document.getElementById('startDate');
                const endDateInput = document.getElementById('endDate');
                const statusInput = document.getElementById('status');

                if (!nameInput.value.trim() && !minDiscountInput.value.trim() && !maxDiscountInput.value.trim() && !startDateInput.value.trim() && !endDateInput.value.trim() && statusInput.value.trim() === "any") {
                    nameInput.classList.add("input-error");
                    minDiscountInput.classList.add("input-error");
                    maxDiscountInput.classList.add("input-error");
                    startDateInput.classList.add("input-error");
                    endDateInput.classList.add("input-error");
                    statusInput.classList.add("input-error");

                    setTimeout(() => {
                        nameInput.classList.remove("input-error");
                        minDiscountInput.classList.remove("input-error");
                        maxDiscountInput.classList.remove("input-error");
                        startDateInput.classList.remove("input-error");
                        endDateInput.classList.remove("input-error");
                        statusInput.classList.remove("input-error");
                    }, 300);

                    return false;
                }

                document.getElementById("searchForm").submit();
            }
        </script>
        <script src="../assets/scripts/admin.js"></script>       
    </body>
</html>
