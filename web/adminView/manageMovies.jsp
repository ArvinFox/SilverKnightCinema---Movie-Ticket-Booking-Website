<%-- 
    Document   : manageMovies
    Created on : Dec 3, 2024, 1:03:14 PM
    Author     : Umindu Haputhanthri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Manage Movies - SilverKnightCinema</title>
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
                <form action="manageMovies.jsp" method="get">
                    <label for="searchTitle">Title:</label>
                    <input type="text" name="searchTitle" id="searchTitle" placeholder="Search by title" 
                           value="<%= request.getParameter("searchTitle") != null ? request.getParameter("searchTitle") : "" %>">

                    <label for="genre">Genre:</label>
                    <select name="genre" id="genre">
                        <option value="">Select Genre</option>
                        <option value="Action" <%= "Action".equals(request.getParameter("genre")) ? "selected" : "" %>>Action</option>
                        <option value="Comedy" <%= "Comedy".equals(request.getParameter("genre")) ? "selected" : "" %>>Comedy</option>
                        <option value="Drama" <%= "Drama".equals(request.getParameter("genre")) ? "selected" : "" %>>Drama</option>
                        <option value="Thriller" <%= "Thriller".equals(request.getParameter("genre")) ? "selected" : "" %>>Thriller</option>
                    </select>

                    <label for="language">Language:</label>
                    <select name="language" id="language">
                        <option value="">Select Language</option>
                        <option value="English" <%= "English".equals(request.getParameter("language")) ? "selected" : "" %>>English</option>
                        <option value="Spanish" <%= "Spanish".equals(request.getParameter("language")) ? "selected" : "" %>>Spanish</option>
                        <option value="French" <%= "French".equals(request.getParameter("language")) ? "selected" : "" %>>French</option>
                        <option value="Hindi" <%= "Hindi".equals(request.getParameter("language")) ? "selected" : "" %>>Hindi</option>
                    </select>

                    <label for="releaseDateFrom">Release Date From:</label>
                    <input type="date" name="releaseDateFrom" id="releaseDateFrom"
                           value="<%= request.getParameter("releaseDateFrom") != null ? request.getParameter("releaseDateFrom") : "" %>">

                    <label for="releaseDateTo">To:</label>
                    <input type="date" name="releaseDateTo" id="releaseDateTo"
                           value="<%= request.getParameter("releaseDateTo") != null ? request.getParameter("releaseDateTo") : "" %>">

                    <label for="status">Status:</label>
                    <select name="status" id="status">
                        <option value="">Select Status</option>
                        <option value="Now Showing" <%= "Now Showing".equals(request.getParameter("status")) ? "selected" : "" %>>Now Showing</option>
                        <option value="Coming Soon" <%= "Coming Soon".equals(request.getParameter("status")) ? "selected" : "" %>>Coming Soon</option>
                    </select>

                    <button type="submit">Search</button>
                </form>
            </div>
                    
            <table class="movies-table">
                <thead>
                    <tr>
                        <th>Movie ID</th>
                        <th>Title</th>
                        <th>Release Date</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>1</td>
                        <td>Test Movie Title 1</td>
                        <td>2010-07-16</td>
                        <td>
                            <div style="display: flex;">
                                <input type="submit" id="openModal" class="action-btn view-btn" data-id="1" data-action="view" data-type="Movie" value="View" />
                                <form action="manageMovies" method="post">
                                    <input type="submit" class="action-btn delete-btn" value="Delete" />
                                    <input type="hidden" name="movieId" value="1"/>
                                </form>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>Test Movie Title 2</td>
                        <td>2009-12-18</td>
                        <td>
                            <div style="display: flex;">
                                <input type="submit" id="openModal" class="action-btn view-btn" data-id="2" data-action="view" data-type="Movie" value="View" />
                                <form action="manageMovies" method="post">
                                    <input type="submit" class="action-btn delete-btn" value="Delete" />
                                    <input type="hidden" name="movieId" value="2"/>
                                </form>
                            </div>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
                    
        <!-- Modal structure -->
        <div id="popupModal" class="modal">
          <div class="modal-content" id="modalContent">
            <button class="close-btn" id="closeModal">X</button>
            <!-- Dynamic content will load here -->
          </div>
        </div>

        <%@ include file="../adminTemplates/footer.jsp" %>
        
        <script src="../assets/scripts/admin.js"></script>
    </body>
</html>
