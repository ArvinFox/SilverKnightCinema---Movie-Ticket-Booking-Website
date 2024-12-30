package admin_servlet_classes;

import model_classes.Showtime;
import dao_classes.ShowtimeDAO;
import model_classes.Movie;
import dao_classes.MovieDAO;
import model_classes.Hall;
import dao_classes.HallDAO;
import model_classes.Cinema;
import dao_classes.CinemaDAO;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

@WebServlet(name = "AdminManageShowtimesServlet", urlPatterns = {"/AdminManageShowtimesServlet", "/admin/manageShowtimes"})
public class AdminManageShowtimesServlet extends HttpServlet {
    private ShowtimeDAO showtimeDAO;
    private MovieDAO movieDAO;
    private HallDAO hallDAO;
    private CinemaDAO cinemaDAO;
    
    @Override
    public void init() {
        showtimeDAO = new ShowtimeDAO();
        movieDAO = new MovieDAO();
        hallDAO = new HallDAO();
        cinemaDAO = new CinemaDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        
        String hallIdParam = request.getParameter("hallId");
        Integer hallId = hallIdParam != null && !hallIdParam.isEmpty() && !hallIdParam.equals("any") ? Integer.valueOf(hallIdParam) : null;
        String movieIdParam = request.getParameter("movieId");
        Integer movieId = movieIdParam != null && !movieIdParam.isEmpty() && !movieIdParam.equals("any") ? Integer.valueOf(movieIdParam) : null;
        String showDate = request.getParameter("showDate");
        String showTime = request.getParameter("showTime");
        List<Showtime> showtimeList;
        
        if (username != null) {
            if ((hallId != null) || (movieId != null) || (showDate != null && !showDate.trim().isEmpty()) || (showTime != null && !showTime.trim().isEmpty())) {
                showtimeList = showtimeDAO.getSearchedShowtimes(hallId, movieId, showDate, showTime);
            } else {
                showtimeList = showtimeDAO.getAllShowtimes();
            }
            
            List<Movie> nowShowingMovieList = movieDAO.getNowShowingMovies();
            List<Hall> hallList = hallDAO.getAllHalls();
            for (Hall hall : hallList) {
                Cinema cinema = cinemaDAO.getCinemaById(hall.getCinemaId());
                hall.setCinema(cinema.getName());
            }
            
            for (Showtime showtime : showtimeList) {
                Hall hall = hallDAO.getHallById(showtime.getHallId());
                Cinema cinema = cinemaDAO.getCinemaById(hall.getCinemaId());
                showtime.setHallName(hall.getName() + " (" + cinema.getName() + ")");
                
                Movie movie = movieDAO.getMovieById(showtime.getMovieId());
                showtime.setMovieTitle(movie.getTitle());
                
                showtime.setFormattedTime(showtime.getShowTime());
            }

            request.setAttribute("showtimeList", showtimeList);
            request.setAttribute("nowShowingMovieList", nowShowingMovieList);   
            request.setAttribute("hallList", hallList);   
            request.getRequestDispatcher("../adminView/manageShowtimes.jsp").forward(request, response);
            
        } else {
            response.sendRedirect("login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        
        if (username != null) {
            String action = request.getParameter("action");
            String actionResponse = "Failed to perform operation.";
            
            if (null != action) switch (action) {
                case "add" -> {
                    Showtime showtime = extractShowtimeFromRequest(request);
                    showtimeDAO.createShowtime(showtime);
                    actionResponse = "Showtime added successfully.";
                }
                
                case "update" -> {
                    int showtimeId = Integer.parseInt(request.getParameter("showtimeId"));
                    Showtime showtime = extractShowtimeFromRequest(request);
                    showtime.setShowtimeId(showtimeId);
                    showtimeDAO.updateShowtime(showtime);
                    actionResponse = "Showtime updated successfully.";
                }
                
                case "delete" -> {
                    int showtimeId = Integer.parseInt(request.getParameter("showtimeId"));
                    showtimeDAO.deleteShowtime(showtimeId);
                    actionResponse = "Showtime deleted successfully.";
                }
                
                default -> response.sendRedirect("login");
            }
            
            // Redirect the admin to manage showtimes panel (manageShowtimes), and display the action response 
            response.sendRedirect("manageShowtimes");
            
        } else {
            response.sendRedirect("login");
        }
    }
    
    private Showtime extractShowtimeFromRequest(HttpServletRequest request) {
        int hallId = Integer.parseInt(request.getParameter("hall"));
        int movieId = Integer.parseInt(request.getParameter("movie"));

        String showDateParam = request.getParameter("showDate");
        java.sql.Date showDate = java.sql.Date.valueOf(showDateParam);
        
        String showTimeParam = request.getParameter("showTime");
        if (showTimeParam.length() == 5) { // Format is HH:MM
            showTimeParam += ":00"; // Add seconds to match HH:MM:SS format
        }
        java.sql.Time showTime = java.sql.Time.valueOf(showTimeParam);

        Showtime showtime = new Showtime(hallId, movieId, showDate, showTime);
        return showtime;
    }

    @Override
    public String getServletInfo() {
        return "Servlet that manages showtimes, including add, view, edit, and delete functionalities.";
    }
}
