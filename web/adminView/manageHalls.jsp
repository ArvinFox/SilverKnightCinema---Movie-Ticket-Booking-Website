<%-- 
    Document   : manageHalls
    Created on : Dec 14, 2024, 8:34:48 PM
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
        <title>Manage Halls - Silver Knight Cinema</title>
        <link rel="stylesheet" href="../assets/css/adminStyles.css">
    </head>
    <body>
        <%@ include file="../adminTemplates/header.jsp" %>
        <%@ include file="../adminTemplates/sidebar.jsp" %>

        <div class="main-content" id="mainContent">
            <h1>Manage Halls</h1>
            
            <button id="openModal" class="action-btn add-btn-link font-16" data-action="add" data-type="Hall">Add New Hall</button>
            
            <div class="filter-header" id="filterHeader">
                <h2>Filter Options</h2>
                <span class="toggle-icon" id="toggleIcon">&#9660;</span> <!-- Down arrow icon -->
            </div>
            
            <div class="search-container" id="filterOptions">
                <form id="searchForm" action="manageHalls" method="get">
                    <label for="name">Name:</label>
                    <input type="text" name="name" id="name" placeholder="Search by hall name" 
                           value="${param.name != null ? param.name : ''}">

                    <label for="hallType">Type:</label>
                    <select name="hallType" id="hallType">
                        <option value="any">Any Type</option>
                        <c:forEach var="hallType" items="${hallTypes}">
                            <option value="${hallType.dbValue}" ${hallType.dbValue == param.hallType ? "selected" : ""}>${hallType.dbValue}</option>
                        </c:forEach>
                    </select>
                    
                    <label for="capacity">Capacity Range:</label>
                    <div class="range-inputs">
                        <input type="number" name="minCapacity" id="minCapacity" step="1" min="0" placeholder="Min Capacity" 
                               value="${param.minCapacity != null ? param.minCapacity : ''}">
                        <input type="number" name="maxCapacity" id="maxCapacity" step="1" min="0" placeholder="Max Capacity" 
                               value="${param.maxCapacity != null ? param.maxCapacity : ''}">
                    </div>
                    
                    <label for="location">Location:</label>
                    <input type="text" name="location" id="location" placeholder="Search by hall location" 
                           value="${param.location != null ? param.location : ''}">

                    <div class="buttons-container">
                        <button type="button" class="reset-btn" ${empty param.name && empty param.hallType && empty param.minCapacity && empty param.maxCapacity && empty param.location ? 'disabled' : ''} onclick="window.location.href = 'manageHalls'">Reset Filters</button>
                        <button type="button" onclick="validateSearchInput()">Search</button>
                    </div>
                </form>
            </div>
                    
            <c:if test="${not empty hallList}">
                <table class="halls-table">
                    <thead>
                        <tr>
                            <th>Hall ID</th>
                            <th>Hall Name</th>
                            <th>Hall Type</th>
                            <th>Capacity</th>
                            <th>Location</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="hall" items="${hallList}">
                            <tr>
                                <td>${hall.hallId}</td>
                                <td>${hall.name}</td>
                                <td>${hall.type.dbValue}</td>
                                <td>${hall.capacity}</td>
                                <td>${hall.location}</td>
                                <td>
                                    <div style="display: flex;">
                                        <input type="submit" id="openModal" class="action-btn view-btn" data-id="${hall.hallId}" data-action="view" data-type="Hall" value="View" />
                                        <form action="manageHalls" method="POST" onsubmit="return confirmHallDelete('${hall.name}', '${hall.hallId}')">
                                            <input type="submit" class="action-btn delete-btn" value="Delete" />
                                            <input type="hidden" name="action" value="delete" />
                                            <input type="hidden" name="hallId" value="${hall.hallId}"/>
                                        </form>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${empty hallList}">
                <p>No halls available.</p>
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
                const typeInput = document.getElementById('hallType');
                const minCapacityInput = document.getElementById('minCapacity');
                const maxCapacityInput = document.getElementById('maxCapacity');
                const locationInput = document.getElementById('location');

                if (!nameInput.value.trim() && typeInput.value.trim() === "any" && !minCapacityInput.value.trim() && !maxCapacityInput.value.trim() && !locationInput.value.trim()) {
                    nameInput.classList.add("input-error");
                    typeInput.classList.add("input-error");
                    minCapacityInput.classList.add("input-error");
                    maxCapacityInput.classList.add("input-error");
                    locationInput.classList.add("input-error");

                    setTimeout(() => {
                        nameInput.classList.remove("input-error");
                        typeInput.classList.remove("input-error");
                        minCapacityInput.classList.add("input-error");
                        minCapacityInput.classList.remove("input-error");
                        maxCapacityInput.classList.remove("input-error");
                        locationInput.classList.remove("input-error");
                    }, 300);

                    return false;
                }

                document.getElementById("searchForm").submit();
            }
        </script>
        <script src="../assets/scripts/admin.js"></script>
    </body>
</html>
