package it.unisa.agency_formation.team.control;

import it.unisa.agency_formation.autenticazione.DAO.DipendenteDAO;
import it.unisa.agency_formation.autenticazione.domain.Dipendente;
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
    private final DipendenteDAO dao = new DipendenteDAO();
    private final AutenticazioneManagerImpl aut = new AutenticazioneManagerImpl(dao);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filtro = request.getParameter("stato");
        RequestDispatcher dispatcher;
        boolean state = true;
        /*a seconda del filtro riempio l'arraylist di dipendeni con lo stato richiesto*/
        if (filtro.equalsIgnoreCase("disponibili")) state = true;
        else if (filtro.equalsIgnoreCase("occupati")) state = false;

        try {
            ArrayList<Dipendente> dipendenti = aut.getEmployeByState(state);
            request.setAttribute("dipendenti", dipendenti);
            dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Dipendenti.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
