package model_classes;

public class CastMember {
    private int movieId;
    private String actorName;
    private String characterName;
    
    // Constructors
    public CastMember() {}
    
    public CastMember(int movieId, String actorName, String characterName) {
        this.movieId = movieId;
        this.actorName = actorName;
        this.characterName = characterName;
    }
    
    // Getters and Setters
    public int getMovieId() { return movieId; }
    public void setMovieId(int movieId) { this.movieId = movieId; }
    
    public String getActorName() { return actorName; }
    public void setActorName(String actorName) { this.actorName = actorName; }
    
    public String getCharacterName() { return characterName; }
    public void setCharacterName(String characterName) { this.characterName = characterName; }
}
