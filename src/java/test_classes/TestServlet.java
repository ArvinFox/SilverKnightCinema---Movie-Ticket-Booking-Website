package test_classes;

// import utility_classes.DBConnection;
// import utility_classes.SMSUtil;
// import utility_classes.EmailUtil;

// import model_classes.User;
// import dao_classes.UserDAO;
// import model_classes.Guest;
// import dao_classes.GuestDAO;
// import model_classes.Genre;
// import dao_classes.GenreDAO;
// import model_classes.Language;
// import dao_classes.LanguageDAO;
// import model_classes.Movie;
// import dao_classes.MovieDAO;
// import model_classes.Promotion;
// import dao_classes.PromotionDAO;
// import model_classes.Hall;
// import dao_classes.HallDAO;
// import model_classes.Showtime;
// import dao_classes.ShowtimeDAO;
// import model_classes.Seat;
// import dao_classes.SeatDAO;
// import model_classes.Booking;
// import dao_classes.BookingDAO;
// import model_classes.Food;
// import model_classes.FoodOrder;
// import dao_classes.FoodDAO;
// import model_classes.Payment;
// import dao_classes.PaymentDAO;
// import model_classes.Admin;
// import dao_classes.AdminDAO;

// import java.sql.Date;
// import java.util.List;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "TestServlet", urlPatterns = {"/TestServlet", "/test"})
public class TestServlet extends HttpServlet {
    // private UserDAO userDAO;
    // private GuestDAO guestDAO;
    // private GenreDAO genreDAO;
    // private LanguageDAO languageDAO;
    // private MovieDAO movieDAO;
    // private PromotionDAO promotionDAO;
    // private HallDAO hallDAO;
    // private ShowtimeDAO showtimeDAO;
    // private SeatDAO seatDAO;
    // private BookingDAO bookingDAO;
    // private FoodDAO foodDAO;
    // private PaymentDAO paymentDAO;
    // private AdminDAO adminDAO;
    
    @Override
    public void init() {
        // userDAO = new UserDAO();
        // guestDAO = new GuestDAO();
        // genreDAO = new GenreDAO();
        // languageDAO = new LanguageDAO();
        // movieDAO = new MovieDAO();
        // promotionDAO = new PromotionDAO();
        // hallDAO = new HallDAO();
        // showtimeDAO = new ShowtimeDAO();
        // seatDAO = new SeatDAO();
        // bookingDAO = new BookingDAO();
        // foodDAO = new FoodDAO();
        // paymentDAO = new PaymentDAO();
        // adminDAO = new AdminDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.getWriter().println("Testing...\n");
        
        /* --------------- TESTING UTILITY CLASSES ---------------
        
            // Testing Database Connections
            // Connection connection = DBConnection.getConnection();    // Success
            // DBConnection.closeConnection(connection);    // Success

            // Testing SMS Functionality
            //SMSUtil.sendSMS("phone number", "Testing the SMS functionality.");    // Success

            // Testing Email Functionality
            // EmailUtil.sendEmail("recipient email", "Testing Purposes", "Testing the Email functionality.");  // Success
        
        */
        
        // -------------------------------------------------------------------------------------------
        
        /* --------------- TESTING USER DAO METHODS ---------------
        
            try {
                // Sample User Data
                User testUser = new User();
                testUser.setFirstName("Test");
                testUser.setLastName("User");
                testUser.setEmail("test.user@example.com");
                testUser.setPassword("password123");
                testUser.setContactNumber("1234567890");

                // Register User
                response.getWriter().println("Testing registerUser...");
                userDAO.registerUser(testUser);
                response.getWriter().println("User registered successfully.\n");

                // Login User
                response.getWriter().println("Testing loginUser...");
                User loggedInUser = userDAO.loginUser("test.user@example.com", "newpassword123");
                response.getWriter().println("Logged in user: " + (loggedInUser != null ? loggedInUser.getFirstName() : "null") + "\n");

                // Check if Email is Registered
                response.getWriter().println("Testing isEmailRegistered...");
                boolean emailRegistered = userDAO.isEmailRegistered("test.user@example.com");
                response.getWriter().println("Is email registered: " + emailRegistered + "\n");

                // Check if Contact Number is Registered
                response.getWriter().println("Testing isContactNumberRegistered...");
                boolean contactRegistered = userDAO.isContactNumberRegistered("1234567890");
                response.getWriter().println("Is contact number registered: " + contactRegistered + "\n");

                // Check if Account is Suspended
                response.getWriter().println("Testing isAccountSuspended...");
                boolean isSuspended = userDAO.isAccountSuspended(loggedInUser.getUserId());
                response.getWriter().println("Is account suspended: " + isSuspended + "\n");

                // Get User by ID
                response.getWriter().println("Testing getUserById...");
                User fetchedUserById = userDAO.getUserById(loggedInUser.getUserId());
                response.getWriter().println("Fetched user by ID: " + (fetchedUserById != null ? fetchedUserById.getFirstName() : "null") + "\n");

                // Get User by Email
                response.getWriter().println("Testing getUserByEmail...");
                User fetchedUserByEmail = userDAO.getUserByEmail("test.user@example.com");
                response.getWriter().println("Fetched user by email: " + (fetchedUserByEmail != null ? fetchedUserByEmail.getFirstName() : "null") + "\n");

                // Reset Password
                response.getWriter().println("Testing resetPassword...");
                userDAO.resetPassword("test.user@example.com", "newpassword123");
                response.getWriter().println("Password reset successfully.\n");

                // Update User Details
                response.getWriter().println("Testing updateUser...");
                loggedInUser.setLastName("Smith");
                loggedInUser.setAccountStatus(User.AccountStatus.SUSPENDED);
                userDAO.updateUser(loggedInUser);
                response.getWriter().println("User updated successfully.\n");

                // Update Contact Number
                response.getWriter().println("Testing updateContactNumber...");
                userDAO.updateContactNumber("9999999999", loggedInUser.getUserId());
                response.getWriter().println("Contact number updated successfully.\n");

                // Delete User
                response.getWriter().println("Testing deleteUser...");
                userDAO.deleteUser(loggedInUser.getUserId());
                response.getWriter().println("User deleted successfully.\n");

                // Adding More Users
                User user1 = new User("First", "User", "user1@example.com", "password1", "1111111111");
                User user2 = new User("Second", "User", "user2@example.com", "password2", "2222222222");
                User user3 = new User("Third", "User", "user3@example.com", "password3", "3333333333");
                userDAO.registerUser(user1);
                userDAO.registerUser(user2);
                userDAO.registerUser(user3);

                // Get All Users
                response.getWriter().println("Testing getAllUsers...");
                List<User> allUsers = userDAO.getAllUsers();
                response.getWriter().println("Total users: " + allUsers.size());
                for (User user : allUsers) {
                    response.getWriter().println(" - " + user.getFirstName() + user.getLastName());
                }
                response.getWriter().println();

                // Get Total Users
                response.getWriter().println("Testing getTotalUsers...");
                int totalUsers = userDAO.getTotalUsers();
                response.getWriter().println("Total users count: " + totalUsers + "\n");

                // Get Total Active Users
                response.getWriter().println("Testing getTotalActiveUsers...");
                int activeUsers = userDAO.getTotalActiveUsers();
                response.getWriter().println("Total active users count: " + activeUsers + "\n");

            } catch (Exception e) {
                response.getWriter().println("An error occurred: " + e.getMessage());
                e.printStackTrace(response.getWriter());
            }
        */
        
        // -------------------------------------------------------------------------------------------
        
        /* --------------- TESTING GUEST DAO METHODS ---------------
        
            try {
                // Sample Guest Data
                Guest testGuest = new Guest();
                testGuest.setName("Test Guest");
                testGuest.setEmail("test.guest@example.com");
                testGuest.setContactNumber("9876543210");
                
                // Add Guest
                response.getWriter().println("Testing addGuest...");
                guestDAO.addGuest(testGuest);
                response.getWriter().println("Guest added successfully.\n");
                
                // Get Guest by Email
                response.getWriter().println("Testing getGuestByEmail...");
                Guest fetchedGuestByEmail = guestDAO.getGuestByEmail("test.guest@example.com");
                response.getWriter().println("Fetched guest by email: " + 
                        (fetchedGuestByEmail != null ? fetchedGuestByEmail.getName() : "null") + "\n");
                
                // Get Guest by Contact Number
                response.getWriter().println("Testing getGuestByContactNumber...");
                Guest fetchedGuestByContact = guestDAO.getGuestByContactNumber("9876543210");
                response.getWriter().println("Fetched guest by contact number: " +
                        (fetchedGuestByContact != null ? fetchedGuestByContact.getName() : "null") + "\n");
                
                // Get Guest by ID
                response.getWriter().println("Testing getGuestById...");
                Guest fetchedGuestById = guestDAO.getGuestById(fetchedGuestByEmail.getGuestId());
                response.getWriter().println("Fetched guest by ID: " +
                        (fetchedGuestById != null ? fetchedGuestById.getName() : "null") + "\n");
                
                // Delete Guest
                response.getWriter().println("Testing deleteGuest...");
                guestDAO.deleteGuest(fetchedGuestByEmail.getGuestId());
                response.getWriter().println("Guest deleted successfully.\n");
                
                // Adding More Guests
                Guest guest1 = new Guest("Guest 1", "guest1@example.com", "1111111111");
                Guest guest2 = new Guest("Guest 2", "guest2@example.com", "2222222222");
                Guest guest3 = new Guest("Guest 3", "guest3@example.com", "3333333333");
                guestDAO.addGuest(guest1);
                guestDAO.addGuest(guest2);
                guestDAO.addGuest(guest3);
                
                // Get All Guests
                response.getWriter().println("Testing getAllGuests...");
                List<Guest> allGuests = guestDAO.getAllGuests();
                response.getWriter().println("Total guests: " + allGuests.size());
                for (Guest guest : allGuests) {
                    response.getWriter().println(" - " + guest.getName());
                }
                response.getWriter().println();
                
                // Get Total Guests
                response.getWriter().println("Testing getTotalGuests...");
                int totalGuests = guestDAO.getTotalGuests();
                response.getWriter().println("Total guests count: " + totalGuests + "\n");

            } catch (Exception e) {
                response.getWriter().println("An error occurred: " + e.getMessage());
                e.printStackTrace(response.getWriter());
            }
        */
        
        // -------------------------------------------------------------------------------------------
        
        /* --------------- TESTING GENRE DAO METHODS ---------------
        
            try {
                // Sample Genre Data
                Genre testGenre = new Genre();
                testGenre.setName("Test Genre");
                
                // Add Genre
                response.getWriter().println("Testing addGenre...");
                genreDAO.addGenre(testGenre);
                response.getWriter().println("Genre added successfully.\n");
                
                // Check if Genre is Added
                response.getWriter().println("Testing isGenreAdded...");
                boolean genreAdded = genreDAO.isGenreAdded("Test Genre");
                response.getWriter().println("Is genre added: " + genreAdded + "\n");
                
                // Get Genre by Name
                response.getWriter().println("Testing getGenreByName...");
                Genre fetchedGenreByName = genreDAO.getGenreByName("Test Genre");
                response.getWriter().println("Fetched genre by name: " + (fetchedGenreByName != null ? fetchedGenreByName.getName() : "null") + "\n");
                
                // Get Genres by IDs
                response.getWriter().println("Testing getGenresByIds...");
                int[] genreIds = {1, 2, 3, 4, 5};
                List<Genre> genresByIds = genreDAO.getGenresByIds(genreIds);
                response.getWriter().println("Genres fetched by IDs: " + genresByIds.size());
                for (Genre genre : genresByIds) {
                    response.getWriter().println(" - " + genre.getName());
                }
                response.getWriter().println();
                
                // Get Genre by ID
                response.getWriter().println("Testing getGenreById...");
                Genre fetchedGenreById = genreDAO.getGenreById(fetchedGenreByName.getGenreId());
                response.getWriter().println("Fetched genre by ID: " + (fetchedGenreById != null ? fetchedGenreById.getName() : "null") + "\n");
                
                // Update Genre
                response.getWriter().println("Testing updateGenre...");
                fetchedGenreByName.setName("Updated Genre");
                genreDAO.updateGenre(fetchedGenreByName);
                response.getWriter().println("Genre updated successfully.\n");
                
                // Delete Genre
                response.getWriter().println("Testing deleteGenre...");
                genreDAO.deleteGenre(fetchedGenreByName.getGenreId());
                response.getWriter().println("Genre deleted successfully.\n");
                
                // Adding More Genres
                Genre genre1 = new Genre("Genre 1");
                Genre genre2 = new Genre("Genre 2");
                Genre genre3 = new Genre("Genre 3");
                genreDAO.addGenre(genre1);
                genreDAO.addGenre(genre2);
                genreDAO.addGenre(genre3);
                
                // Get All Genres
                response.getWriter().println("Testing getAllGenres...");
                List<Genre> allGenres = genreDAO.getAllGenres();
                response.getWriter().println("Total genres: " + allGenres.size());
                for (Genre genre : allGenres) {
                    response.getWriter().println(" - " + genre.getName());
                }
                response.getWriter().println();
                
                // Get Total Genres
                response.getWriter().println("Testing getTotalGenres...");
                int totalGenres = genreDAO.getTotalGenres();
                response.getWriter().println("Total genres count: " + totalGenres + "\n");

            } catch (Exception e) {
                response.getWriter().println("An error occurred: " + e.getMessage());
                e.printStackTrace(response.getWriter());
            }
        */
        
        // -------------------------------------------------------------------------------------------
        
        /* --------------- TESTING LANGUAGE DAO METHODS ---------------
        
            try {
                // Sample Language Data
                Language testLanguage = new Language();
                testLanguage.setLanguage("Test Language");
                
                // Add Language
                response.getWriter().println("Testing addLanguage...");
                languageDAO.addLanguage(testLanguage);
                response.getWriter().println("Language added successfully.\n");
                
                // Check if Language is Added
                response.getWriter().println("Testing isLanguageAdded...");
                boolean languageAdded = languageDAO.isLanguageAdded("Test Language");
                response.getWriter().println("Is language added: " + languageAdded + "\n");
                
                // Get Language by Name
                response.getWriter().println("Testing getLanguageByName...");
                Language fetchedLanguageByName = languageDAO.getLanguageByName("Test Language");
                response.getWriter().println("Fetched language by name: " + (fetchedLanguageByName != null ? fetchedLanguageByName.getLanguage() : "null") + "\n");
                
                // Get Language by ID
            response.getWriter().println("Testing getLanguageById...");
            Language fetchedLanguageById = languageDAO.getLanguageById(fetchedLanguageByName.getLanguageId());
            response.getWriter().println("Fetched language by ID: " + (fetchedLanguageById != null ? fetchedLanguageById.getLanguage() : "null") + "\n");
            
                // Update Language
                response.getWriter().println("Testing updateLanguage...");
                fetchedLanguageByName.setLanguage("Updated Language");
                languageDAO.updateLanguage(fetchedLanguageByName);
                response.getWriter().println("Language updated successfully.\n");
                
                // Delete Language
                response.getWriter().println("Testing deleteLanguage...");
                languageDAO.deleteLanguage(fetchedLanguageByName.getLanguageId());
                response.getWriter().println("Language deleted successfully.\n");
                
                // Adding More Languages
                Language language1 = new Language("Language 1");
                Language language2 = new Language("Language 2");
                Language language3 = new Language("Language 3");
                languageDAO.addLanguage(language1);
                languageDAO.addLanguage(language2);
                languageDAO.addLanguage(language3);
                
                // Get All Languages
                response.getWriter().println("Testing getAllLanguages...");
                List<Language> allLanguages = languageDAO.getAllLanguages();
                response.getWriter().println("Total languages: " + allLanguages.size());
                for (Language language : allLanguages) {
                    response.getWriter().println(" - " + language.getLanguage());
                }
                response.getWriter().println();
                
                // Get Total Languages
                response.getWriter().println("Testing getTotalLanguages...");
                int totalLanguages = languageDAO.getTotalLanguages();
                response.getWriter().println("Total languages count: " + totalLanguages + "\n");
            
            } catch (Exception e) {
                response.getWriter().println("An error occurred: " + e.getMessage());
                e.printStackTrace(response.getWriter());
            }
        */
        
        // -------------------------------------------------------------------------------------------
        
        /* --------------- TESTING MOVIE DAO METHODS ---------------
        
            try {
                // Sample Movie Data
                Movie testMovie = new Movie();
                testMovie.setTitle("Test Movie");
                testMovie.setSynopsis("This is a test movie.");
                testMovie.setLanguageId(1);
                testMovie.setGenreIdsFromJson("[1, 2]");
                testMovie.setDuration(120);
                testMovie.setRating(4.5);
                testMovie.setReleaseDate(Date.valueOf("2024-11-25"));
                
                String castJson = "[{\"actorName\": \"Actor1\", \"characterName\": \"Character1\"}, {\"actorName\": \"Actor2\", \"characterName\": \"Character2\"}]";
                String crewJson = "[{\"role\": \"Director\", \"name\": \"Director1\"}, {\"role\": \"Producer\", \"name\": \"Producer1\"}]";
                testMovie.setCastFromJson(castJson);
                testMovie.setCrewFromJson(crewJson);
            
                testMovie.setPosterUrl("http://example.com/poster.jpg");
                testMovie.setTrailerUrl("http://example.com/trailer.mp4");
            
                // Add Movie
                response.getWriter().println("Testing addMovie...");
                movieDAO.addMovie(testMovie);
                response.getWriter().println("Movie added successfully.\n");
                
                // Get Movie by ID
                response.getWriter().println("Testing getMovieById...");
                Movie fetchedMovieById = movieDAO.getMovieById(2);
                response.getWriter().println("Fetched movie by ID: " + (fetchedMovieById != null ? fetchedMovieById.getTitle() : "null") + "\n");
                
                // Update Movie
                response.getWriter().println("Testing updateMovie...");
                fetchedMovieById.setTitle("Updated Test Movie");
                movieDAO.updateMovie(fetchedMovieById);
                response.getWriter().println("Movie updated successfully.\n");
                
                // Delete Movie
                response.getWriter().println("Testing deleteMovie...");
                movieDAO.deleteMovie(fetchedMovieById.getMovieId());
                response.getWriter().println("Movie deleted successfully.\n");
                
                // Get All Movies
                response.getWriter().println("Testing getAllMovies...");
                List<Movie> allMovies = movieDAO.getAllMovies();
                response.getWriter().println("Total movies: " + allMovies.size());
                for (Movie movie : allMovies) {
                    response.getWriter().println(" - " + movie.getTitle());
                }
                response.getWriter().println();
                
                // Get Movies by Language
                response.getWriter().println("Testing getMoviesByLanguage...");
                List<Movie> moviesByLanguage = movieDAO.getMoviesByLanguage(1);
                response.getWriter().println("Movies found for language 1: " + moviesByLanguage.size());
                for (Movie movie : moviesByLanguage) {
                    response.getWriter().println(" - " + movie.getTitle());
                }
                response.getWriter().println();
                
                // Get Movies by Genres
                response.getWriter().println("Testing getMoviesByGenres...");
                int[] genreIds = {1, 2};
                List<Movie> moviesByGenres = movieDAO.getMoviesByGenres(genreIds);
                response.getWriter().println("Movies found for genres 1 and 2: " + moviesByGenres.size());
                for (Movie movie : moviesByGenres) {
                    response.getWriter().println(" - " + movie.getTitle());
                }
                response.getWriter().println();
                
                // Get Total Movies
                response.getWriter().println("Testing getTotalMovies...");
                int totalMovies = movieDAO.getTotalMovies();
                response.getWriter().println("Total movies count: " + totalMovies + "\n");
                
            } catch (Exception e) {
                response.getWriter().println("An error occurred: " + e.getMessage());
                e.printStackTrace(response.getWriter());
            }
        */
        
        // -------------------------------------------------------------------------------------------
        
        /* --------------- TESTING PROMOTION DAO METHODS ---------------
        
            try {
                // Sample Promotion Data
                Promotion testPromotion = new Promotion();
                testPromotion.setName("Test Promotion");
                testPromotion.setDescription("Get 20% off this testing season!");
                testPromotion.setCode("TEST20");
                testPromotion.setDiscount(20.0);
                testPromotion.setStartDate(Date.valueOf("2024-12-01"));
                testPromotion.setEndDate(Date.valueOf("2024-12-31"));
                testPromotion.setIsActive(true);
                
                // Create Promotion
                response.getWriter().println("Testing createPromotion...");
                promotionDAO.createPromotion(testPromotion);
                response.getWriter().println("Promotion created successfully.\n");
                
                // Get Promotion by Code
                response.getWriter().println("Testing getPromotionByCode...");
                Promotion fetchedPromotionByCode = promotionDAO.getPromotionByCode("TEST20");
                response.getWriter().println("Fetched promotion by code: " + (fetchedPromotionByCode != null ? fetchedPromotionByCode.getName() : "null") + "\n");
                
                // Get Promotion by ID
                response.getWriter().println("Testing getPromotionById...");
                Promotion fetchedPromotionById = promotionDAO.getPromotionById(fetchedPromotionByCode.getPromotionId());
                response.getWriter().println("Fetched promotion by ID: " + (fetchedPromotionById != null ? fetchedPromotionById.getName() : "null") + "\n");
                
                // Update Promotion
                response.getWriter().println("Testing updatePromotion...");
                fetchedPromotionById.setDescription("Updated description for the test discount.");
                promotionDAO.updatePromotion(fetchedPromotionById);
                response.getWriter().println("Promotion updated successfully.\n");
                
                // Delete Promotion
                response.getWriter().println("Testing deletePromotion...");
                promotionDAO.deletePromotion(fetchedPromotionById.getPromotionId());
                response.getWriter().println("Promotion deleted successfully.\n");
                
                // Add More Promotions
                Promotion promotion1 = new Promotion("Promotion 1", "text - promotion 1", "PROMO1", 20, Date.valueOf("2024-11-30"), Date.valueOf("2024-12-15"), true);
                Promotion promotion2 = new Promotion("Promotion 2", "text - promotion 2", "PROMO2", 20, Date.valueOf("2024-11-30"), Date.valueOf("2024-12-15"), true);
                Promotion promotion3 = new Promotion("Promotion 3", "text - promotion 3", "PROMO3", 20, Date.valueOf("2024-11-30"), Date.valueOf("2024-12-15"), true);
                promotionDAO.createPromotion(promotion1);
                promotionDAO.createPromotion(promotion2);
                promotionDAO.createPromotion(promotion3);
                
                // Get All Promotions
                response.getWriter().println("Testing getAllPromotions...");
                List<Promotion> allPromotions = promotionDAO.getAllPromotions();
                response.getWriter().println("Total promotions: " + allPromotions.size());
                for (Promotion promo : allPromotions) {
                    response.getWriter().println(" - " + promo.getName());
                }
                response.getWriter().println();
                
                // Get Total Promotions
                response.getWriter().println("Testing getTotalPromotions...");
                int totalPromotions = promotionDAO.getTotalPromotions();
                response.getWriter().println("Total promotions count: " + totalPromotions + "\n");
                
                // Get Total Active Promotions
                response.getWriter().println("Testing getTotalActivePromotions...");
                int totalActivePromotions = promotionDAO.getTotalActivePromotions();
                response.getWriter().println("Total active promotions count: " + totalActivePromotions + "\n");

            } catch (Exception e) {
                response.getWriter().println("An error occurred: " + e.getMessage());
                e.printStackTrace(response.getWriter());
            }
        */
        
        // -------------------------------------------------------------------------------------------
        
        /* --------------- TESTING HALL DAO METHODS ---------------
        
            try {
                // Sample Hall Data
                Hall newHall = new Hall();
                newHall.setName("Test Hall");
                newHall.setType(Hall.Type._2D);
                newHall.setCapacity(200);
                newHall.setLocation("Colombo");
                
                // Add Hall
                response.getWriter().println("Testing addHall...");
                hallDAO.addHall(newHall);
                response.getWriter().println("Hall added successfully.\n");
                
                // Get Hall by ID
                response.getWriter().println("Testing getHallById...");
                Hall fetchedHallById = hallDAO.getHallById(12);
                response.getWriter().println("Fetched hall by ID: " + (fetchedHallById != null ? fetchedHallById.getName() : "null") + "\n");
                
                // Update Hall
                response.getWriter().println("Testing updateHall...");
                fetchedHallById.setName("Updated Test Hall");
                fetchedHallById.setCapacity(250);
                hallDAO.updateHall(fetchedHallById);
                response.getWriter().println("Hall updated successfully.\n");
                
                // Delete Hall
                response.getWriter().println("Testing deleteHall...");
                hallDAO.deleteHall(fetchedHallById.getHallId());
                response.getWriter().println("Hall deleted successfully.\n");
                
                // Get All Halls
                response.getWriter().println("Testing getAllHalls...");
                List<Hall> allHalls = hallDAO.getAllHalls();
                response.getWriter().println("Total halls: " + allHalls.size());
                for (Hall hall : allHalls) {
                    response.getWriter().println(" - " + hall.getName() + " (" + hall.getCapacity() + " seats)");
                }
                response.getWriter().println();
               
                // Get Total Halls
                response.getWriter().println("Testing getTotalHalls...");
                int totalHalls = hallDAO.getTotalHalls();
                response.getWriter().println("Total halls count: " + totalHalls + "\n");
    
            } catch (Exception e) {
                response.getWriter().println("An error occurred: " + e.getMessage());
                e.printStackTrace(response.getWriter());
            }
        */
        
        // -------------------------------------------------------------------------------------------
        
        /* --------------- TESTING SHOWTIME DAO METHODS ---------------
            
            try {
                // Sample Showtime Data
                Showtime testShowtime = new Showtime();
                testShowtime.setHallId(4);
                testShowtime.setMovieId(6);
                testShowtime.setShowDate(java.sql.Date.valueOf("2024-12-01"));
                testShowtime.setShowTime(java.sql.Time.valueOf("14:30:00"));
                
                // Create Showtime
                response.getWriter().println("Testing createShowtime...");
                showtimeDAO.createShowtime(testShowtime);
                response.getWriter().println("Showtime created successfully.\n");
                
                // Get Showtime by ID
                response.getWriter().println("Testing getShowtimeById...");
                Showtime fetchedShowtimeById = showtimeDAO.getShowtimeById(6);
                response.getWriter().println("Fetched showtime by ID: " + (fetchedShowtimeById != null ? fetchedShowtimeById.getShowtimeId() : "null") + "\n");
                
                // Update Showtime
                response.getWriter().println("Testing updateShowtime...");
                fetchedShowtimeById.setShowTime(java.sql.Time.valueOf("16:00:00"));
                showtimeDAO.updateShowtime(fetchedShowtimeById);
                response.getWriter().println("Showtime updated successfully.\n");
                
                // Delete Showtime
                response.getWriter().println("Testing deleteShowtime...");
                showtimeDAO.deleteShowtime(fetchedShowtimeById.getShowtimeId());
                response.getWriter().println("Showtime deleted successfully.\n");

                // Get All Showtimes for a Movie
                response.getWriter().println("Testing getShowtimesByMovie...");
                List<Showtime> showtimesByMovie = showtimeDAO.getShowtimesByMovie(6);
                response.getWriter().println("Total showtimes for movieId 6: " + showtimesByMovie.size());
                for (Showtime showtime : showtimesByMovie) {
                    response.getWriter().println(" - Showtime ID: " + showtime.getShowtimeId() + " at " + showtime.getShowTime());
                }
                response.getWriter().println();
                
                // Get All Showtimes of a Hall
                response.getWriter().println("Testing getShowtimesByHall...");
                List<Showtime> showtimesByHall = showtimeDAO.getShowtimesByHall(4);
                response.getWriter().println("Total showtimes of hallId 4: " + showtimesByHall.size());
                for (Showtime showtime : showtimesByHall) {
                    response.getWriter().println(" - Showtime ID: " + showtime.getShowtimeId() + " at " + showtime.getShowTime());
                }
                response.getWriter().println();
                
                // Get Total Showtimes
                response.getWriter().println("Testing getTotalShowtimes...");
                int totalShowtimes = showtimeDAO.getTotalShowtimes();
                response.getWriter().println("Total showtimes count: " + totalShowtimes + "\n");
    
            } catch (Exception e) {
                response.getWriter().println("An error occurred: " + e.getMessage());
                e.printStackTrace(response.getWriter());
            }
        */
        
        // -------------------------------------------------------------------------------------------
        
        /* --------------- TESTING SEATS DAO METHODS ---------------
        
            try {
                // Sample Seat Data
                Seat testSeat = new Seat();
                testSeat.setHallId(4);
                testSeat.setShowtimeId(8);
                testSeat.setSeatNumber("A2");
                testSeat.setSeatType(Seat.SeatType.STANDARD);
                testSeat.setPrice(10.00);
                testSeat.setIsAvailable(true);
                testSeat.setIsReserved(false);
                
                // Add Seat
                response.getWriter().println("Testing addSeat...");
                seatDAO.addSeat(testSeat);
                response.getWriter().println("Seat added successfully.\n");
                
                // Get Seat by ID
                response.getWriter().println("Testing getSeatById...");
                Seat fetchedSeatById = seatDAO.getSeatById(30);
                response.getWriter().println("Fetched seat by ID: " + (fetchedSeatById != null ? fetchedSeatById.getSeatNumber() : "null") + "\n");

                // Update Seat
                response.getWriter().println("Testing updateSeat...");
                fetchedSeatById.setPrice(20.00);
                seatDAO.updateSeat(fetchedSeatById);
                response.getWriter().println("Seat updated successfully.\n");

                // Update Seat Availability
                response.getWriter().println("Testing updatSeatAvailability...");
                seatDAO.updatSeatAvailability(fetchedSeatById.getSeatId(), false);
                response.getWriter().println("Seat availability updated successfully.\n");

                // Update Seat Reserved
                response.getWriter().println("Testing updateSeatReserved...");
                seatDAO.updateSeatReserved(fetchedSeatById.getSeatId(), true);
                response.getWriter().println("Seat reserved status updated successfully.\n");

                // Delete Seat
                response.getWriter().println("Testing deleteSeat...");
                seatDAO.deleteSeat(fetchedSeatById.getSeatId());
                response.getWriter().println("Seat deleted successfully.\n");

                // Get All Seats
                response.getWriter().println("Testing getAllSeats...");
                List<Seat> allSeats = seatDAO.getAllSeats();
                response.getWriter().println("Total seats: " + allSeats.size());
                for (Seat seat : allSeats) {
                    response.getWriter().println(" - " + seat.getSeatNumber() + " (" + seat.getSeatType() + ")");
                }
                response.getWriter().println();

                // Get All Seats by Hall
                response.getWriter().println("Testing getAllSeatsByHall...");
                List<Seat> seatsByHall = seatDAO.getAllSeatsByHall(4);
                response.getWriter().println("Total seats in hallId 4: " + seatsByHall.size());
                for (Seat seat : seatsByHall) {
                    response.getWriter().println(" - " + seat.getSeatNumber() + " (" + seat.getSeatType() + ")");
                }
                response.getWriter().println();

                // Get All Seats by Showtime
                response.getWriter().println("Testing getAllSeatsByShowtime...");
                List<Seat> seatsByShowtime = seatDAO.getAllSeatsByShowtime(8);
                response.getWriter().println("Total seats for showtimeId 8: " + seatsByShowtime.size());
                for (Seat seat : seatsByShowtime) {
                    response.getWriter().println(" - " + seat.getSeatNumber() + " (" + seat.getSeatType() + ")");
                }
                response.getWriter().println();

                // Get Available Seats by Showtime
                response.getWriter().println("Testing getAvailableSeatsByShowtime...");
                List<Seat> availableSeats = seatDAO.getAvailableSeatsByShowtime(8);
                response.getWriter().println("Available seats for showtimeId 8: " + availableSeats.size());
                for (Seat seat : availableSeats) {
                    response.getWriter().println(" - " + seat.getSeatNumber() + " (" + seat.getSeatType() + ")");
                }
                response.getWriter().println();

                // Get Reserved Seats by Showtime
                response.getWriter().println("Testing getReservedSeatsByShowtime...");
                List<Seat> reservedSeats = seatDAO.getReservedSeatsByShowtime(8);
                response.getWriter().println("Reserved seats for showtimeId 8: " + reservedSeats.size());
                for (Seat seat : reservedSeats) {
                    response.getWriter().println(" - " + seat.getSeatNumber() + " (" + seat.getSeatType() + ")");
                }
                response.getWriter().println();

            } catch (Exception e) {
                response.getWriter().println("An error occurred: " + e.getMessage());
                e.printStackTrace(response.getWriter());
            }
        */
        
        // -------------------------------------------------------------------------------------------
        
        /* --------------- TESTING BOOKINGS DAO METHODS ---------------
        
            try {
                // Sample Booking Data
                Booking testBooking = new Booking();
                testBooking.setUserId(null);
                testBooking.setGuestId(null);
                testBooking.setShowtimeId(8);
                testBooking.setPromotionId(null);
                testBooking.setBookedSeatsFromJson("[\"A1\", \"A2\"]");
                testBooking.setExpiryDate(java.sql.Date.valueOf("2024-12-31"));
                testBooking.setTotalPrice(30.00);
                testBooking.setIsPaid(true);
                
                Booking newBooking = new Booking();
                newBooking.setGuestId(2);
                newBooking.setShowtimeId(8);
                newBooking.setBookedSeatsFromJson("[\"A1\", \"A2\"]");
                newBooking.setExpiryDate(java.sql.Date.valueOf("2024-12-31"));
                newBooking.setTotalPrice(30.00);
                newBooking.setIsPaid(true);
                
                // Create Booking
                response.getWriter().println("Testing createBooking...");
                bookingDAO.createBooking(testBooking);
                bookingDAO.createBooking(newBooking);
                response.getWriter().println("Booking created successfully.\n");
                
                // Get Booking by ID
                response.getWriter().println("Testing getBookingById...");
                Booking fetchedBooking = bookingDAO.getBookingById(5);
                response.getWriter().println("Fetched booking by ID: " + (fetchedBooking != null ? fetchedBooking.getBookingId() : "null") + "\n");
                
                // Update Booking
                response.getWriter().println("Testing updateBooking...");
                bookingDAO.updateBooking(9, fetchedBooking.getBookingId());
                response.getWriter().println("Booking updated successfully.\n");
                
                // Delete Booking
                response.getWriter().println("Testing deleteBooking...");
                bookingDAO.deleteBooking(fetchedBooking.getBookingId());
                response.getWriter().println("Booking deleted successfully.\n");
                
                // Get All Bookings of User
                response.getWriter().println("Testing getAllBookingsOfUser...");
                List<Booking> userBookings = bookingDAO.getAllBookingsOfUser(3);
                response.getWriter().println("Total bookings for userId 1: " + userBookings.size());
                for (Booking booking : userBookings) {
                    response.getWriter().println(" - Booking ID: " + booking.getBookingId() + ", Showtime ID: " + booking.getShowtimeId());
                }
                response.getWriter().println();
                
                // Get Total Bookings
                response.getWriter().println("Testing getTotalBookings...");
                int totalBookings = bookingDAO.getTotalBookings();
                response.getWriter().println("Total number of bookings: " + totalBookings + "\n");
                
            } catch (Exception e) {
                response.getWriter().println("An error occurred: " + e.getMessage());
                e.printStackTrace(response.getWriter());
            }
        */
        
        // -------------------------------------------------------------------------------------------
        
        /* --------------- TESTING FOODS DAO METHODS ---------------
        
            try {
                // Sample Food Data
                Food testFoodItem = new Food();
                testFoodItem.setItemName("Orange Juice");
                testFoodItem.setItemType(Food.ItemType.JUICE);
                testFoodItem.setPrice(3.50);
                testFoodItem.setStock(80);
                
                // Add Food Item
                response.getWriter().println("Testing addFoodItem...");
                foodDAO.addFoodItem(testFoodItem);
                response.getWriter().println("Food item added successfully.\n");
                
                // Get Food Item by ID
                response.getWriter().println("Testing getFoodItemById...");
                Food fetchedFoodById = foodDAO.getFoodItemById(30);
                response.getWriter().println("Fetched food item: " + (fetchedFoodById != null ? fetchedFoodById.getItemName() : "null") + "\n");
                
                // Update Food Item
                response.getWriter().println("Testing updateFoodItem...");
                fetchedFoodById.setPrice(5.80);
                foodDAO.updateFoodItem(fetchedFoodById);
                response.getWriter().println("Food item updated successfully.\n");
                
                // Update Stock
                response.getWriter().println("Testing updateStock...");
                foodDAO.updateStock(10, fetchedFoodById.getItemId());
                response.getWriter().println("Stock updated successfully.\n");
                
                // Delete Food Item
                response.getWriter().println("Testing deleteFoodItem...");
                foodDAO.deleteFoodItem(31);
                response.getWriter().println("Food item deleted successfully.\n");
                
                // Get All Food Items
                response.getWriter().println("Testing getAllFoodItems...");
                List<Food> allFoodItems = foodDAO.getAllFoodItems();
                response.getWriter().println("Total food items: " + allFoodItems.size());
                for (Food food : allFoodItems) {
                    response.getWriter().println(" - Item Name: " + food.getItemName() + ", Price: " + food.getPrice() + ", Stock: " + food.getStock());
                }
                response.getWriter().println();
                
                // Get Food Items by Type
                response.getWriter().println("Testing getFoodItemsByType...");
                List<Food> typeItems = foodDAO.getFoodItemsByType("JUICE");
                response.getWriter().println("Total juice items: " + typeItems.size());
                for (Food type : typeItems) {
                    response.getWriter().println(" - Item Name: " + type.getItemName() + ", Price: " + type.getPrice());
                }
                response.getWriter().println();
                
                // Get Stock
                response.getWriter().println("Testing getStock...");
                int stock = foodDAO.getStock(30);
                response.getWriter().println("Stock for itemId 30: " + stock + "\n");
                
                // Get Total Food Items
                response.getWriter().println("Testing getTotalFoodItems...");
                int totalFoodItems = foodDAO.getTotalFoodItems();
                response.getWriter().println("Total food items: " + totalFoodItems + "\n");
                
                // Get Total Food Items by Type
                response.getWriter().println("Testing getTotalFoodItemsByType...");
                int totalTypeItems = foodDAO.getTotalFoodItemsByType("JUICE");
                response.getWriter().println("Total snack items: " + totalTypeItems + "\n");
                
                // Sample FoodOrder Data
                FoodOrder newOrder = new FoodOrder();
                newOrder.setBookingId(8);
                newOrder.setItemId(30);
                newOrder.setQuantity(2);
                newOrder.setPricePerItem(3.50);
                newOrder.setTotalPrice(7.00);
                
                // Add Order
                response.getWriter().println("Testing addOrder...");
                foodDAO.addOrder(newOrder);
                response.getWriter().println("Order added successfully.\n");
                
                // Get Order by ID
                response.getWriter().println("Testing getOrderById...");
                FoodOrder fetchedOrderById = foodDAO.getOrderById(7);
                response.getWriter().println("Fetched order: " + (fetchedOrderById != null ? "Order ID: " + fetchedOrderById.getOrderId() + ", Total Price: " + fetchedOrderById.getTotalPrice() : "null") + "\n");
                
                // Get Orders by Booking
                response.getWriter().println("Testing getOrdersByBooking...");
                List<FoodOrder> bookingOrders = foodDAO.getOrdersByBooking(fetchedOrderById.getBookingId());
                response.getWriter().println("Total orders for bookingId 1: " + bookingOrders.size());
                for (FoodOrder order : bookingOrders) {
                    response.getWriter().println(" - Order ID: " + order.getOrderId() + ", Item ID: " + order.getItemId() + ", Quantity: " + order.getQuantity());
                }
                response.getWriter().println();
                
                // Get Total Price of Orders by Booking
                response.getWriter().println("Testing getTotalPriceOfOrdersByBooking...");
                double totalPriceByBooking = foodDAO.getTotalPriceOfOrdersByBooking(fetchedOrderById.getBookingId());
                response.getWriter().println("Total price for orders in bookingId 1: " + totalPriceByBooking + "\n");
                
                // Get Total Orders
                response.getWriter().println("Testing getTotalOrders...");
                int totalOrders = foodDAO.getTotalOrders();
                response.getWriter().println("Total number of food orders: " + totalOrders + "\n");

            } catch (Exception e) {
                response.getWriter().println("An error occurred: " + e.getMessage());
                e.printStackTrace(response.getWriter());
            }
        */
        
        // -------------------------------------------------------------------------------------------
        
        /* --------------- TESTING PAYMENTS DAO METHODS ---------------
        
            try {
                // Sample Payment Data
                Payment testPayment = new Payment();
                testPayment.setBookingId(6);
                testPayment.setTransactionId("TXN123456789");
                testPayment.setPaymentMethod(Payment.PaymentMethod.CREDIT_CARD);
                testPayment.setPaymentStatus(Payment.PaymentStatus.COMPLETED);
                testPayment.setAmount(100.0);
                
                // Add Payment
                response.getWriter().println("Testing addPayment...");
                paymentDAO.addPayment(testPayment);
                response.getWriter().println("Payment added successfully.\n");
                
                // Get Payment by ID
                response.getWriter().println("Testing getPaymentById...");
                Payment fetchedPaymentById = paymentDAO.getPaymentById(1);
                response.getWriter().println("Fetched payment: " + (fetchedPaymentById != null ? "Payment ID: " + fetchedPaymentById.getPaymentId() + ", Amount: " + fetchedPaymentById.getAmount() : "null") + "\n");
                
                // Update Payment Status
                response.getWriter().println("Testing updatePaymentStatus...");
                paymentDAO.updatePaymentStatus(fetchedPaymentById.getPaymentId(), Payment.PaymentStatus.REFUNDED);
                response.getWriter().println("Payment status updated successfully.\n");
                
                // Delete Payment
                response.getWriter().println("Testing deletePayment...");
                paymentDAO.deletePayment(fetchedPaymentById.getPaymentId());
                response.getWriter().println("Payment deleted successfully.\n");

            } catch (Exception e) {
                response.getWriter().println("An error occurred: " + e.getMessage());
                e.printStackTrace(response.getWriter());
            }
        */
        
        // -------------------------------------------------------------------------------------------
        
        /* --------------- TESTING ADMINS DAO METHODS ---------------
        
            try {
                // Sample Admin Data
                Admin testAdmin = new Admin();
                testAdmin.setUsername("testAdmin");
                testAdmin.setPassword("password123");
                testAdmin.setContactNumber("1234567890");
                
                // Create Admin
                response.getWriter().println("Testing createAdmin...");
                adminDAO.createAdmin(testAdmin);
                response.getWriter().println("Admin created successfully.\n");
                
                // Authenticate Admin
                response.getWriter().println("Testing authenticateAdmin...");
                boolean isAuthenticated = adminDAO.authenticateAdmin("testAdmin", "password123");
                response.getWriter().println("Authentication result: " + (isAuthenticated ? "Success" : "Failed") + "\n");
                
                // Get Admin by ID
                response.getWriter().println("Testing getAdminById...");
                Admin fetchedAdminById = adminDAO.getAdminById(1);
                response.getWriter().println("Fetched admin: " + (fetchedAdminById != null ? "Admin ID: " + fetchedAdminById.getAdminId() + ", Username: " + fetchedAdminById.getUsername() : "null") + "\n");
                
                // Delete Admin
                response.getWriter().println("Testing deleteAdmin...");
                adminDAO.deleteAdmin(fetchedAdminById.getAdminId());
                response.getWriter().println("Admin deleted successfully.\n");

            } catch (Exception e) {
                response.getWriter().println("An error occurred: " + e.getMessage());
                e.printStackTrace(response.getWriter());
            }
        */
        
        // -------------------------------------------------------------------------------------------
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet used to test the features and functionalities of our SilverKnightCinema Project";
    }
}
