package servlet_classes;

import model_classes.Food;
import model_classes.FoodOrder;
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
        
        int bookingId = Integer.parseInt(request.getParameter("bookingId"));
        
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
    }
}

class CartItem {
    private String itemId;
    private String itemName;
    private int qty;
    private double price;

    // Getters and setters
    public String getItemId() {
        return itemId;
    }
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public int getQty() {
        return qty;
    }
    public void setQty(int qty) {
        this.qty = qty;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
}
