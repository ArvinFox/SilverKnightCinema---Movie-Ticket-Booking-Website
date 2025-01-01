package servlet_classes;

import dao_classes.MovieDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model_classes.Movie;
import model_classes.Promotion;
import dao_classes.PromotionDAO;


@WebServlet(name = "IndexServlet", urlPatterns = {"/IndexServlet","/home"})
public class IndexServlet extends HttpServlet {
    private MovieDAO movieDAO;
    private PromotionDAO promotionDao;
    
    @Override
    public void init() {
        movieDAO = new MovieDAO();
        promotionDao = new PromotionDAO(); 
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Movie> nowShowingMovies = movieDAO.getNowShowingMovies();
        List<Movie> comingSoonMovies = movieDAO.getComingSoonMovies();
        List<Promotion> activePromotions =  promotionDao.getAllActivePromotions();
        request.setAttribute("activePromotions", activePromotions);
        request.setAttribute("nowShowingMovies", nowShowingMovies);
        request.setAttribute("comingSoonMovies", comingSoonMovies);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {}

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
