package dao_classes;

import utility_classes.DBConnection;
import model_classes.Genre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenreDAO {
    
    // Method to add a genre
    public void addGenre(Genre genre) {
        String query = "INSERT INTO genres (name) VALUES (?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setString(1, genre.getName());
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in adding genre: " + e.getMessage());
        }
    }
    
    // Method to check if genre is already added
    public boolean isGenreAdded(String name) {
        boolean isAdded = false;
        String query = "SELECT COUNT(*) FROM genres WHERE name = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    isAdded = count > 0;
                }
            }
            
        }catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in checking whether genre is already added: " + e.getMessage());
        }
        
        return isAdded;
    }
    
    // Method to get genre by its name
    public Genre getGenreByName(String name) {
        Genre genre = null;
        String query = "SELECT * FROM genres WHERE name = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    genre = populateGenre(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching genre by its name: " + e.getMessage());
        }

        return genre;
    }
    
    // Method to get genres by their ids
    public List<Genre> getGenresByIds(int[] genreIds) {
        List<Genre> genres = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM genres WHERE genreId IN (");

        for (int i = 0; i < genreIds.length; i++) {
            queryBuilder.append("?");
            if (i < genreIds.length - 1) {
                queryBuilder.append(", ");
            }
        }
        queryBuilder.append(")");

        String query = queryBuilder.toString();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            for (int i = 0; i < genreIds.length; i++) {
                ps.setInt(i + 1, genreIds[i]);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Genre genre = populateGenre(rs);
                    genres.add(genre);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching genres by their IDs: " + e.getMessage());
        }

        return genres;
    }
    
    // Method to get genre by its id
    public Genre getGenreById(int genreId) {
        Genre genre = null;
        String query = "SELECT * FROM genres WHERE genreId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, genreId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    genre = populateGenre(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching genre by its ID: " + e.getMessage());
        }

        return genre;
    }
    
    // Method to update genre details
    public void updateGenre(Genre genre) {
        String query = "UPDATE genres SET name = ? WHERE genreId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setString(1, genre.getName());
            ps.setInt(2, genre.getGenreId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in updating genre: " + e.getMessage());
        }
    }
    
    // Method to delete a genre
    public void deleteGenre(int genreId) {
        String query = "DELETE FROM genres WHERE genreId = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, genreId);
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in deleting genre: " + e.getMessage());
        }
    }
    
    // Method to get all genres
    public List<Genre> getAllGenres() {
        List<Genre> genres = new ArrayList<>();
        String query = "SELECT * FROM genres";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Genre genre = populateGenre(rs);
                genres.add(genre);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching genres: " + e.getMessage());
        }
        
        return genres;
    }
    
    // Method to get the total number of genres
    public int getTotalGenres() {
        int count = 0;
        String query = "SELECT COUNT(*) FROM genres";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            
            if (rs.next()) {
                count = rs.getInt(1);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching total genres count: " + e.getMessage());
        }
        
        return count;
    }
    
    // Utility method to populate Genre object from ResultSet
    private Genre populateGenre(ResultSet rs) throws SQLException {
        Genre genre = new Genre();
        genre.setGenreId(rs.getInt("genreId"));
        genre.setName(rs.getString("name"));
        
        return genre;
    }
}
