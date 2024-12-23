<%-- 
    Document   : addHall
    Created on : Dec 14, 2024, 8:37:43 PM
    Author     : Umindu Haputhanthri
--%>

<%@page import="dao_classes.CinemaDAO"%>
<%@page import="model_classes.Hall"%>
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
        <title>Add Hall - Silver Knight Cinema</title>
        <link rel="stylesheet" href="../assets/css/adminStyles.css">
    </head>
    <body>
        <div class="main-content sub-content">
            <h1>Add New Hall</h1>
            
            <form id="hallForm" action="manageHalls" method="POST">
                <input type="hidden" name="action" value="add">
                
                <div class="form-group">
                    <label for="name">Hall Name</label>
                    <input type="text" id="name" name="name" required>
                </div>

                <%
                    Hall.Type[] hallTypes = Hall.Type.values();
                    request.setAttribute("hallTypes", hallTypes);
                %>

                <div class="form-group">
                    <label for="hallType">Hall Type</label>
                    <select id="hallType" name="hallType" required>
                        <c:forEach var="hallType" items="${hallTypes}">
                            <option value="${hallType.dbValue}">${hallType.dbValue}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label for="capacity">Capacity</label>
                    <input type="number" id="capacity" name="capacity" step="1" min="0" required>
                </div>

                <%
                    CinemaDAO cinemaDAO = new CinemaDAO();
                    request.setAttribute("cinemas", cinemaDAO.getAllCinemas());
                %>
                
                <div class="form-group">
                    <label for="cinema">Cinema</label>
                    <select id="cinema" name="cinema" required>
                        <c:forEach var="cinema" items="${cinemas}">
                            <option value="${cinema.cinemaId}">${cinema.name} - ${cinema.location}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label for="posterUrl">Hall Poster</label>
                    <input type="file" id="poster" accept="image/*" required>
                    <input type="hidden" id="posterPath" name="posterUrl">
                </div>

                <button type="submit" class="action-btn add-btn-link font-16" onclick="uploadPoster('hall')">Add Hall</button>
            </form>
        </div>

        <script src="../assets/scripts/admin.js"></script>
    </body>
</html>
