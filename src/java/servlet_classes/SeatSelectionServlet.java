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
import dao_classes.SeatDAO;
import model_classes.Seat;
import dao_classes.HallDAO;
import jakarta.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import model_classes.Hall;
import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet(name = "SeatSelectionServlet", urlPatterns = {"/SeatSelectionServlet", "/seatSelection"})
public class SeatSelectionServlet extends HttpServlet {

    private ShowtimeDAO showtimeDao;
    private HallDAO hallDao;
    private MovieDAO movieDao;
    private SeatDAO seatDao;
    
    int showtimeId;
    
    @Override
    public void init(){
        showtimeDao = new ShowtimeDAO();
        movieDao = new MovieDAO();
        hallDao = new HallDAO();
        seatDao = new SeatDAO();
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
        System.out.println("Selected Seats: " + seatArray);
        
        HttpSession session = request.getSession();
        
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        JSONObject responseJson = new JSONObject();
        responseJson.put("message", "Seats booked successfully!");
        responseJson.put("bookedSeats", seatArray);
        session.setAttribute("seatArray", seatArray);
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
