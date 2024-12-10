<%-- 
    Document   : manageLanguages
    Created on : Dec 10, 2024, 6:59:00 PM
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
        <title>Manage Languages - Silver Knight Cinema</title>
        <link rel="stylesheet" href="../assets/css/adminStyles.css">
        <style>
            @keyframes shake {
                0% { transform: translateX(0); }
                25% { transform: translateX(-5px); }
                50% { transform: translateX(5px); }
                75% { transform: translateX(-5px); }
                100% { transform: translateX(0); }
            }

            .input-error {
                animation: shake 0.3s ease-in-out;
                border: 2px solid red;
                outline: none;
            }
        </style>
    </head>
    <body>
        <%@ include file="../adminTemplates/header.jsp" %>
        <%@ include file="../adminTemplates/sidebar.jsp" %>
        
        <div class="main-content" id="mainContent">
            <h1>Manage Languages</h1>
            
            <button id="openModal" class="action-btn add-btn-link font-16" data-action="add" data-type="Language">Add New Language</button>
            
            <div class="filter-header" id="filterHeader">
                <h2>Filter Options</h2>
                <span class="toggle-icon" id="toggleIcon">&#9660;</span> <!-- Down arrow icon -->
            </div>
            
            <div class="search-container" id="filterOptions">
                <form id="searchForm" action="manageLanguages" method="get">
                    <label for="language">Language:</label>
                    <input type="text" name="language" id="language" placeholder="Search by language" 
                           value="${param.language != null ? param.language : ''}">

                    <div class="buttons-container">
                        <button type="button" class="reset-btn" ${empty param.language ? 'disabled' : ''} onclick="window.location.href = 'manageLanguages'">Reset Filters</button>
                        <button type="button" onclick="validateSearchInput()">Search</button>
                    </div>             
                </form>
            </div>

            <c:if test="${not empty languageList}">
                <table class="languages-table">
                    <thead>
                        <tr>
                            <th>Language ID</th>
                            <th>Language</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="language" items="${languageList}">
                            <tr>
                                <td>${language.languageId}</td>
                                <td>${language.language}</td>
                                <td>
                                    <form action="manageLanguages" method="POST" onsubmit="return confirmLanguageDelete('${language.language}', '${language.languageId}')">
                                        <input type="submit" class="action-btn delete-btn" value="Delete" />
                                        <input type="hidden" name="action" value="delete" />
                                        <input type="hidden" name="languageId" value="${language.languageId}"/>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>        
            <c:if test="${empty languageList}">
                <p>No languages available.</p>
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
                const languageInput = document.getElementById("language");

                if (!languageInput.value.trim()) {
                    languageInput.classList.add("input-error");

                    setTimeout(() => {
                        languageInput.classList.remove("input-error");
                    }, 300);

                    return false;
                }

                document.getElementById("searchForm").submit();
            }
        </script>
        <script src="../assets/scripts/admin.js"></script>
    </body>
</html>
