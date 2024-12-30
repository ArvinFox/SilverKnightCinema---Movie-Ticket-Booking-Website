package servlet_classes;

import model_classes.Movie;
import dao_classes.MovieDAO;
import model_classes.Showtime;
import dao_classes.ShowtimeDAO;
import model_classes.Hall;
import dao_classes.HallDAO;
import model_classes.Cinema;
import dao_classes.CinemaDAO;
import model_classes.Booking;
import dao_classes.BookingDAO;
import model_classes.User;
import dao_classes.UserDAO;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;


@WebServlet(name = "CheckoutServlet", urlPatterns = {"/CheckoutServlet", "/checkout"})
public class CheckoutServlet extends HttpServlet {
    private MovieDAO movieDAO;
    private ShowtimeDAO showtimeDAO;
    private HallDAO hallDAO;
    private CinemaDAO cinemaDAO;
    private BookingDAO bookingDAO;
    private UserDAO userDAO;
    
    @Override
    public void init() {
        movieDAO = new MovieDAO();
        showtimeDAO = new ShowtimeDAO();
        hallDAO = new HallDAO();
        cinemaDAO = new CinemaDAO();
        bookingDAO = new BookingDAO();
        userDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        boolean isLoggedIn = false;
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            isLoggedIn = true;
        }
        
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for(Cookie c : cookies) {
                if("email".equals(c.getName())) {
                    isLoggedIn = true;
                }
            }
        }
        
        if (isLoggedIn) {
            int showtimeId = Integer.parseInt(request.getParameter("showtimeId"));        
            Showtime showtime = showtimeDAO.getShowtimeById(showtimeId);

            Movie movie = movieDAO.getMovieById(showtime.getMovieId());

            showtime.setFormattedTime(showtime.getShowTime());
            showtime.setFormattedDate(showtime.getShowDate());

            Hall hall = hallDAO.getHallById(showtime.getHallId());
            Cinema cinema = cinemaDAO.getCinemaById(hall.getCinemaId());
            hall.setCinema(cinema.getName());

            request.setAttribute("showtimeId", showtimeId);
            request.setAttribute("movie", movie);
            request.setAttribute("showtime", showtime);
            request.setAttribute("hall", hall);

            request.getRequestDispatcher("checkout.jsp?showtimeId=" + showtimeId).forward(request, response);
            
        } else {
            response.sendRedirect("login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Servlet that generates the checkout details and forwards it to the checkout.jsp page";
    }
}
