<%-- 
    Document   : viewUser
    Created on : Dec 11, 2024, 2:26:29 PM
    Author     : Umindu Haputhanthri
--%>

<%@page import="model_classes.User"%>
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
        <title>User Details - Silver Knight Cinema</title>
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
            <h1>User Details</h1>
            
            <form id="editForm" action="manageUsers" method="POST">
                <input type="hidden" name="action" value="update">
                
                <div class="user-details entity-details">
                    <p><strong>User ID:</strong></p>
                    <input type="text" class="form-control" value="${user.userId}" disabled>
                    <input type="hidden" name="userId" value="${user.userId}">

                    <p><strong>First Name:</strong></p>
                    <input type="text" class="form-control editable" name="firstName" value="${user.firstName}" disabled>

                    <p><strong>Last Name:</strong></p>
                    <input type="text" class="form-control editable" name="lastName" value="${user.lastName}" disabled>
                    
                    <p><strong>Email:</strong></p>
                    <input type="email" class="form-control editable" name="email" value="${user.email}" disabled>
                    
                    <p><strong>Password:</strong></p>
                    <input type="text" class="form-control editable" name="password" value="**********" disabled>

                    <p><strong>Contact Number:</strong></p>
                    <input type="text" class="form-control editable" name="contactNumber" value="${user.contactNumber}" disabled>

                    <p><strong>Registration Date:</strong></p>
                    <input type="date" class="form-control editable" name="registrationDate" value="${user.registrationDate}" disabled>
                </div>
            </form>
            
                <!--
                <div class="action-buttons hidden">
                    <div>
                        <button type="button" class="action-btn cancel-button" onclick="toggleEditMode(false, 'user')">Cancel</button>
                        <button type="button" class="action-btn reset-button" onclick="resetChanges('user')">Reset</button>
                    </div>
                    <div>
                        <button type="submit" class="action-btn save-changes-button">Save Changes</button>
                    </div>
                </div>
            </form>

            <button class="action-btn edit-btn" onclick="toggleEditMode(true, 'user')">Edit User</button> -->
            <form action="manageUsers" method="POST" style="margin-top: 15px">
                <input type="hidden" name="action" value="status">
                <input type="hidden" name="userId" value="${user.userId}">
                
                <c:choose>
                    <c:when test="${user.accountStatus == 'SUSPENDED'}">
                        <button type="submit" class="action-btn activate-btn">Activate Account</button>
                    </c:when>
                    <c:otherwise>
                        <button type="submit" class="action-btn suspend-btn">Suspend Account</button>
                    </c:otherwise>
                </c:choose>
            </form>
        </div>
        
        <script src="../assets/scripts/admin.js"></script>
    </body>
</html>
