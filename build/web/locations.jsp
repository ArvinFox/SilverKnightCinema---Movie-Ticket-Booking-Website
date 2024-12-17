<%-- 
    Document   : locations
    Created on : Nov 20, 2024, 12:09:27 AM
    Author     : sanuji
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Locations | SilverKnight Cinema</title>
        <link rel="stylesheet" href="assets/css/style.css">
        <!-- Font Awesome for Icons -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    </head>
    
    <body>
        <jsp:include page="header.jsp"/>
        
        <div class="banner-locations">
            <h1 class="banner-title"> Locations </h1>
            <div class="home-link">
                <a href="home"> Home </a>
                <span> /</span>
                <span>Locations </span>
            </div>
        </div>

        <!-- Locations Section -->
         <div class="locations">
             <div class="locations-header">
                 <h2>Our Cinemas</h2>
                 <p>Find a cinema near you and enjoy a premium movie experience across our multiple locations. Book your seats and get ready for the show!</p>
             </div>
                 <div class="row-locations">
                     <div class="location-card">
                         <img src="assets/images/locations-img1.jpeg" alt="SilverKnight Cinema">
                         <h2>SilverKnight Cinema (Head Office)</h2>
                         <p><i class="fa fa-map-marker-alt"></i> 367 Hill-Crest Lane, Bambalapitiya</p>
                         <p><i class="fa fa-clock"></i> Mon-Sun, 10:00 AM - 11:00 PM</p>
                         <p><i class="fa fa-phone"></i> +94 11 554 3345<br></i> +94 11 478 8991</p>
                         <a href="#" class="view-location" onclick="showPopup(this, 'https://www.google.com/maps?q=Bambalapitiya&output=embed')">View Location</a>
                    </div>
                     <div class="location-card">
                         <img src="assets/images/locations-img2.jpeg" alt="CineHub Deluxe">
                         <h2>CineHub Deluxe - Kandy</h2>
                         <p><i class="fa fa-map-marker-alt"></i> 456 Temple Avenue, Kandy City Center, Kandy</p>
                         <p><i class="fa fa-clock"></i> Mon-Sun, 9:00 AM - 11:00 PM</p>
                         <p><i class="fa fa-phone"></i> +94 81 224 5679</p>
                         <a href="#" class="view-location" onclick="showPopup(this, 'https://www.google.com/maps?q=Kandy&output=embed')">View Location</a>
                     </div>
                     <div class="location-card">
                         <img src="assets/images/locations-img3.jpeg" alt="Movie World Cinema">
                         <h2> World Cinema - Galle</h2>
                         <p><i class="fa fa-map-marker-alt"></i> 789 Lighthouse Street, Galle Fort, Galle</p>
                         <p><i class="fa fa-clock"></i> Mon-Sun, 10:00 AM - 10:00 PM</p>
                         <p><i class="fa fa-phone"></i> +94 11 445 6578</p>
                         <a href="#" class="view-location" onclick="showPopup(this, 'https://www.google.com/maps?q=Galle&output=embed')">View Location</a>
                     </div>
                     <div class="location-card">
                         <img src="assets/images/locations-img4.jpeg" alt="Silver Screen Cinema">
                         <h2>Silver Screen Cinema - Colombo</h2>
                         <p><i class="fa fa-map-marker-alt"></i> 212 Melder Place, Colombo 03</p>
                         <p><i class="fa fa-clock"></i> Mon-Sun, 10:00 AM - 11:00 PM</p>
                         <p><i class="fa fa-phone"></i> +94 11 556 789</p>
                         <a href="#" class="view-location" onclick="showPopup(this, 'https://www.google.com/maps?q=Colombo+3&output=embed')">View Location</a>
                     </div>
                 </div>

                 <!-- Popup for map -->
                 <div id="popup-map" style="display: none;">
                     <div id="popup-content">
                         <iframe id="map-iframe" src="" frameborder="0"></iframe>
                         <button id="close-btn" onclick="closePopup()">Close</button>
                     </div>
                 </div>

                  <script src="assets/scripts/main.js"></script>            
         </div>

         <jsp:include page="footer.jsp" />
         <script src="assets/scripts/main.js"></script>
         
    </body>
</html>
