<%-- 
    Document   : viewInquiry
    Created on : Dec 10, 2024, 6:28:11 PM
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
        <title>Inquiry Details - Silver Knight Cinema</title>
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
            <h1>Inquiry Details</h1>

            <form action="manageInquiries" method="POST">
                <div class="inquiry-details entity-details">
                    <p><strong>Inquiry ID:</strong></p>
                    <input type="text" class="form-control" value="${inquiry.inquiryId}" disabled>

                    <p><strong>Name:</strong></p>
                    <input type="text" class="form-control" name="name" value="${inquiry.name}" disabled>
                    
                    <p><strong>Email:</strong></p>
                    <input type="email" class="form-control" name="email" value="${inquiry.email}" disabled>
                    
                    <p><strong>Subject:</strong></p>
                    <input type="text" class="form-control" name="subject" value="${inquiry.subject}" disabled>

                    <p><strong>Message:</strong></p>
                    <textarea class="form-control" name="message" rows="3" disabled>${inquiry.message}</textarea>

                    <p><strong>Date Submitted:</strong></p>
                    <input type="date" class="form-control" name="createdAt" value="${inquiry.createdAt}" disabled>
                </div>
            </form>
        </div>
        
        <script src="../assets/scripts/admin.js"></script>
    </body>
</html>
