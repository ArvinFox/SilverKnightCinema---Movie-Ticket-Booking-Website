<%-- 
    Document   : manageGenres
    Created on : Dec 10, 2024, 6:58:48 PM
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
        <title>Manage Genres - Silver Knight Cinema</title>
        <link rel="stylesheet" href="../assets/css/adminStyles.css">
    </head>
    <body>
        <%@ include file="../adminTemplates/header.jsp" %>
        <%@ include file="../adminTemplates/sidebar.jsp" %>
        
        <div class="main-content" id="mainContent">
            <h1>Manage Genres</h1>
            
            <button id="openModal" class="action-btn add-btn-link font-16" data-action="add" data-type="Genre">Add New Genre</button>
            
            <div class="filter-header" id="filterHeader">
                <h2>Filter Options</h2>
                <span class="toggle-icon" id="toggleIcon">&#9660;</span> <!-- Down arrow icon -->
            </div>
            
            <div class="search-container" id="filterOptions">
                <form id="searchForm" action="manageGenres" method="get">                   
                    <label for="genre">Genre:</label>
                    <input type="text" name="genre" id="genre" placeholder="Search by genre" 
                           value="${param.genre != null ? param.genre : ''}">

                    <div class="buttons-container">
                        <button type="button" class="reset-btn" ${empty param.genre ? 'disabled' : ''} onclick="window.location.href = 'manageGenres'">Reset Filters</button>
                        <button type="button" onclick="validateSearchInput()">Search</button>
                    </div> 
                </form>
            </div>

            <c:if test="${not empty genreList}">
                <table class="genres-table">
                    <thead>
                        <tr>
                            <th>Genre ID</th>
                            <th>Genre</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="genre" items="${genreList}">
                            <tr>
                                <td>${genre.genreId}</td>
                                <td>${genre.name}</td>
                                <td>
                                    <form id="deleteGenreForm" action="manageGenres" method="POST">
                                        <input type="submit" class="action-btn delete-btn" value="Delete" />
                                        <input type="hidden" name="action" value="delete" />
                                        <input type="hidden" name="genreId" value="${genre.genreId}"/>
                                        <input type="hidden" name="genre" value="${genre.name}"/>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>        
            <c:if test="${empty genreList}">
                <p>No genres available.</p>
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
                const genreInput = document.getElementById("genre");

                if (!genreInput.value.trim()) {
                    genreInput.classList.add("input-error");

                    setTimeout(() => {
                        genreInput.classList.remove("input-error");
                    }, 300);

                    return false;
                }

                document.getElementById("searchForm").submit();
            }

            document.querySelectorAll('#deleteGenreForm').forEach(form => {
                form.addEventListener('submit', async function (event) {
                    event.preventDefault();

                    const genreId = form.querySelector('input[name="genreId"]').value;
                    const genreName = form.querySelector('input[name="genre"]').value;

                    const confirmed = await confirmGenreDelete(genreName, genreId);

                    if (confirmed) {
                        form.submit();
                    } else {
                        console.log('Genre deletion canceled');
                    }
                });
            }); 
        </script>
        <script src="../assets/scripts/admin.js"></script>
    </body>
</html>
