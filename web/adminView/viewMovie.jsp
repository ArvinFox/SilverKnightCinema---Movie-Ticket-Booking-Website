<%-- 
    Document   : viewMovie
    Created on : Dec 3, 2024, 1:05:20 PM
    Author     : Umindu Haputhanthri
--%>

<%@page import="java.util.Map"%>
<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>
<%@page import="model_classes.Movie"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Movie movie = (Movie) request.getAttribute("movie");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Movie Details - SilverKnightCinema</title>
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

            <form id="editForm">
                <div class="movie-details">
                    <% 
                        int movieId = 1;
                        String title = "Test Movie";
                        String synopsis = "A team gathers to build a user-friendly ticket booking website for a cinema, to allow movie-lovers to book their seats seamlessly.";
                        String language = "English";
                        String genreIds = "[1, 2]";
                        int duration = 60;
                        double rating = 10.0;
                        String releaseDate = "2005-04-11";
                        String castJson = "[{'actor': 'Actor 01', 'character': 'Character 01'}, {'actor': 'Actor 02', 'character': 'Character 02'}]";
                        String crewJson = "[{'member': 'Member 01', 'role': 'Role 01'}, {'member': 'Member 02', 'role': 'Role 02'}]";
                        String posterUrl = "../assets/images/posters/Test-Image.jpg";
                        String trailerUrl = "https://www.youtube.com/watch?v=test123";

                        Map<Integer, String> genreMap = Map.of(1, "Action", 2, "Adventure", 3, "Comedy", 4, "Drama", 5, "Sci-Fi");

                        // Parse genreIds JSON and generate genre names
                        JSONArray genreArray = new JSONArray(genreIds);
                        StringBuilder genres = new StringBuilder();
                        for (int i = 0; i < genreArray.length(); i++) {
                            int genreId = genreArray.getInt(i);
                            genres.append(genreMap.getOrDefault(genreId, "Unknown")).append(i < genreArray.length() - 1 ? ", " : "");
                        }

                        // Parse cast JSON
                        JSONArray castArray = new JSONArray(castJson);

                        // Parse crew JSON
                        JSONArray crewArray = new JSONArray(crewJson);
                    %>

                    <img src="<%= posterUrl %>" alt="<%= title %>" width="150">

                    <p><strong>Movie ID:</strong></p>
                    <input type="text" class="form-control" value="<%= movieId %>" disabled>

                    <p><strong>Title:</strong></p>
                    <input type="text" class="form-control editable" value="<%= title %>" disabled>

                    <p><strong>Synopsis:</strong></p>
                    <textarea class="form-control editable" rows="3" disabled><%= synopsis %></textarea>

                    <p><strong>Language:</strong></p>
                    <div id="language-container">
                        <input type="text" class="form-control editable" value="<%= language %>" disabled>
                    </div> 

                    <p><strong>Genres:</strong></p>
                    <div id="genre-container" class="genres">
                        <% 
                            for (Map.Entry<Integer, String> entry : genreMap.entrySet()) { 
                                int genreId = entry.getKey();
                                String genreName = entry.getValue();
                                boolean isSelected = false;
                                for (int i = 0; i < genreArray.length(); i++) {
                                    if (genreArray.getInt(i) == genreId) {
                                        isSelected = true;
                                        break;
                                    }
                            }
                        %>
                        
                        <span class="genre-tag" data-genre-id="genre-<%= genreName.toLowerCase().replaceAll("\\s+", "-") %>" <%= !isSelected ? "hidden" : "" %>><%= genreName %></span>
                        <% } %>
                    </div>

                    <p><strong>Duration (in minutes):</strong></p>
                    <input type="number" class="form-control editable" value="<%= duration %>" disabled>

                    <p><strong>Rating:</strong></p>
                    <input type="number" class="form-control editable" value="<%= rating %>" step="0.1" disabled>

                    <p><strong>Release Date:</strong></p>
                    <input type="date" class="form-control editable" value="<%= releaseDate %>" disabled>

                    <p><strong>Cast Members:</strong></p>
                    <div id="cast-container">
                        <table id="cast-table">
                            <thead>
                                <tr>
                                    <th>Actor</th>
                                    <th>Character</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% for (int i = 0; i < castArray.length(); i++) {
                                    JSONObject castMember = castArray.getJSONObject(i);
                                    String actor = castMember.getString("actor");
                                    String character = castMember.getString("character");
                                %>
                                <tr>
                                    <td><%= actor %></td>
                                    <td><%= character %></td>
                                </tr>
                                <% } %>
                            </tbody>
                        </table>
                    </div>
                    
                    <p><strong>Crew Members:</strong></p>
                    <div id="crew-container">
                        <table id="crew-table">
                            <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>Role</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% for (int i = 0; i < crewArray.length(); i++) {
                                    JSONObject crewMember = crewArray.getJSONObject(i);
                                    String role = crewMember.getString("role");
                                    String member = crewMember.getString("member");
                                %>
                                <tr>
                                    <td><%= member %></td>
                                    <td><%= role %></td>
                                </tr>
                                <% } %>
                            </tbody>
                        </table>
                    </div>
                    
                    <p><strong>Trailer:</strong></p>
                    <a id="trailerLink" href="<%= trailerUrl %>" target="_blank">Watch Trailer</a>
                    <input type="url" id="trailerInput" class="form-control editable" value="<%= trailerUrl %>" disabled>
                </div>
            
                <div class="action-buttons hidden">
                    <div>
                        <button type="button" class="action-btn cancel-button" onclick="toggleEditMode(false)">Cancel</button>
                        <button type="button" class="action-btn reset-button" onclick="resetChanges()">Reset</button>
                    </div>
                    <div>
                        <button type="submit" class="action-btn save-changes-button">Save Changes</button>
                    </div>
                </div>
            </form>
            

            <button class="action-btn edit-btn" onclick="toggleEditMode(true)">Edit Movie</button>
        </div>
        
        <script src="../assets/scripts/admin.js"></script>
    </body>
</html>

