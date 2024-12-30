<%-- 
    Document   : manageUsers
    Created on : Dec 11, 2024, 2:26:15 PM
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
        <title>Manage Users - Silver Knight Cinema</title>
        <link rel="stylesheet" href="../assets/css/adminStyles.css">
    </head>
    <body>
        <%@ include file="../adminTemplates/header.jsp" %>
        <%@ include file="../adminTemplates/sidebar.jsp" %>
        
        <div class="main-content" id="mainContent">
            <h1>Manage Users</h1>
            
            <div class="filter-header" id="filterHeader">
                <h2>Filter Options</h2>
                <span class="toggle-icon" id="toggleIcon">&#9660;</span> <!-- Down arrow icon -->
            </div>
            
            <div class="search-container" id="filterOptions">
                <form id="searchForm" action="manageUsers" method="get">
                    <label for="type">Type:</label>
                    <select name="type" id="type" onchange="toggleStatus()">
                        <option value="any">All Users</option>
                        <option value="Registered Users" ${param.type == 'Registered Users' ? 'selected' : ''}>Registered Users</option>
                        <option value="Guests" ${param.type == 'Guests' ? 'selected' : ''}>Guests</option>
                    </select>
                    
                    <label for="name">Name:</label>
                    <input type="text" name="name" id="name" placeholder="Search by name" 
                           value="${param.name != null ? param.name : ''}">
                    
                    <label for="email">Email:</label>
                    <input type="text" name="email" id="email" placeholder="Search by email" 
                           value="${param.email != null ? param.email : ''}">
                    
                    <label for="contactNumber">Contact No:</label>
                    <input type="text" name="contactNumber" id="contactNumber" placeholder="Search by contact number" 
                           value="${param.contactNumber != null ? param.contactNumber : ''}">

                    <label for="status">Status:</label>
                    <select name="status" id="status" ${param.type == 'Guests' ? "disabled" : ""}>
                        <option value="any">Any Status</option>
                        <option value="Active" ${param.status == 'Active' ? 'selected' : ''}>Active</option>
                        <option value="Inactive" ${param.status == 'Inactive' ? 'selected' : ''}>Inactive</option>
                        <option value="Suspended" ${param.status == 'Suspended' ? 'selected' : ''}>Suspended</option>
                    </select>

                    <div class="buttons-container">
                        <button type="button" class="reset-btn" ${empty param.type && empty param.name && empty param.email && empty param.contactNumber && empty param.status ? 'disabled' : ''} onclick="window.location.href = 'manageUsers'">Reset Filters</button>
                        <button type="button" onclick="validateSearchInput()">Search</button>
                    </div>
                </form>
            </div>

            <c:if test="${userList != null}">
                <h3>Registered Users</h3>
                <c:if test="${not empty userList}">
                    <table class="users-table">
                        <thead>
                            <tr>
                                <th>User ID</th>
                                <th>Name</th>
                                <th>Email</th>
                                <th>Status</th>
                                <th>Registration Date</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="user" items="${userList}">
                                <tr>
                                    <td>${user.userId}</td>
                                    <td>${user.firstName} ${user.lastName}</td>
                                    <td>${user.email}</td>
                                    <td>${user.accountStatus}</td>
                                    <td>${user.registrationDate}</td>
                                    <td>
                                        <div style="display: flex;">
                                            <input type="submit" id="openModal" class="action-btn view-btn" data-id="${user.userId}" data-action="view" data-type="User" value="View" />
                                            <form action="manageUsers" method="POST" onsubmit="return confirmUserDelete('${user.firstName} ${user.lastName}', '${user.email}', '${user.userId}')">
                                                <input type="submit" class="action-btn delete-btn" value="Delete" />
                                                <input type="hidden" name="action" value="delete" />
                                                <input type="hidden" name="type" value="user" />
                                                <input type="hidden" name="userId" value="${user.userId}"/>
                                            </form>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>        
                <c:if test="${empty userList}">
                    <p>No users available.</p>
                </c:if>
            </c:if>
            
            <c:if test="${guestList != null}">
                <h3>Guests</h3>
                <c:if test="${not empty guestList}">
                    <table class="guests-table">
                        <thead>
                            <tr>
                                <th>Guest ID</th>
                                <th>Name</th>
                                <th>Email</th>
                                <th>Contact Number</th>
                                <th>Created At</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="guest" items="${guestList}">
                                <tr>
                                    <td>${guest.guestId}</td>
                                    <td>${guest.name}</td>
                                    <td>${guest.email}</td>
                                    <td>${guest.contactNumber}</td>
                                    <td>${guest.createdAt}</td>
                                    <td>
                                        <form action="manageUsers" method="POST" onsubmit="return confirmGuestDelete('${guest.name}', '${guest.email}', '${guest.guestId}')">
                                            <input type="submit" class="action-btn delete-btn" value="Delete" />
                                            <input type="hidden" name="action" value="delete" />
                                            <input type="hidden" name="type" value="guest" />
                                            <input type="hidden" name="guestId" value="${guest.guestId}"/>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>        
                <c:if test="${empty guestList}">
                    <p>No guests available.</p>
                </c:if>
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
            function toggleStatus() {
                var type = document.getElementById("type").value;
                var status = document.getElementById("status");

                if (type === "Guests") {
                    status.disabled = true;
                } else {
                    status.disabled = false;
                }
            }

            function validateSearchInput() {
                const typeInput = document.getElementById('type');
                const nameInput = document.getElementById('name');
                const emailInput = document.getElementById('email');
                const contactNumberInput = document.getElementById('contactNumber');
                const statusInput = document.getElementById('status');

                if (typeInput.value.trim() === "any" && !nameInput.value.trim() && !emailInput.value.trim() && !contactNumberInput.value.trim() && statusInput.value.trim() === "any") {
                    typeInput.classList.add("input-error");
                    nameInput.classList.add("input-error");
                    emailInput.classList.add("input-error");
                    contactNumberInput.classList.add("input-error");
                    statusInput.classList.add("input-error");

                    setTimeout(() => {
                        typeInput.classList.remove("input-error");
                        nameInput.classList.remove("input-error");
                        emailInput.classList.remove("input-error");
                        contactNumberInput.classList.remove("input-error");
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
