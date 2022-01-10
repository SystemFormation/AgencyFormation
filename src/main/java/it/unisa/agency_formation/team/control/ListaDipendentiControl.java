package it.unisa.agency_formation.team.control;

import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.autenticazione.domain.RuoliUtenti;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.autenticazione.manager.AutenticazioneManager;
import it.unisa.agency_formation.autenticazione.manager.AutenticazioneManagerImpl;
import it.unisa.agency_formation.formazione.domain.Skill;
import it.unisa.agency_formation.formazione.manager.FormazioneManager;
import it.unisa.agency_formation.formazione.manager.FormazioneManagerImpl;
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

@WebServlet("/ListaDipendentiControl")
public class ListaDipendentiControl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Utente user = (Utente) req.getSession().getAttribute("user");
        if (user != null && user.getRole() == RuoliUtenti.TM) {
            RequestDispatcher dispatcher;
            /*visualizzo tutti i dipendenti*/
            try {
                ArrayList<Dipendente> dipendenti = getTuttiDipendentiFromManager();
                for (int i = 0; i < dipendenti.size(); i++) {
                    ArrayList<Skill> skills = new ArrayList<>();
                    if (dipendenti.get(i).getTeam() != null && dipendenti.get(i).getTeam().getIdTeam() > 0) {
                        dipendenti.get(i).setTeam(getTeamIdFromManager(dipendenti.get(i).getTeam().getIdTeam()));
                    }
                    if (getSkillDipendenteFromManager(dipendenti.get(i).getIdDipendente()) != null && getSkillDipendenteFromManager(dipendenti.get(i).getIdDipendente()).size() > 0) {
                        skills = getSkillDipendenteFromManager(dipendenti.get(i).getIdDipendente());
                        dipendenti.get(i).setSkills(skills);
                    }
                }
                req.setAttribute("dipendenti", dipendenti);
                dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/VisualizzaDipendenti.jsp");
                dispatcher.forward(req, resp);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            resp.sendRedirect("./static/Login.html");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public static ArrayList<Team> viewAllTeamsFromManager() throws SQLException {
        TeamManager teamManager = new TeamManagerImpl();
        return teamManager.visualizzaTuttiTeams();
    }

    public static Team getTeamIdFromManager(int idTeam) throws SQLException {
        TeamManager teamManager = new TeamManagerImpl();
        return teamManager.getTeamById(idTeam);
    }

    public static ArrayList<Skill> getSkillDipendenteFromManager(int idDip) throws SQLException {
        FormazioneManager formazioneManager = new FormazioneManagerImpl();
        return formazioneManager.recuperoSkillConIdDipendete(idDip);
    }
    public static ArrayList<Dipendente> getTuttiDipendentiFromManager() throws SQLException{
        AutenticazioneManager autenticazioneManager = new AutenticazioneManagerImpl();
        return autenticazioneManager.getTuttiDipendenti();
    }

}
