package it.unisa.agency_formation.autenticazione.control;

import it.unisa.agency_formation.autenticazione.DAO.DipendenteDAO;
import it.unisa.agency_formation.autenticazione.DAO.UtenteDAO;
import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.autenticazione.domain.Utente;
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

@WebServlet("/UtentiControl")
public class UtentiControl extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UtenteDAO dao = new UtenteDAO();
        DipendenteDAO dao2 = new DipendenteDAO();
        AutenticazioneManagerImpl aut = new AutenticazioneManagerImpl(dao);
        AutenticazioneManagerImpl aut2 = new AutenticazioneManagerImpl(dao2);
        RequestDispatcher dispatcher;
        try {
            ArrayList<Dipendente> listaDipendenti = aut2.getAllEmploye();
            ArrayList<Utente> listaUtenti = aut.getCandidatesDip();
            request.setAttribute("dipendenti",listaDipendenti);
            request.setAttribute("utenti",listaUtenti);
            dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/DipendentiDispo.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}