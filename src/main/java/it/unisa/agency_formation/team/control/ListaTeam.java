package it.unisa.agency_formation.team.control;

import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.autenticazione.domain.RuoliUtenti;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.autenticazione.manager.AutenticazioneManager;
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

@WebServlet("/ListaTeam")
public class ListaTeam extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        Utente user = (Utente) req.getSession().getAttribute("user");
        if (user != null && user.getRole() == RuoliUtenti.TM) { //sei tm
            try {
                ArrayList<Dipendente> listaDipsUsers = recuperoDipendetiDiUnTeamFromManager();
                ArrayList<Team> teams = visualizzaTeamOfTMFromManager(user.getId());
                req.setAttribute("listDip", listaDipsUsers);
                req.setAttribute("listTeam", teams);
                resp.getWriter().write("1");
                dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/ListaTeamTM.jsp");
                dispatcher.forward(req, resp);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (user != null && user.getRole() == RuoliUtenti.HR) {
            try {
                ArrayList<Dipendente> listaDip = getAllDipendentiFromManager();
                ArrayList<Team> team = visuallizzaTeamsForHRFromManager();
                req.setAttribute("listTeam", team);
                req.setAttribute("listDip", listaDip);
                resp.getWriter().write("2");
                dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/ListaTeamHR.jsp");
                dispatcher.forward(req, resp);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            resp.getWriter().write("3");
            resp.sendRedirect("./static/Login.html");
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public static ArrayList<Dipendente> recuperoDipendetiDiUnTeamFromManager() throws SQLException {
        TeamManager teamManager = new TeamManagerImpl();
        return teamManager.recuperaDipendentiDelTeam();
    }

    public static ArrayList<Team> visualizzaTeamOfTMFromManager(int idTM) throws SQLException {
        TeamManager teamManager = new TeamManagerImpl();
        return teamManager.visualizzaTeams(idTM);
    }

    public static ArrayList<Dipendente> getAllDipendentiFromManager() throws SQLException {
        AutenticazioneManager autenticazioneManager = new AutenticazioneManagerImpl();
        return autenticazioneManager.getTuttiDipendenti();
    }

    public static ArrayList<Team> visuallizzaTeamsForHRFromManager() throws SQLException {
        TeamManager teamManager = new TeamManagerImpl();
        return teamManager.visualizzaTuttiTeams();
    }
}
