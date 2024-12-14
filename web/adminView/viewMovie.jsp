<%-- 
    Document   : viewMovie
    Created on : Dec 3, 2024, 1:05:20 PM
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
        <title>Movie Details - Silver Knight Cinema</title>
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
            <h1>Movie Details</h1>

            <form id="editForm" action="manageMovies" method="POST">
                <input type="hidden" name="action" value="update">
                
                <div class="movie-details entity-details">
                    <img src="../${movie.posterUrl}" alt="${movie.title}" width="200px">
                    <input type="hidden" name="posterUrl" value="${movie.posterUrl}">

                    <p><strong>Movie ID:</strong></p>
                    <input type="text" class="form-control" value="${movie.movieId}" disabled>
                    <input type="hidden" name="movieId" value="${movie.movieId}">

                    <p><strong>Title:</strong></p>
                    <input type="text" class="form-control editable" name="title" value="${movie.title}" disabled>

                    <p><strong>Synopsis:</strong></p>
                    <textarea class="form-control editable" name="synopsis" rows="3" disabled>${movie.synopsis}</textarea>

                    <p><strong>Language:</strong></p>
                    <div id="language-container">
                        <input type="text" class="form-control editable" value="${movieLanguage}" disabled>
                    </div> 

                    <p><strong>Genres:</strong></p>
                    <div id="genre-container" class="genres">
                        <c:forEach var="genre" items="${movieGenres}">
                            <span class="genre-tag" data-genre-id="genre-${genre.genreId}">${genre.name}</span>
                        </c:forEach>
                    </div>

                    <p><strong>Duration (in minutes):</strong></p>
                    <input type="number" class="form-control editable" name="duration" value="${movie.duration}" disabled>

                    <p><strong>Rating:</strong></p>
                    <input type="number" class="form-control editable" name="rating" value="${movie.rating}" step="0.1" disabled>

                    <p><strong>Release Date:</strong></p>
                    <input type="date" class="form-control editable" name="releaseDate" value="${movie.releaseDate}" disabled>
                    
                    <p><strong>Status:</strong></p>
                    <div id="status-container">
                        <input type="text" class="form-control editable" value="${movie.status.dbValue}" disabled>
                    </div>

                    <p><strong>Cast Members:</strong></p>
                    <div id="cast-container">
                        <c:if test="${not empty movie.cast}">
                            <table id="cast-table">
                                <thead>
                                    <tr>
                                        <th>Actor</th>
                                        <th>Character</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="cast" items="${movie.cast}">
                                        <tr>
                                            <td>${cast.actor}</td>
                                            <td>${cast.character}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </c:if>
                        <c:if test="${empty movie.cast}">
                            <p>No cast members available</p>
                        </c:if>
                    </div>
                    
                    <p><strong>Crew Members:</strong></p>
                    <div id="crew-container">
                        <c:if test="${not empty movie.crew}">
                            <table id="crew-table">
                                <thead>
                                    <tr>
                                        <th>Name</th>
                                        <th>Role</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="crew" items="${movie.crew}">
                                        <tr>
                                            <td>${crew.name}</td>
                                            <td>${crew.role}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </c:if>
                        <c:if test="${empty movie.crew}">
                            <p>No crew members available</p>
                        </c:if>
                    </div>
                    
                    <p><strong>Trailer:</strong></p>
                    <a id="trailerLink" href="${movie.trailerUrl}" target="_blank">Watch Trailer</a>
                    <input type="url" id="trailerInput" class="form-control editable" name="trailerUrl" value="${movie.trailerUrl}" disabled>
                </div>
            
                <div class="action-buttons hidden">
                    <div>
                        <button type="button" class="action-btn cancel-button" onclick="toggleEditMode(false, 'movie')">Cancel</button>
                        <button type="button" class="action-btn reset-button" onclick="resetChanges('movie')">Reset</button>
                    </div>
                    <div>
                        <button type="submit" class="action-btn save-changes-button">Save Changes</button>
                    </div>
                </div>
            </form>

            <button class="action-btn edit-btn" onclick="toggleEditMode(true, 'movie')">Edit Movie</button>
        </div>
        
        <script src="../assets/scripts/admin.js"></script>
    </body>
</html>
