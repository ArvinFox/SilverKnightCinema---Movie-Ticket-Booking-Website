<%-- 
    Document   : addMovie
    Created on : Dec 3, 2024, 1:05:00 PM
    Author     : Umindu Haputhanthri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Add Movie - SilverKnightCinema</title>
        <link rel="stylesheet" href="../assets/css/adminStyles.css">
    </head>
    <body>
        
        <div class="main-content sub-content">
            <h1>Add New Movie</h1>
            
            <form action="manageMovies" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="title">Title</label>
                    <input type="text" id="title" name="title" required>
                </div>

                <div class="form-group">
                    <label for="synopsis">Synopsis</label>
                    <textarea id="synopsis" name="synopsis" required></textarea>
                </div>

                <div class="form-group">
                    <label for="language">Language</label>
                    <select id="language" name="language" required>
                        <option value="1">English</option>
                        <option value="2">Spanish</option>
                        <option value="3">French</option>
                        <option value="4">German</option>
                        <option value="5">Hindi</option>
                        <option value="6">Japanese</option>
                        <option value="7">Korean</option>
                        <option value="8">Sinhala</option>
                    </select>
                </div>

                <div class="form-group">
                    <label>Genres</label>
                    <div class="checkbox-group">
                        <input type="checkbox" id="genre-action" name="genres" value="1">
                        <label for="genre-action">Action</label>

                        <input type="checkbox" id="genre-adventure" name="genres" value="2">
                        <label for="genre-adventure">Adventure</label>

                        <input type="checkbox" id="genre-comedy" name="genres" value="3">
                        <label for="genre-comedy">Comedy</label>

                        <input type="checkbox" id="genre-drama" name="genres" value="4">
                        <label for="genre-drama">Drama</label>

                        <input type="checkbox" id="genre-sci-fi" name="genres" value="5">
                        <label for="genre-sci-fi">Sci-Fi</label>

                        <input type="checkbox" id="genre-horror" name="genres" value="6">
                        <label for="genre-horror">Horror</label>

                        <input type="checkbox" id="genre-thriller" name="genres" value="7">
                        <label for="genre-thriller">Thriller</label>

                        <input type="checkbox" id="genre-crime" name="genres" value="8">
                        <label for="genre-crime">Crime</label>

                        <input type="checkbox" id="genre-fantasy" name="genres" value="9">
                        <label for="genre-fantasy">Fantasy</label>

                        <input type="checkbox" id="genre-romance" name="genres" value="10">
                        <label for="genre-romance">Romance</label>

                        <input type="checkbox" id="genre-mystery" name="genres" value="11">
                        <label for="genre-mystery">Mystery</label>

                        <input type="checkbox" id="genre-family" name="genres" value="12">
                        <label for="genre-family">Family</label>

                        <input type="checkbox" id="genre-sport" name="genres" value="13">
                        <label for="genre-sport">Sport</label>

                        <input type="checkbox" id="genre-history" name="genres" value="14">
                        <label for="genre-history">History</label>

                        <input type="checkbox" id="genre-documentary" name="genres" value="15">
                        <label for="genre-documentary">Documentary</label>
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
                    <input type="file" id="posterUrl" name="posterUrl" accept="image/*" required>
                </div>

                <div class="form-group">
                    <label for="trailerUrl">Trailer Link</label>
                    <input type="url" id="trailerUrl" name="trailerUrl" required>
                </div>

                <button type="submit" class="action-btn add-movie-button font-16">Add Movie</button>
            </form>
        </div>
        
        <script src="../assets/scripts/admin.js"></script>
    </body>
</html>
