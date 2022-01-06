package it.unisa.agency_formation.team.control;

import it.unisa.agency_formation.autenticazione.domain.Dipendente;
import it.unisa.agency_formation.autenticazione.domain.RuoliUtenti;
import it.unisa.agency_formation.autenticazione.domain.StatiDipendenti;
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
    TeamManagerImpl aut = new TeamManagerImpl();
    AutenticazioneManagerImpl aut2= new AutenticazioneManagerImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        Utente d = (Utente) req.getSession().getAttribute("user");
        if (d.getRole() == RuoliUtenti.TM) { //sei tm
            try {
                //ArrayList<Dipendente> listaDip = aut2.getAllEmploye(); //Mettere questo se l'altro non funziona
                ArrayList<Dipendente> listaDipsUsers = aut.retrieveAllDipsTeam(); //<------- Nuovo metodo
                ArrayList<Team> list = aut.viewTeams(d.getId());
                //req.setAttribute("listDip",listaDip);
                req.setAttribute("listDip", listaDipsUsers);
                req.setAttribute("listTeam", list);
                resp.getWriter().write("1");
                dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Team.jsp");
                dispatcher.forward(req, resp);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (d.getRole() == RuoliUtenti.HR) {
            try {
                ArrayList<Dipendente> listaDip = aut2.getAllEmploye();
                ArrayList<Team> list = aut.viewAllTeams();
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
            dispatcher = req.getServletContext().getRequestDispatcher("/static/Error.html");
            dispatcher.forward(req, resp);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
