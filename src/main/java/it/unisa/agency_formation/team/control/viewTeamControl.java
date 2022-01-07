package it.unisa.agency_formation.team.control;

import it.unisa.agency_formation.autenticazione.domain.RuoliUtenti;
import it.unisa.agency_formation.autenticazione.domain.Utente;
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

@WebServlet("/ViewTeamControl")
public class viewTeamControl extends HttpServlet {
    private static final TeamManagerImpl tman = new TeamManagerImpl();
    private static final AutenticazioneManagerImpl aut= new AutenticazioneManagerImpl();
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente user = (Utente) request.getSession().getAttribute("user");

        if(user.getRole() == RuoliUtenti.HR) { //sei HR
            try {
                ArrayList<Team> teams = viewAllTeamsFromManager();
                if(teams!=null && teams.size()>0){
                    request.setAttribute("listaTeams", teams);
                    response.getWriter().write("1");//ci sono team
                    RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Candidati.jsp");
                    dispatcher.forward(request, response);
                } else{
                    response.getWriter().write("2"); //non ci sono team
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            //ritorna errore
            response.sendRedirect("/static/Errore.html");
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
    public static ArrayList<Team> viewAllTeamsFromManager()throws SQLException{
        TeamManager teamManager = new TeamManagerImpl();
        return teamManager.visualizzaTuttiTeams();
    }
}
