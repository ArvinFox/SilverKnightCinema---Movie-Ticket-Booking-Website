package servlet_classes;

import model_classes.Food;
import model_classes.User;
import dao_classes.FoodDAO;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.BufferedReader;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import model_classes.CartItem;

/**
 *
 * @author Udani Indrachapa
 */
@WebServlet(name = "CartServlet", urlPatterns = {"/CartServlet","/cart"})
public class CartServlet extends HttpServlet 
{
    private FoodDAO foodDao;

    @Override
    public void init()
    {
        foodDao = new FoodDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        if(user != null)
        {
            List<Food> foodList;
            String itemType = request.getParameter("itemType");

            if (itemType == null || itemType.trim().isEmpty()) 
            {
                itemType = "BEVERAGE";
                foodList = foodDao.getFoodItemsByType(itemType);
                request.setAttribute("foodList", foodList);
                request.getRequestDispatcher("foodsAndBeverages.jsp").forward(request, response);
            } 
            else 
            {
                itemType = itemType.trim().toUpperCase();
            }

            foodList = foodDao.getFoodItemsByType(itemType);
            request.setAttribute("foodList", foodList);
           
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            try (PrintWriter out = response.getWriter())
            {
                out.write("[");
                for (int i = 0; i < foodList.size(); i++) 
                {
                    Food food = foodList.get(i);
                    String foodJson = String.format(
                        "{\"itemName\": \"%s\", \"price\": %.2f, \"itemUrl\": \"%s\", \"stock\": %d, \"itemId\": %d}",
                        food.getItemName(), food.getPrice(), food.getItemUrl(), food.getStock(), food.getItemId()
                    );
                    out.write(foodJson);

                    if (i < foodList.size() - 1) 
                    {
                        out.write(",");
                    }
                }
                out.write("]");
            }
        }
        else
        {
            response.sendRedirect("login");
        }
    }

     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        BufferedReader reader = request.getReader();
        Gson gson = new Gson();
        List<CartItem> cartItems = gson.fromJson(reader, new TypeToken<List<CartItem>>() {}.getType());
        
        HttpSession session = request.getSession();
        session.setAttribute("cartItems", cartItems);
    }
}
