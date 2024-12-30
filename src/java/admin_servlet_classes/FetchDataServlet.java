package admin_servlet_classes;

import com.google.gson.*;

import model_classes.Movie;
import dao_classes.MovieDAO;
import model_classes.Language;
import dao_classes.LanguageDAO;
import model_classes.Genre;
import dao_classes.GenreDAO;
import model_classes.User;
import dao_classes.UserDAO;
import model_classes.Promotion;
import dao_classes.PromotionDAO;
import model_classes.Food;
import model_classes.FoodOrder;
import dao_classes.FoodDAO;
import model_classes.Cinema;
import dao_classes.CinemaDAO;
import model_classes.Hall;
import dao_classes.HallDAO;
import model_classes.Showtime;
import dao_classes.ShowtimeDAO;
import model_classes.Booking;
import dao_classes.BookingDAO;
import model_classes.Seat;
import dao_classes.SeatDAO;
import model_classes.Inquiry;
import dao_classes.InquiryDAO;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
// import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "FetchDataServlet", urlPatterns = {"/FetchDataServlet", "/fetch"})
public class FetchDataServlet extends HttpServlet {
    private MovieDAO movieDAO;
    private LanguageDAO languageDAO;
    private GenreDAO genreDAO;
    private UserDAO userDAO;
    private PromotionDAO promotionDAO;
    private FoodDAO foodDAO;
    private CinemaDAO cinemaDAO;
    private HallDAO hallDAO;
    private ShowtimeDAO showtimeDAO;
    private BookingDAO bookingDAO;
    private SeatDAO seatDAO;
    private InquiryDAO inquiryDAO;
    
    @Override
    public void init() {
        movieDAO = new MovieDAO();
        languageDAO = new LanguageDAO();
        genreDAO = new GenreDAO();
        userDAO = new UserDAO();
        promotionDAO = new PromotionDAO();
        foodDAO = new FoodDAO();
        cinemaDAO = new CinemaDAO();
        hallDAO = new HallDAO();
        showtimeDAO = new ShowtimeDAO();
        bookingDAO = new BookingDAO();
        seatDAO = new SeatDAO();
        inquiryDAO = new InquiryDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Map<String, Object> result = new HashMap<>();
        
        String languagesParam = request.getParameter("languages");
        String genresParam = request.getParameter("genres");
        
        String languageIdParam = request.getParameter("languageId");
        String genreIdParam = request.getParameter("genreId");
        
        String foodItemTypesParam = request.getParameter("foodItemTypes");
        
        String hallTypesParam = request.getParameter("hallTypes");
        String cinemasParam = request.getParameter("cinemas");
        
        String showtimeHallsParam = request.getParameter("showtimeHalls");
        String showtimeMoviesParam = request.getParameter("showtimeMovies");
        
        String showtimeMovieIdParam = request.getParameter("showtimeMovieId");
        
        if (languagesParam != null) {
            List<Language> languages = languageDAO.getAllLanguages();
            result.put("languages", languages);
        }
        
        if (genresParam != null) {
            List<Genre> genres = genreDAO.getAllGenres();
            result.put("genres", genres);
        }
        
        if (languageIdParam != null) {
            int languageId = Integer.parseInt(languageIdParam);
            List<Movie> moviesByLanguage = movieDAO.getMoviesByLanguage(languageId);
            result.put("movies", moviesByLanguage);
        }
        
        if (genreIdParam != null) {
            /*
            int[] genreIds = Arrays.stream(genreIdParam.split(","))
                                    .mapToInt(Integer::parseInt)
                                    .toArray();
            */
            int genreId = Integer.parseInt(genreIdParam);
            List<Movie> moviesByGenre = movieDAO.getMoviesByGenres(new int[] {genreId});
            result.put("movies", moviesByGenre);
        }
        
        if (foodItemTypesParam != null) {
            List<String> itemTypes = new ArrayList<>();
            for (Food.ItemType itemType : Food.ItemType.values()) {
                itemTypes.add(itemType.toString());
            }
            result.put("itemTypes", itemTypes);
        }
        
        if (hallTypesParam != null) {
            List<String> hallTypes = new ArrayList<>();
            for (Hall.Type hallType : Hall.Type.values()) {
                hallTypes.add(hallType.toString());
            }
            result.put("hallTypes", hallTypes);
        }
        
        if (cinemasParam != null) {
            List<Cinema> cinemas = cinemaDAO.getAllCinemas();
            result.put("cinemas", cinemas);
        }
        
        if (showtimeHallsParam != null) {
            List<Hall> showtimeHalls = hallDAO.getAllHalls();
            for (Hall hall : showtimeHalls) {
                Cinema cinema = cinemaDAO.getCinemaById(hall.getCinemaId());
                hall.setCinema(cinema.getName());
            }
            result.put("showtimeHalls", showtimeHalls);
        }
        
        if (showtimeMoviesParam != null) {
            List<Movie> showtimeMovies = movieDAO.getNowShowingMovies();
            result.put("showtimeMovies", showtimeMovies);
        }
        
        if (showtimeMovieIdParam != null) {
            int showtimeMovieId = Integer.parseInt(showtimeMovieIdParam);
            List<Showtime> showtimes = showtimeDAO.getShowtimesByMovie(showtimeMovieId);
            for (Showtime showtime : showtimes) {
                Hall hall = hallDAO.getHallById(showtime.getHallId());
                Cinema cinema = cinemaDAO.getCinemaById(hall.getCinemaId());
                showtime.setHallName(hall.getName() + " (" + cinema.getLocation() + ")");
                showtime.setFormattedTime(showtime.getShowTime());
            }
            result.put("showtimes", showtimes);
        }
        
        // Convert lists to JSON
        Gson gson = new Gson();
        String jsonResponse = gson.toJson(result);

        // Write JSON response
        response.setContentType("application/json");
        response.getWriter().write(jsonResponse);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Read the JSON body
        StringBuilder jsonBody = new StringBuilder();
        String line;
        try (var reader = request.getReader()) {
            while ((line = reader.readLine()) != null) {
                jsonBody.append(line);
            }
        }

        // Parse the JSON body to extract the "id" and "type" fields
        Integer id = null;
        String type = null;
        if (jsonBody.length() > 0) {
            try {
                JsonObject jsonObject = JsonParser.parseString(jsonBody.toString()).getAsJsonObject();
                id = Integer.valueOf(jsonObject.get("id").getAsString());
                type = jsonObject.get("type").getAsString();
            } catch (JsonSyntaxException | NumberFormatException e) {
                e.printStackTrace();
            }
        }
        
        if (type != null && id != null) {
            switch (type) {
                case "Movie" -> {
                    Movie movie = movieDAO.getMovieById(id);
                    Language language = languageDAO.getLanguageById(movie.getLanguageId());
                    String movieLanguage;
                    if (language == null) {
                        movieLanguage = "Unknown Language";
                    } else {
                        movieLanguage = language.getLanguage();
                    }
                    List<Genre> movieGenres = genreDAO.getGenresByIds(movie.getGenreIds());
                    request.setAttribute("movie", movie);
                    request.setAttribute("movieLanguage", movieLanguage);
                    request.setAttribute("movieGenres", movieGenres);
                    request.getRequestDispatcher("/adminView/viewMovie.jsp").forward(request, response);
                }
                
                case "User" -> {
                    User user = userDAO.getUserById(id);
                    request.setAttribute("user", user);
                    request.getRequestDispatcher("/adminView/viewUser.jsp").forward(request, response);
                }
                
                case "Promotion" -> {
                    Promotion promotion = promotionDAO.getPromotionById(id);
                    request.setAttribute("promotion", promotion);
                    request.getRequestDispatcher("/adminView/viewPromotion.jsp").forward(request, response);
                }
                
                case "Hall" -> {
                    Hall hall = hallDAO.getHallById(id);
                    
                    Cinema cinema = cinemaDAO.getCinemaById(hall.getCinemaId());
                    hall.setCinema(cinema.getName() + " - " + cinema.getLocation());
                    
                    request.setAttribute("hall", hall);
                    request.getRequestDispatcher("/adminView/viewHall.jsp").forward(request, response);
                }
                
                case "Showtime" -> {
                    Showtime showtime = showtimeDAO.getShowtimeById(id);
                    
                    Hall hall = hallDAO.getHallById(showtime.getHallId());
                    Cinema cinema = cinemaDAO.getCinemaById(hall.getCinemaId());
                    showtime.setHallName(hall.getName() + " (" + cinema.getName() + ")");
                    
                    Movie movie = movieDAO.getMovieById(showtime.getMovieId());
                    showtime.setMovieTitle(movie.getTitle());
                    
                    request.setAttribute("showtime", showtime);
                    request.getRequestDispatcher("/adminView/viewShowtime.jsp").forward(request, response);
                }
                
                case "Booking" -> {
                    Booking booking = bookingDAO.getBookingById(id);
                    
                    String[] bookedSeats = booking.getBookedSeats();
                    String bookedSeatsAsString = "";
                    for (int i = 0; i < bookedSeats.length; i++) {
                        Seat seat = seatDAO.getSeatById(Integer.parseInt(bookedSeats[i]));
                        bookedSeatsAsString += seat.getSeatNumber();
                        if (i < bookedSeats.length - 1) {
                            bookedSeatsAsString += ", ";
                        }
                    }
                    booking.setBookedSeatsAsString(bookedSeatsAsString);
                    booking.setIsUser(booking.getUserId() != 0);
                    
                    Showtime showtime = showtimeDAO.getShowtimeById(booking.getShowtimeId());
                    Hall hall = hallDAO.getHallById(showtime.getHallId());
                    Cinema cinema = cinemaDAO.getCinemaById(hall.getCinemaId());
                    showtime.setHallName(hall.getName() + " (" + cinema.getLocation() + ")");
                    showtime.setMovieTitle(movieDAO.getMovieById(showtime.getMovieId()).getTitle());
                    showtime.setFormattedTime(showtime.getShowTime());
                    
                    List<FoodOrder> orders = foodDAO.getOrdersByBooking(booking.getBookingId());
                    for (FoodOrder order : orders) {
                        Food item = foodDAO.getFoodItemById(order.getItemId());
                        order.setItemName(item.getItemName());
                    }
                    
                    request.setAttribute("booking", booking);
                    request.setAttribute("showtime", showtime);
                    request.setAttribute("orders", orders);
                    request.getRequestDispatcher("/adminView/viewBooking.jsp").forward(request, response);
                }
                
                case "FoodItem" -> {
                    Food foodItem = foodDAO.getFoodItemById(id);
                    request.setAttribute("foodItem", foodItem);
                    request.getRequestDispatcher("/adminView/viewFoodItem.jsp").forward(request, response);
                }
                
                case "Inquiry" -> {
                    Inquiry inquiry = inquiryDAO.getInquiryById(id);
                    request.setAttribute("inquiry", inquiry);
                    request.getRequestDispatcher("/adminView/viewInquiry.jsp").forward(request, response);
                }
                
                default -> response.getWriter().println("<h1>No data available.</h1>");
            }
        } else {
            response.getWriter().println("<h1>No data available.</h1>");
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet used to fetch content from the database and send it to the JSP.";
    }
}
