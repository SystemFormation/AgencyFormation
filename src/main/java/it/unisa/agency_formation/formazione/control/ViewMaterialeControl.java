package it.unisa.agency_formation.formazione.control;

import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.autenticazione.manager.AutenticazioneManager;
import it.unisa.agency_formation.autenticazione.manager.AutenticazioneManagerImpl;
import it.unisa.agency_formation.formazione.domain.Documento;
import it.unisa.agency_formation.formazione.manager.FormazioneManager;
import it.unisa.agency_formation.formazione.manager.FormazioneManagerImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/ViewMaterialeControl")
public class ViewMaterialeControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente user = (Utente) request.getSession().getAttribute("user");
        try {
            Dipendente dipendente = getDipendentefromManager(user.getId());
            if(dipendente==null){
                response.getWriter().write("1");//errore retrieve dipendente
            }
            int idTeam = dipendente.getTeam().getIdTeam();
            Documento documento = getDocumentofromManager(idTeam);
            if(documento!=null){
                response.getWriter().write("2");//il documento esiste
            }
            else{
                response.getWriter().write("3");//il documento non esiste
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    public static Dipendente getDipendentefromManager(int idUtente) throws SQLException {
        AutenticazioneManager autenticazioneManager = new AutenticazioneManagerImpl();
        return autenticazioneManager.getDatiDipendente(idUtente);
    }
    public static Documento getDocumentofromManager(int idTeam) throws SQLException {
        FormazioneManager formazioneManager = new FormazioneManagerImpl();
        return formazioneManager.getMaterialeByIdTeam(idTeam);
    }
}
