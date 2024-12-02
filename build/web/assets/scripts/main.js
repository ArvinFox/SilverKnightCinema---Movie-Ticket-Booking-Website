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
});
 
document.querySelector('.coming-soon').addEventListener('click',()=>{
    document.querySelector('.movies-container').innerHTML = coming_soon_html;     
    });
  
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


//-------------------Profile Page JS---------------------//

//-----Loading contents to User Profile-----//
function loadContent(page) {
    // Use AJAX to load the content of the JSP into the user-content div tag
    fetch(page)
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.text();
        })
        .then(data => {
            document.getElementById('user-content').innerHTML = data;
            // Reinitialize JavaScript for the new content
            initializeEventListeners();
        })
        .catch(error => console.error('Error loading page:', error));
}

function initializeEventListeners() {
    const editContactButton = document.getElementById("changeContact");
    const sendOtpButton = document.getElementById("changeContactSendOTP");
    const resendOtpButton = document.getElementById("changeContactResendOTP");
    const saveContactButton = document.getElementById("saveNewContact");

    const newContactDiv = document.querySelector(".form-row.hidden:nth-child(2)");
    const otpVerificationDiv = document.querySelector(".form-row.hidden:nth-child(3)");
    const contactInput = document.getElementById("newContactNumber");
    const contactLabel = document.getElementById("dbContact");
    const otpCountdownLabel = otpVerificationDiv.querySelector("label[for='OTP countdown']");

    let countdownTimer = null;

    if (editContactButton) {
        editContactButton.addEventListener("click", () => {
            newContactDiv.classList.remove("hidden");
        });
    }

    if (sendOtpButton) {
        sendOtpButton.addEventListener("click", () => {
            const contactValue = contactInput.value;

            if (contactValue.length !== 10 || isNaN(contactValue)) {
                alert("Please enter a valid 10-digit contact number.");
                return;
            }

            newContactDiv.classList.add("hidden");
            otpVerificationDiv.classList.remove("hidden");

            resendOtpButton.disabled = true;
            startCountdown();
        });
    }

    if (resendOtpButton) {
        resendOtpButton.addEventListener("click", () => {
            resendOtpButton.disabled = true;
            startCountdown();
        });
    }

    if (saveContactButton) {
        saveContactButton.addEventListener("click", () => {
            const newContact = contactInput.value;

            if (newContact.length !== 10 || isNaN(newContact)) {
                alert("Please enter a valid 10-digit contact number.");
                return;
            }

            contactLabel.textContent = newContact;
            otpVerificationDiv.classList.add("hidden");

            alert("Contact number updated successfully!");
        });
    }

    // Function to start a 60-second countdown
    function startCountdown() {
        let secondsRemaining = 60;

        otpCountdownLabel.textContent = `Resend OTP again in ${secondsRemaining}s`;

        countdownTimer = setInterval(() => {
            secondsRemaining -= 1;
            otpCountdownLabel.textContent = `Resend OTP again in ${secondsRemaining}s`;

            if (secondsRemaining <= 0) {
                clearInterval(countdownTimer);
                otpCountdownLabel.textContent = "";
                resendOtpButton.disabled = false;
            }
        }, 1000);
    }
}

//For Location Popups
function showPopup(button, mapUrl) {
    // Get the parent card dimensions
    var card = button.parentElement;
    var cardWidth = card.offsetWidth;
    var cardHeight = card.offsetHeight;

    // Get the popup and iframe elements
    var popup = document.getElementById("popup-map");
    var iframe = document.getElementById("map-iframe");
    var popupContent = document.getElementById("popup-content");

    // Set iframe source and adjust popup size
    iframe.src = mapUrl;
    popupContent.style.width = cardWidth + "px";
    popupContent.style.height = cardHeight + "px";

    // Show the popup
    popup.style.display = "flex";
}

function closePopup() {
    var popup = document.getElementById("popup-map");
    var iframe = document.getElementById("map-iframe");

    // Hide the popup and reset the iframe
    popup.style.display = "none";
    iframe.src = "";
}