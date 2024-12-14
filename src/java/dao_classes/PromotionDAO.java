package dao_classes;

import utility_classes.DBConnection;
import model_classes.Promotion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PromotionDAO {
    
    // Method to create a promotion
    public void createPromotion(Promotion promotion) {
        String query = "INSERT INTO promotions (name, description, code, discount, posterUrl, startDate, endDate, isActive) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setString(1, promotion.getName());
            ps.setString(2, promotion.getDescription());
            ps.setString(3, promotion.getCode());
            ps.setDouble(4, promotion.getDiscount());
            ps.setString(5, promotion.getPosterUrl());
            ps.setDate(6, promotion.getStartDate());
            ps.setDate(7, promotion.getEndDate());
            ps.setBoolean(8, promotion.getIsActive());
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in creating promotion: " + e.getMessage());
        }
    }
    
    // Method to get searched promotions
    public List<Promotion> getSearchedPromotions(String name, Double minDiscount, Double maxDiscount, String startDate, String endDate, String status) {
        List<Promotion> promotions = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM promotions WHERE 1=1");
        
        List<Object> parameters = new ArrayList<>();
        
        if (name != null && !name.trim().isEmpty()) {
            query.append(" AND name LIKE ?");
            parameters.add("%" + name.trim() + "%");
        }
        
        if (minDiscount != null) {
            query.append(" AND discount >= ?");
            parameters.add(minDiscount);
        }
        
        if (maxDiscount != null) {
            query.append(" AND discount <= ?");
            parameters.add(maxDiscount);
        }
        
        if (startDate != null && !startDate.trim().isEmpty()) {
            query.append(" AND startDate >= ?");
            parameters.add(java.sql.Date.valueOf(startDate));
        }
        
        if (endDate != null && !endDate.trim().isEmpty()) {
            query.append(" AND endDate <= ?");
            parameters.add(java.sql.Date.valueOf(endDate));
        }
        
        if (status != null && !status.trim().equalsIgnoreCase("any")) {
            query.append(" AND isActive = ?");
            parameters.add(status.equalsIgnoreCase("Active"));
        }
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query.toString())) {
            
            for (int i = 0; i < parameters.size(); i++) {
                ps.setObject(i + 1, parameters.get(i));
            }
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Promotion promotion = populatePromotion(rs);
                    promotions.add(promotion);
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching searched promotions: " + e.getMessage());
        }
        
        return promotions;
    }
    
    // Method to get promotion by its code
    public Promotion getPromotionByCode(String promotionCode) {
        Promotion promotion = null;
        String query = "SELECT * FROM promotions WHERE code = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, promotionCode);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    promotion = populatePromotion(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching promotion by its code: " + e.getMessage());
        }

        return promotion;
    }
    
    // Method to get promotion by its id
    public Promotion getPromotionById(int promotionId) {
        Promotion promotion = null;
        String query = "SELECT * FROM promotions WHERE promotionId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, promotionId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    promotion = populatePromotion(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching promotion by its ID: " + e.getMessage());
        }

        return promotion;
    }
    
    // Method to update promotion details
    public void updatePromotion(Promotion promotion) {
        String query = "UPDATE promotions SET name = ?, description = ?, code = ?, discount = ?, posterUrl = ?, startDate = ?, endDate = ?, isActive = ? WHERE promotionId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setString(1, promotion.getName());
            ps.setString(2, promotion.getDescription());
            ps.setString(3, promotion.getCode());
            ps.setDouble(4, promotion.getDiscount());
            ps.setString(5, promotion.getPosterUrl());
            ps.setDate(6, promotion.getStartDate());
            ps.setDate(7, promotion.getEndDate());
            ps.setBoolean(8, promotion.getIsActive());
            ps.setInt(9, promotion.getPromotionId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in updating promotion: " + e.getMessage());
        }
    }
    
    // Method to delete a promotion
    public void deletePromotion(int promotionId) {
        String query = "DELETE FROM promotions WHERE promotionId = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, promotionId);
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in deleting promotion: " + e.getMessage());
        }
    }
    
    // Method to get all promotions
    public List<Promotion> getAllPromotions() {
        List<Promotion> promotions = new ArrayList<>();
        String query = "SELECT * FROM promotions";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Promotion promotion = populatePromotion(rs);
                promotions.add(promotion);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching promotions: " + e.getMessage());
        }
        
        return promotions;
    }
    
    // Method to get all active promotions
    public List<Promotion> getAllActivePromotions() {
        List<Promotion> activePromotions = new ArrayList<>();
        String query = "SELECT * FROM promotions WHERE isActive = TRUE";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Promotion promotion = populatePromotion(rs);
                activePromotions.add(promotion);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching promotions: " + e.getMessage());
        }
        
        return activePromotions;
    }
    
    
    // Method to get the total number of promotions
    public int getTotalPromotions() {
        int count = 0;
        String query = "SELECT COUNT(*) FROM promotions";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            
            if (rs.next()) {
                count = rs.getInt(1);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching total promotions count: " + e.getMessage());
        }
        
        return count;
    }
    
    // Method to get the total number of active promotions
    public int getTotalActivePromotions() {
        int count = 0;
        String query = "SELECT COUNT(*) FROM promotions WHERE isActive = TRUE";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            
            if (rs.next()) {
                count = rs.getInt(1);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching total active promotions count: " + e.getMessage());
        }
        
        return count;
    }
    
    // Utility method to populate Promotion object from ResultSet
    private Promotion populatePromotion(ResultSet rs) throws SQLException {
        Promotion promotion = new Promotion();
        promotion.setPromotionId(rs.getInt("promotionId"));
        promotion.setName(rs.getString("name"));
        promotion.setDescription(rs.getString("description"));
        promotion.setCode(rs.getString("code"));
        promotion.setDiscount(rs.getDouble("discount"));
        promotion.setPosterUrl(rs.getString("posterUrl"));
        promotion.setStartDate(rs.getDate("startDate"));
        promotion.setEndDate(rs.getDate("endDate"));
        promotion.setIsActive(rs.getBoolean("isActive"));
        
        return promotion;
    }
}
