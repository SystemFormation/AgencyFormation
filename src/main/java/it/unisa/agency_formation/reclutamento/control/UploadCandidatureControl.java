package it.unisa.agency_formation.reclutamento.control;

import it.unisa.agency_formation.autenticazione.domain.RuoliUtenti;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.reclutamento.domain.Candidatura;
import it.unisa.agency_formation.reclutamento.domain.StatiCandidatura;
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
import java.util.Date;

@WebServlet("/UploadCandidatureControl")
@MultipartConfig
public class UploadCandidatureControl extends HttpServlet {

    private static final String pathRelative = "\\AgencyFormationFile\\Candidature\\";
    private static final String pathAbsolute = System.getProperty("user.home") + pathRelative;
    private static final int MAXDIM = 83886080; //10MB
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente user = (Utente) request.getSession().getAttribute("user");
        if (user != null && user.getRole() == RuoliUtenti.CANDIDATO) {
            if (request.getParameter("sceltaUpload") == null) {
                try {
                    Candidatura cand = getCandidaturaByIdFromManager(user.getId());
                    request.setAttribute("candidatura", cand);
                    RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/static/Upload.jsp");
                    dispatcher.forward(request, response);
                    return;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            //se scelta = 1 curriculum caricato
            //se scelta = 2 documenti aggiuntivi caricati
            int scelta = Integer.parseInt(request.getParameter("sceltaUpload"));
            File file = new File(pathAbsolute + "\\" + "IdUtente-" + user.getId());
            if (scelta == 1) {
                Part curriculum = request.getPart("curriculum");
                if (curriculum.getSize() > MAXDIM) {
                    //TODO ERROR FOR SIZE OF FILE MORE
                    response.getWriter().write("1"); //file troppo grande
                } else {
                    Candidatura cand = new Candidatura();
                    //TODO RISULTATO NON GESTITO
                    file.mkdirs();
                    curriculum.write(file.getAbsolutePath() + "\\" + curriculum.getSubmittedFileName());
                    String cv = pathRelative + "\\" + "IdUtente-" + user.getId() + "\\" + curriculum.getSubmittedFileName();
                    Date date = new Date();
                    java.sql.Date data = new java.sql.Date(date.getTime());
                    cand.setCurriculum(cv);
                    cand.setStato(StatiCandidatura.NonRevisionato);
                    cand.setDataCandidatura(data);
                    cand.setIdCandidato(user.getId());
                    try {
                        uploadCandidatureFromManager(cand);
                        request.setAttribute("candidatura", cand);

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            } else if (scelta == 2) {
                Part documenti = request.getPart("documenti");
                if (documenti.getSize() > MAXDIM) {
                    //TODO ERROR FOR SIZE OF FILE MORE
                } else {
                    Candidatura cand = new Candidatura();
                    documenti.write(file.getAbsolutePath() + "\\" + documenti.getSubmittedFileName());
                    String documentiAggiuntivi = pathRelative + "\\" + "IdUtente-" + user.getId() + "\\" + documenti.getSubmittedFileName();
                    cand.setIdCandidato(user.getId());
                    cand.setDocumentiAggiuntivi(documentiAggiuntivi);
                    try {
                        //TODO return non gestito
                        uploadCandidatureFromManager(cand);
                        request.setAttribute("candidatura", cand);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            try {
                Candidatura candidatura = getCandidaturafromManager(user.getId());
                request.setAttribute("candidatura", candidatura);
                RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/HomeCandidato.jsp");
                dispatcher.forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            response.sendRedirect("./static/Login.html");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public static Candidatura getCandidaturafromManager(int idCandidato) throws SQLException {
        ReclutamentoManager reclutamentoManager = new ReclutamentoManagerImpl();
        return reclutamentoManager.getCandidaturaById(idCandidato);
    }

    public static boolean uploadCandidatureFromManager(Candidatura candidatura) throws SQLException {
        ReclutamentoManager reclutamentoManager = new ReclutamentoManagerImpl();
        return reclutamentoManager.caricaCandidatura(candidatura);
    }

    public static Candidatura getCandidaturaByIdFromManager(int idCandidato) throws SQLException {
        ReclutamentoManager reclutamentoManager = new ReclutamentoManagerImpl();
        return reclutamentoManager.getCandidaturaById(idCandidato);
    }
}
