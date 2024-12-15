package dao_classes;

import utility_classes.DBConnection;
import model_classes.Food;
import model_classes.Food.ItemType;
import model_classes.FoodOrder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodDAO {
    
    // Method to add a food item
    public void addFoodItem(Food foodItem) {
        String query = "INSERT INTO foods (itemName, itemType, price, stock, itemUrl) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setString(1, foodItem.getItemName());
            ps.setString(2, foodItem.getItemType().toString());
            ps.setDouble(3, foodItem.getPrice());
            ps.setInt(4, foodItem.getStock());
            ps.setString(5, foodItem.getItemUrl());
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in adding food item: " + e.getMessage());
        }
    }
    
    // Method to get searched food items
    public List<Food> getSearchedFoodItems(String itemName, String itemType, Double minPrice, Double maxPrice, Integer minStock, Integer maxStock) {
        List<Food> foodItems = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM foods WHERE 1=1");
        
        List<Object> parameters = new ArrayList<>();
        
        if (itemName != null && !itemName.trim().isEmpty()) {
            query.append(" AND itemName LIKE ?");
            parameters.add("%" + itemName.trim() + "%");
        }
        
        if (itemType != null && !itemType.equalsIgnoreCase("any")) {
            query.append(" AND itemType = ?");
            parameters.add(itemType);
        }
        
        if (minPrice != null) {
            query.append(" AND price >= ?");
            parameters.add(minPrice);
        }
        
        if (maxPrice != null) {
            query.append(" AND price <= ?");
            parameters.add(maxPrice);
        }
        
        if (minStock != null) {
            query.append(" AND stock >= ?");
            parameters.add(minStock);
        }
        
        if (maxStock != null) {
            query.append(" AND stock <= ?");
            parameters.add(maxStock);
        }
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query.toString())) {
            
            for (int i = 0; i < parameters.size(); i++) {
                ps.setObject(i + 1, parameters.get(i));
            }
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Food foodItem = populateFoodItem(rs);
                    foodItems.add(foodItem);
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching searched food items: " + e.getMessage());
        }
        
        return foodItems;
    }
    
    // Method to get food item by its id
    public Food getFoodItemById(int itemId) {
        Food foodItem = null;
        String query = "SELECT * FROM foods WHERE itemId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, itemId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    foodItem = populateFoodItem(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching food item by its ID: " + e.getMessage());
        }

        return foodItem;
    }
    
    // Method to update food item details
    public void updateFoodItem(Food foodItem) {
        String query = "UPDATE foods SET itemName = ?, itemType = ?, price = ?, stock = ?, itemUrl = ? WHERE itemId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setString(1, foodItem.getItemName());
            ps.setString(2, foodItem.getItemType().toString());
            ps.setDouble(3, foodItem.getPrice());
            ps.setInt(4, foodItem.getStock());
            ps.setString(5, foodItem.getItemUrl());
            ps.setInt(6, foodItem.getItemId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in updating food item: " + e.getMessage());
        }
    }
    
    // Method to update stock of food item
    public void updateStock(int quantity, int itemId) {
        String stockQuery = "SELECT stock FROM foods WHERE itemId = ?";
        String query = "UPDATE foods SET stock = stock + ? WHERE itemId = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement psStock = conn.prepareStatement(stockQuery);
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            psStock.setInt(1, itemId);
            try(ResultSet rs = psStock.executeQuery())
            {
                if(rs.next())
                {
                    int stock = rs.getInt("stock");
                    
                    if(!(stock + quantity < 0 ))
                    {
                        ps.setInt(1, quantity);
                        ps.setInt(2, itemId);
                        ps.executeUpdate();
                    }
                    else{
                        System.out.println("Insufficient stock");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in updating stock of food item: " + e.getMessage());
        }
    }
    
    // Method to delete a food item
    public void deleteFoodItem(int itemId) {
        String query = "DELETE FROM foods WHERE itemId = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, itemId);
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in deleting food item: " + e.getMessage());
        }
    }
    
    // Method to get all food items
    public List<Food> getAllFoodItems() {
        List<Food> foodItems = new ArrayList<>();
        String query = "SELECT * FROM foods";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Food foodItem = populateFoodItem(rs);
                foodItems.add(foodItem);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching food items: " + e.getMessage());
        }
        
        return foodItems;
    }
    
    // Method to get all food items of a specific type
    public List<Food> getFoodItemsByType(String itemType) {
        List<Food> foodItems = new ArrayList<>();
        String query = "SELECT * FROM foods WHERE itemType = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setString(1, ItemType.valueOf(itemType).name());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Food foodItem = populateFoodItem(rs);
                    foodItems.add(foodItem);
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching food items of specfic type: " + e.getMessage());
        }
        
        return foodItems;
    }
    
    // Method to get the stock of a food item
    public int getStock(int itemId) {
        int stock = 0;
        String query = "SELECT stock FROM foods WHERE itemId = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setInt(1, itemId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    stock = rs.getInt("stock");
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching stock of food item: " + e.getMessage());
        }
        
        return stock;
    }
    
    // Method to get the total number of food items
    public int getTotalFoodItems() {
        int count = 0;
        String query = "SELECT COUNT(*) FROM foods";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            
            if (rs.next()) {
                count = rs.getInt(1);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching total food items count: " + e.getMessage());
        }
        
        return count;
    }
    
    // Method to get the total number of food items of a specific type
    public int getTotalFoodItemsByType(String itemType) {
        int count = 0;
        String query = "SELECT COUNT(*) FROM foods WHERE itemType = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setString(1, ItemType.valueOf(itemType).name());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt(1);
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching total food items count: " + e.getMessage());
        }
        
        return count;
    }
    
    // Utility method to populate FoodItem object from ResultSet
    private Food populateFoodItem(ResultSet rs) throws SQLException {
        Food foodItem = new Food();
        foodItem.setItemId(rs.getInt("itemId"));
        foodItem.setItemName(rs.getString("itemName"));
        foodItem.setItemType(ItemType.fromString(rs.getString("itemType")));
        foodItem.setPrice(rs.getDouble("price"));
        foodItem.setStock(rs.getInt("stock"));
        foodItem.setItemUrl(rs.getString("itemUrl"));
        foodItem.setCreatedAt(rs.getDate("createdAt"));
        foodItem.setUpdatedAt(rs.getDate("updatedAt"));
        
        return foodItem;
    }
    
    // Method to add a food item order
    public void addOrder(FoodOrder foodItemOrder) {
        String query = "INSERT INTO foodOrders (bookingId, itemId, quantity, pricePerItem, totalPrice) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setInt(1, foodItemOrder.getBookingId());
            ps.setInt(2, foodItemOrder.getItemId());
            ps.setInt(3, foodItemOrder.getQuantity());
            ps.setDouble(4, foodItemOrder.getPricePerItem());
            ps.setDouble(5, foodItemOrder.getTotalPrice());
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in adding order: " + e.getMessage());
        }
    }
    
    // Method to get food item order by its id
    public FoodOrder getOrderById(int orderId) {
        FoodOrder foodItemOrder = null;
        String query = "SELECT * FROM foodOrders WHERE orderId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, orderId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    foodItemOrder = populateOrder(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching order by its ID: " + e.getMessage());
        }

        return foodItemOrder;
    }
    
    // Method to get all food item orders of a specific booking
    public List<FoodOrder> getOrdersByBooking(int bookingId) {
        List<FoodOrder> foodItemOrders = new ArrayList<>();
        String query = "SELECT * FROM foodOrders WHERE bookingId = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setInt(1, bookingId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    FoodOrder foodItemOrder = populateOrder(rs);
                    foodItemOrders.add(foodItemOrder);
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching orders of specfic booking: " + e.getMessage());
        }
        
        return foodItemOrders;
    }
    
    // Method to get total price of food item orders of a specific booking
    public double getTotalPriceOfOrdersByBooking(int bookingId) {
        double totalPrice = 0;
        String query = "SELECT SUM(totalPrice) AS totalPriceByBooking FROM foodOrders WHERE bookingId = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setInt(1, bookingId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    totalPrice = rs.getDouble("totalPriceByBooking");
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching total price of orders of specific booking: " + e.getMessage());
        }
        
        return totalPrice;
    }
    
    // Method to get the total number of food item orders
    public int getTotalOrders() {
        int count = 0;
        String query = "SELECT COUNT(*) FROM foodOrders";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            
            if (rs.next()) {
                count = rs.getInt(1);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching total orders count: " + e.getMessage());
        }
        
        return count;
    }
    
    // Utility method to populate FoodItemOrder object from ResultSet
    private FoodOrder populateOrder(ResultSet rs) throws SQLException {
        FoodOrder foodItemOrder = new FoodOrder();
        foodItemOrder.setOrderId(rs.getInt("orderId"));
        foodItemOrder.setBookingId(rs.getInt("bookingId"));
        foodItemOrder.setItemId(rs.getInt("itemId"));
        foodItemOrder.setQuantity(rs.getInt("quantity"));
        foodItemOrder.setPricePerItem(rs.getDouble("pricePerItem"));
        foodItemOrder.setTotalPrice(rs.getDouble("totalPrice"));
        foodItemOrder.setCreatedAt(rs.getDate("createdAt"));
        
        return foodItemOrder;
    }
}
