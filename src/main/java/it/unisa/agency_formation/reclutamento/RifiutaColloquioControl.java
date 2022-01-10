package it.unisa.agency_formation.reclutamento;

import it.unisa.agency_formation.autenticazione.domain.RuoliUtenti;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.reclutamento.domain.Candidatura;
import it.unisa.agency_formation.reclutamento.domain.Colloquio;
import it.unisa.agency_formation.reclutamento.manager.ReclutamentoManager;
import it.unisa.agency_formation.reclutamento.manager.ReclutamentoManagerImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import static java.util.Objects.requireNonNull;


public class RifiutaColloquioControl extends HttpServlet {
    private String path ="\\AgencyFormationFile\\Candidature\\";
    private String pathAbsolute = System.getProperty("user.home") + path;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws SecurityException, IOException{
        Utente user = (Utente) request.getSession().getAttribute("user");
        if(user!=null && user.getRole()== RuoliUtenti.HR){
            int idCandidato = Integer.parseInt(request.getParameter("idCandidato"));
            try {
                Colloquio colloquio = getColloquio(idCandidato);
                File toDelete = new File(pathAbsolute + "IdUtente-" + colloquio.getIdCandidato());
                delete(toDelete);

                if (rejectCandidatura(colloquio.getIdColloquio(), user.getId())) {

                    response.getWriter().write("1");

                } else {


                    response.getWriter().write("2");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            response.sendRedirect("./static/Login.html");
        }
    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
    public static Candidatura getColloquio(int idCandidato) throws SQLException{
        ReclutamentoManager reclutamentoManager = new ReclutamentoManagerImpl();
        return reclutamentoManager.getCandidaturaById(idCandidato);
    }
    public static boolean rejectCandidatura(int idCandidatura, int idHR) throws SQLException{
        ReclutamentoManager reclutamentoManager = new ReclutamentoManagerImpl();
        return reclutamentoManager.rifiutaCandidature(idCandidatura, idHR);
    }
    public static void delete(File file){
        for (File subFile : requireNonNull(file.listFiles())) {
            if(subFile.isDirectory()) {
                delete(subFile);
            } else {
                subFile.delete();
            }
        }
        file.delete();
    }
}