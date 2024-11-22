package model_classes;

public class Genre {
    private int genreId;
    private String name;
    
    // Constructors
    public Genre() {}
    
    public Genre(String name) {
        this.name = name;
    }
    
    // Getters and Setters
    public int getGenreId() { return genreId; }
    public void setGenreId(int genreId) { this.genreId = genreId; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
