package it.unisa.agency_formation.reclutamento.control;

import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.autenticazione.domain.RuoliUtenti;
import it.unisa.agency_formation.autenticazione.domain.StatiDipendenti;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.autenticazione.manager.AutenticazioneManager;
import it.unisa.agency_formation.autenticazione.manager.AutenticazioneManagerImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
@WebServlet("/CandidatoAssuntoControl")
public class CandidatoAssuntoControl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente user = (Utente) request.getSession().getAttribute("user");
        if (user == null || user.getRole() != RuoliUtenti.CANDIDATO) {
            response.sendRedirect("./static/Login.html");
        } else {
            try {
                int idDipendente = user.getId();
                    int annoNascita = Integer.parseInt(request.getParameter("annoDipendente"));
                    String residenza = request.getParameter("residenzaDipendente");
                    String telefono = request.getParameter("telefonoDipendente");
                    Dipendente dipendente = new Dipendente();
                    dipendente.setIdDipendente(idDipendente);
                    dipendente.setAnnoNascita(annoNascita);
                    dipendente.setResidenza(residenza);
                    dipendente.setTelefono(telefono);
                    dipendente.setStato(StatiDipendenti.DISPONIBILE);
                    boolean esito = assumiCandidato(dipendente);
                    if (!esito) {
                        response.getWriter().write("2"); //errore assunzione
                        response.sendRedirect("/WEB-INF/jsp/HomeCandidato.jsp");
                    } else {
                        request.getSession().invalidate();
                        response.sendRedirect("./static/Login.html");
                    }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }


    public static boolean assumiCandidato(Dipendente dipendente) throws SQLException {
      AutenticazioneManager autenticazioneManager = new AutenticazioneManagerImpl();
      return autenticazioneManager.assumiCandidato(dipendente);
    }
}
