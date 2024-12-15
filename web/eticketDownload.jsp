<%-- 
    Document   : eticketDownload
    Created on : Dec 10, 2024, 7:45:39 PM
    Author     : Udani Indrachapa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>E-Ticket Download | SilverKnight Cinema</title>
        <link rel="stylesheet" href="assets/css/style.css">
        <script src="https://kit.fontawesome.com/16735b712d.js" crossorigin="anonymous"></script>
    </head>
    <body class="notification">
        <div class="notification-container">
            
            <jsp:include page="eticket.jsp"/>
            <div class="confirmation-section">
                <div class="confirmation-message">
                    Your payment has been successfully completed.
                </div>
                <p class="download-message"> Click here to download your e-ticket </p>
                <button class="download-button" onclick="generatePDF()">
                    <i class="fa-solid fa-download"></i>
                    Download
                </button>
                
            </div>
        </div>
    </body>
</html>
