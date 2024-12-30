package servlet_classes;


import model_classes.Booking;
import model_classes.Movie;
import model_classes.Showtime;
import model_classes.User;
import model_classes.Hall;
import model_classes.Seat;
import dao_classes.BookingDAO;
import dao_classes.CinemaDAO;
import dao_classes.HallDAO;
import dao_classes.MovieDAO;
import dao_classes.SeatDAO;
import dao_classes.ShowtimeDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.net.URI;
import model_classes.Cinema;


/**
 *
 * @author Udani Indrachapa
 */
@WebServlet(name = "EticketServlet", urlPatterns = {"/EticketServlet", "/eticket"})
public class EticketServlet extends HttpServlet 
{
   private BookingDAO bookingDao;
   private ShowtimeDAO showtimeDao;
   private MovieDAO movieDao;
   private HallDAO hallDao;
   private SeatDAO seatDao;
   private CinemaDAO cinemaDao;
   
   private int showtimeId;

   @Override
   public void init()
   {
       bookingDao = new BookingDAO();
       showtimeDao = new ShowtimeDAO();
       movieDao = new MovieDAO();
       hallDao = new HallDAO();
       seatDao = new SeatDAO();
       cinemaDao = new CinemaDAO();
   }

   @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        showtimeId = Integer.parseInt(request.getParameter("showtimeId"));
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if(user != null)
        {
            String previousPageUrl = request.getHeader("Referer");

            if (previousPageUrl != null && !previousPageUrl.isEmpty()) 
            {
                try{
                    String id = BookingIdFromUrl(previousPageUrl);
                    id = "1";   // test
                    if(id != null)
                    {
                        ticketDetails(request, response, id);
                    }
                    else
                    {
                        response.sendRedirect("checkout?showtimeId=" + showtimeId);
                    }
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }
            }
            else{
                response.sendRedirect("checkout?showtimeId=" + showtimeId);
            }
        }
    }

    private String BookingIdFromUrl(String url)
    {
        try{
            // Parse the URL and extract the query string
            URI uri = new URI(url);
            String query = uri.getQuery(); // Get the query part of the URL (ex: "bookingId=1")

            if (query != null && !query.isEmpty()) {
                // Parse the query parameters
                String[] queryParams = query.split("&"); // Split query parameters (in case there are more than one)
                for (String param : queryParams) {
                    String[] pair = param.split("="); // Split each key-value pair
                    if (pair.length == 2 && "bookingId".equals(pair[0])) {
                        return pair[1];
                    }
                }
            }
        } 
        catch (Exception ex) 
        {
            ex.printStackTrace();
        }
        return null; //return null if booking id not found
    }

    private void ticketDetails(HttpServletRequest request, HttpServletResponse response, String id)
            throws ServletException, IOException
    {
        try{  
            int bId = Integer.parseInt(id);
            System.out.println("id :" +id);
            bId = 1;    // test

            Booking booking = bookingDao.getBookingById(bId);

            if(booking != null)
            {
                System.out.println(booking);
                showtimeId = booking.getShowtimeId();
                Showtime showtime = showtimeDao.getShowtimeById(showtimeId);
                showtime.setFormattedTime(showtime.getShowTime());
                showtime.setFormattedDate(showtime.getShowDate());

                int movieId = showtime.getMovieId();
                Movie movie = movieDao.getMovieById(movieId);

                int hallId = showtime.getHallId();
                Hall hall = hallDao.getHallById(hallId);
                
                Cinema cinema = cinemaDao.getCinemaById(hall.getCinemaId());

                String[] bookedSeats = booking.getBookedSeats();
                String seats = "";

                for (int i = 0; i < bookedSeats.length; i++) 
                {
                    int seatId = Integer.parseInt(bookedSeats[i]);
                    Seat seat = seatDao.getSeatById(seatId);
                    seats += seat.getSeatNumber();
                    if(i < bookedSeats.length-1)
                    {
                        seats += ", ";
                    }
                }

                request.setAttribute("movie", movie);
                request.setAttribute("showtime", showtime);
                request.setAttribute("booking", booking);
                request.setAttribute("hall", hall);
                request.setAttribute("cinema", cinema);
                request.setAttribute("bookedSeats", seats);
                request.getRequestDispatcher("eticketDownload.jsp").forward(request, response);
            }
            else{
                request.getRequestDispatcher("checkout?showtimeId=" + showtimeId).forward(request, response);
                System.out.println(2);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
