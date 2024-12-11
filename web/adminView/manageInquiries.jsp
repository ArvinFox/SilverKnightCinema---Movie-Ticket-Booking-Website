<%-- 
    Document   : manageInquiries
    Created on : Dec 10, 2024, 6:28:05 PM
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
        <title>Manage Inquiries - Silver Knight Cinema</title>
        <link rel="stylesheet" href="../assets/css/adminStyles.css">
    </head>
    <body>
        <%@ include file="../adminTemplates/header.jsp" %>
        <%@ include file="../adminTemplates/sidebar.jsp" %>
        
        <div class="main-content" id="mainContent">
            <h1>Manage Inquiries</h1>
            
            <div class="filter-header" id="filterHeader">
                <h2>Filter Options</h2>
                <span class="toggle-icon" id="toggleIcon">&#9660;</span> <!-- Down arrow icon -->
            </div>
            
            <div class="search-container" id="filterOptions">
                <form id="searchForm" action="manageInquiries" method="get">
                    <label for="email">Email:</label>
                    <input type="text" name="email" id="email" placeholder="Search by email" 
                        value="${param.email != null ? param.email : ''}">

                    <label for="subject">Subject:</label>
                    <input type="text" name="subject" id="subject" placeholder="Search by subject" 
                        value="${param.subject != null ? param.subject : ''}">

                    <label for="startDate">Date From:</label>
                    <input type="date" name="startDate" id="startDate"
                        value="${param.startDate != null ? param.startDate : ''}">

                    <label for="endDate">To:</label>
                    <input type="date" name="endDate" id="endDate"
                        value="${param.endDate != null ? param.endDate : ''}">

                    <div class="buttons-container">
                        <button type="button" class="reset-btn" ${empty param.email && empty param.subject && empty param.startDate && empty param.endDate ? 'disabled' : ''} onclick="window.location.href = 'manageInquiries'">Reset Filters</button>
                        <button type="button" onclick="validateSearchInput()">Search</button>
                    </div>
                </form>
            </div>
                    
            <c:if test="${not empty inquiryList}">
                <table class="inquiries-table">
                    <thead>
                        <tr>
                            <th>Inquiry ID</th>
                            <th>Email</th>
                            <th>Subject</th>
                            <th>Date Submitted</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="inquiry" items="${inquiryList}">
                            <tr>
                                <td>${inquiry.inquiryId}</td>
                                <td>${inquiry.email}</td>
                                <td>${inquiry.subject}</td>
                                <td>${inquiry.createdAt}</td>
                                <td>
                                    <div style="display: flex;">
                                        <input type="submit" id="openModal" class="action-btn view-btn" data-id="${inquiry.inquiryId}" data-action="view" data-type="Inquiry" value="View" />
                                        <form action="manageInquiries" method="POST" onsubmit="return confirmInquiryDelete('${inquiry.subject}', '${inquiry.inquiryId}')">
                                            <input type="submit" class="action-btn delete-btn" value="Delete" />
                                            <input type="hidden" name="action" value="delete" />
                                            <input type="hidden" name="inquiryId" value="${inquiry.inquiryId}"/>
                                        </form>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${empty inquiryList}">
                <p>No inquiries available.</p>
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
                const emailInput = document.getElementById('email');
                const subjectInput = document.getElementById('subject');
                const startDateInput = document.getElementById('startDate');
                const endDateInput = document.getElementById('endDate');

                if (!emailInput.value.trim() && !subjectInput.value.trim() && !startDateInput.value.trim() && !endDateInput.value.trim()) {
                    emailInput.classList.add("input-error");
                    subjectInput.classList.add("input-error");
                    startDateInput.classList.add("input-error");
                    endDateInput.classList.add("input-error");

                    setTimeout(() => {
                        emailInput.classList.remove("input-error");
                        subjectInput.classList.remove("input-error");
                        startDateInput.classList.remove("input-error");
                        endDateInput.classList.remove("input-error");
                    }, 300);

                    return false;
                }

                document.getElementById("searchForm").submit();
            }
        </script>
        <script src="../assets/scripts/admin.js"></script>
    </body>
</html>
