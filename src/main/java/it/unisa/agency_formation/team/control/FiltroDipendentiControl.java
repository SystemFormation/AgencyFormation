package it.unisa.agency_formation.team.control;

import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.autenticazione.domain.RuoliUtenti;
import it.unisa.agency_formation.autenticazione.domain.StatiDipendenti;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.autenticazione.manager.AutenticazioneManager;
import it.unisa.agency_formation.autenticazione.manager.AutenticazioneManagerImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/FitroDipendentiControl")
public class FiltroDipendentiControl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente user = (Utente) request.getSession().getAttribute("user");
        if (user != null && user.getRole() == RuoliUtenti.TM) {
            String filtro = request.getParameter("stato");
            RequestDispatcher dispatcher;
            /*a seconda del filtro riempio l'arraylist di dipendeni con lo stato richiesto*/
            if (filtro.equalsIgnoreCase("disponibili")) {
                try {
                    ArrayList<Dipendente> dipendenti = getDipendetiByStatoFromManager(StatiDipendenti.DISPONIBILE);
                    request.setAttribute("dipendenti", dipendenti);
                    dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Dipendenti.jsp");
                    dispatcher.forward(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (filtro.equalsIgnoreCase("occupati")) {
                try {
                    ArrayList<Dipendente> dipendenti = getDipendetiByStatoFromManager(StatiDipendenti.OCCUPATO);
                    request.setAttribute("dipendenti", dipendenti);
                    dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Dipendenti.jsp");
                    dispatcher.forward(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            response.sendRedirect("./static/Login.html");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public static ArrayList<Dipendente> getDipendetiByStatoFromManager(StatiDipendenti stato) throws SQLException {
        AutenticazioneManager autenticazioneManager = new AutenticazioneManagerImpl();
        return autenticazioneManager.getDipendentiByStato(stato);
    }
}
