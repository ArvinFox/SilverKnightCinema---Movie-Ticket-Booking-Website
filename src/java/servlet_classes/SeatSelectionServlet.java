package servlet_classes;

import dao_classes.CinemaDAO;
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
import dao_classes.SeatDAO;
import model_classes.Seat;
import dao_classes.HallDAO;
import jakarta.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.util.List;
import model_classes.Cinema;
import model_classes.Hall;
import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet(name = "SeatSelectionServlet", urlPatterns = {"/SeatSelectionServlet", "/seats"})
public class SeatSelectionServlet extends HttpServlet {

    private ShowtimeDAO showtimeDao;
    private HallDAO hallDao;
    private MovieDAO movieDao;
    private SeatDAO seatDao;
    private CinemaDAO cinemaDao;
    
    int showtimeId;
    
    @Override
    public void init(){
        showtimeDao = new ShowtimeDAO();
        movieDao = new MovieDAO();
        hallDao = new HallDAO();
        seatDao = new SeatDAO();
        cinemaDao = new CinemaDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Release expired seats before loading the page
        seatDao.releaseExpiredSeats();
    
        showtimeId = Integer.parseInt(request.getParameter("showtimeId"));
        Showtime showtime = showtimeDao.getShowtimeById(showtimeId);
        Movie movie = movieDao.getMovieById(showtime.getMovieId());
        Hall hall = hallDao.getHallById(showtime.getHallId());
        
        Cinema cinema = cinemaDao.getCinemaById(hall.getCinemaId());
        hall.setCinema(cinema.getName());

        // This already returns List<String>
        List<String> reservedSeatNumbers = seatDao.getReservedSeatNumbersByShowtime(showtimeId);

        request.setAttribute("reservedSeatNumbers", reservedSeatNumbers);
        request.setAttribute("showtime", showtime);
        request.setAttribute("movie", movie);
        request.setAttribute("hall", hall);
        request.getRequestDispatcher("movieSeatSelection.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        
        JSONObject json = new JSONObject(sb.toString());      
        JSONArray seatArray = json.getJSONArray("seats");
        int total = json.getInt("total");
        
        HttpSession session = request.getSession();

        session.setAttribute("seatArray", seatArray);
        session.setAttribute("seatCount", seatArray.length());
        session.setAttribute("total", total);
        
        for (Object item : seatArray) {
            String s = (String) item; // Cast to appropriate type
            Seat seat = new Seat();
            seat.setSeatNumber(s);
            seat.setSeatId(seatDao.getSeatIdByName(s, showtimeId));
            
            seatDao.updateSeatReserved(seat.getSeatId(), true);
        }
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
