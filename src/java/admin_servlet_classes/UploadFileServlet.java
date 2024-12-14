package admin_servlet_classes;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Paths;

@WebServlet(name = "UploadFileServlet", urlPatterns = {"/UploadFileServlet", "/upload"})
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2, // 2MB
    maxFileSize = 1024 * 1024 * 10, // 10MB
    maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class UploadFileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Retrieve the 'type' parameter to determine the upload directory
        String type = request.getParameter("type");
        String uploadDir;
        
        // Determine the upload directory based on the 'type' parameter
        if ("movie".equalsIgnoreCase(type)) {
            uploadDir = "assets/images/posters";
        } else if ("promotion".equalsIgnoreCase(type)) {
            uploadDir = "assets/images/promotions";
        } else if ("foodItem".equalsIgnoreCase(type)) {
            uploadDir = "assets/images/foods";
        } else if ("hall".equalsIgnoreCase(type)) {
            uploadDir = "assets/images/halls";
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().print("Invalid type parameter.");
            return;
        }
        
        // Retrieve the uploaded file part
        Part filePart = request.getPart("poster");
        if (filePart != null && filePart.getSize() > 0) {
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();  // Get the original file name

            // Get the absolute path of the upload directory from the web application root
            String uploadPath = getServletContext().getRealPath("") + File.separator + uploadDir;

            // Create the directory if it doesn't exist
            File uploadDirectory = new File(uploadPath);
            if (!uploadDirectory.exists()) {
                uploadDirectory.mkdirs();  // Creates all non-existing parent directories as well
            }

            // Create the file object to write the uploaded file
            File file = new File(uploadPath + File.separator + fileName);
            filePart.write(file.getAbsolutePath());  // Write the file to disk

            response.getWriter().print(uploadDir + "/" + fileName);
            System.out.println(uploadDir + "/" + fileName);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().print("No file provided or file size is zero.");
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet used to upload posters.";
    }
}
