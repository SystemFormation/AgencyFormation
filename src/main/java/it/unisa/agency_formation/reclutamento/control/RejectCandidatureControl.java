package it.unisa.agency_formation.reclutamento.control;

import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.reclutamento.domain.Candidatura;
import it.unisa.agency_formation.reclutamento.manager.ReclutamentoManager;
import it.unisa.agency_formation.reclutamento.manager.ReclutamentoManagerImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


@WebServlet("/RejectCandidatureControl")
public class RejectCandidatureControl extends HttpServlet {
    private String path ="\\AgencyFormationFile\\Candidature\\";
    private String pathAbsolute = System.getProperty("user.home") + path;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente user = (Utente) request.getSession().getAttribute("user");
        int idCandidato = Integer.parseInt(request.getParameter("idCandidato"));
        try {
            Candidatura candidatura = getCandidatura(idCandidato);
            File toDelete = new File(pathAbsolute+"IdUtente-"+candidatura.getIdCandidato());
            delete(toDelete);

            if(rejectCandidatura(candidatura.getIdCandidatura(),user.getId())){
               response.getWriter().write("1"); //rifiuto ok

            }else{
                //TODO errore nel rifiutare la candidatura
                response.getWriter().write("2"); //rifiuto non avvenuto
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }


    public static Candidatura getCandidatura(int idCandidato) throws SQLException{
        ReclutamentoManager reclutamentoManager = new ReclutamentoManagerImpl();
        return reclutamentoManager.getCandidaturaById(idCandidato);
    }
    public static boolean rejectCandidatura(int idCandidatura, int idHR) throws SQLException{
        ReclutamentoManager reclutamentoManager = new ReclutamentoManagerImpl();
        return reclutamentoManager.rifiutaCandidature(idCandidatura, idHR);
    }
    public static void delete(File file){
        for (File subFile : file.listFiles()) {
            if(subFile.isDirectory()) {
                delete(subFile);
            } else {
                subFile.delete();
            }
        }
        file.delete();
    }
}
