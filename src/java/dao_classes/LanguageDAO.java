package dao_classes;

import utility_classes.DBConnection;
import model_classes.Language;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LanguageDAO {
    
    // Method to add a language
    public void addLanguage(Language language) {
        String query = "INSERT INTO languages (language) VALUES (?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setString(1, language.getLanguage());
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in adding language: " + e.getMessage());
        }
    }
    
    // Method to get searched languages
    public List<Language> getSearchedLanguages(String searchQuery) {
        List<Language> languages = new ArrayList<>();
        String query = "SELECT * FROM languages WHERE language LIKE ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setString(1, "%" + searchQuery.trim() + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Language language = populateLanguage(rs);
                    languages.add(language);
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching searched languages: " + e.getMessage());
        }
        
        return languages;
    }
    
    // Method to check if language is already added
    public boolean isLanguageAdded(String languageName) {
        boolean isAdded = false;
        String query = "SELECT COUNT(*) FROM languages WHERE language = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setString(1, languageName);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    isAdded = count > 0;
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in checking whether language is already added: " + e.getMessage());
        }
        
        return isAdded;
    }
    
    // Method to get language by its name
    public Language getLanguageByName(String languageName) {
        Language language = null;
        String query = "SELECT * FROM languages WHERE language = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, languageName);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    language = populateLanguage(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching language by its name: " + e.getMessage());
        }

        return language;
    }
    
    // Method to get language by its id
    public Language getLanguageById(int languageId) {
        Language language = null;
        String query = "SELECT * FROM languages WHERE languageId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, languageId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    language = populateLanguage(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching language by its ID: " + e.getMessage());
        }

        return language;
    }
    
    
    // Method to update language details
    public void updateLanguage(Language language) {
        String query = "UPDATE languages SET language = ? WHERE languageId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setString(1, language.getLanguage());
            ps.setInt(2, language.getLanguageId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in updating language: " + e.getMessage());
        }
    }
    
    // Method to delete a language
    public void deleteLanguage(int languageId) {
        String query = "DELETE FROM languages WHERE languageId = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, languageId);
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in deleting language: " + e.getMessage());
        }
    }
    
    // Method to get all languages
    public List<Language> getAllLanguages() {
        List<Language> languages = new ArrayList<>();
        String query = "SELECT * FROM languages";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Language language = populateLanguage(rs);
                languages.add(language);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching languages: " + e.getMessage());
        }
        
        return languages;
    }
    
    // Method to get the total number of languages
    public int getTotalLanguages() {
        int count = 0;
        String query = "SELECT COUNT(*) FROM languages";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            
            if (rs.next()) {
                count = rs.getInt(1);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching total languages count: " + e.getMessage());
        }
        
        return count;
    }
    
    // Utility method to populate Language object from ResultSet
    private Language populateLanguage(ResultSet rs) throws SQLException {
        Language language = new Language();
        language.setLanguageId(rs.getInt("languageId"));
        language.setLanguage(rs.getString("language"));
        
        return language;
    }
}
