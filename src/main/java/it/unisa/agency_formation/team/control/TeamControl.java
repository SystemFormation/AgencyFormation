package it.unisa.agency_formation.team.control;

import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.autenticazione.domain.RuoliUtenti;
import it.unisa.agency_formation.autenticazione.domain.Utente;
import it.unisa.agency_formation.autenticazione.manager.AutenticazioneManagerImpl;
import it.unisa.agency_formation.team.domain.Team;
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

@WebServlet("/TeamControl")
public class TeamControl extends HttpServlet {
    TeamManagerImpl teamManager = new TeamManagerImpl();
    AutenticazioneManagerImpl aut2= new AutenticazioneManagerImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        Utente user = (Utente) req.getSession().getAttribute("user");
        if (user.getRole() == RuoliUtenti.TM) { //sei tm
            try {

                ArrayList<Dipendente> listaDipsUsers = teamManager.recuperaDipendentiDelTeam();
                ArrayList<Team> list = teamManager.visualizzaTeams(user.getId());
                req.setAttribute("listDip", listaDipsUsers);
                req.setAttribute("listTeam", list);
                resp.getWriter().write("1");
                dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Team.jsp");
                dispatcher.forward(req, resp);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (user.getRole() == RuoliUtenti.HR) {
            try {
                ArrayList<Dipendente> listaDip = aut2.getTuttiDipendenti();
                ArrayList<Team> list = teamManager.visualizzaTuttiTeam();

                req.setAttribute("listTeam", list);
                req.setAttribute("listDip", listaDip);
                resp.getWriter().write("2");
                dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Team.jsp");
                dispatcher.forward(req, resp);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            resp.getWriter().write("3");
           /* dispatcher = req.getServletContext().getRequestDispatcher("/static/Error.html");
            dispatcher.forward(req, resp);*/
            resp.sendRedirect("/static/Error.html");
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
