//For FAQ Popup Answers

document.querySelectorAll('.faq-question').forEach(question => {
    question.addEventListener('click', () => {
        const answer = question.nextElementSibling;
        const arrow = question.querySelector('.arrow');

        // Check if the answer is currently expanded
        if (answer.style.height && answer.style.height !== '0px') {
            // Collapse the answer
            answer.style.height = '0';
            answer.style.paddingTop = '0';
            answer.style.paddingBottom = '0';
            arrow.innerHTML = '&#9660;'; // Down arrow
        } else {
            // Expand the answer
            answer.style.height = `${answer.scrollHeight}px`;
            answer.style.paddingTop = '0';
            answer.style.paddingBottom = '0';
            arrow.innerHTML = '&#9650;'; // Up arrow
        }
    });
});

// Handle tab switching
const tabs = document.querySelectorAll('.tab-btn');
const contents = document.querySelectorAll('.faq-content');

tabs.forEach(tab => {
    tab.addEventListener('click', () => {
        // Remove active class from all tabs
        tabs.forEach(t => t.classList.remove('active'));
        // Hide all FAQ contents
        contents.forEach(content => content.classList.add('hidden'));

        // Add active class to clicked tab and show corresponding content
        tab.classList.add('active');
        const target = document.getElementById(tab.dataset.tab);
        target.classList.remove('hidden');
    });
});



//Movie Section Handling
let ongoing_movies_html = `
<!--container 1-->
            <a href="ongoingMovieDetails.jsp" class="box"> <!-- Link to a movie page -->
                <div class="box">
                    <div class="box-img">
                        <img src="assets/images/posters/m1.jpg" alt="">
                    </div>
                    <h3>Venom</h3>
                    <span>Now Screening</span>
                </div>
            </a>
    
            <!--container 2-->
            <a href="ongoingMovieDetails.jsp" class="box"> <!-- Link to a movie page -->
                <div class="box">
                    <div class="box-img">
                        <img src="assets/images/posters/m2.jpg" alt="">
                    </div>
                    <h3>Dunkrik</h3>
                    <span>Now Screening</span>
                </div>
            </a>

            <!--container 3-->
            <a href=ongoingMovieDetails.jsp" class="box"> <!-- Link to a movie page -->
                <div class="box">
                    <div class="box-img">
                        <img src="assets/images/posters/m3.jpg" alt="">
                    </div>
                    <h3>Batman</h3>
                    <span>Now Screening</span>
                </div>
            </a>

            <!--container 4-->
            <a href="ongoingMovieDetails.jsp" class="box"> <!-- Link to a movie page -->
                <div class="box">
                    <div class="box-img">
                        <img src="assets/images/posters/m4.jpg" alt="">
                    </div>
                    <h3>John Wick</h3>
                    <span>Now Screening</span>
                </div>
            </a>

            <!--container 5-->
            <a href="ongoingMovieDetails.jsp" class="box"> <!-- Link to a movie page -->
                <div class="box">
                    <div class="box-img">
                        <img src="assets/images/posters/m5.jpg" alt="">
                    </div>
                    <h3>Aquaman</h3>
                    <span>Now Screening</span>
                </div>
            </a>

            <!--container 6-->
                <a href="ongoingMovieDetails.jsp" class="box"> <!-- Link to a movie page -->
                    <div class="box">
                        <div class="box-img">
                            <img src="assets/images/posters/m1.jpg" alt="">
                        </div>
                        <h3>Venom</h3>
                        <span>Now Screening</span>
                    </div>
                </a>
    
                <!--container 7-->
                <a href="ongoingMovieDetails.jsp" class="box"> <!-- Link to a movie page -->
                    <div class="box">
                        <div class="box-img">
                            <img src="assets/images/posters/m2.jpg" alt="">
                        </div>
                        <h3>Dunkrik</h3>
                        <span>Now Screening</span>
                    </div>
                </a>

                <!--container 8-->
                <a href="ongoingMovieDetails.jsp" class="box"> <!-- Link to a movie page -->
                    <div class="box">
                        <div class="box-img">
                            <img src="assets/images/posters/m3.jpg" alt="">
                        </div>
                        <h3>Batman</h3>
                        <span>Now Screening</span>
                    </div>
                </a>

                <!--container 9-->
                <a href="ongoingMovieDetails.jsp" class="box"> <!-- Link to a movie page -->
                    <div class="box">
                        <div class="box-img">
                            <img src="assets/images/posters/m4.jpg" alt="">
                        </div>
                        <h3>John Wick</h3>
                        <span>Now Screening</span>
                    </div>
                </a>

                <!--container 10-->
                <a href="ongoingMovieDetails.jsp" class="box"> <!-- Link to a movie page -->
                    <div class="box">
                        <div class="box-img">
                            <img src="assets/images/posters/m5.jpg" alt="">
                        </div>
                        <h3>Aquaman</h3>
                        <span>Now Screening</span>
                    </div>
                </a>`;
let coming_soon_html=`<!--container 1-->
                    <a href="comingSoonMovieDetails.jsp" class="box"> <!-- Link to a movie page -->
                    <div class="box">
                        <div class="box-img">
                            <img src="assets/images/posters/m1.jpg" alt="">
                        </div>
                        <h3>Venom</h3>
                        <span>Date</span>
                    </div>
                </a>
    
                <!--container 2-->
                <a href="comingSoonMovieDetails.jsp" class="box"> <!-- Link to a movie page -->
                    <div class="box">
                        <div class="box-img">
                            <img src="assets/images/posters/m2.jpg" alt="">
                        </div>
                        <h3>Dunkrik</h3>
                        <span>Date</span>
                    </div>
                </a>

                <!--container 3-->
                <a href="comingSoonMovieDetails.jsp" class="box"> <!-- Link to a movie page -->
                    <div class="box">
                        <div class="box-img">
                            <img src="assets/images/posters/m3.jpg" alt="">
                        </div>
                        <h3>Batman</h3>
                        <span>Date</span>
                    </div>
                </a>

                <!--container 4-->
                <a href="comingSoonMovieDetails.jsp" class="box"> <!-- Link to a movie page -->
                    <div class="box">
                        <div class="box-img">
                            <img src="assets/images/posters/m4.jpg" alt="">
                        </div>
                        <h3>John Wick</h3>
                        <span>Date</span>
                    </div>
                </a>

                <!--container 5-->
                <a href="comingSoonMovieDetails.jsp" class="box"> <!-- Link to a movie page -->
                    <div class="box">
                        <div class="box-img">
                            <img src="assets/images/posters/m5.jpg" alt="">
                        </div>
                        <h3>Aquaman</h3>
                        <span>Date</span>
                    </div>
                </a>

                <!--container 6-->
                <a href="comingSoonMovieDetails.jsp" class="box"> <!-- Link to a movie page -->
                    <div class="box">
                        <div class="box-img">
                            <img src="assets/images/posters/m1.jpg" alt="">
                        </div>
                        <h3>Venom</h3>
                        <span>Date</span>
                    </div>
                </a>
    
                <!--container 7-->
                <a href="comingSoonMovieDetails.jsp" class="box"> <!-- Link to a movie page -->
                    <div class="box">
                        <div class="box-img">
                            <img src="assets/images/posters/m2.jpg" alt="">
                        </div>
                        <h3>Dunkrik</h3>
                        <span>Date</span>
                    </div>
                </a>

                <!--container 8-->
                <a href="comingSoonMovieDetails.jsp" class="box"> <!-- Link to a movie page -->
                    <div class="box">
                        <div class="box-img">
                            <img src="assets/images/posters/m3.jpg" alt="">
                        </div>
                        <h3>Batman</h3>
                        <span>Date</span>
                    </div>
                </a>

                <!--container 9-->
                <a href="comingSoonMovieDetails.jsp" class="box"> <!-- Link to a movie page -->
                    <div class="box">
                        <div class="box-img">
                            <img src="assets/images/posters/m4.jpg" alt="">
                        </div>
                        <h3>John Wick</h3>
                        <span>Date</span>
                    </div>
                </a>

                <!--container 10-->
                <a href="comingSoonMovieDetails.jsp" class="box"> <!-- Link to a movie page -->
                    <div class="box">
                        <div class="box-img">
                            <img src="assets/images/posters/m5.jpg" alt="">
                        </div>
                        <h3>Aquaman</h3>
                        <span>Date</span>
                    </div>
                </a>`;
document.querySelector('.ongoing').addEventListener('click',()=>{
document.querySelector('.movies-container').innerHTML = ongoing_movies_html;     
})
 
document.querySelector('.coming-soon').addEventListener('click',()=>{
    document.querySelector('.movies-container').innerHTML = coming_soon_html;     
    })
  
// Select all movie-type elements
const movieTypes = document.querySelectorAll('.movie-type');

// Add a click event listener to each movie-type
movieTypes.forEach(movie => {
    movie.addEventListener('click', () => {
        // Remove the 'selected' class from all movie-types
        movieTypes.forEach(type => type.classList.remove('selected'));
        
        // Add the 'selected' class to the clicked element
        movie.classList.add('selected');
    });
});