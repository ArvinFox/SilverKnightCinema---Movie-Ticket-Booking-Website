<%-- 
    Document   : addLanguage
    Created on : Dec 10, 2024, 6:59:05 PM
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
        <title>Add Language - Silver Knight Cinema</title>
        <link rel="stylesheet" href="../assets/css/adminStyles.css">
    </head>
    <body>
        <div class="main-content sub-content">
            <h1>Add New Language</h1>
            
            <form action="manageLanguages" method="POST">
                <input type="hidden" name="action" value="add">
                
                <div class="form-group">
                    <label for="language">Language</label>
                    <input type="text" id="language" name="language" required>
                </div>

                <button type="submit" class="action-btn add-btn-link font-16">Add Language</button>
            </form>
        </div>

        <script src="../assets/scripts/admin.js"></script>
    </body>
</html>
