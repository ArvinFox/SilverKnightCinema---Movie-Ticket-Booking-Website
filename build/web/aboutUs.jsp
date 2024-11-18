<%-- 
    Document   : aboutUs
    Created on : Nov 18, 2024, 2:23:25 AM
    Author     : sanuj
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>About Us | SilverKnight Cinema</title>
        <link rel="stylesheet" href="assets/css/style.css">
    </head>
    
    <body>
         <jsp:include page="navigation.jsp"/>
         
         <section class="banner-about-us">
            <h1>About Us</h1>
            <p>
                 <a href="#" class="home-link">Home</a> / About Us
             </p>
        </section>

        <section class="about-section">
            <div class="row">
                <div class="content">
                    <h2>WHO WE ARE</h2>
                    <p>
                        Movies bring us together, and we're here to make sure you can enjoy them in a hassle-free environment whether it's the latest blockbuster or a hidden gem. We've dedicated ourselves to making your movie-going experience easy and fun.  
                        Over the years, we’ve built strong relationships with theaters enabling you to get the best deals. Our genuine intention and firm goal are to deliver to you a smooth booking process coupled with memorable moments. The four locations we’ve introduced for you offer an excellent experience nationwide.  

                    </p>
                </div>
                <div class="content">
                    <h2>WHAT WE DO</h2>
                    <p>
                        Since launching in 2013, we’ve been helping movie lovers find the perfect movies, showtimes, seats, and deals at theaters across the country. We've introduced state-of-the-art technologies equipped to meet audience demands, ensuring your experience is smooth and personalized.  
                        Whether you're searching for the latest blockbuster or an independent film, we’ve got you covered. Everything is designed to make it simple and easy to book your tickets in just a few clicks.
                    </p>
                </div>
            </div>
    
            <div class="row">
                <div class="content vision-content">
                    <h2>Our Vision</h2>
                    <p>
                        We want to be the top choice for movie lovers when it comes to booking tickets. Our vision is to provide a quick, reliable, and enjoyable way for you to book the movies you love.  
                        We aim to create a community of movie enthusiasts who can share their love of films with ease and accessibility. Our ultimate goal is to redefine the way you plan your movie experiences.
                    </p>
                </div>
                <div class="image vision-image">
                    <img src="assets/images/about-us-banner2.png" alt="Vision">
                </div>
            </div>
    
            <div class="row reverse">
                <div class="image mission-image">
                    <img src="assets/images/about-us-banner3.png" alt="Mission">
                </div>
                <div class="content mission-content">
                    <h2>Our Mission</h2>
                    <p>
                        <b>Our mission is simple:</b>
                    </p>
                    <ul>
                        <li>Smooth booking experience with an intuitive platform.</li>
                        <li>A wide range of movies to suit every taste and language.</li>
                        <li>Real-time updates on showtimes and available seating.</li>
                        <li>Continuous improvement of our platform for a better experience.</li>
                    </ul>
                </div>
            </div>
        </section>            
                  
         <jsp:include page="footer.jsp" />
    </body>
</html>
