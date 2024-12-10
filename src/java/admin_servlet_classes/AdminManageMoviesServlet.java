package admin_servlet_classes;

import model_classes.Movie;
import model_classes.Movie.Status;
import dao_classes.MovieDAO;
import model_classes.Language;
import dao_classes.LanguageDAO;
import model_classes.Genre;
import dao_classes.GenreDAO;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.text.SimpleDateFormat;
import java.text.ParseException;

@WebServlet(name = "AdminManageMoviesServlet", urlPatterns = {"/AdminManageMoviesServlet", "/admin/manageMovies"})
public class AdminManageMoviesServlet extends HttpServlet {
    private MovieDAO movieDAO;
    private LanguageDAO languageDAO;
    private GenreDAO genreDAO;
    
    @Override
    public void init() {
        movieDAO = new MovieDAO();
        languageDAO = new LanguageDAO();
        genreDAO = new GenreDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        
        if (username != null) {
            List<Movie> movieList = movieDAO.getAllMovies();
            List<Language> languageList = languageDAO.getAllLanguages();
            List<Genre> genreList = genreDAO.getAllGenres();
            
            request.setAttribute("movieList", movieList);            
            request.setAttribute("languageList", languageList);            
            request.setAttribute("genreList", genreList);            
            request.getRequestDispatcher("../adminView/manageMovies.jsp").forward(request, response);
            
        } else {
            response.sendRedirect("login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        
        if (username != null) {
            String action = request.getParameter("action");
            String actionResponse = "Failed to perform operation.";
            
            if (null != action) switch (action) {
                case "add" -> {
                    Movie movie = extractMovieFromRequest(request);
                    movieDAO.addMovie(movie);
                    actionResponse = "Movie added successfully.";
                }
                
                case "update" -> {
                    int movieId = Integer.parseInt(request.getParameter("movieId"));
                    Movie movie = extractMovieFromRequest(request);
                    movie.setMovieId(movieId);
                    movieDAO.updateMovie(movie);
                    actionResponse = "Movie updated successfully.";
                }
                
                case "delete" -> {
                    int movieId = Integer.parseInt(request.getParameter("movieId"));
                    movieDAO.deleteMovie(movieId);
                    actionResponse = "Movie deleted successfully.";
                }
                
                default -> response.sendRedirect("login");
            }
            
            // Redirect the admin to manage movies panel (manageMovies), and display the action response 
            response.sendRedirect("manageMovies");
            
        } else {
            response.sendRedirect("login");
        }
    }
    
    private Movie extractMovieFromRequest(HttpServletRequest request) {
        String title = request.getParameter("title");
        String synopsis = request.getParameter("synopsis");
        int languageId = Integer.parseInt(request.getParameter("language"));

        String[] genres = request.getParameterValues("genres");
        int[] genreIds = new int[genres.length];

        for (int i = 0; i < genres.length; i++) {
            genreIds[i] = Integer.parseInt(genres[i]);
        }

        int duration = Integer.parseInt(request.getParameter("duration"));
        double rating = Double.parseDouble(request.getParameter("rating"));

        java.sql.Date releaseDate = null;
        try {
            String releaseDateStr = request.getParameter("releaseDate");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate = dateFormat.parse(releaseDateStr);
            releaseDate = new java.sql.Date(parsedDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String[] castActors = request.getParameterValues("castActor[]");
        String[] castCharacters = request.getParameterValues("castCharacter[]");
        List<Map<String, String>> cast = new ArrayList<>();

        for (int i = 0; i < castActors.length; i++) {
            Map<String, String> castMember = new HashMap<>();
            castMember.put("actor", castActors[i]);
            castMember.put("character", castCharacters[i]);
            cast.add(castMember);
        }

        String[] crewMembers = request.getParameterValues("crewMember[]");
        String[] crewRoles = request.getParameterValues("crewRole[]");
        List<Map<String, String>> crew = new ArrayList<>();

        for (int i = 0; i < crewMembers.length; i++) {
            Map<String, String> crewMember = new HashMap<>();
            crewMember.put("name", crewMembers[i]);
            crewMember.put("role", crewRoles[i]);
            crew.add(crewMember);
        }

        String posterUrl = request.getParameter("posterUrl");
        String trailerUrl = request.getParameter("trailerUrl");
        String status = request.getParameter("status");

        Movie movie = new Movie(title, synopsis, languageId, genreIds, duration, rating, releaseDate, cast, crew, posterUrl, trailerUrl, Status.valueOf(status));
        return movie;
    }

    @Override
    public String getServletInfo() {
        return "Servlet that manages movies, including add, view, edit, and delete functionalities.";
    }
}
