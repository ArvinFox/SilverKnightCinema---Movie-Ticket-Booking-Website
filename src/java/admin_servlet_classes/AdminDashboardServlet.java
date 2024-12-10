package admin_servlet_classes;

import dao_classes.*;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "AdminDashboardServlet", urlPatterns = {"/AdminDashboardServlet", "/admin/dashboard"})
public class AdminDashboardServlet extends HttpServlet {
    private AdminDAO adminDAO;
    private BookingDAO bookingDAO;
    private FoodDAO foodDAO;
    private GenreDAO genreDAO;
    private GuestDAO guestDAO;
    private HallDAO hallDAO;
    private InquiryDAO inquiryDAO;
    private LanguageDAO languageDAO;
    private MovieDAO movieDAO;
    private PaymentDAO paymentDAO;
    private PromotionDAO promotionDAO;
    private SeatDAO seatDAO;
    private ShowtimeDAO showtimeDAO;
    private UserDAO userDAO;
    
    @Override
    public void init() {
        adminDAO = new AdminDAO();
        bookingDAO = new BookingDAO();
        foodDAO = new FoodDAO();
        genreDAO = new GenreDAO();
        guestDAO = new GuestDAO();
        hallDAO = new HallDAO();
        inquiryDAO = new InquiryDAO();
        languageDAO = new LanguageDAO();
        movieDAO = new MovieDAO();
        paymentDAO = new PaymentDAO();
        promotionDAO = new PromotionDAO();
        seatDAO = new SeatDAO();
        showtimeDAO = new ShowtimeDAO();
        userDAO = new UserDAO();
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        if(username != null) {
            
            int totalUsers = userDAO.getTotalUsers();
            int totalActiveUsers = userDAO.getTotalActiveUsers();
            int totalGuests = guestDAO.getTotalGuests();
            int totalBookings = bookingDAO.getTotalBookings();
            int totalPromotions = promotionDAO.getTotalPromotions();
            int totalActivePromotions = promotionDAO.getTotalActivePromotions();
            int totalGenres = genreDAO.getTotalGenres();
            int totalLanguages = languageDAO.getTotalLanguages();
            int totalMovies = movieDAO.getTotalMovies();
            int totalHalls = hallDAO.getTotalHalls();
            int totalFoodItems = foodDAO.getTotalFoodItems();
            int totalInquiries = inquiryDAO.getTotalInquiries();
            int totalAdmins = adminDAO.getTotalAdmins();
            
            request.setAttribute("totalUsers", totalUsers);
            request.setAttribute("totalActiveUsers", totalActiveUsers);
            request.setAttribute("totalGuests", totalGuests);
            request.setAttribute("totalBookings", totalBookings);
            request.setAttribute("totalPromotions", totalPromotions);
            request.setAttribute("totalActivePromotions", totalActivePromotions);
            request.setAttribute("totalGenres", totalGenres);
            request.setAttribute("totalLanguages", totalLanguages);
            request.setAttribute("totalMovies", totalMovies);
            request.setAttribute("totalHalls", totalHalls);
            request.setAttribute("totalFoodItems", totalFoodItems);
            request.setAttribute("totalInquiries", totalInquiries);
            request.setAttribute("totalAdmins", totalAdmins);
            
            request.getRequestDispatcher("../adminView/dashboard.jsp").forward(request, response);
            
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
        return "Short description";
    }

}
