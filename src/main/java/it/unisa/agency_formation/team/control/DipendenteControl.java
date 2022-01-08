package it.unisa.agency_formation.team.control;

import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.autenticazione.manager.AutenticazioneManagerImpl;
import it.unisa.agency_formation.team.domain.Team;
import it.unisa.agency_formation.team.manager.TeamManager;
import it.unisa.agency_formation.team.manager.TeamManagerImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/DipendenteControl")
public class DipendenteControl extends HttpServlet {
    private AutenticazioneManagerImpl aut= new AutenticazioneManagerImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        int idTeam = 0; //(int) req.getAttribute("idTeam");
        /*visualizzo tutti i dipendenti*/
            try {
                ArrayList<Dipendente> dipendenti= aut.getTuttiDipendenti();
                ArrayList<Team> team = viewAllTeamsFromManager();
                req.setAttribute("teams", team);
                req.setAttribute("dipendenti", dipendenti);
                req.setAttribute("idTeam",idTeam);
                dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/VisualizzaDipendenti.jsp");
                dispatcher.forward(req,resp);
            } catch (SQLException e) {
                e.printStackTrace();
            }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
    public static ArrayList<Team> viewAllTeamsFromManager()throws SQLException{
        TeamManager teamManager = new TeamManagerImpl();
        return teamManager.visualizzaTuttiTeams();
    }

}
