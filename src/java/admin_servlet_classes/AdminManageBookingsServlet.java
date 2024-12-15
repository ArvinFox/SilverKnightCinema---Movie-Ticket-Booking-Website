package admin_servlet_classes;

import model_classes.Booking;
import dao_classes.BookingDAO;
import model_classes.Seat;
import dao_classes.SeatDAO;
import dao_classes.MovieDAO;
import dao_classes.HallDAO;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

@WebServlet(name = "AdminManageBookingsServlet", urlPatterns = {"/AdminManageBookingsServlet", "/admin/manageBookings"})
public class AdminManageBookingsServlet extends HttpServlet {
    private BookingDAO bookingDAO;
    private SeatDAO seatDAO;
    private MovieDAO movieDAO;
    private HallDAO hallDAO;
    
    @Override
    public void init() {
        bookingDAO = new BookingDAO();
        seatDAO = new SeatDAO();
        movieDAO = new MovieDAO();
        hallDAO = new HallDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        
        String movieIdParam = request.getParameter("movieId");
        Integer movieId = movieIdParam != null && !movieIdParam.isEmpty() && !movieIdParam.equals("any") ? Integer.valueOf(movieIdParam) : null;
        String hallIdParam = request.getParameter("hallId");
        Integer hallId = hallIdParam != null && !hallIdParam.isEmpty() && !hallIdParam.equals("any") ? Integer.valueOf(hallIdParam) : null;
        String type = request.getParameter("type");
        String userInfo = request.getParameter("userInfo");
        String bookingDateFrom = request.getParameter("bookingDateFrom");
        String bookingDateTo = request.getParameter("bookingDateTo");
        List<Booking> bookingList;
        
        if (username != null) {
            if ((movieId != null) || (hallId != null) || (type != null && !type.equals("any")) || (userInfo != null && !userInfo.trim().isEmpty()) || (bookingDateFrom != null && !bookingDateFrom.trim().isEmpty()) || (bookingDateTo != null && !bookingDateTo.trim().isEmpty())) {
                bookingList = bookingDAO.getSearchedBookings(movieId, hallId, type, userInfo, bookingDateFrom, bookingDateTo);
                
            } else {
                bookingList = bookingDAO.getAllBookings();
            }
            
            request.setAttribute("bookingList", bookingList);
            for (Booking booking : bookingList) {
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
            }
            
            request.setAttribute("movieList", movieDAO.getAllMovies());
            request.setAttribute("hallList", hallDAO.getAllHalls());
            request.getRequestDispatcher("../adminView/manageBookings.jsp").forward(request, response);
            
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
                case "delete" -> {
                    int bookingId = Integer.parseInt(request.getParameter("bookingId"));
                    bookingDAO.deleteBooking(bookingId);
                    actionResponse = "Booking deleted successfully.";
                }
                
                default -> response.sendRedirect("login");
            }
            
            // Redirect the admin to manage bookings panel (manageBookings), and display the action response 
            response.sendRedirect("manageBookings");
            
        } else {
            response.sendRedirect("login");
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet that manages bookings, including view, and delete functionalities.";
    }
}
