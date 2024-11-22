package model_classes;

import java.sql.Date;
import java.sql.Time;

public class Showtime {
    private int showtimeId;
    private int hallId;
    private int movieId;
    private Date showDate;
    private Time showTime;
    
    // Constructors
    public Showtime() {}
    
    public Showtime(int hallId, int movieId, Date showDate, Time showTime) {
        this.hallId = hallId;
        this.movieId = movieId;
        this.showDate = showDate;
        this.showTime = showTime;
    }
    
    // Getters and Setters
    public int getShowtimeId() { return showtimeId; }
    public void setShowtimeId(int showtimeId) { this.showtimeId = showtimeId; }
    
    public int getHallId() { return hallId; }
    public void setHallId(int hallId) { this.hallId = hallId; }

    public int getMovieId() { return movieId; }
    public void setMovieId(int movieId) { this.movieId = movieId; }

    public Date getShowDate() { return showDate; }
    public void setShowDate(Date showDate) { this.showDate = showDate; }

    public Time getShowTime() { return showTime; }
    public void setShowTime(Time showTime) { this.showTime = showTime; }
}
