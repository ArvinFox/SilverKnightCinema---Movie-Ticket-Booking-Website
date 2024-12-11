package admin_servlet_classes;

import com.google.gson.*;

import model_classes.Movie;
import dao_classes.MovieDAO;
import model_classes.Language;
import dao_classes.LanguageDAO;
import model_classes.Genre;
import dao_classes.GenreDAO;
import model_classes.User;
import dao_classes.UserDAO;
import model_classes.Promotion;
import dao_classes.PromotionDAO;
import model_classes.Inquiry;
import dao_classes.InquiryDAO;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
// import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "FetchDataServlet", urlPatterns = {"/FetchDataServlet", "/fetch"})
public class FetchDataServlet extends HttpServlet {
    private MovieDAO movieDAO;
    private LanguageDAO languageDAO;
    private GenreDAO genreDAO;
    private UserDAO userDAO;
    private PromotionDAO promotionDAO;
    private InquiryDAO inquiryDAO;
    
    @Override
    public void init() {
        movieDAO = new MovieDAO();
        languageDAO = new LanguageDAO();
        genreDAO = new GenreDAO();
        userDAO = new UserDAO();
        promotionDAO = new PromotionDAO();
        inquiryDAO = new InquiryDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Map<String, Object> result = new HashMap<>();
        
        String languageIdParam = request.getParameter("languageId");
        String genreIdParam = request.getParameter("genreId");
        
        if (languageIdParam != null || genreIdParam != null) {
            if (languageIdParam != null) {
                int languageId = Integer.parseInt(languageIdParam);
                List<Movie> moviesByLanguage = movieDAO.getMoviesByLanguage(languageId);
                result.put("movies", moviesByLanguage);
            } else if (genreIdParam != null) {
                /*
                int[] genreIds = Arrays.stream(genreIdParam.split(","))
                                        .mapToInt(Integer::parseInt)
                                        .toArray();
                */
                int genreId = Integer.parseInt(genreIdParam);
                List<Movie> moviesByGenre = movieDAO.getMoviesByGenres(new int[] {genreId});
                result.put("movies", moviesByGenre);
            }
            
        } else {
            List<Language> languages = languageDAO.getAllLanguages();
            List<Genre> genres = genreDAO.getAllGenres();
            
            result.put("languages", languages);
            result.put("genres", genres);
        }
        
        // Convert lists to JSON
        Gson gson = new Gson();
        String jsonResponse = gson.toJson(result);

        // Write JSON response
        response.setContentType("application/json");
        response.getWriter().write(jsonResponse);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Read the JSON body
        StringBuilder jsonBody = new StringBuilder();
        String line;
        try (var reader = request.getReader()) {
            while ((line = reader.readLine()) != null) {
                jsonBody.append(line);
            }
        }

        // Parse the JSON body to extract the "id" and "type" fields
        Integer id = null;
        String type = null;
        if (jsonBody.length() > 0) {
            try {
                JsonObject jsonObject = JsonParser.parseString(jsonBody.toString()).getAsJsonObject();
                id = Integer.valueOf(jsonObject.get("id").getAsString());
                type = jsonObject.get("type").getAsString();
            } catch (JsonSyntaxException | NumberFormatException e) {
                e.printStackTrace();
            }
        }
        
        if (type != null && id != null) {
            switch (type) {
                case "Movie" -> {
                    Movie movie = movieDAO.getMovieById(id);
                    Language language = languageDAO.getLanguageById(movie.getLanguageId());
                    String movieLanguage;
                    if (language == null) {
                        movieLanguage = "Unknown Language";
                    } else {
                        movieLanguage = language.getLanguage();
                    }
                    List<Genre> movieGenres = genreDAO.getGenresByIds(movie.getGenreIds());
                    request.setAttribute("movie", movie);
                    request.setAttribute("movieLanguage", movieLanguage);
                    request.setAttribute("movieGenres", movieGenres);
                    request.getRequestDispatcher("/adminView/viewMovie.jsp").forward(request, response);
                }
                
                case "User" -> {
                    User user = userDAO.getUserById(id);
                    request.setAttribute("user", user);
                    request.getRequestDispatcher("/adminView/viewUser.jsp").forward(request, response);
                }
                
                case "Promotion" -> {
                    Promotion promotion = promotionDAO.getPromotionById(id);
                    request.setAttribute("promotion", promotion);
                    request.getRequestDispatcher("/adminView/viewPromotion.jsp").forward(request, response);
                }
                
                case "Inquiry" -> {
                    Inquiry inquiry = inquiryDAO.getInquiryById(id);
                    request.setAttribute("inquiry", inquiry);
                    request.getRequestDispatcher("/adminView/viewInquiry.jsp").forward(request, response);
                }
                
                default -> response.getWriter().println("<h1>No data available.</h1>");
            }
        } else {
            response.getWriter().println("<h1>No data available.</h1>");
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet used to fetch content from the database and send it to the JSP.";
    }
}
