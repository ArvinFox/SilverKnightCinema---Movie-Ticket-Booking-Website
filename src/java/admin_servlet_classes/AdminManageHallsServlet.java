package admin_servlet_classes;

import model_classes.Hall;
import model_classes.Hall.Type;
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

@WebServlet(name = "AdminManageHallsServlet", urlPatterns = {"/AdminManageHallsServlet", "/admin/manageHalls"})
public class AdminManageHallsServlet extends HttpServlet {
    private HallDAO hallDAO;
    private CinemaDAO cinemaDAO;
    
    @Override
    public void init() {
        hallDAO = new HallDAO();
        cinemaDAO = new CinemaDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        
        String name = request.getParameter("name");
        String type = request.getParameter("hallType");
        String minCapacityParam = request.getParameter("minCapacity");
        Integer minCapacity = minCapacityParam != null && !minCapacityParam.isEmpty() ? Integer.valueOf(minCapacityParam) : null;
        String maxCapacityParam = request.getParameter("maxCapacity");
        Integer maxCapacity = maxCapacityParam != null && !maxCapacityParam.isEmpty() ? Integer.valueOf(maxCapacityParam) : null;
        String cinemaId = request.getParameter("cinema");
        List<Hall> hallList;
        
        if (username != null) {
            if ((name != null && !name.trim().isEmpty()) || (type != null && !type.equals("any")) || (minCapacity != null) || (maxCapacity != null) || (cinemaId != null && !cinemaId.equals("any"))) {
                hallList = hallDAO.getSearchedHalls(name, type, minCapacity, maxCapacity, cinemaId);
            } else {
                hallList = hallDAO.getAllHalls();
            }
            
            for (Hall hall : hallList) {
                Cinema cinema = cinemaDAO.getCinemaById(hall.getCinemaId());
                hall.setCinema(cinema.getName() + " - " + cinema.getLocation());
            }
            
            request.setAttribute("hallList", hallList);
            request.setAttribute("hallTypes", Type.values());          
            request.setAttribute("cinemaList", cinemaDAO.getAllCinemas());          
            request.getRequestDispatcher("../adminView/manageHalls.jsp").forward(request, response);
            
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
                    Hall hall = extractHallFromRequest(request);
                    hallDAO.addHall(hall);
                    actionResponse = "Hall added successfully.";
                }
                
                case "update" -> {
                    int hallId = Integer.parseInt(request.getParameter("hallId"));
                    Hall hall = extractHallFromRequest(request);
                    hall.setHallId(hallId);
                    hallDAO.updateHall(hall);
                    actionResponse = "Hall updated successfully.";
                }
                
                case "delete" -> {
                    int hallId = Integer.parseInt(request.getParameter("hallId"));
                    hallDAO.deleteHall(hallId);
                    actionResponse = "Hall deleted successfully.";
                }
                
                default -> response.sendRedirect("login");
            }
            
            // Redirect the admin to manage halls panel (manageHalls), and display the action response 
            response.sendRedirect("manageHalls");
            
        } else {
            response.sendRedirect("login");
        }
    }
    
    private Hall extractHallFromRequest(HttpServletRequest request) {
        String name = request.getParameter("name");
        String type = request.getParameter("hallType");
        int capacity = Integer.parseInt(request.getParameter("capacity"));
        int cinemaId = Integer.parseInt(request.getParameter("cinema"));
        String hallUrl = request.getParameter("hallUrl");

        Hall hall = new Hall(name, Type.fromString(type), capacity, cinemaId, hallUrl);
        return hall;
    }

    @Override
    public String getServletInfo() {
        return "Servlet that manages halls, including add, view, edit, and delete functionalities.";
    }
}
