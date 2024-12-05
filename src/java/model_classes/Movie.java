package model_classes;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Movie {
    private int movieId;
    private String title;
    private String synopsis;
    private int languageId;
    private int[] genreIds;
    private int duration;
    private double rating;
    private Date releaseDate;
    private List<Map<String, String>> cast;
    private List<Map<String, String>> crew;
    private String posterUrl;
    private String trailerUrl;
    private Date createdAt;

    // Constructors
    public Movie() {}
    
    public Movie(String title, String synopsis, int languageId, int[] genreIds, int duration, double rating, Date releaseDate, List<Map<String, String>> cast, List<Map<String, String>> crew, String posterUrl, String trailerUrl) {
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
    }

    public Movie(String title, String synopsis, int languageId, int[] genreIds, int duration, double rating, Date releaseDate, List<Map<String, String>> cast, List<Map<String, String>> crew, String posterUrl, String trailerUrl, Date createdAt) {
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

    public int[] getGenreIds() { return genreIds; }
    public void setGenreIds(int[] genreIds) { this.genreIds = genreIds; }

    // Convert genreIds to JSON
    public String getGenreIdsAsJson() {
        Gson gson = new Gson();
        return gson.toJson(genreIds);
    }

    // Set genreIds from JSON
    public void setGenreIdsFromJson(String json) {
        Gson gson = new Gson();
        this.genreIds = gson.fromJson(json, int[].class);   // Converts JSON array back to int[]
    }

    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    public Date getReleaseDate() { return releaseDate; }
    public void setReleaseDate(Date releaseDate) { this.releaseDate = releaseDate; }

    public List<Map<String, String>> getCast() { return cast; }
    public void setCast(List<Map<String, String>> cast) { this.cast = cast; }

    // Convert cast to JSON
    public String getCastAsJson() {
        Gson gson = new Gson();
        return gson.toJson(cast); // Converts List<Map<String, String>> to JSON
    }

    // Set cast from JSON
    public void setCastFromJson(String json) {
        Gson gson = new Gson();
        this.cast = gson.fromJson(json, new TypeToken<List<Map<String, String>>>() {}.getType());
    }

    public List<Map<String, String>> getCrew() { return crew; }
    public void setCrew(List<Map<String, String>> crew) { this.crew = crew; }

    // Convert crew to JSON
    public String getCrewAsJson() {
        Gson gson = new Gson();
        return gson.toJson(crew); // Converts List<Map<String, String>> to JSON
    }

    // Set crew from JSON
    public void setCrewFromJson(String json) {
        Gson gson = new Gson();
        this.crew = gson.fromJson(json, new TypeToken<List<Map<String, String>>>() {}.getType());
    }

    public String getPosterUrl() { return posterUrl; }
    public void setPosterUrl(String posterUrl) { this.posterUrl = posterUrl; }

    public String getTrailerUrl() { return trailerUrl; }
    public void setTrailerUrl(String trailerUrl) { this.trailerUrl = trailerUrl; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}