package admin_servlet_classes;

import model_classes.Language;
import dao_classes.LanguageDAO;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

@WebServlet(name = "AdminManageLanguagesServlet", urlPatterns = {"/AdminManageLanguagesServlet", "/admin/manageLanguages"})
public class AdminManageLanguagesServlet extends HttpServlet {
    private LanguageDAO languageDAO;
    
    @Override
    public void init() {
        languageDAO = new LanguageDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String searchedLanguage = request.getParameter("language");
        List<Language> languageList;
        
        if (username != null) {
            if (searchedLanguage != null) {
                languageList = languageDAO.getSearchedLanguages(searchedLanguage);               
            } else {
                languageList = languageDAO.getAllLanguages();                  
            }
            
            request.setAttribute("languageList", languageList);
            request.getRequestDispatcher("../adminView/manageLanguages.jsp").forward(request, response);
            
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
                    String languageName = request.getParameter("language");
                    Language language = new Language();
                    language.setLanguage(languageName);
                    languageDAO.addLanguage(language);
                    actionResponse = "Language added successfully.";
                }
                
                case "update" -> {
                    int languageId = Integer.parseInt(request.getParameter("languageId"));
                    String languageName = request.getParameter("language");
                    Language language = new Language();
                    language.setLanguageId(languageId);
                    language.setLanguage(languageName);
                    languageDAO.updateLanguage(language);
                    actionResponse = "Language updated successfully.";
                }
                
                case "delete" -> {
                    int languageId = Integer.parseInt(request.getParameter("languageId"));
                    languageDAO.deleteLanguage(languageId);
                    actionResponse = "Language deleted successfully.";
                }
                
                default -> response.sendRedirect("login");
            }
            
            // Redirect the admin to manage languages panel (manageLanguages), and display the action response 
            response.sendRedirect("manageLanguages");
            
        } else {
            response.sendRedirect("login");
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet that manages languages, including add, view, and delete functionalities.";
    }
}
