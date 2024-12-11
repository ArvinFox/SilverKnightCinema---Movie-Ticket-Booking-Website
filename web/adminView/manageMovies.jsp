<%-- 
    Document   : manageMovies
    Created on : Dec 3, 2024, 1:03:14 PM
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
        <title>Manage Movies - Silver Knight Cinema</title>
        <link rel="stylesheet" href="../assets/css/adminStyles.css">
    </head>
    <body>
        <%@ include file="../adminTemplates/header.jsp" %>
        <%@ include file="../adminTemplates/sidebar.jsp" %>

        <div class="main-content" id="mainContent">
            <h1>Manage Movies</h1>
            
            <button id="openModal" class="action-btn add-btn-link font-16" data-action="add" data-type="Movie">Add New Movie</button>
            
            <div class="filter-header" id="filterHeader">
                <h2>Filter Options</h2>
                <span class="toggle-icon" id="toggleIcon">&#9660;</span> <!-- Down arrow icon -->
            </div>
            
            <div class="search-container" id="filterOptions">
                <form id="searchForm" action="manageMovies" method="get">
                    <label for="title">Title:</label>
                    <input type="text" name="title" id="title" placeholder="Search by title" 
                           value="${param.title != null ? param.title : ''}">

                    <label for="genre">Genre:</label>
                    <select name="genre" id="genre">
                        <option value="any">All Genres</option>
                        <c:forEach var="genre" items="${genreList}">
                            <option value="${genre.name}" ${genre.name == param.genre ? "selected" : ""}>${genre.name}</option>
                        </c:forEach>
                    </select>

                    <label for="language">Language:</label>
                    <select name="language" id="language">
                        <option value="any">All Languages</option>
                        <c:forEach var="language" items="${languageList}">
                            <option value="${language.language}" ${language.language == param.language ? "selected" : ""}>${language.language}</option>
                        </c:forEach>
                    </select>

                    <label for="releaseDateFrom">Release Date From:</label>
                    <input type="date" name="releaseDateFrom" id="releaseDateFrom"
                           value="${param.releaseDateFrom != null ? param.releaseDateFrom : ''}">

                    <label for="releaseDateTo">To:</label>
                    <input type="date" name="releaseDateTo" id="releaseDateTo"
                           value="${param.releaseDateTo != null ? param.releaseDateTo : ''}">

                    <label for="status">Status:</label>
                    <select name="status" id="status">
                        <option value="any">Any Status</option>
                        <option value="NOW_SHOWING" ${param.status == 'NOW_SHOWING' ? 'selected' : ''}>Now Showing</option>
                        <option value="COMING_SOON" ${param.status == 'COMING_SOON' ? 'selected' : ''}>Coming Soon</option>
                    </select>

                    <div class="buttons-container">
                        <button type="button" class="reset-btn" ${empty param.title && empty param.genre && empty param.language && empty param.releaseDateFrom && empty param.releaseDateTo && empty param.status ? 'disabled' : ''} onclick="window.location.href = 'manageMovies'">Reset Filters</button>
                        <button type="button" onclick="validateSearchInput()">Search</button>
                    </div>
                </form>
            </div>
                    
            <c:if test="${not empty movieList}">
                <table class="movies-table">
                    <thead>
                        <tr>
                            <th>Movie ID</th>
                            <th>Title</th>
                            <th>Release Date</th>
                            <th>Status</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="movie" items="${movieList}">
                            <tr>
                                <td>${movie.movieId}</td>
                                <td>${movie.title}</td>
                                <td>${movie.releaseDate}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${movie.status == 'NOW_SHOWING'}">Now Showing</c:when>
                                        <c:when test="${movie.status == 'COMING_SOON'}">Coming Soon</c:when>
                                        <c:otherwise>Unknown</c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <div style="display: flex;">
                                        <input type="submit" id="openModal" class="action-btn view-btn" data-id="${movie.movieId}" data-action="view" data-type="Movie" value="View" />
                                        <form action="manageMovies" method="POST" onsubmit="return confirmMovieDelete('${movie.title}', '${movie.movieId}')">
                                            <input type="submit" class="action-btn delete-btn" value="Delete" />
                                            <input type="hidden" name="action" value="delete" />
                                            <input type="hidden" name="movieId" value="${movie.movieId}"/>
                                        </form>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${empty movieList}">
                <p>No movies available.</p>
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
                const titleInput = document.getElementById('title');
                const genreInput = document.getElementById('genre');
                const languageInput = document.getElementById('language');
                const releaseDateFromInput = document.getElementById('releaseDateFrom');
                const releaseDateToInput = document.getElementById('releaseDateTo');
                const statusInput = document.getElementById('status');

                if (!titleInput.value.trim() && genreInput.value.trim() === "any" && languageInput.value.trim() === "any" && !releaseDateFromInput.value.trim() && !releaseDateToInput.value.trim() && statusInput.value.trim() === "any") {
                    titleInput.classList.add("input-error");
                    genreInput.classList.add("input-error");
                    languageInput.classList.add("input-error");
                    releaseDateFromInput.classList.add("input-error");
                    releaseDateToInput.classList.add("input-error");
                    statusInput.classList.add("input-error");

                    setTimeout(() => {
                        titleInput.classList.remove("input-error");
                        genreInput.classList.remove("input-error");
                        languageInput.classList.remove("input-error");
                        releaseDateFromInput.classList.remove("input-error");
                        releaseDateToInput.classList.remove("input-error");
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
