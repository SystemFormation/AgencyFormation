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
        //se scelta = 1 curriculum caricato
        //se scelta = 2 documenti aggiuntivi caricati
        int scelta = Integer.parseInt(request.getParameter("sceltaUpload"));
        File file = new File(pathAbsolute + "\\" + "IdUtente-" + user.getId());
        Candidatura cand = new Candidatura();
        if(scelta==1){
            String stato = "Non Revisionato";
            file.mkdirs();
            Part part = (Part) request.getPart("curriculum");
            part.write(file.getAbsolutePath() + "\\" + part.getSubmittedFileName());
            String cv = pathRelative+"\\"+ "IdUtente-" + user.getId()+"\\"+part.getSubmittedFileName();
            Date date = new Date();
            java.sql.Date data = new java.sql.Date(date.getTime());
            cand.setCv(cv);
            cand.setStato(stato);
            cand.setDataCandidatura(data);
            cand.setIdCandidato(user.getId());
            try {
                reclutamento.uploadCandidatureWithoutDocument(cand);
                request.setAttribute("curriculum",true);
                request.setAttribute("document",false);//i documenti non sono stati ancora caricati
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else if(scelta == 2){
            Part part = (Part) request.getPart("documenti");
            part.write(file.getAbsolutePath() + "\\" + part.getSubmittedFileName());
            String documentiAggiuntivi = pathRelative+"\\"+ "IdUtente-" + user.getId()+"\\"+part.getSubmittedFileName();
            try {
                reclutamento.uploadDocument(documentiAggiuntivi,user.getId());
                request.setAttribute("document",true);//i documenti sono stati caricati
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Profilo.jsp");
        dispatcher.forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
