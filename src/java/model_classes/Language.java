package model_classes;

public class Language {
    private int languageId;
    private String language;
    
    // Constructors
    public Language() {}
    
    public Language(String language) {
        this.language = language;
    }
    
    // Getters and Setters
    public int getLanguageId() { return languageId; }
    public void setLanguageId(int languageId) { this.languageId = languageId; }
    
    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }
}
