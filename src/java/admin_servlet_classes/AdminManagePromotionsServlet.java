package admin_servlet_classes;

import model_classes.Promotion;
import dao_classes.PromotionDAO;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet(name = "AdminManagePromotionsServlet", urlPatterns = {"/AdminManagePromotionsServlet", "/admin/managePromotions"})
public class AdminManagePromotionsServlet extends HttpServlet {
    private PromotionDAO promotionDAO;
    
    @Override
    public void init() {
        promotionDAO = new PromotionDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        
        String name = request.getParameter("name");
        String discountParam = request.getParameter("discount");
        Double discount = discountParam != null && !discountParam.isEmpty() ? Double.valueOf(discountParam) : null;
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String status = request.getParameter("status");
        List<Promotion> promotionList;
        
        if (username != null) {
            if ((name != null && !name.trim().isEmpty()) || (discount != null) || (startDate != null && !startDate.trim().isEmpty()) || (endDate != null && !endDate.trim().isEmpty()) || (status != null && !status.equals("any"))) {              
                promotionList = promotionDAO.getSearchedPromotions(name, discount, startDate, endDate, status);
            } else {
                promotionList = promotionDAO.getAllPromotions();
            }
            
            request.setAttribute("promotionList", promotionList);
            request.getRequestDispatcher("../adminView/managePromotions.jsp").forward(request, response);
            
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
                    Promotion promotion = extractPromotionFromRequest(request);
                    promotionDAO.createPromotion(promotion);
                    actionResponse = "Promotion added successfully.";
                }
                
                case "update" -> {
                    int promotionId = Integer.parseInt(request.getParameter("promotionId"));
                    Promotion promotion = extractPromotionFromRequest(request);
                    promotion.setPromotionId(promotionId);
                    promotionDAO.updatePromotion(promotion);
                    actionResponse = "Promotion updated successfully.";
                }
                
                case "delete" -> {
                    int promotionId = Integer.parseInt(request.getParameter("promotionId"));
                    promotionDAO.deletePromotion(promotionId);
                    actionResponse = "Promotion deleted successfully.";
                }
                
                default -> response.sendRedirect("login");
            }
            
            // Redirect the admin to manage promotions panel (managePromotions), and display the action response 
            response.sendRedirect("managePromotions");
            
        } else {
            response.sendRedirect("login");
        }
    }
    
    private Promotion extractPromotionFromRequest(HttpServletRequest request) {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String code = request.getParameter("code");
        double discount = Double.parseDouble(request.getParameter("discount"));
        String posterUrl = request.getParameter("posterUrl");
        
        java.sql.Date startDate = null;
        try {
            String startDateStr = request.getParameter("startDate");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate = dateFormat.parse(startDateStr);
            startDate = new java.sql.Date(parsedDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        java.sql.Date endDate = null;
        try {
            String endDateStr = request.getParameter("endDate");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate = dateFormat.parse(endDateStr);
            endDate = new java.sql.Date(parsedDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        String status = request.getParameter("status");
        boolean isActive = "active".equals(status);

        Promotion promotion = new Promotion(name, description, code, discount, posterUrl, startDate, endDate, isActive);        
        return promotion;
    }

    @Override
    public String getServletInfo() {
        return "Servlet that manages promotions, including add, view, edit, and delete functionalities.";
    }
}
