package it.unisa.agency_formation.formazione.control;

import it.unisa.agency_formation.formazione.manager.FormazioneManagerImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

@WebServlet("/DownloadMaterialeControl")
public class DownloadMaterialeControl extends HttpServlet {
    private static final FormazioneManagerImpl formazione = new FormazioneManagerImpl();
    private static final String directory = System.getProperty("user.home");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idTeam = Integer.parseInt(request.getParameter("idTeam"));
        ServletContext context = request.getServletContext();
        String materiale = null;
        try {
            materiale = formazione.getMaterialeByIdTeam(idTeam);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(!materiale.equalsIgnoreCase(null)) {
            String pathMateriale = directory + materiale;
            File file = new File(pathMateriale);
            FileInputStream fileIn = new FileInputStream(file);

            String mimeType = context.getMimeType(pathMateriale);
            if (mimeType == null) {
                mimeType = "application/octet-stream";
            }
            response.setContentType(mimeType);
            response.setContentLength((int) file.length());

            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename=\"%s\"", file.getName());
            response.setHeader(headerKey, headerValue);
            OutputStream outStream = response.getOutputStream();

            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = fileIn.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }
            fileIn.close();
            outStream.close();
        } else{
            //TODO NESSUN FILE CARICATO
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
