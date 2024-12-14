package admin_servlet_classes;

import model_classes.Food;
import model_classes.Food.ItemType;
import dao_classes.FoodDAO;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

@WebServlet(name = "AdminManageFoodsServlet", urlPatterns = {"/AdminManageFoodsServlet", "/admin/manageFoods"})
public class AdminManageFoodsServlet extends HttpServlet {
    private FoodDAO foodDAO;
    
    @Override
    public void init() {
        foodDAO = new FoodDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        
        String itemName = request.getParameter("itemName");
        String itemType = request.getParameter("itemType");
        String minPriceParam = request.getParameter("minPrice");
        Double minPrice = minPriceParam != null && !minPriceParam.isEmpty() ? Double.valueOf(minPriceParam) : null;
        String maxPriceParam = request.getParameter("maxPrice");
        Double maxPrice = maxPriceParam != null && !maxPriceParam.isEmpty() ? Double.valueOf(maxPriceParam) : null;
        String minStockParam = request.getParameter("minStock");
        Integer minStock = minStockParam != null && !minStockParam.isEmpty() ? Integer.valueOf(minStockParam) : null;
        String maxStockParam = request.getParameter("maxStock");
        Integer maxStock = maxStockParam != null && !maxStockParam.isEmpty() ? Integer.valueOf(maxStockParam) : null;
        List<Food> foodItemList;
        
        if (username != null) {
            if ((itemName != null && !itemName.trim().isEmpty()) || (itemType != null && !itemType.equals("any")) || (minPrice != null) || (maxPrice != null) || (minStock != null) || (maxStock != null)) {
               foodItemList = foodDAO.getSearchedFoodItems(itemName, itemType, minPrice, maxPrice, minStock, maxStock);
            } else {
                foodItemList = foodDAO.getAllFoodItems();
            }
            
            request.setAttribute("foodItemList", foodItemList);
            request.setAttribute("itemTypes", ItemType.values());
            request.getRequestDispatcher("../adminView/manageFoods.jsp").forward(request, response);
            
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
                    Food foodItem = extractFoodItemFromRequest(request);
                    foodDAO.addFoodItem(foodItem);
                    actionResponse = "Item added successfully.";
                }
                
                case "update" -> {
                    int itemId = Integer.parseInt(request.getParameter("itemId"));
                    Food foodItem = extractFoodItemFromRequest(request);
                    foodItem.setItemId(itemId);
                    foodDAO.updateFoodItem(foodItem);
                    actionResponse = "Item updated successfully.";
                }
                
                case "delete" -> {
                    int itemId = Integer.parseInt(request.getParameter("itemId"));
                    foodDAO.deleteFoodItem(itemId);
                    actionResponse = "Item deleted successfully.";
                }
                
                default -> response.sendRedirect("login");
            }
            
            // Redirect the admin to manage foods panel (manageFoods), and display the action response 
            response.sendRedirect("manageFoods");
            
        } else {
            response.sendRedirect("login");
        }
    }
    
    private Food extractFoodItemFromRequest(HttpServletRequest request) {
        String itemName = request.getParameter("itemName");
        String itemType = request.getParameter("itemType");
        double price = Double.parseDouble(request.getParameter("price"));
        int stock = Integer.parseInt(request.getParameter("stock"));
        String itemUrl = request.getParameter("posterUrl");

        Food foodItem = new Food(itemName, ItemType.fromString(itemType), price, stock, itemUrl);
        return foodItem;
    }

    @Override
    public String getServletInfo() {
        return "Servlet that manages food items, including add, view, edit, and delete functionalities.";
    }
}
