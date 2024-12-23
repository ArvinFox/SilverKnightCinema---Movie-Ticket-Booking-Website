package servlet_classes;

import model_classes.Movie;
import dao_classes.MovieDAO;
import model_classes.Genre;
import dao_classes.GenreDAO;
import dao_classes.LanguageDAO;
import model_classes.Hall;
import dao_classes.HallDAO;
import model_classes.Showtime;
import dao_classes.ShowtimeDAO;
import model_classes.Cinema;
import dao_classes.CinemaDAO;

import com.google.gson.Gson;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "MoviesServlet", urlPatterns = {"/MoviesServlet","/movies"})
public class MoviesServlet extends HttpServlet {
    private MovieDAO movieDAO;
    private GenreDAO genreDAO;
    private LanguageDAO languageDAO;
    private HallDAO hallDAO;
    private ShowtimeDAO showtimeDAO;
    private CinemaDAO cinemaDAO;
    
    @Override
    public void init(){
        movieDAO = new MovieDAO();
        genreDAO = new GenreDAO();
        languageDAO = new LanguageDAO();
        hallDAO = new HallDAO();
        showtimeDAO = new ShowtimeDAO();
        cinemaDAO = new CinemaDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String movieIdParam = request.getParameter("movieId");
        String movieTypeParam = request.getParameter("type");
        String movieFilterParam = request.getParameter("filter");
        String showtimeParam = request.getParameter("showtime");
        
        if (showtimeParam != null) {
            handleShowtimeRequest(request, response);
        } else if (movieFilterParam != null) {
            handleFilteredMovies(request, response);           
        } else if (movieTypeParam != null) {
            handleMovieTypeRequest(movieTypeParam, response);           
        } else if (movieIdParam != null){
            handleMovieDetailsRequest(movieIdParam, request, response);           
        } else {
            handleDefaultMoviesPage(request, response);
        }
    }
    
    private Map<String, Object> getOrganizedShowtimes(int movieId, int cinemaId, String date, String experience) {
        Map<String, Object> result = new HashMap<>();
        Map<Integer, Map<Integer, Map<String, List<Showtime>>>> showtimes = new LinkedHashMap<>();
        Map<Integer, Cinema> cinemas = new HashMap<>();
        Map<Integer, Hall> halls = new HashMap<>();
        
        try {
            // Retrieve filtered showtimes
            List<Showtime> filteredShowtimes = showtimeDAO.getFilteredShowtimes(movieId, cinemaId, date, experience);

            for (Showtime showtime : filteredShowtimes) {
                int hallId = showtime.getHallId();
                Hall hall = hallDAO.getHallById(hallId);
                int showtimeCinemaId = hall.getCinemaId();
                Cinema cinema = cinemaDAO.getCinemaById(showtimeCinemaId);

                // Map cinema details
                cinemas.putIfAbsent(showtimeCinemaId, cinema);

                // Map hall details
                halls.putIfAbsent(hallId, hall);

                // Ensure the cinema exists in the organized showtimes map
                showtimes.putIfAbsent(showtimeCinemaId, new LinkedHashMap<>());

                // Ensure the hall exists in the cinema's inner map
                Map<Integer, Map<String, List<Showtime>>> cinemaHalls = showtimes.get(showtimeCinemaId);
                cinemaHalls.putIfAbsent(hallId, new LinkedHashMap<>());

                // Ensure the experience exists in the hall's map
                Map<String, List<Showtime>> hallExperiences = cinemaHalls.get(hallId);
                String showtimeExperience = hall.getType().getDbValue(); // IMAX, 3D, etc.
                hallExperiences.putIfAbsent(showtimeExperience, new ArrayList<>());

                // Add the showtime to the correct experience list
                showtime.setFormattedTime(showtime.getShowTime());
                hallExperiences.get(showtimeExperience).add(showtime);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error organizing showtimes: " + e.getMessage());
        }
        
        result.put("showtimes", showtimes);
        result.put("cinemas", cinemas);
        result.put("halls", halls);

        return result;
    }
    
    private void handleShowtimeRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int movieId = Integer.parseInt(request.getParameter("movieId"));
        int cinemaId = Integer.parseInt(request.getParameter("cinemaId"));
        String date = request.getParameter("date");
        String experience = request.getParameter("experience");

        Map<String, Object> result = getOrganizedShowtimes(movieId, cinemaId, date, experience);

        Gson gson = new Gson();
        String jsonResponse = gson.toJson(result);

        response.setContentType("application/json");
        response.getWriter().write(jsonResponse);
    }
    
    private void handleFilteredMovies(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int languageId = Integer.parseInt(request.getParameter("languageId"));
        int genreId = Integer.parseInt(request.getParameter("genreId"));
        String title = request.getParameter("title");
        String status = request.getParameter("status");

        List<Movie> movies = movieDAO.getFilteredMovies(languageId, genreId, title, status);
        Gson gson = new Gson();
        String jsonResponse = gson.toJson(movies);
        response.setContentType("application/json");
        response.getWriter().write(jsonResponse);
    }
    
    private void handleMovieTypeRequest(String movieTypeParam, HttpServletResponse response) throws IOException {
        List<Movie> movies = "nowShowing".equals(movieTypeParam) ? movieDAO.getNowShowingMovies() : movieDAO.getComingSoonMovies();
        Gson gson = new Gson();
        String jsonResponse = gson.toJson(movies);
        response.setContentType("application/json");
        response.getWriter().write(jsonResponse);
    }
    
    private void handleMovieDetailsRequest(String movieIdParam, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int movieId = Integer.parseInt(movieIdParam);
        Movie movie = movieDAO.getMovieById(movieId);
        movie.setDurationString(movie.getDuration());
        List<Genre> movieGenres = genreDAO.getGenresByIds(movie.getGenreIds());

        List<Showtime> allShowtimesOfMovie = showtimeDAO.getShowtimesByMovie(movieId);
        List<Cinema> cinemasOfMovie = new ArrayList<>();

        for (Showtime showtime : allShowtimesOfMovie) {
            Hall hall = hallDAO.getHallById(showtime.getHallId());
            Cinema cinema = cinemaDAO.getCinemaById(hall.getCinemaId());

            boolean exists = false;
            for (Cinema existingCinema : cinemasOfMovie) {
                if (existingCinema.getCinemaId() == cinema.getCinemaId()) {
                    exists = true;
                    break;
                }
            }

            if (!exists) {
                cinemasOfMovie.add(cinema);
            }
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String today = dateFormat.format(new Date());

        Map<String, Object> showtimeData = getOrganizedShowtimes(movieId, -1, today, "any");

        Map<Integer, Map<Integer, Map<String, List<Showtime>>>> organizedShowtimes =
            (Map<Integer, Map<Integer, Map<String, List<Showtime>>>>) showtimeData.get("showtimes");
        Map<Integer, Cinema> cinemaMap = (Map<Integer, Cinema>) showtimeData.get("cinemas");
        Map<Integer, Hall> hallMap = (Map<Integer, Hall>) showtimeData.get("halls");

        request.setAttribute("organizedShowtimes", organizedShowtimes);
        request.setAttribute("cinemaMap", cinemaMap);
        request.setAttribute("hallMap", hallMap);

        request.setAttribute("cinemasOfMovie", cinemasOfMovie);          
        request.setAttribute("movieGenres", movieGenres);
        request.setAttribute("cinemaList", cinemaDAO.getAllCinemas());
        request.setAttribute("movie", movie);
        request.getRequestDispatcher("movieDetails.jsp").forward(request, response);
    }
    
    private void handleDefaultMoviesPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("movies", movieDAO.getNowShowingMovies());
        request.setAttribute("genres", genreDAO.getAllGenres());
        request.setAttribute("languages", languageDAO.getAllLanguages());
        request.setAttribute("hallList", hallDAO.getAllHalls());
        request.getRequestDispatcher("movies.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet that handles all operations related to displaying movies.";
    }
}
