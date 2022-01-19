package it.unisa.agency_formation.formazione.control;

import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.autenticazione.domain.RuoliUtenti;
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
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente user = (Utente) request.getSession().getAttribute("user");
        if (user != null && user.getRole() == RuoliUtenti.DIPENDENTE) {
            try {
                Dipendente dipendente = getDipendenteFromManager(user.getId());
                if (dipendente == null) {
                    response.getWriter().write("1"); //errore retrieve dipendente
                    //TODO da controllare la descrizione err1
                    String err1 = "dipendente non abilitato";
                    response.sendRedirect("./static/Error.jsp?descrizione=" + err1);
                } else {
                    int idTeam = dipendente.getTeam().getIdTeam();
                    Documento documento = getDocumentoFromManager(idTeam);
                    if (documento != null) {
                        response.getWriter().write("2"); //il documento esiste
                    } else {
                        response.getWriter().write("3"); //il documento non esiste
                        String err2 = "documento inesistente";
                        response.sendRedirect("./static/Error.jsp?descrizione=" + err2);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            response.getWriter().write("4"); // user null o ruolo non corretto
            response.sendRedirect("./static/Login.html");
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public static Dipendente getDipendenteFromManager(int idUtente) throws SQLException {
        AutenticazioneManager autenticazioneManager = new AutenticazioneManagerImpl();
        return autenticazioneManager.getDipendente(idUtente);
    }

    public static Documento getDocumentoFromManager(int idTeam) throws SQLException {
        FormazioneManager formazioneManager = new FormazioneManagerImpl();
        return formazioneManager.getMaterialeByIdTeam(idTeam);
    }
}
