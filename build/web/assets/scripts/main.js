
// ------------------ Footer ---------------------//
document.addEventListener("DOMContentLoaded", () => {
    adjustFooter();
});

function adjustFooter() {
    const body = document.body;
    const html = document.documentElement;
    const footer = document.querySelector("footer");

    const isScrollable = html.scrollHeight > html.clientHeight;

    if (!isScrollable) {
        footer.style.position = "fixed";
        footer.style.bottom = "0";
    } else {
        footer.style.position = "static";
    }
}

// ------------ Movies Section ------------------ //
const ongoingMoviesLink = document.querySelector(".ongoing");
const comingSoonMoviesLink = document.querySelector(".coming-soon");

if (ongoingMoviesLink) {
    ongoingMoviesLink.addEventListener('click', async () => {
        ongoingMoviesLink.classList.add("selected");
        comingSoonMoviesLink.classList.remove("selected");

        const movies = await fetchMoviesByType('nowShowing');
        updateMoviesSection(movies);
    });
}

if (comingSoonMoviesLink) {
    comingSoonMoviesLink.addEventListener('click', async () => {
        ongoingMoviesLink.classList.remove("selected");
        comingSoonMoviesLink.classList.add("selected");

        const movies = await fetchMoviesByType('comingSoon');
        updateMoviesSection(movies);
    });
}

async function fetchMoviesByType(type) {
    try {
        const response = await fetch("movies?type=" + type);
        if (!response.ok)
            throw new Error("Error in fetching movies");

        const data = await response.json();
        return data;

    } catch (error) {
        console.error("Error in fetching movies: ", error);
        return "No movies available";
    }
}

function updateMoviesSection(data) {
    const footer = document.querySelector("footer");
    footer.style.position = "static";

    if (data && data.length > 0) {
        let html = "";
        data.forEach(movie => {
            html += `
                <div class="movie">
                    <a href="movies?movieId=${movie.movieId}">
                        <img src="${movie.posterUrl}" alt="${movie.title}">
                        <div class="movie-content">
                            <h4>${movie.title}</h4>
                            <p>${movie.status === "NOW_SHOWING" ? "NOW SCREENING" : movie.releaseDate}</p>
                        </div>
                        <div class="movie-overlay">
                            <div class="overlay-content">
                                <button class="movie-action trailer-btn" data-trailer="${movie.trailerUrl}">Watch Trailer</button>
                                ${movie.status === "NOW_SHOWING"
                    ? `<button class="movie-action buy-ticket-btn" data-movie-id="${movie.movieId}">Buy Ticket</button>`
                    : ""}
                                <button class="movie-action">More Info</button>
                            </div>
                        </div>
                    </a>
                </div>
            `;
        });
        document.querySelector(".movie-list").innerHTML = html;
        initializeTrailerAndTicketButtons();
        playVideo();

    } else {
        document.querySelector(".movie-list").innerHTML = "<p>No movies available for the selected filters.</p>";
        document.querySelector(".movie-list").style.display = "flex";
        footer.style.position = "fixed";
        footer.style.bottom = "0";
    }
}

async function fetchFilteredMovies() {
    let languageId = document.getElementById('language-select').value;
    let genreId = document.getElementById('genre-select').value;
    const title = document.getElementById('search-bar').value;

    if (languageId.trim() === "any") {
        languageId = -1;
    }

    if (genreId.trim() === "any") {
        genreId = -1;
    }

    let movieType;
    const nowShowingLink = document.querySelector('.ongoing');
    if (nowShowingLink.classList.contains("selected")) {
        movieType = "Now Showing";
    } else {
        movieType = "Coming Soon";
    }

    try {
        const response = await fetch(`movies?filter=true&languageId=${languageId}&genreId=${genreId}&title=${title}&status=${movieType}`);
        if (!response.ok)
            throw new Error("Error in fetching filtered movies");

        const data = await response.json();
        return data;

    } catch (error) {
        console.error("Error in fetching filtered movies:" + error);
        return [];
    }
}

function playVideo() {
    const trailerPopup = document.getElementById("trailerPopup");
    const trailerVideo = document.getElementById("trailerVideo");
    const closePopup = document.getElementById("closePopup");

    document.querySelectorAll(".trailer-btn[data-trailer]").forEach(button => {
        button.addEventListener("click", function () {
            const trailerUrl = button.getAttribute("data-trailer");

            trailerVideo.src = trailerUrl;

            trailerPopup.classList.add("visible");
            document.body.style.overflow = 'hidden';
        });
    });

    const closeTrailerPopup = () => {
        trailerPopup.classList.remove("visible");
        trailerVideo.src = "";
        document.body.style.overflow = '';
    };

    closePopup.addEventListener("click", closeTrailerPopup);

    trailerPopup.addEventListener("click", function (e) {
        if (e.target === trailerPopup) {
            closeTrailerPopup();
        }
    });
}

function initializeTrailerAndTicketButtons() {
    const movieCards = document.querySelectorAll('.movie');
    movieCards.forEach(card => {
        card.addEventListener('click', (event) => {
            const trailerBtn = event.target.closest('.trailer-btn');
            const buyTicketBtn = event.target.closest('.buy-ticket-btn');

            if (trailerBtn) {
                event.preventDefault();


            } else if (buyTicketBtn) {
                event.preventDefault();

                const movieId = buyTicketBtn.getAttribute("data-movie-id");
                window.location.href = 'movies?movieId=' + movieId + '#ticket';
            }
        });
    });
}

async function fetchShowtimes() {
    let cinemaId = document.getElementById('cinema-filter').value;
    const date = document.getElementById('date-filter').value;
    const experience = document.getElementById('experience-filter').value;

    if (cinemaId.trim() === "any") {
        cinemaId = -1;
    }

    const urlParameters = new URLSearchParams(window.location.search);
    const movieId = urlParameters.get("movieId");

    try {
        const response = await fetch(`movies?showtime=true&movieId=${movieId}&cinemaId=${cinemaId}&date=${date}&experience=${experience}`);
        if (!response.ok) throw new Error("Error in fetching filtered showtimes");

        const data = await response.json();
        const { showtimes, cinemas } = data;

        updateShowtimes(showtimes, cinemas);

    } catch (error) {
        console.error("Error in fetching filtered showtimes: " + error);
    }
}

function updateShowtimes(showtimes, cinemas) {
    const showtimesContainer = document.querySelector(".showtimes-list");

    if (Object.keys(showtimes).length === 0) {
        showtimesContainer.innerHTML = `<p class="no-showtimes">No showtimes available for the selected filters.</p>`;
        return;
    }

    let html = "";

    const cinemaIds = Object.keys(showtimes);

    cinemaIds.forEach((cinemaId, index) => {
        const cinema = cinemas[cinemaId];
        html += `<div class="cinema">
                    <h2>${cinema.name} - ${cinema.location}</h2>`;

        const cinemaHalls = showtimes[cinemaId];

        for (const [hallId, hallExperiences] of Object.entries(cinemaHalls)) {
            for (const [experience, showtimesList] of Object.entries(hallExperiences)) {
                html += `<div class="cinema-type">
                            <h3>${experience}</h3>`;
                
                let experienceImageElement = '';
                switch (experience) {
                    case "IMAX":
                        experienceImageElement = '<img src="assets/images/hall_logos/imax.png">';
                        break;
                    case "2D":
                        experienceImageElement = '<img src="assets/images/hall_logos/digital2d.png">';
                        break;
                    case "3D":
                        experienceImageElement = '<img src="assets/images/hall_logos/digital2d.png">';
                        break;
                    case "VIP":
                        experienceImageElement = '<img src="assets/images/hall_logos/vip.png">';
                        break;
                }
                html += experienceImageElement;
                html += `</div>`;

                html += `<div class="showtime-buttons">`;
                const urlParameters = new URLSearchParams(window.location.search);
                const movieId = urlParameters.get("movieId");

                showtimesList.forEach(showtime => {
                    const currentTime = new Date();

                    const dateParts = showtime.showDate.replace(',', '').split(' ');
                    const monthNames = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
                    const monthIndex = monthNames.indexOf(dateParts[0]);
                    const formattedDate = `${dateParts[2]}-${(monthIndex + 1).toString().padStart(2, '0')}-${dateParts[1].padStart(2, '0')}`;

                    const timeParts = showtime.showTime.split(' ');
                    let [hours, minutes, seconds] = timeParts[0].split(':');
                    const ampm = timeParts[1].toUpperCase();
                    hours = (ampm === "AM" && hours === "12") ? "00" : (ampm === "PM" && hours !== "12") ? (parseInt(hours, 10) + 12).toString() : hours;
                    const formattedTime = `${hours}:${minutes}:${seconds}`;

                    const showtimeDate = new Date(formattedDate + 'T' + formattedTime);

                    const isPast = showtimeDate < currentTime;

                    html += `<button 
                                class="${isPast ? 'showtime-disabled' : ''}" 
                                ${isPast ? 'disabled' : ''}
                                onclick="window.location.href = 'seats?showtimeId=${showtime.showtimeId}&movieId=${movieId}'">
                                ${showtime.formattedTime}
                            </button>`;
                });
                html += `</div>`;
            }
        }
        html += `</div>`;

        if (index < cinemaIds.length - 1) {
            html += `<div class="divider"></div>`;
        }
    });

    showtimesContainer.innerHTML = html;
}


//For FAQ Popup Answers
const faqQuestions = document.querySelectorAll('.faq-question');
if (faqQuestions) {
    faqQuestions.forEach(question => {
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
}

// Handle tab switching
const tabs = document.querySelectorAll('.tab-btn');
const contents = document.querySelectorAll('.faq-content');

if (tabs) {
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
}


//-------------------Profile Page JS---------------------//

//-----Loading contents to User Profile-----//
async function loadContent(page) {
    try {
        const response = await fetch(page);
        if (!response.ok) throw new Error("Failed to fetch user content");

        const data = await response.text();
        document.getElementById('user-content').innerHTML = data;
        initializeEventListeners();

    } catch (error) {
        console.error("Error: " + error);
    }
}

let OtpResult;

async function sendAndVerifyOTP(action) {
    const newContact = document.getElementById("newContactNumber").value.trim();
    const enteredOtp = document.getElementById("enteredOtp").value.trim();

    try {
        let response;

        if (action === "send") {
            response = await fetch("otp?action=" + action + "&contact=" + newContact);
        } else {
            response = await fetch("otp?action=" + action + "&enteredOtp=" + enteredOtp);
        }

        const result = await response.json();
        OtpResult = await result.success;
        if (result.success) {
            console.log("otp verified");
        } else {
            console.log("Invalid OTP");
        }
    }
    catch (error) {
        console.error(error);
    }
}

function getOtpResult(result) {
    return result;
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
    let otpCountdownLabel;
    
    if (otpVerificationDiv) {
        otpCountdownLabel = otpVerificationDiv.querySelector("label[for='OTP countdown']");
    }

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
            sendAndVerifyOTP("send");
            startCountdown();
        });
    }

    if (resendOtpButton) {
        resendOtpButton.addEventListener("click", () => {
            resendOtpButton.disabled = true;
            sendAndVerifyOTP("send");
            startCountdown();
        });
    }

    if (saveContactButton) {
        saveContactButton.addEventListener("click", async () => {
            const newContact = contactInput.value;

            if (newContact.length !== 10 || isNaN(newContact)) {
                alert("Please enter a valid 10-digit contact number.");
                return;
            }

            await sendAndVerifyOTP("verify");
            if (getOtpResult(OtpResult)) {
                contactLabel.textContent = newContact;
                otpVerificationDiv.classList.add("hidden");
                alert("Contact number updated successfully!");
            }
            else {
                alert("Invalid OTP");
            }
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


// ------------- For Location Popups --------------//
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


//----------Timer for seats page-----------//
let timerDuration = 5 * 60;
const timerElement = document.getElementById('timer');
const timerInterval = setInterval(updateTimer, 1000);

function updateTimer() {
    let minutes = Math.floor(timerDuration / 60);
    let seconds = timerDuration % 60;

    minutes = minutes < 10 ? '0' + minutes : minutes;
    seconds = seconds < 10 ? '0' + seconds : seconds;

    if (timerElement) {
        timerElement.textContent = `${minutes}:${seconds}`;
    }
    if (timerDuration <= 0) {
        clearInterval(timerInterval);
        location.reload();
    } else {
        timerDuration--;
    }
}

let selectedSeatsArray = [];
const seats = document.querySelectorAll('.seat');

let selectedSeats = 0;
let adultCount = 0;
let childCount = 0;
let total = 0;

const ADULT_TICKET_PRICE = 1500;
const CHILD_TICKET_PRICE = 1000;

seats.forEach(seat => {
    seat.addEventListener('click', () => {
        if (seat.classList.contains('available')) {
            const seatId = seat.getAttribute('data-seat-id');

            if (!seat.classList.contains('selected')) {
                seat.classList.add('selected');
                selectedSeats++;
                adultCount++;
                total += ADULT_TICKET_PRICE;
                selectedSeatsArray.push(seatId);
            } else {
                seat.classList.remove('selected');
                selectedSeats--;
                total -= 1000;
                if (adultCount > 0) {
                    adultCount--;
                    total -= ADULT_TICKET_PRICE;
                } else if (childCount > 0) {
                    childCount--;
                    total -= CHILD_TICKET_PRICE;
                }
                selectedSeatsArray = selectedSeatsArray.filter(id => id !== seatId);
            }
            updateTicketCount();
            updateIncrementDecrementStates();
        }
    });
});

async function submitSeats() {
    try {
        const response = await fetch('seats', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                seats: selectedSeatsArray,
                total: total
            }),
        });

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

    } catch (error) {
        console.error("Error:", error);
    }
}

//----------Ticket selection for seats page-----------//
const continueBtn = document.querySelector('.btn-continue-checkout');
if (continueBtn) {
    continueBtn.addEventListener('click', async () => {
        submitSeats();
        await fetchUserStatus();
    });
}

async function fetchUserStatus() {
    try {
        const response = await fetch("login?checkIsLoggedIn=true");
        if (!response.ok) throw new Error("Failed to fetch user status");

        const result = await response.json();
        if (result.success) {
            const urlParameters = new URLSearchParams(window.location.search);
            const showtimeId = urlParameters.get("showtimeId");

            window.location.href = "checkout?showtimeId=" + showtimeId;
        } else {
            alert("You need to be logged in to book seats.");
            return;
        }

    } catch (error) {
        console.error("Error: " + error);
    }
}

const adultDecrementBtn = document.getElementById('adult-decrement');
const adultIncrementBtn = document.getElementById('adult-increment');
const childDecrementBtn = document.getElementById('child-decrement');
const childIncrementBtn = document.getElementById('child-increment');
const adultCountDisplay = document.getElementById('adult-count');
const childCountDisplay = document.getElementById('child-count');
const totalPrice = document.getElementById('ticketPrice');

function updateTicketCount() {
    adultCountDisplay.textContent = adultCount;
    childCountDisplay.textContent = childCount;
    total = (adultCount * ADULT_TICKET_PRICE) + (childCount * CHILD_TICKET_PRICE)
    if (totalPrice) {
        totalPrice.textContent = total;
    }
}

function updateIncrementDecrementStates() {
    const totalTickets = adultCount + childCount;

    adultIncrementBtn.disabled = !(totalTickets >= selectedSeats) || childCount <= 0;
    childIncrementBtn.disabled = !(totalTickets >= selectedSeats) || adultCount <= 0;

    adultDecrementBtn.disabled = adultCount === 0;
    childDecrementBtn.disabled = childCount === 0;

    if (totalTickets > 0) {
        continueBtn.disabled = false;
        continueBtn.classList.add('continue-active');
    }
    else {
        continueBtn.disabled = true;
        continueBtn.classList.remove('continue-active');
    }
}

if (adultIncrementBtn) {
    adultIncrementBtn.addEventListener('click', () => {
        adultCount++;
        childCount--;
        updateTicketCount();
        updateIncrementDecrementStates();
    });
}

if (adultDecrementBtn) {
    adultDecrementBtn.addEventListener('click', () => {
        adultCount--;
        childCount++;
        updateTicketCount();
        updateIncrementDecrementStates();
    });
}

if (childIncrementBtn) {
    childIncrementBtn.addEventListener('click', () => {
        childCount++;
        adultCount--;
        updateTicketCount();
        updateIncrementDecrementStates();
    });
}

if (childDecrementBtn) {
    childDecrementBtn.addEventListener('click', () => {
        childCount--;
        adultCount++;
        updateTicketCount();
        updateIncrementDecrementStates();
    });
}