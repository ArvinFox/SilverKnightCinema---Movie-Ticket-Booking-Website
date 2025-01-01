package servlet_classes;


import model_classes.Booking;
import model_classes.Movie;
import model_classes.Showtime;
import model_classes.User;
import model_classes.Hall;
import model_classes.CartItem;
import model_classes.FoodOrder;
import model_classes.Cinema;
import dao_classes.FoodDAO;
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
import java.util.List;
import model_classes.Seat;
import org.json.JSONArray;


/**
 *
 * @author Udani Indrachapa
 */
@WebServlet(name = "EticketServlet", urlPatterns = {"/EticketServlet", "/eticket"})
public class EticketServlet extends HttpServlet {
   private BookingDAO bookingDao;
   private ShowtimeDAO showtimeDao;
   private MovieDAO movieDao;
   private HallDAO hallDao;
   private CinemaDAO cinemaDao;
   private FoodDAO foodDao;
   private SeatDAO seatDao;

   @Override
   public void init() {
       bookingDao = new BookingDAO();
       showtimeDao = new ShowtimeDAO();
       movieDao = new MovieDAO();
       hallDao = new HallDAO();
       cinemaDao = new CinemaDAO();
       foodDao = new FoodDAO();
       seatDao = new SeatDAO();
   }

   @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        String showtimeIdParam = request.getParameter("showtimeId");
                
        if (user != null && showtimeIdParam != null) {
            int showtimeId = Integer.parseInt(showtimeIdParam);
            
            JSONArray bookedSeatsArrayArray = (JSONArray) session.getAttribute("seatArray");

            String[] bookedSeats = new String[bookedSeatsArrayArray.length()];
            for (int i = 0; i < bookedSeatsArrayArray.length(); i++) {
                String seatNumber = bookedSeatsArrayArray.getString(i);
                int seatId = seatDao.getSeatIdByName(seatNumber, showtimeId);
                bookedSeats[i] = String.valueOf(seatId);
            }
            
            double totalPrice = Double.parseDouble(request.getParameter("total"));
            
            Booking booking = new Booking();
            booking.setUserId(user.getUserId());
            booking.setShowtimeId(showtimeId);
            booking.setBookedSeats(bookedSeats);
            booking.setTotalPrice(totalPrice);
            booking.setIsPaid(true);
                        
            int bookingId = bookingDao.createBooking(booking);
            
            List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cartItems");
            if (cartItems != null) {
                for (CartItem item : cartItems) {
                    FoodOrder foodOrder = new FoodOrder();
                    foodOrder.setBookingId(bookingId);
                    foodOrder.setItemId(Integer.parseInt(item.getItemId()));
                    foodOrder.setQuantity(item.getQty());
                    foodOrder.setPricePerItem(item.getPrice());
                    foodOrder.setTotalPrice(item.getPrice() * item.getQty());
                    foodDao.addOrder(foodOrder);
                    foodDao.updateStock(-item.getQty(), Integer.parseInt(item.getItemId()));
                }
                
                session.removeAttribute("cartItems");
            }
            
            Showtime showtime = showtimeDao.getShowtimeById(showtimeId);
            showtime.setFormattedTime(showtime.getShowTime());
            showtime.setFormattedDate(showtime.getShowDate());

            int movieId = showtime.getMovieId();
            Movie movie = movieDao.getMovieById(movieId);

            int hallId = showtime.getHallId();
            Hall hall = hallDao.getHallById(hallId);

            Cinema cinema = cinemaDao.getCinemaById(hall.getCinemaId());

            String seats = "";
            Booking createdBooking = bookingDao.getBookingById(bookingId);
            String[] seatIds = createdBooking.getBookedSeats();
            for (int i = 0; i < seatIds.length; i++) 
            {
                String seatId = seatIds[i];
                Seat seat = seatDao.getSeatById(Integer.parseInt(seatId));
                String seatNumber = seat.getSeatNumber();
                seats += seatNumber;
                if (i < bookedSeats.length-1) {
                    seats += ", ";
                }
            }
            
            session.removeAttribute("seatArray");
            session.removeAttribute("total");
            session.removeAttribute("seatCount");

            request.setAttribute("movie", movie);
            request.setAttribute("showtime", showtime);
            request.setAttribute("bookingId", bookingId);
            request.setAttribute("hall", hall);
            request.setAttribute("cinema", cinema);
            request.setAttribute("bookedSeats", seats);
            request.getRequestDispatcher("eticketDownload.jsp").forward(request, response);
            
        } else {
            response.sendRedirect("login");
        }
    }
}
