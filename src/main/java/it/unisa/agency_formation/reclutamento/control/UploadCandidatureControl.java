package it.unisa.agency_formation.reclutamento.control;

import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.reclutamento.DAO.CandidaturaDAO;
import it.unisa.agency_formation.reclutamento.domain.Candidatura;
import it.unisa.agency_formation.reclutamento.manager.ReclutamentoManager;
import it.unisa.agency_formation.reclutamento.manager.ReclutamentoManagerImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

@WebServlet("/UploadCandidatureControl")
@MultipartConfig
public class UploadCandidatureControl extends HttpServlet {
    private String pathRelative = "\\AgencyFormationFile\\Candidature\\";
    private String pathAbsolute = System.getProperty("user.home") + pathRelative;
    private CandidaturaDAO dao = new CandidaturaDAO();
    private ReclutamentoManager reclutamento = new ReclutamentoManagerImpl(dao);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente user = (Utente) request.getSession().getAttribute("user");
        File file = new File(pathAbsolute + "\\" + "IdUtente-" + user.getId());
        Candidatura cand = new Candidatura();
        String cv = null;
        String documentiAggiuntivi = null;
        String stato = "Non Revisionato";
        file.mkdirs();
        ArrayList<Part> parts = (ArrayList<Part>) request.getParts();
        for (Part part : parts) {
            part.write(file.getAbsolutePath() + "\\" + part.getSubmittedFileName());
            String fileName = part.getSubmittedFileName();

        }
        Date date = new Date();
        java.sql.Date data = new java.sql.Date(date.getTime());
        cand.setCv(cv);
        cand.setDocumentiAggiuntivi(documentiAggiuntivi);

        cand.setStato(stato);
        cand.setDataCandidatura(data);
        cand.setIdCandidato(user.getId());
        try {
            reclutamento.uploadCandidature(cand);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(new File(pathAbsolute + "\\" + "IdUtente-" + user.getId()).list().length<=2){
            request.setAttribute("esito",true);
        }else{
            request.setAttribute("esito",false);
        }
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Profilo.jsp");
        dispatcher.forward(request,response);

    }
    /*
if (fileName.substring(0,fileName.indexOf(".")).equalsIgnoreCase("cv")) {
        cv = pathRelative +"IdUtente-" + user.getId()+"\\"+ part.getSubmittedFileName();
    }
            if (fileName.substring(0,fileName.indexOf(".")).equalsIgnoreCase("attestato")) {
        attestati = pathRelative + "IdUtente-" + user.getId()+"\\"+part.getSubmittedFileName();
    }
            if (fileName.substring(0,fileName.indexOf(".")).equalsIgnoreCase("certificazione")) {
        certificazioni = pathRelative + "IdUtente-" + user.getId()+"\\"+part.getSubmittedFileName();
    }

     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
