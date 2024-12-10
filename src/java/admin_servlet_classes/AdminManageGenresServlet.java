package admin_servlet_classes;

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

@WebServlet(name = "AdminManageGenresServlet", urlPatterns = {"/AdminManageGenresServlet", "/admin/manageGenres"})
public class AdminManageGenresServlet extends HttpServlet {
    private GenreDAO genreDAO;
    
    @Override
    public void init() {
        genreDAO = new GenreDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String searchedGenre = request.getParameter("genre");
        List<Genre> genreList;
        
        if (username != null) {
            if (searchedGenre != null) {
                genreList = genreDAO.getSearchedGenres(searchedGenre);               
            } else {
                genreList = genreDAO.getAllGenres();                  
            }
            
            request.setAttribute("genreList", genreList);
            request.getRequestDispatcher("../adminView/manageGenres.jsp").forward(request, response);
            
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
                    String genreName = request.getParameter("genre");
                    Genre genre = new Genre();
                    genre.setName(genreName);
                    genreDAO.addGenre(genre);
                    actionResponse = "Genre added successfully.";
                }
                
                case "update" -> {
                    int genreId = Integer.parseInt(request.getParameter("genreId"));
                    String genreName = request.getParameter("genre");
                    Genre genre = new Genre();
                    genre.setGenreId(genreId);
                    genre.setName(genreName);
                    genreDAO.updateGenre(genre);
                    actionResponse = "Genre updated successfully.";
                }
                
                case "delete" -> {
                    int genreId = Integer.parseInt(request.getParameter("genreId"));
                    genreDAO.deleteGenre(genreId);
                    actionResponse = "Genre deleted successfully.";
                }
                
                default -> response.sendRedirect("login");
            }
            
            // Redirect the admin to manage genres panel (manageGenres), and display the action response 
            response.sendRedirect("manageGenres");
            
        } else {
            response.sendRedirect("login");
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet that manages genres, including add, view, and delete functionalities.";
    }
}
