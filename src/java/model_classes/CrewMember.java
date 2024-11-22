package model_classes;

public class CrewMember {
    
    public enum Role {
        DIRECTOR,
        PRODUCER,
        WRITER,
        CINEMATOGRAPHER,
        EDITOR,
        OTHER
    }
    
    private int movieId;
    private String crewName;
    private Role role;
    
    // Constructors
    public CrewMember() {}
    
    public CrewMember(int movieId, String crewName, Role role) {
        this.movieId = movieId;
        this.crewName = crewName;
        this.role = role;
    }
    
    // Getters and Setters
    public int getMovieId() { return movieId; }
    public void setMovieId(int movieId) { this.movieId = movieId; }
    
    public String getCrewName() { return crewName; }
    public void setCrewName(String crewName) { this.crewName = crewName; }
    
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
}
