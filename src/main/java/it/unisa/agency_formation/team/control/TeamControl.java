package it.unisa.agency_formation.team.control;

import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.team.DAO.TeamDAO;
import it.unisa.agency_formation.team.domain.Team;
import it.unisa.agency_formation.team.manager.TeamManagerImpl;
import it.unisa.agency_formation.utils.Check;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/TeamControl")
public class TeamControl extends HttpServlet {
    private TeamDAO dao = new TeamDAO();
    private TeamManagerImpl team = new TeamManagerImpl(dao);

    //da raffinare
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TeamManagerImpl teamManager = new TeamManagerImpl(dao);
        Team team = new Team();

        RequestDispatcher dispatcher;
        String action = req.getParameter("action");
        Utente d = (Utente) req.getSession().getAttribute("user");
        int z = d.getId();
        try {
            if (action == null) {
                dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Team.jsp");
                dispatcher.forward(req, resp);
            } else if (action.equalsIgnoreCase("crea")) {
                String nomeProgetto = req.getParameter("lname");
                int numeroDipendenti = Integer.parseInt(req.getParameter("quantity"));
                String nomeTeam = req.getParameter("fname");
                String descrizione = req.getParameter("teamDescr");
                if (d.getRole() == 3) { //ruolo dell'utente è uguale a 3 può salvare
                    team.setNomeProgetto(nomeProgetto);
                    team.setDescrizione(descrizione);
                    team.setNomeTeam(nomeTeam);
                    team.setNumeroDipendenti(numeroDipendenti);
                    teamManager.createTeam(team,z);

                    dispatcher = req.getServletContext().getRequestDispatcher("/UtenteControl");
                    dispatcher.forward(req, resp);
                }


            } else if(action.equalsIgnoreCase("aggiungi")){


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        @Override
        protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            doGet(req, resp);
        }
}
