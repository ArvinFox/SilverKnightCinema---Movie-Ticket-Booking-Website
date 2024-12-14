<%-- 
    Document   : addMovie
    Created on : Dec 3, 2024, 1:05:00 PM
    Author     : Umindu Haputhanthri
--%>

<%@page import="java.util.List"%>
<%@page import="model_classes.Language"%>
<%@page import="dao_classes.LanguageDAO"%>
<%@page import="model_classes.Genre"%>
<%@page import="dao_classes.GenreDAO"%>
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
        <title>Add Movie - Silver Knight Cinema</title>
        <link rel="stylesheet" href="../assets/css/adminStyles.css">
    </head>
    <body>
        <div class="main-content sub-content">
            <h1>Add New Movie</h1>
            
            <form id="movieForm" action="manageMovies" method="POST">
                <input type="hidden" name="action" value="add">
                
                <div class="form-group">
                    <label for="title">Title</label>
                    <input type="text" id="title" name="title" required>
                </div>

                <div class="form-group">
                    <label for="synopsis">Synopsis</label>
                    <textarea id="synopsis" name="synopsis" required></textarea>
                </div>
                
                <%
                    LanguageDAO languageDAO = new LanguageDAO();
                    List<Language> languageList = languageDAO.getAllLanguages();
                    request.setAttribute("languageList", languageList);
                %>

                <div class="form-group">
                    <label for="language">Language</label>
                    <select id="language" name="language" required>
                        <c:forEach var="language" items="${languageList}">
                            <option value="${language.languageId}">${language.language}</option>
                        </c:forEach>
                    </select>
                </div>
                
                <%
                    GenreDAO genreDAO = new GenreDAO();
                    List<Genre> genreList = genreDAO.getAllGenres();
                    request.setAttribute("genreList", genreList);
                %>

                <div class="form-group">
                    <label>Genres</label>
                    <div class="checkbox-group">
                        <c:forEach var="genre" items="${genreList}">
                            <input type="checkbox" id="genre-${genre.genreId}" name="genres" value="${genre.genreId}">
                            <label for="genre-${genre.genreId}">${genre.name}</label>
                        </c:forEach>
                    </div>
                </div>

                <div class="form-group">
                    <label for="duration">Duration (in minutes)</label>
                    <input type="number" id="duration" name="duration" step="1" min="0" required>
                </div>

                <div class="form-group">
                    <label for="rating">Rating</label>
                    <input type="number" id="rating" name="rating" step="0.1" min="0" max="10" required>
                </div>

                <div class="form-group">
                    <label for="releaseDate">Release Date</label>
                    <input type="date" id="releaseDate" name="releaseDate" required>
                </div>

                <div class="cast-crew-section">
                    <h3>Cast Members</h3>
                    <div id="cast-container">
                        <div class="cast-crew-row">
                            <input type="text" name="castActor[]" placeholder="Actor Name" required>
                            <input type="text" name="castCharacter[]" placeholder="Character Name" required>
                            <button type="button" class="action-btn remove-btn" onclick="removeCastCrewRow('cast', this)" disabled>Remove</button>
                        </div>
                    </div>
                    <button type="button" class="add-cast-button" onclick="addCastCrewRow('cast')">Add Cast Member</button>
                </div>

                <div class="cast-crew-section">
                    <h3>Crew Members</h3>
                    <div id="crew-container">
                        <div class="cast-crew-row">
                            <input type="text" name="crewMember[]" placeholder="Member Name" required>
                            <input type="text" name="crewRole[]" placeholder="Role (e.g., Director)" required>
                            <button type="button" class="action-btn remove-btn" onclick="removeCastCrewRow('crew', this)" disabled>Remove</button>
                        </div>
                    </div>
                    <button type="button" class="add-crew-button" onclick="addCastCrewRow('crew')">Add Crew Member</button>
                </div>

                <div class="form-group">
                    <label for="posterUrl">Poster</label>
                    <input type="file" id="poster" accept="image/*" required>
                    <input type="hidden" id="posterPath" name="posterUrl">
                </div>

                <div class="form-group">
                    <label for="trailerUrl">Trailer Link</label>
                    <input type="url" id="trailerUrl" name="trailerUrl" required>
                </div>
                
                <div class="form-group">
                    <label for="status">Status</label>
                    <select id="status" name="status" required>
                        <option value="Now Showing">Now Showing</option>
                        <option value="Coming Soon">Coming Soon</option>
                    </select>
                </div>

                <button type="submit" class="action-btn add-btn-link font-16" onclick="uploadPoster('movie')">Add Movie</button>
            </form>
        </div>

        <script src="../assets/scripts/admin.js"></script>
    </body>
</html>
