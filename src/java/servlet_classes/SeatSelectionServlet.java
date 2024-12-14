package servlet_classes;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao_classes.ShowtimeDAO;
import model_classes.Showtime;
import dao_classes.MovieDAO;
import model_classes.Movie;
import dao_classes.HallDAO;
import model_classes.Hall;

@WebServlet(name = "SeatSelectionServlet", urlPatterns = {"/SeatSelectionServlet", "/seatselection"})
public class SeatSelectionServlet extends HttpServlet {

    private ShowtimeDAO showtimeDao;
    private HallDAO hallDao;
    private MovieDAO movieDao;
    
    @Override
    public void init(){
        showtimeDao = new ShowtimeDAO();
        movieDao = new MovieDAO();
        hallDao = new HallDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int showtimeId = Integer.parseInt(request.getParameter("showtimeId"));
        Showtime showtime = showtimeDao.getShowtimeById(showtimeId);
        Movie movie = movieDao.getMovieById(showtime.getMovieId());
        Hall hall = hallDao.getHallById(showtime.getHallId());
        
        request.setAttribute("showtime", showtime);
        request.setAttribute("movie", movie);
        request.setAttribute("hall", hall);
        request.getRequestDispatcher("movieSeatSelection.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
