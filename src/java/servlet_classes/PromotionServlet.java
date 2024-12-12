
package servlet_classes;

import dao_classes.PromotionDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model_classes.Promotion;

/**
 *
 * @Kavindya De Silva
 */
@WebServlet(name = "PromotionServlet", urlPatterns = {"/PromotionServlet", "/dealsAndOffers"})
public class PromotionServlet extends HttpServlet {

    private PromotionDAO promotionDao;

    @Override
    public void init() {
        promotionDao = new PromotionDAO();
    }
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Promotion> activePromotion =  promotionDao.getAllActivePromotions();
        request.setAttribute("activePromotion", activePromotion);
        request.getRequestDispatcher("dealsAndOffers.jsp").forward(request,response);
    }

    
   
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
