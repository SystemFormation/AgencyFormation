package it.unisa.agency_formation.autenticazione.control;

import it.unisa.agency_formation.autenticazione.domain.Dipendente;
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

//TODO-r: il nome di questa servlet non mi è chiaro. cosa fa? Scegliete un nome migliore. Anche perché rendirizza a DipendentiDispo.jsp che penso siano i dipendenti disponibili, o no?
@WebServlet("/UtentiControl")
public class UtentiControl extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        try {
            ArrayList<Dipendente> listaDipendenti = getAllEmployeFromManager();
            if(listaDipendenti==null || listaDipendenti.size()<1){
                response.getWriter().write("1");//errore retrieve getDipendenti
            }
            ArrayList<Utente> listaUtenti = getCandidatesDipFromManager();
            if(listaUtenti==null || listaUtenti.size()<1){
                response.getWriter().write("2");//errore retrieve getCandidatesDip
            }

            request.setAttribute("dipendenti",listaDipendenti);
            request.setAttribute("utenti",listaUtenti);
            response.getWriter().write("3");//retrieves ok
            dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/Dipendenti.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
    public static ArrayList<Dipendente> getAllEmployeFromManager() throws SQLException{
        AutenticazioneManager autenticazioneManager = new AutenticazioneManagerImpl();
        return autenticazioneManager.getTuttiDipendenti();
    }
    public static ArrayList<Utente> getCandidatesDipFromManager() throws SQLException{
        AutenticazioneManager autenticazioneManager = new AutenticazioneManagerImpl();
        return autenticazioneManager.getDipendentiByRuolo();
    }
}