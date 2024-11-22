package model_classes;

import java.sql.Date;
import com.google.gson.Gson;

public class Movie {
    private int movieId;
    private String title;
    private String synopsis;
    private int languageId;
    private String[] genreIds;
    private int duration;
    private double rating;
    private Date releaseDate;
    private String[] cast;
    private String[] crew;
    private String posterUrl;
    private String trailerUrl;
    private Date createdAt;
    
    // Constructors
    public Movie() {}
    
    public Movie(String title, String synopsis, int languageId, String[] genreIds, int duration, double rating, Date releaseDate, String[] cast, String[] crew, String posterUrl, String trailerUrl, Date createdAt) {
        this.title = title;
        this.synopsis = synopsis;
        this.languageId = languageId;
        this.genreIds = genreIds;
        this.duration = duration;
        this.rating = rating;
        this.releaseDate = releaseDate;
        this.cast = cast;
        this.crew = crew;
        this.posterUrl = posterUrl;
        this.trailerUrl = trailerUrl;
        this.createdAt = createdAt;
    }
    
    // Getters and Setters
    public int getMovieId() { return movieId; }
    public void setMovieId(int movieId) { this.movieId = movieId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getSynopsis() { return synopsis; }
    public void setSynopsis(String synopsis) { this.synopsis = synopsis; }
    
    public int getLanguageId() { return languageId; }
    public void setLanguageId(int languageId) { this.languageId = languageId; }

    public String[] getGenreIds() { return genreIds; }
    public void setGenreIds(String[] genreIds) { this.genreIds = genreIds; }
    
    // Method to convert the genreIds array to a JSON string
    public String getGenreIdsAsJson() {
        Gson gson = new Gson();
        return gson.toJson(genreIds);  // Converts the array to a JSON string
    }
    // Method to set genreIds from a JSON string
    public void setGenreIdsFromJson(String json) {
        Gson gson = new Gson();
        this.genreIds = gson.fromJson(json, String[].class);  // Converts the JSON string back to an array
    }

    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    public Date getReleaseDate() { return releaseDate; }
    public void setReleaseDate(Date releaseDate) { this.releaseDate = releaseDate; }
    
    public String[] getCast() { return cast; }
    public void setCast(String[] cast) { this.cast = cast; }
    
    // Method to convert the cast array to a JSON string
    public String getCastAsJson() {
        Gson gson = new Gson();
        return gson.toJson(cast);  // Converts the array to a JSON string
    }
    // Method to set cast from a JSON string
    public void setCastFromJson(String json) {
        Gson gson = new Gson();
        this.cast = gson.fromJson(json, String[].class);  // Converts the JSON string back to an array
    }

    public String[] getCrew() { return crew; }
    public void setCrew(String[] crew) { this.crew = crew; }
    
    // Method to convert the cast array to a JSON string
    public String getCrewAsJson() {
        Gson gson = new Gson();
        return gson.toJson(crew);  // Converts the array to a JSON string
    }
    // Method to set cast from a JSON string
    public void setCrewFromJson(String json) {
        Gson gson = new Gson();
        this.crew = gson.fromJson(json, String[].class);  // Converts the JSON string back to an array
    }
    
    public String getPosterUrl() { return posterUrl; }
    public void setPosterUrl(String posterUrl) { this.posterUrl = posterUrl; }
    
    public String getTrailerUrl() { return trailerUrl; }
    public void setTrailerUrl(String trailerUrl) { this.trailerUrl = trailerUrl; }
    
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}
