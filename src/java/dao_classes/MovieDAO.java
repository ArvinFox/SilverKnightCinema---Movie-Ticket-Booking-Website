package dao_classes;

import utility_classes.DBConnection;
import model_classes.Movie;
import model_classes.Movie.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO {
    
    // Method to add a movie
    public void addMovie(Movie movie) {
        String query = "INSERT INTO movies (title, synopsis, languageId, genreIds, duration, rating, releaseDate, cast, crew, posterUrl, trailerUrl, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setString(1, movie.getTitle());
            ps.setString(2, movie.getSynopsis());
            ps.setInt(3, movie.getLanguageId());
            ps.setString(4, movie.getGenreIdsAsJson());
            ps.setInt(5, movie.getDuration());
            ps.setDouble(6, movie.getRating());
            ps.setDate(7, movie.getReleaseDate());
            ps.setString(8, movie.getCastAsJson());
            ps.setString(9, movie.getCrewAsJson());
            ps.setString(10, movie.getPosterUrl());
            ps.setString(11, movie.getTrailerUrl());
            ps.setString(12, movie.getStatus().toString());
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in adding movie: " + e.getMessage());
        }
    }
    
    // Method to get searched movies
    public List<Movie> getSearchedMovies(String title, String genre, String language, String releaseDateFrom, String releaseDateTo, String status) {
        List<Movie> movies = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM movies WHERE 1=1");
        
        List<Object> parameters = new ArrayList<>();
        
        if (title != null && !title.trim().isEmpty()) {
            query.append(" AND title LIKE ?");
            parameters.add("%" + title.trim() + "%");
        }
        
        if (genre != null && !genre.trim().isEmpty() && !genre.equals("any")) {
            query.append(" AND JSON_CONTAINS(genreIds, CAST(? AS JSON))");
            parameters.add("[" + Integer.valueOf(genre) + "]");
        }
        
        if (language != null && !language.trim().isEmpty() && !language.equals("any")) {
            query.append(" AND languageId = ?");
            parameters.add(Integer.valueOf(language));
        }
        
        if (releaseDateFrom != null && !releaseDateFrom.trim().isEmpty()) {
            query.append(" AND releaseDate >= ?");
            parameters.add(releaseDateFrom.trim());
        }
        
        if (releaseDateTo != null && !releaseDateTo.trim().isEmpty()) {
            query.append(" AND releaseDate <= ?");
            parameters.add(releaseDateTo.trim());
        }
        
        if (status != null && !status.trim().isEmpty() && !status.equals("any")) {
            query.append(" AND status = ?");
            parameters.add(status);
        }
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query.toString())) {
            
            for (int i = 0; i < parameters.size(); i++) {
                ps.setObject(i + 1, parameters.get(i));
            }
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Movie movie = populateMovie(rs);
                    movies.add(movie);
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching searched movies: " + e.getMessage());
        }
        
        return movies;
    }
    
    // Method to get movie by its id
    public Movie getMovieById(int movieId) {
        Movie movie = null;
        String query = "SELECT * FROM movies WHERE movieId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, movieId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    movie = populateMovie(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching movie by its ID: " + e.getMessage());
        }

        return movie;
    }
    
    // Method to update movie details
    public void updateMovie(Movie movie) {
        String query = "UPDATE movies SET title = ?, synopsis = ?, languageId = ?, genreIds = ?, duration = ?, rating = ?, releaseDate = ?, cast = ?, crew = ?, posterUrl = ?, trailerUrl = ?, status = ? WHERE movieId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setString(1, movie.getTitle());
            ps.setString(2, movie.getSynopsis());
            ps.setInt(3, movie.getLanguageId());
            ps.setString(4, movie.getGenreIdsAsJson());
            ps.setInt(5, movie.getDuration());
            ps.setDouble(6, movie.getRating());
            ps.setDate(7, movie.getReleaseDate());
            ps.setString(8, movie.getCastAsJson());
            ps.setString(9, movie.getCrewAsJson());
            ps.setString(10, movie.getPosterUrl());
            ps.setString(11, movie.getTrailerUrl());
            ps.setString(12, movie.getStatus().toString());
            ps.setInt(13, movie.getMovieId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in updating movie: " + e.getMessage());
        }
    }
    
    // Method to delete a movie
    public void deleteMovie(int movieId) {
        String query = "DELETE FROM movies WHERE movieId = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, movieId);
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in deleting movie: " + e.getMessage());
        }
    }
    
    // Method to delete movies by language
    public void deleteMoviesByLanguage(int languageId) {
        String query = "DELETE FROM movies WHERE languageId = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setInt(1, languageId);
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in deleting movies by language: " + e.getMessage());
        }
    }
    
    // Method to delete movies by genre
    public void deleteMoviesByGenre(int genreId) {
        String query = "DELETE FROM movies WHERE JSON_CONTAINS(genreIds, CAST(? AS JSON))";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setString(1, "[" + genreId + "]");
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in deleting movies by genre: " + e.getMessage());
        }
    }
    
    // Method to get all movies
    public List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();
        String query = "SELECT * FROM movies";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Movie movie = populateMovie(rs);
                movies.add(movie);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching movies: " + e.getMessage());
        }
        
        return movies;
    }
    
    // Method to get all movies that are now in theatres (now showing)
    public List<Movie> getNowShowingMovies() {
        List<Movie> movies = new ArrayList<>();
        String query = "SELECT * FROM movies WHERE status = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setString(1, Status.NOW_SHOWING.toString());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Movie movie = populateMovie(rs);
                    movies.add(movie);
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching movies that are currently screening: " + e.getMessage());
        }
        
        return movies;
    }
    
    // Method to get all coming soon movies
    public List<Movie> getComingSoonMovies() {
        List<Movie> movies = new ArrayList<>();
        String query = "SELECT * FROM movies WHERE status = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setString(1, Status.COMING_SOON.toString());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Movie movie = populateMovie(rs);
                    movies.add(movie);
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching coming-soon movies: " + e.getMessage());
        }
        
        return movies;
    }
    
    // Method to get all movies by language
    public List<Movie> getMoviesByLanguage(int languageId) {
        List<Movie> movies = new ArrayList<>();
        String query = "SELECT * FROM movies WHERE languageId = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setInt(1, languageId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Movie movie = populateMovie(rs);
                    movies.add(movie);
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching movies by language: " + e.getMessage());
        }
        
        return movies;
    }
    
    // Method to get all movies of a specific genre(s)
    public List<Movie> getMoviesByGenres(int[] genreIds) {
        List<Movie> movies = new ArrayList<>();

        StringBuilder query = new StringBuilder("SELECT * FROM movies WHERE ");

        for (int i = 0; i < genreIds.length; i++) {
            query.append("JSON_CONTAINS(genreIds, CAST(? AS JSON))");
            if (i < genreIds.length - 1) {
                query.append(" OR ");
            }
        }

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query.toString())) {

            for (int i = 0; i < genreIds.length; i++) {
                ps.setString(i + 1, "[" + genreIds[i] + "]");
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Movie movie = populateMovie(rs);
                    movies.add(movie);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching movies by genres: " + e.getMessage());
        }

        return movies;
    }
    
    // Method to get the total number of movies
    public int getTotalMovies() {
        int count = 0;
        String query = "SELECT COUNT(*) FROM movies";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            
            if (rs.next()) {
                count = rs.getInt(1);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error in fetching total movies count: " + e.getMessage());
        }
        
        return count;
    }
    
    // Utility method to populate Movie object from ResultSet
    private Movie populateMovie(ResultSet rs) throws SQLException {
        Movie movie = new Movie();
        movie.setMovieId(rs.getInt("movieId"));
        movie.setTitle(rs.getString("title"));
        movie.setSynopsis(rs.getString("synopsis"));
        movie.setLanguageId(rs.getInt("languageId"));
        
        String genreIds = rs.getString("genreIds");
        if (genreIds != null) {
            movie.setGenreIdsFromJson(genreIds);
        }
        
        movie.setDuration(rs.getInt("duration"));
        movie.setRating(rs.getInt("rating"));
        movie.setReleaseDate(rs.getDate("releaseDate"));
        
        String castJson = rs.getString("cast");
        if (castJson != null) {
            movie.setCastFromJson(castJson);
        }
        
        String crewJson = rs.getString("crew");
        if (crewJson != null) {
            movie.setCrewFromJson(crewJson);
        }
        
        movie.setPosterUrl(rs.getString("posterUrl"));
        movie.setTrailerUrl(rs.getString("trailerUrl"));
        movie.setStatus(Status.fromString(rs.getString("status")));
        movie.setCreatedAt(rs.getDate("createdAt"));
        
        return movie;
    }
}
