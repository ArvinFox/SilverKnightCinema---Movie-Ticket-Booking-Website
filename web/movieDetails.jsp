<%-- 
    Document   : movieDetails
    Created on : Dec 23, 2024, 11:06:35 PM
    Author     : Umindu Haputhanthri
--%>

<%@page import="model_classes.Hall"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${movie.title} | SilverKnight Cinema</title>
        <link rel="stylesheet" href="assets/css/movies.css">
    </head>
    <body>
        <jsp:include page="header.jsp"/>

        <section class="movie-details">
            <div class="movie-poster">
                <img src="${movie.detailedPosterUrl != null ? movie.detailedPosterUrl : movie.posterUrl}" alt="${movie.title}">
                <div class="cover-poster">
                    <img src="${movie.posterUrl}">
                </div>
                <button class="watch-trailer trailer-btn" data-trailer="${movie.trailerUrl}">Watch Trailer</button>
            </div>

            <div class="movie-info">
                <h1>${movie.title}</h1>
                <p class="duration">${movie.durationString}</p>

<!--                <p class="release-date">
                    <c:if test="${movie.status == 'COMING_SOON'}">
                        <p class="release-date">Releasing on ${movie.releaseDate}</p>
                    </c:if>
                </p> -->
            </div>

            <c:if test="${movie.status == 'NOW_SHOWING'}">
                <div class="now-showing">
                    <h2>Now Showing At</h2>
                    <div class="cinema-list">
                        <c:forEach var="cinema" items="${cinemasOfMovie}">
                            <div class="cinema-card">
                                <span>${cinema.name}</span>
                                <p>${cinema.location}</p>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </c:if>

            <c:if test="${movie.status == 'NOW_SHOWING'}">
                <div id="ticket" class="showtimes-container">
                    <div class="filters">
                        <div>
                            <label for="cinema-filter">Location:</label>
                            <select id="cinema-filter">
                                <option value="any">All Locations</option>
                                <c:forEach var="cinema" items="${cinemaList}">
                                    <option value="${cinema.cinemaId}">${cinema.name} - ${cinema.location}</option>
                                </c:forEach>
                            </select>
                        </div>
                        
                        <div>
                            <label for="date-filter">Date:</label>
                            <select id="date-filter">
                                <%
                                    // java.text.SimpleDateFormat displayFormat  = new java.text.SimpleDateFormat("EEEE, MMMM d");
                                    java.text.SimpleDateFormat displayFormat = new java.text.SimpleDateFormat("E, d MMM");
                                    java.text.SimpleDateFormat valueFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
                                    java.util.Calendar calendar = java.util.Calendar.getInstance();

                                    for (int i = 0; i < 5; i++) {
                                        String formattedDate = displayFormat .format(calendar.getTime());
                                        String valueDate = valueFormat.format(calendar.getTime());
                                %>
                                        <option value="<%= valueDate %>"><%= formattedDate %></option>
                                <%
                                        calendar.add(java.util.Calendar.DATE, 1);
                                    }
                                %>
                            </select>
                        </div>
                            
                        <div>
                            <%
                                request.setAttribute("hallTypes", Hall.Type.values());
                            %>

                            <label for="experience-filter">Hall Type:</label>
                            <select id="experience-filter">
                                <option value="any">All Experiences</option>
                                <c:forEach var="hallType" items="${hallTypes}">
                                    <option value="${hallType.dbValue}">${hallType.dbValue}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
          
                    <div class="showtimes-list">
                        <c:forEach var="cinemaId" items="${organizedShowtimes.keySet()}" varStatus="status">
                            <c:set var="cinema" value="${cinemaMap[cinemaId]}" />
                            <div class="cinema">
                                <h2>${cinema.name} - ${cinema.location}</h2>

                                <c:forEach var="hallId" items="${organizedShowtimes[cinemaId].keySet()}">
                                    <c:set var="hall" value="${hallMap[hallId]}" />

                                    <c:forEach var="experience" items="${organizedShowtimes[cinemaId][hallId].keySet()}">
                                        <div class="cinema-type">
                                            <h3>${experience}</h3>
                                            <c:choose>
                                                <c:when test="${experience == 'IMAX'}">
                                                    <img src="assets/images/hall_logos/imax.png">
                                                </c:when>
                                                <c:when test="${experience == '2D'}">
                                                    <img src="assets/images/hall_logos/digital2d.png">
                                                </c:when>
                                                <c:when test="${experience == '3D'}">
                                                    <img src="assets/images/hall_logos/digital3d.png">
                                                </c:when>
                                                <c:when test="${experience == 'VIP'}">
                                                    <img src="assets/images/hall_logos/vip.png">
                                                </c:when>
                                            </c:choose>
                                        </div>

                                        <div class="showtime-buttons">
                                            <c:forEach var="showtime" items="${organizedShowtimes[cinemaId][hallId][experience]}">
                                                <button onclick="window.location.href = 'seatselection?showtimeId=${showtime.showtimeId}'">
                                                    ${showtime.formattedTime}
                                                </button>
                                            </c:forEach>
                                        </div>
                                    </c:forEach>
                                    
                                </c:forEach>
                            </div>

                            <c:if test="${!status.last}">
                                <div class="divider"></div>
                            </c:if>
                        </c:forEach>

                        <c:if test="${organizedShowtimes.isEmpty()}">
                            <p class="no-showtimes">No showtimes available for the selected filters.</p>
                        </c:if>  
                    </div>
                </div>
            </c:if>

            <div class="synopsis">
                <h2>Synopsis</h2>
                <p>${movie.synopsis}</p>
            </div>

            <hr class="divider">
            
            <div class="genre">
                <h2>Genre</h2>
                <div class="genre-list">
                    <c:forEach var="genre" items="${movieGenres}">
                        <span class="genre-item">${genre.name}</span>
                    </c:forEach>
                </div>
            </div>

            <hr class="divider">

            <div class="cast-team">
                <div class="cast">
                    <h3>Cast</h3>
                    <table>
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
                </div>

                <div class="separator"></div>

                <div class="team">
                    <h3>Team</h3>
                    <table>
                        <thead>
                            <tr>
                                <th>Role</th>
                                <th>Name</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="crew" items="${movie.crew}">
                                <tr>
                                    <td>${crew.role}</td>
                                    <td>${crew.name}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </section>

        <div class="trailer-popup" id="trailerPopup">
            <div class="popup-content">
                <span class="close-btn" id="closePopup">&times;</span>
                <iframe id="trailerVideo" allow="autoplay; encrypted-media" allowfullscreen></iframe>
            </div>
        </div>

        <jsp:include page="footer.jsp"/>
        
        <script src="assets/scripts/main.js"></script>
        <script>
            document.getElementById("cinema-filter").addEventListener('change', async () => {
                await fetchShowtimes();
            });

            document.getElementById("date-filter").addEventListener('change', async () => {
                await fetchShowtimes();
            });

            document.getElementById("experience-filter").addEventListener('change', async () => {
                await fetchShowtimes();
            });
            
            document.addEventListener('DOMContentLoaded', () => {
                playVideo();

                if (window.location.hash === '#ticket') {
                    setTimeout(function () {
                        const ticketSection = document.getElementById('ticket');
                        if (ticketSection) {
                            ticketSection.scrollIntoView({ behavior: 'smooth' });
                        }
                    }, 500);
                }
            });
        </script>
    </body>
</html>
